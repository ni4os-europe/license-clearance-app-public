package gr.uoa.di.madgik.lcapp.model.form;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

public class Section {

    @Field("id")
    private String id;
    private String name;
    private String description;

    private Integer order;
    private Boolean mandatory;
    private Boolean acceptsMultiple;


    public Section(String id, String name, String description, Integer order, Boolean mandatory, Boolean acceptsMultiple) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.order = order;
        this.mandatory = mandatory;
        this.acceptsMultiple = acceptsMultiple;
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
}
