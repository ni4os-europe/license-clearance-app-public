package gr.uoa.di.madgik.lcapp.pdf;


import com.lowagie.text.DocumentException;
import gr.uoa.di.madgik.lcapp.controller.clearance.ClearanceController;
import gr.uoa.di.madgik.lcapp.model.QuestionPdfInfo;
import gr.uoa.di.madgik.lcapp.model.SectionPdfInfo;
import gr.uoa.di.madgik.lcapp.model.clearance.ClearanceSubmission;
import gr.uoa.di.madgik.lcapp.model.clearance.Response;
import gr.uoa.di.madgik.lcapp.model.form.ClearanceSchema;
import gr.uoa.di.madgik.lcapp.model.form.Question;
import gr.uoa.di.madgik.lcapp.model.form.Section;
import gr.uoa.di.madgik.lcapp.security.UserPrincipal;
import gr.uoa.di.madgik.lcapp.service.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

@Service
public class PdfService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    AuthService authService;

    @Autowired
    VocabularyService vocabularyService;

    @Autowired
    ClearanceSchemaService clearanceSchemaService;

    @Autowired
    UserService userService;


    @Value("${app.pdf.base-path}")
    private String reportsBaseDirectory;

    @PostConstruct
    public void templateInit(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding("UTF-8");

        templateEngine.setTemplateResolver(templateResolver);
    }

    public byte[] generatePdf(Response response) throws EntityNotFoundException, DocumentException, IOException {

        ClearanceSubmission data = response.getClearanceSubmission();

        if(!data.getWorkflow().equals("resourceDriven") && !data.getWorkflow().equals("licenseDriven")) {
            throw new IOException(String.format("Unknown workflow ('%s') when trying to render HTML template for the PDF report", data.getWorkflow()));
        }

        ClearanceSchema schema = clearanceSchemaService.getSchema(data.getWorkflow()).orElseThrow(EntityNotFoundException::new);

        // Map term ids to term names
        vocabularyService.replaceTermIdsWithNames(data, schema);

        // Prepare the data

        LinkedHashMap<String, SectionPdfInfo> pdfData = new LinkedHashMap<>();

        Map<String, List<Map<String, Object>>> sections = data.getSections();

        // Sort the sections and questions
        schema.getSections().sort(Comparator.comparing(Section::getId));
        schema.getQuestions().sort(Comparator.comparing(Question::getId));

        System.out.println("BUILDING PDF DATA");
        // Iterate over sections of schema and populate the first level of SectionPdfInfo's
        for(Section s: schema.getSections()) {
            ArrayList<Map<String, QuestionPdfInfo>> questions = new ArrayList<>();

            int numMaps = sections.get(s.getId()).size();
            for(int i = 0; i < numMaps; i++) questions.add(new LinkedHashMap<>());

            pdfData.put(s.getId(), new SectionPdfInfo(s.getId(), s.getName(), s.getAcceptsMultiple(), questions));
        }

        for(Question q: schema.getQuestions()) {
            List<Map<String, Object>> submittedAnswers = data.getSections().get(q.getSectionId());

            int i = 0;
            for(Map<String, Object> answer: submittedAnswers) {
                Object answerValue = answer.get(q.getId());
                if(answerValue == null) {
                    answerValue = "-";
                }
                else if(answerValue.equals("")) {
                    answerValue = "-";
                }
                else if(answerValue instanceof Boolean) {
                    answerValue = ((Boolean) answerValue) ? "Yes" : "No";
                }
                pdfData.get(q.getSectionId()).getQuestions().get(i).put(q.getId(), new QuestionPdfInfo(q.getId(), q.getName(), answerValue));
                i++;
            }
        }

        for(Map.Entry<String, SectionPdfInfo> entry: pdfData.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("------------------------");
        }


        // Parse and fill in html template
        Context context = new Context();

        context.setVariable("date", response.getCreatedAt());
        context.setVariable("workflow", data.getWorkflow());

        context.setVariable("data", pdfData);

        context.setVariable("user",response.getUserId() != null ? userService.findById(response.getUserId()): null);


        String renderedHtmlContent;

        renderedHtmlContent = templateEngine.process("report-template", context);


        // Render pdf from html template
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("./fonts/ARIALUNI.TTF", IDENTITY_H, EMBEDDED);
        renderer.setDocumentFromString(renderedHtmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();

        return outputStream.toByteArray();
    }

    public String createTodayDirForUserIfNotExists(String username) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer year  = localDate.getYear();
        Integer month = localDate.getMonthValue();
        Integer day   = localDate.getDayOfMonth();

        Path path = Paths.get(reportsBaseDirectory, year.toString(), month.toString(), day.toString(), username);
        new File(path.toString()).mkdirs();

        return Paths.get(year.toString(), month.toString(), day.toString(), username).toString();
    }
}
