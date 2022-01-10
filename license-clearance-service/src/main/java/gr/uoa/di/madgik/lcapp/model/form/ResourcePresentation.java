package gr.uoa.di.madgik.lcapp.model.form;

import java.util.List;

public class ResourcePresentation {

    private List<String> columnNames;
    private List<String> questionIds;

    public ResourcePresentation() {
    }

    public ResourcePresentation(List<String> columnNames, List<String> questionIds) {
        this.columnNames = columnNames;
        this.questionIds = questionIds;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }
}
