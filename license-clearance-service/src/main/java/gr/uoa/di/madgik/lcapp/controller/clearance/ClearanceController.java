package gr.uoa.di.madgik.lcapp.controller.clearance;

import com.lowagie.text.DocumentException;
import gr.uoa.di.madgik.lcapp.mail.MailService;
import gr.uoa.di.madgik.lcapp.model.*;
import gr.uoa.di.madgik.lcapp.model.clearance.Clearance;
import gr.uoa.di.madgik.lcapp.model.clearance.ClearanceSubmission;
import gr.uoa.di.madgik.lcapp.model.form.LicenseAttributesDTO;
import gr.uoa.di.madgik.lcapp.pdf.PdfService;
import gr.uoa.di.madgik.lcapp.security.UserPrincipal;
import gr.uoa.di.madgik.lcapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class ClearanceController {

    @Autowired
    LicenseService licenseService;

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

//    @PostMapping("/compatibility-check")
//    public Map<String, Boolean> isCompatible(@RequestBody Map<String, Object> payload){
//        Boolean result = licenseService.isCompatible(payload);
//        return Collections.singletonMap("success",result);
//    }

    @PostMapping("/contact")
    public void getContact(@RequestBody Map<String, Object> payload){
        mailService.sendMail(payload);
    }

    @GetMapping("/compatible-licenses")
    public Map<String, List<License>> getCompatibleLicenses(@RequestParam List<String> licenses){
        return Collections.singletonMap("compatibleLicenses", licenseService.getCompatibleLicenses(licenses));
    }

    @GetMapping("/compatibility-check")
    public ResponseEntity<Map<String, Object>> canProceed(@RequestParam String outlicense, @RequestParam List<String> licenses) {

        boolean isCompatible = licenseService.isCompatible(outlicense, licenses);

        return new ResponseEntity<>(Collections.singletonMap("proceed", isCompatible), HttpStatus.OK);
    }

    @PostMapping("/submit-clearance")
    public ResponseEntity<Map<String, String>> submit(@RequestBody ClearanceSubmission payload) throws  Exception {
        Clearance cl = clearanceService.saveClearance(responseService.saveResponse(payload));
        UserPrincipal p = authService.getPrincipal();
        String reportPath = clearanceService.savePdfReport(cl.getId(), cl.getName(), p == null ? "unauthenticated" : p.getUsername());
        cl.setReportPath(reportPath);
        clearanceService.saveClearance(cl);

        return ResponseEntity.ok(Collections.singletonMap("id", cl.getUuid()));
    }

    @PostMapping("/attribute-compatibility")
    public ResponseEntity<List<License>> getAttributeCompatibleLicenses(@RequestBody LicenseAttributesDTO licenseAttributesDTO) {
        List<License> ret = licenseService.getAttributeCompatibleLicenses(licenseAttributesDTO);

        return ResponseEntity.ok(ret);
    }

    @GetMapping("/attribute-questions")
    public ResponseEntity<LicenseAttributeQuestions> getLicenseAttributeQuestions() {
        return ResponseEntity.ok(licenseService.getLicenseAttributeQuestions());
    }

    @GetMapping("/licenses")
    public ResponseEntity<List<License>> getLicenses() {
        return new ResponseEntity<>(licenseService.getLicenses(), HttpStatus.OK);
    }

    @GetMapping("/license-summaries")
    public ResponseEntity<List<LicenseSummary>> getLicenseSummaries() {
        return new ResponseEntity<>(licenseService.getLicenseSummaries(), HttpStatus.OK);
    }

    @GetMapping("/license-info")
    public ResponseEntity<LicenseInfo> getLicenseInfo(@RequestParam("id") String id) {
        return new ResponseEntity<>(licenseService.getLicenseInfo(id), HttpStatus.OK);
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]>  getClearanceReport(@RequestParam("id") String uuid) throws IOException, DocumentException {

        HttpHeaders headers = new HttpHeaders();
        Clearance c = clearanceService.getClearanceByUUID(uuid);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, c.getName().replace(" ","_"));
        headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.ok()
                .headers(headers)
                .body(clearanceService.getReportPdf(c.getReportPath()));
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }

}
