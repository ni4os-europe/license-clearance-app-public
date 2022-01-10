package gr.uoa.di.madgik.lcapp.model.clearance;

import java.util.List;
import java.util.Map;

public class ClearanceSubmission {

    private String workflow;

    private Map<String, List<Map<String, Object>>> sections;

    public ClearanceSubmission() {
    }

    public ClearanceSubmission(String workflow, Map<String, List<Map<String, Object>>> sections) {
        this.workflow = workflow;
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "ClearanceSubmission{" +
                "workflow='" + workflow + '\'' +
                ", sections=" + sections +
                '}';
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public Map<String, List<Map<String, Object>>> getSections() {
        return sections;
    }

    public void setSections(Map<String, List<Map<String, Object>>> sections) {
        this.sections = sections;
    }
}

