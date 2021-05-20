package gr.uoa.di.madgik.lcapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="licenses")
public class License {

    @Id
    private String id;
    private String name;
    private List<String> compatibleLicenses;

    public License(String id, String name, List<String> compatibleLicenses) {
        this.id = id;
        this.name = name;
        this.compatibleLicenses = compatibleLicenses;
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

    public List<String> getCompatibleLicenses() {
        return compatibleLicenses;
    }

    public void setCompatibleLicenses(List<String> compatibleLicenses) {
        this.compatibleLicenses = compatibleLicenses;
    }
}


