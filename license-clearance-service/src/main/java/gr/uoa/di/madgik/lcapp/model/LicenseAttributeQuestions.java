package gr.uoa.di.madgik.lcapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "licenseAttributeQuestions")
public class LicenseAttributeQuestions {

    @Id
    @JsonIgnore
    private String id;

    public static class Attribute {
        public String name;
        public String question;
        public String description;
    }

    private List<Attribute> permissions;
    private List<Attribute> prohibitions;
    private List<Attribute> obligations;

    public LicenseAttributeQuestions() {
    }

    public LicenseAttributeQuestions(String id, List<Attribute> permissions, List<Attribute> prohibitions, List<Attribute> obligations) {
        this.id = id;
        this.permissions = permissions;
        this.prohibitions = prohibitions;
        this.obligations = obligations;
    }

    @Override
    public String toString() {
        return "LicenseAttributeQuestions{" +
                "id='" + id + '\'' +
                ", permissions=" + permissions +
                ", prohibitions=" + prohibitions +
                ", obligations=" + obligations +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Attribute> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Attribute> permissions) {
        this.permissions = permissions;
    }

    public List<Attribute> getProhibitions() {
        return prohibitions;
    }

    public void setProhibitions(List<Attribute> prohibitions) {
        this.prohibitions = prohibitions;
    }

    public List<Attribute> getObligations() {
        return obligations;
    }

    public void setObligations(List<Attribute> obligations) {
        this.obligations = obligations;
    }
}
