package gr.uoa.di.madgik.lcapp.controller.clearance;

import com.lowagie.text.DocumentException;
import gr.uoa.di.madgik.lcapp.mail.MailService;
import gr.uoa.di.madgik.lcapp.model.Country;
import gr.uoa.di.madgik.lcapp.model.clearance.Clearance;
import gr.uoa.di.madgik.lcapp.pdf.PdfService;
import gr.uoa.di.madgik.lcapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ClearanceController {

    @Autowired
    LicenseService licenseService;

    @Autowired
    ContactCategoryService contactCategoryService;

    @Autowired
    MailService mailService;

    @Autowired
    PdfService pdfService;

    @Autowired
    AuthService authService;

    @Autowired
    ResponseService responseService;

    @Autowired
    ClearanceService clearanceService;

    @Autowired
    CountryService countryService;

    @PostMapping("/compatibility-check")
    public Map<String, Boolean> isCompatible(@RequestBody Map<String, Object> payload){
        Boolean result = licenseService.isCompatible(payload);
        return Collections.singletonMap("success",result);
    }

    @PostMapping("/contact")
    public void getContact(@RequestBody Map<String, Object> payload){
        mailService.sendMail(payload);
    }

    @PostMapping("/compatible-licenses")
    public Map<String, List<String>> getCompatibleLicenses(@RequestBody List<String> givenLicenses){
        return Collections.singletonMap("compatible_licenses", licenseService.getCompatibleLicenses(givenLicenses));
    }

    @PostMapping(value = "/submit-clearance")
    public ResponseEntity<Long> submit(@RequestBody Map<String, Object> payload){
        // Save response
        // Save clearance
        Clearance cl = clearanceService.saveClearance(responseService.saveResponse(payload));
        return ResponseEntity.ok(cl.getId());
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]>  getClearanceReport(@RequestParam("id") Long id) throws IOException, DocumentException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, clearanceService.getClearanceName(id));
        headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.ok()
                .headers(headers)
                .body(clearanceService.getClearanceReport(id));
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }

}
