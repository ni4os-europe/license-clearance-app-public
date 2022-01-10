package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.License;

import java.util.List;

public interface CustomLicenseRepository {
    List<License> findLicensesWithCompatibleAttributes(List<String> permissions,
                                                       List<String> noPermissions,
                                                       List<String> prohibitions,
                                                       List<String> noProhibitions,
                                                       List<String> obligations,
                                                       List<String> noObligations);
}
