package gr.uoa.di.madgik.lcapp.model.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Question {

    @Field("id")
    private String id;
    private String name;
    private String description;

    private String sectionId;
    private Integer order;
    private Boolean mandatory;
    private String responseType;

    @JsonProperty("public")
    @Field("public")
    private Boolean publicAccess;

    private String dependingQuestionId;
    private String vocabularyId;
    private List<VocabularyCondition> vocabularies;


    public Question(String id, String name, String description, String sectionId, Integer order, Boolean mandatory, String responseType, Boolean publicAccess, String dependingQuestionId, String vocabularyId, List<VocabularyCondition> vocabularies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sectionId = sectionId;
        this.order = order;
        this.mandatory = mandatory;
        this.responseType = responseType;
        this.publicAccess = publicAccess;
        this.dependingQuestionId = dependingQuestionId;
        this.vocabularyId = vocabularyId;
        this.vocabularies = vocabularies;
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

    public Boolean getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(Boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    public String getDependingQuestionId() {
        return dependingQuestionId;
    }

    public void setDependingQuestionId(String dependingQuestionId) {
        this.dependingQuestionId = dependingQuestionId;
    }

    public String getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(String vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public List<VocabularyCondition> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(List<VocabularyCondition> vocabularies) {
        this.vocabularies = vocabularies;
    }
}
