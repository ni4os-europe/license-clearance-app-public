package gr.uoa.di.madgik.lcapp.model.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="clearanceSchema")
public class ClearanceSchema {

    @Id
    @JsonIgnore
    private String id;

    private List<Section> sections;
    private List<Question> questions;
    private List<Vocabulary> vocabularies;


    public ClearanceSchema(List<Section> sections,List<Question> questions, List<Vocabulary> vocabularies){
        this.questions = questions;
        this.sections = sections;
        this.vocabularies = vocabularies;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void setVocabularies(List<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }
}
