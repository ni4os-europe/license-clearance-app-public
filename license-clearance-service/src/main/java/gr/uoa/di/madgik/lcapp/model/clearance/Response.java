package gr.uoa.di.madgik.lcapp.model.clearance;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document("response")
public class Response {

    @Id
    private String id;

    private Long userId;

    private Date createdAt;

    private Date updatedAt;

    private String reportPath;

    private ClearanceSubmission clearanceSubmission;

    public Response() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public ClearanceSubmission getClearanceSubmission() {
        return clearanceSubmission;
    }

    public void setClearanceSubmission(ClearanceSubmission clearanceSubmission) {
        this.clearanceSubmission = clearanceSubmission;
    }
}
