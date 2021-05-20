package gr.uoa.di.madgik.lcapp.service;

import com.lowagie.text.DocumentException;
import gr.uoa.di.madgik.lcapp.model.clearance.Response;
import gr.uoa.di.madgik.lcapp.model.clearance.Clearance;
import gr.uoa.di.madgik.lcapp.pdf.PdfService;
import gr.uoa.di.madgik.lcapp.repository.ClearanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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


    public Clearance saveClearance(Response response){

        Clearance clearance = new Clearance();

        clearance.setCreatedAt(response.getCreatedAt());
        clearance.setUpdatedAt(response.getUpdatedAt());
        clearance.setUserId(response.getUserId());
        clearance.setResponseId(response.getId());
        clearance.setLicense((String) response.getAnswers().get("Step3question1"));
        clearance.setName((String) response.getAnswers().get("Step1question2"));
        return clearanceRepository.save(clearance);
    }

    public List<Clearance> getClearances(){
        return clearanceRepository.findAllByUserId(authService.getPrincipal().getId());
    }

    public void deleteClearance(Long id){
        responseService.deleteById(getClearanceById(id).getResponseId());
        clearanceRepository.deleteById(id);
    }

    public Clearance getClearanceById(Long id){
        return clearanceRepository.findById(id).orElse(null);
    }

    public byte[] getClearanceReport(Long id) throws IOException, DocumentException {

        Clearance clearance = getClearanceById(id);
        Response response = responseService.getResponseById(clearance.getResponseId());
        return pdfService.generatePdf(response.getAnswers(),response.getCreatedAt());
    }

    public String getClearanceName(Long id){
        return getClearanceById(id).getName().replace(" ","_");
    }
}
