package gr.uoa.di.madgik.lcapp.model.form;

public class VocabularyCondition {

    private String condition;
    private String vocabularyId;


    public VocabularyCondition(String condition, String vocabularyId) {
        this.condition = condition;
        this.vocabularyId = vocabularyId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(String vocabularyId) {
        this.vocabularyId = vocabularyId;
    }
}
