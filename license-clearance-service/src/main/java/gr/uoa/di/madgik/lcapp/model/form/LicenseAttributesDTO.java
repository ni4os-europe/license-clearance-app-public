package gr.uoa.di.madgik.lcapp.model.form;

import java.util.List;

public class LicenseAttributesDTO {

    private List<String> permissions;
    private List<String> prohibitions;
    private List<String> obligations;

    private List<String> noPermissions;
    private List<String> noProhibitions;
    private List<String> noObligations;

    public LicenseAttributesDTO() {
    }

    public LicenseAttributesDTO(List<String> permissions, List<String> prohibitions, List<String> obligations, List<String> noPermissions, List<String> noProhibitions, List<String> noObligations) {
        this.permissions = permissions;
        this.prohibitions = prohibitions;
        this.obligations = obligations;
        this.noPermissions = noPermissions;
        this.noProhibitions = noProhibitions;
        this.noObligations = noObligations;
    }

    @Override
    public String toString() {
        return "LicenseAttributesDTO{" +
                "permissions=" + permissions +
                ", prohibitions=" + prohibitions +
                ", obligations=" + obligations +
                ", noPermissions=" + noPermissions +
                ", noProhibitions=" + noProhibitions +
                ", noObligations=" + noObligations +
                '}';
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getProhibitions() {
        return prohibitions;
    }

    public void setProhibitions(List<String> prohibitions) {
        this.prohibitions = prohibitions;
    }

    public List<String> getObligations() {
        return obligations;
    }

    public void setObligations(List<String> obligations) {
        this.obligations = obligations;
    }

    public List<String> getNoPermissions() {
        return noPermissions;
    }

    public void setNoPermissions(List<String> noPermissions) {
        this.noPermissions = noPermissions;
    }

    public List<String> getNoProhibitions() {
        return noProhibitions;
    }

    public void setNoProhibitions(List<String> noProhibitions) {
        this.noProhibitions = noProhibitions;
    }

    public List<String> getNoObligations() {
        return noObligations;
    }

    public void setNoObligations(List<String> noObligations) {
        this.noObligations = noObligations;
    }
}
