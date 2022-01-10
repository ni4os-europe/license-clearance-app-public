package gr.uoa.di.madgik.lcapp.model.form;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.List;

public class Section {

    @Field("id")
    private String id;
    private String name;
    private String description;
    private String detailedDescription;

    private Integer order;
    private Boolean mandatory;
    private Boolean acceptsMultiple;
    private ResourcePresentation resourcePresentation;

    private List<DependingQuestion> dependsOn;
    private String callback;

    public static class OnNoProceed {
        public boolean proceed;
        public String title;
        public String body;
    }

    private OnNoProceed onNoProceed;

    private Boolean hasHelperModal;

    public Section() {
    }

    public Section(String id, String name, String description, String detailedDescription, Integer order, Boolean mandatory, Boolean acceptsMultiple, ResourcePresentation resourcePresentation, List<DependingQuestion> dependsOn, String callback, OnNoProceed onNoProceed, Boolean hasHelperModal) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.order = order;
        this.mandatory = mandatory;
        this.acceptsMultiple = acceptsMultiple;
        this.resourcePresentation = resourcePresentation;
        this.dependsOn = dependsOn;
        this.callback = callback;
        this.onNoProceed = onNoProceed;
        this.hasHelperModal = hasHelperModal;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", order=" + order +
                ", mandatory=" + mandatory +
                ", acceptsMultiple=" + acceptsMultiple +
                ", resourcePresentation=" + resourcePresentation +
                ", dependsOn=" + dependsOn +
                ", callback='" + callback + '\'' +
                ", onNoProceed=" + onNoProceed +
                ", hasHelperModal=" + hasHelperModal +
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

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
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

    public Boolean getAcceptsMultiple() {
        return acceptsMultiple;
    }

    public void setAcceptsMultiple(Boolean acceptsMultiple) {
        this.acceptsMultiple = acceptsMultiple;
    }

    public ResourcePresentation getResourcePresentation() {
        return resourcePresentation;
    }

    public void setResourcePresentation(ResourcePresentation resourcePresentation) {
        this.resourcePresentation = resourcePresentation;
    }

    public List<DependingQuestion> getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(List<DependingQuestion> dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public OnNoProceed getOnNoProceed() {
        return onNoProceed;
    }

    public void setOnNoProceed(OnNoProceed onNoProceed) {
        this.onNoProceed = onNoProceed;
    }

    public Boolean getHasHelperModal() {
        return hasHelperModal;
    }

    public void setHasHelperModal(Boolean hasHelperModal) {
        this.hasHelperModal = hasHelperModal;
    }
}