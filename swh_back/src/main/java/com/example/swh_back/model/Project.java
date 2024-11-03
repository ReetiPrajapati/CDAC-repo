package com.example.swh_back.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_allot")
public class Project {

    public Project() {

    }

    public Project(String projectName, String description, Boolean isAssigned, String students, String mentor) {
        this.projectName = projectName;
        this.description = description;
        this.isAssigned = isAssigned;
        this.students = students;
        this.mentor = mentor;
    }

    @Id
    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_assigned")
    private Boolean isAssigned;

    @Column(name = "students")
    private String students;

    @Column(name = "mentor")
    private String mentor;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }


}
