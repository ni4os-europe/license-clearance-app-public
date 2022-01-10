package gr.uoa.di.madgik.lcapp.model;

public class QuestionPdfInfo {
    private String questionId;
    private String questionName;
    private Object answer; // Could be number, boolean, string

    public QuestionPdfInfo() {
    }

    public QuestionPdfInfo(String questionId, String questionName, Object answer) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionPdfInfo{" +
                "questionId='" + questionId + '\'' +
                ", questionName='" + questionName + '\'' +
                ", answer=" + answer +
                '}';
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }
}
