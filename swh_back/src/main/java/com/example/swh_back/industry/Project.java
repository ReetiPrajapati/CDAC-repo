package com.example.swh_back.industry;

import java.time.LocalDate;

public class Project {
    private String studentName;
    private String studentEmail;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String technologiesUsed;
    private LocalDate reportSubmissionDate;
    private String documentationLink;

    // Getters and Setters
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) { // Accept LocalDate
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) { // Accept LocalDate
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTechnologiesUsed() {
        return technologiesUsed;
    }

    public void setTechnologiesUsed(String technologiesUsed) {
        this.technologiesUsed = technologiesUsed;
    }

    public LocalDate getReportSubmissionDate() {
        return reportSubmissionDate;
    }

    public void setReportSubmissionDate(LocalDate reportSubmissionDate) { // Accept LocalDate
        this.reportSubmissionDate = reportSubmissionDate;
    }

    public String getDocumentationLink() {
        return documentationLink;
    }

    public void setDocumentationLink(String documentationLink) {
        this.documentationLink = documentationLink;
    }
}
