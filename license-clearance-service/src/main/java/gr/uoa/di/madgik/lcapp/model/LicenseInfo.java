package gr.uoa.di.madgik.lcapp.model;

import java.util.List;

public class LicenseInfo {

    private String id;
    private String name;
    private String type;
    private List<String> permissions;
    private List<String> prohibitions;
    private List<String> obligations;
    private String url;

    public LicenseInfo() {
    }

    public LicenseInfo(String id, String name, String type, List<String> permissions, List<String> prohibitions, List<String> obligations, String url) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.permissions = permissions;
        this.prohibitions = prohibitions;
        this.obligations = obligations;
        this.url = url;
    }

    @Override
    public String toString() {
        return "LicenseInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", permissions=" + permissions +
                ", prohibitions=" + prohibitions +
                ", obligations=" + obligations +
                ", url='" + url + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
