package gr.uoa.di.madgik.lcapp.service;

import gr.uoa.di.madgik.lcapp.model.License;
import gr.uoa.di.madgik.lcapp.model.LicenseAttributeQuestions;
import gr.uoa.di.madgik.lcapp.model.LicenseInfo;
import gr.uoa.di.madgik.lcapp.model.LicenseSummary;
import gr.uoa.di.madgik.lcapp.model.form.LicenseAttributesDTO;
import gr.uoa.di.madgik.lcapp.repository.LicenseAttributeRepository;
import gr.uoa.di.madgik.lcapp.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseService {

    @Autowired
    LicenseRepository licenseRepository;

    @Autowired
    LicenseAttributeRepository licenseAttributeRepository;

    // Checks compatibility of a desired license against a list of licenses
//    public Boolean isCompatible(Map<String, Object> payload){
//        String desired = payload.get("desiredLicense").toString();
//
//        List<String> givenLicenses = (List) payload.get("givenLicenseList");
//
//        License lc = licenseRepository.findByName(desired).orElse(null);
//        if (lc==null)
//            return false;
//
//        return lc.getCompatibleLicenses().containsAll(givenLicenses);
//    }

    public boolean isCompatible(String licenseId, List<String> licenses) {
        return licenseRepository.findCompatible(licenseId, licenses).isPresent();
    }

    // Returns all licenses from DB that are compatible with every license from the given list
    public List<License> getCompatibleLicenses(List<String> givenLicenses){
        return  licenseRepository.findCompatibleLicenses(givenLicenses);
    }

    public List<License> getLicenses() {
        return licenseRepository.findAllWithoutCompatibilityMatrix();
    }

    public List<License> getAttributeCompatibleLicenses(LicenseAttributesDTO dto) {
        return licenseRepository.findLicensesWithCompatibleAttributes(
                dto.getPermissions(),
                dto.getNoPermissions(),
                dto.getProhibitions(),
                dto.getNoProhibitions(),
                dto.getObligations(),
                dto.getNoObligations());
    }

    public LicenseAttributeQuestions getLicenseAttributeQuestions() {
        return licenseAttributeRepository.findAll().get(0);
    }

    public List<LicenseSummary> getLicenseSummaries() {
        return licenseRepository.findLicenseSummaries();
    }

    public LicenseInfo getLicenseInfo(String id) {
        return licenseRepository.findLicenseInfo(id);
    }
}
