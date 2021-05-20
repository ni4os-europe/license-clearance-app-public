package gr.uoa.di.madgik.lcapp.service;

import gr.uoa.di.madgik.lcapp.model.License;
import gr.uoa.di.madgik.lcapp.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.sort;

@Service
public class LicenseService {

    @Autowired
    LicenseRepository licenseRepository;

    // Checks compatibility of a desired license against a list of licenses
    public Boolean isCompatible(Map<String, Object> payload){
        String desired = payload.get("desiredLicense").toString();

        List<String> givenLicenses = (List) payload.get("givenLicenseList");

        License lc = licenseRepository.findByName(desired).orElse(null);
        if (lc==null)
            return false;

        return lc.getCompatibleLicenses().containsAll(givenLicenses);
    }

    // Returns all licenses from DB that are compatible with every license from the given list
    public List<String> getCompatibleLicenses(List<String> givenLicenses){

        List<License> licenses;
        List<String> names = new ArrayList<>();

        if (givenLicenses.isEmpty())
            licenses = licenseRepository.findAll();
        else
            licenses = licenseRepository.findCompatibleLicenses(givenLicenses);

        for (License lc : licenses) {
            names.add(lc.getName());
        }
        sort(names);
        return names;
    }
}
