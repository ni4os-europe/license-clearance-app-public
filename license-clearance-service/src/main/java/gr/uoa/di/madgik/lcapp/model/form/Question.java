package gr.uoa.di.madgik.lcapp.model.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

public class Question {

    @Field("id")
    private String id;
    private String name;
    private String description;

    private String sectionId;
    private Integer order;
    private Boolean mandatory;
    private String responseType;
    private String callback;

    private DependingQuestion dependsOn;

    private Map<String,String> conditions;

    @JsonProperty("public")
    @Field("public")
    private Boolean publicAccess;

    public Question() {
    }

    public Question(String id, String name, String description, String sectionId, Integer order, Boolean mandatory, String responseType, String callback, DependingQuestion dependsOn, Map<String, String> conditions, Boolean publicAccess) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sectionId = sectionId;
        this.order = order;
        this.mandatory = mandatory;
        this.responseType = responseType;
        this.callback = callback;
        this.dependsOn = dependsOn;
        this.conditions = conditions;
        this.publicAccess = publicAccess;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sectionId='" + sectionId + '\'' +
                ", order=" + order +
                ", mandatory=" + mandatory +
                ", responseType='" + responseType + '\'' +
                ", callback='" + callback + '\'' +
                ", dependsOn=" + dependsOn +
                ", conditions=" + conditions +
                ", publicAccess=" + publicAccess +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public DependingQuestion getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(DependingQuestion dependsOn) {
        this.dependsOn = dependsOn;
    }

    public Map<String, String> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, String> conditions) {
        this.conditions = conditions;
    }

    public Boolean getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(Boolean publicAccess) {
        this.publicAccess = publicAccess;
    }
}
