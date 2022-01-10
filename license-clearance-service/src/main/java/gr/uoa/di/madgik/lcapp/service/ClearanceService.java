package gr.uoa.di.madgik.lcapp.service;

import com.lowagie.text.DocumentException;
import com.sun.istack.NotNull;
import gr.uoa.di.madgik.lcapp.model.clearance.Response;
import gr.uoa.di.madgik.lcapp.model.clearance.Clearance;
import gr.uoa.di.madgik.lcapp.pdf.PdfService;
import gr.uoa.di.madgik.lcapp.repository.ClearanceRepository;
import gr.uoa.di.madgik.lcapp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
public class ClearanceService {

    @Autowired
    ClearanceRepository clearanceRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PdfService pdfService;

    @Autowired
    ResponseService responseService;

    @Autowired
    VocabularyService vocabularyService;

    @Value("${app.pdf.base-path}")
    private String reportsBaseDirectory;


    public Clearance saveClearance(Response response) throws Exception {

        Clearance clearance = new Clearance();

        clearance.setCreatedAt(response.getCreatedAt());
        clearance.setUpdatedAt(response.getUpdatedAt());
        clearance.setUserId(response.getUserId());
        clearance.setResponseId(response.getId());
        clearance.setWorkflow(response.getClearanceSubmission().getWorkflow());
        //TODO: LICENSE DRIVEN LOGIC HERE

        if (clearance.getWorkflow().equals("resourceDriven")){
            clearance.setName((String) response.getClearanceSubmission().getSections().get("s01").get(0).get("q002"));
            clearance.setLicense((String) response.getClearanceSubmission().getSections().get("s03").get(0).get("q016"));
        }
        else if (clearance.getWorkflow().equals("licenseDriven")){
            clearance.setName((String) response.getClearanceSubmission().getSections().get("s01").get(0).get("q002"));
            clearance.setLicense((String) response.getClearanceSubmission().getSections().get("s02").get(0).get("q008"));
        }
        else
            throw new Exception("Invalid workflow");

        return clearanceRepository.save(clearance);
    }

    public Clearance saveClearance(Clearance clearance) {
        return clearanceRepository.save(clearance);
    }


    public List<Clearance> getClearances(Long user_id){

        List<Clearance> clearances;

        if (user_id != null){
            clearances = clearanceRepository.findAllByUserId(user_id);
        }
        else
            clearances = clearanceRepository.findAllByUserId(authService.getPrincipal().getId());

        for (Clearance cl: clearances){
            cl.setLicense(this.vocabularyService.getTermNameFromTermId(cl.getLicense()));
        }
        return clearances;
    }


    public void deleteClearance(Long id){
        responseService.deleteById(getClearanceById(id).getResponseId());
        clearanceRepository.deleteById(id);
    }

    public Clearance getClearanceById(Long id){
        return clearanceRepository.findById(id).orElse(null);
    }

    public Clearance getClearanceByUUID(String uuid){
        return clearanceRepository.findByUuid(uuid).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(String.format("Cannot find clearance with uuid: %s", uuid));
                }
        );
    }

    public byte[] getClearanceReport(Long id) throws IOException, DocumentException {

        Clearance clearance = getClearanceById(id);
        Response response = responseService.getResponseById(clearance.getResponseId());
        return pdfService.generatePdf(response);
    }

    public String savePdfReport(Long id, String fileName, String username) throws IOException, DocumentException{
        byte[] arr = getClearanceReport(id);
        Path pathNoBase = Paths.get(pdfService.createTodayDirForUserIfNotExists(username), fileName.replace(" ", "_") + ".pdf");

        Path fullPath = Paths.get(reportsBaseDirectory, pathNoBase.toString());
        try (OutputStream os = Files.newOutputStream(fullPath)) {
            os.write(arr);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return pathNoBase.toString();
    }

    public byte[] getReportPdf(String reportPath) throws IOException {
        return Files.readAllBytes(new File(reportsBaseDirectory + reportPath).toPath());

    }
}
