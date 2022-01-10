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
    private String version;
    private String workflow;
    private List<Section> sections;
    private List<Question> questions;

    public ClearanceSchema() {
    }

    public ClearanceSchema(String id, String version, String workflow, List<Section> sections, List<Question> questions) {
        this.id = id;
        this.version = version;
        this.workflow = workflow;
        this.sections = sections;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "ClearanceSchema{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", workflow='" + workflow + '\'' +
                ", sections=" + sections +
                ", questions=" + questions +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
