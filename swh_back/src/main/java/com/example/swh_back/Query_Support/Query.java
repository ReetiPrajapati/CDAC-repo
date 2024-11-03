/*package com.example.swh_back.Query_Support;

public class Query {
    private String email;
    private String issueRelated;
    private String description;
    private String status; // Add status field

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIssueRelated() {
        return issueRelated;
    }

    public void setIssueRelated(String issueRelated) {
        this.issueRelated = issueRelated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}*/

package com.example.swh_back.Query_Support;

public class Query {
    private int id;  // Add id field
    private String email;
    private String issueRelated;
    private String description;
    private String status; 
    private String reply;  // Add reply field

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIssueRelated() {
        return issueRelated;
    }

    public void setIssueRelated(String issueRelated) {
        this.issueRelated = issueRelated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}

