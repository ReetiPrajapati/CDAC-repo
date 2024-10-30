package com.example.swh_back.industry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProjectDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveProject(String studentName, String studentEmail, String title, String description, String startDate, String endDate, String status, String technologiesUsed, String reportSubmissionDate, String documentationLink) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Convert the start and end date strings to LocalDate
    LocalDate start = LocalDate.parse(startDate, formatter);
    LocalDate end = LocalDate.parse(endDate, formatter);
    LocalDate reportDate= LocalDate.parse(reportSubmissionDate, formatter);

    // Now pass the LocalDate objects to your query
    jdbcTemplate.update(
        "INSERT INTO projects (student_name, student_email, title, description, start_date, end_date, status, technologies_used, report_submission_date, documentation_link) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        studentName, studentEmail, title, description, start, end, status, technologiesUsed, reportDate, documentationLink
    );
}
   
public List<Project> getAllProjects() {
    return jdbcTemplate.query("SELECT * FROM projects", (rs, rowNum) -> {
        Project project = new Project();
        project.setStudentName(rs.getString("student_name"));
        project.setStudentEmail(rs.getString("student_email"));
        project.setTitle(rs.getString("title"));
        project.setDescription(rs.getString("description"));
        project.setStartDate(rs.getDate("start_date").toLocalDate());
        project.setEndDate(rs.getDate("end_date").toLocalDate());
        project.setStatus(rs.getString("status"));
        project.setTechnologiesUsed(rs.getString("technologies_used"));
        project.setReportSubmissionDate(rs.getDate("report_submission_date").toLocalDate());
        project.setDocumentationLink(rs.getString("documentation_link"));
        return project;
    });
}

   
}
