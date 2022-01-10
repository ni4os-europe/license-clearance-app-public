package gr.uoa.di.madgik.lcapp.model.form;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection ="vocabularies")
public class Vocabulary {

    @Field("id")
    private String id;
    private String name;
    private String description;
    private List<Term> terms;

    public Vocabulary(String id, String name, String description, List<Term> terms) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.terms = terms;
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

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }
}
