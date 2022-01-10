package gr.uoa.di.madgik.lcapp.model;

import java.util.List;
import java.util.Map;

public class SectionPdfInfo {
    private String sectionId;
    private String sectionName;
    private boolean acceptsMultiple;
    private List<Map<String, QuestionPdfInfo>> questions;

    public SectionPdfInfo() {
    }

    public SectionPdfInfo(String sectionId, String sectionName, boolean acceptsMultiple, List<Map<String, QuestionPdfInfo>> questions) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.acceptsMultiple = acceptsMultiple;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "SectionPdfInfo{" +
                "sectionId='" + sectionId + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", acceptsMultiple=" + acceptsMultiple +
                ", questions=" + questions +
                '}';
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public boolean isAcceptsMultiple() {
        return acceptsMultiple;
    }

    public void setAcceptsMultiple(boolean acceptsMultiple) {
        this.acceptsMultiple = acceptsMultiple;
    }

    public List<Map<String, QuestionPdfInfo>> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Map<String, QuestionPdfInfo>> questions) {
        this.questions = questions;
    }
}
