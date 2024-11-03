package com.example.swh_back.StudentForm;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;


@Repository
public class StudentDao2 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert student data into personal_info table
    public void insertPersonalInfo(String email, String salutation, String fname, String mname,
            String lname,
            String phone_number, Date dob) {

        String query = "INSERT INTO personal_info (email,salutation, first_name, middle_name, last_name, phone_number, dob) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int update = jdbcTemplate.update(query, email, salutation, fname, mname, lname, phone_number,
                dob);

        System.out.println(update + " rows added to personal_info");
    }

    // Insert student data into address table
    public void insertAddress(String email, String street, String city, String state, String zipCode) {
        String query = "INSERT INTO address (email, street_no, city, state, zip_code) "
                + "VALUES (?, ?, ?, ?, ?)";
        int update = jdbcTemplate.update(query, email, street, city, state, zipCode);

        System.out.println(update + " rows added to address");
    }

    // Insert student data into academic_info table
    public void insertAcademicInfo(String email, String currentInstitution, String degreeProgram, String major,
            String yearOfGraduation, Double gpa) {
        String query = "INSERT INTO academicinfo (email, current_institution, degree_program, major, year_of_graduation, gpa) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        int update = jdbcTemplate.update(query, email, currentInstitution, degreeProgram, major, yearOfGraduation, gpa);

        System.out.println(update + " rows added to academic_info");
    }

    // Insert student data into work_experience table
    public void insertWorkExperience(String email, String companyName, String role, String duration, String title,
            String description, String duration2, String contributions) {
        String query = "INSERT INTO workexperience (email, company_name, role, duration, title, description, duration2, contributions) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int update = jdbcTemplate.update(query, email, companyName, role, duration, title, description, duration2,
                contributions);

        System.out.println(update + " rows added to work_experience");
    }

    // Insert student data into skills table
    public void insertSkills(String email, String technologies, List<String> programmingLanguages,
                         List<String> toolsFrameworks, List<String> softSkills) {

    // Convert lists to comma-separated strings
    String programmingLanguagesStr = String.join(",", programmingLanguages);
    String toolsFrameworksStr = String.join(",", toolsFrameworks);
    String softSkillsStr = String.join(",", softSkills);

    String query = "INSERT INTO skills (email, technologies, programminglanguages, toolsframeworks, softskills) "
                 + "VALUES (?, ?, ?, ?, ?)";
    try {
        int update = jdbcTemplate.update(query, email, technologies, programmingLanguagesStr, toolsFrameworksStr, softSkillsStr);
        System.out.println(update + " rows added to skills");
    } catch (DataAccessException e) {
        // Handle database access exceptions
        System.err.println("Database error: " + e.getMessage());
    }
}

    // Insert student data into extra table
    public void insertExtra(String email, String clubs, String volunteerWork, String hobbies, String motivation) {
        String query = "INSERT INTO extra(email, clubs, volunteerwork, hobbies, motivation)"
                + "VALUES (?, ?, ?, ?, ?)";
        int update = jdbcTemplate.update(query, email, clubs, volunteerWork, hobbies, motivation);

        System.out.println(update + " rows added to extra");
    }

    public void insertResume(String email, MultipartFile resumePdf, boolean check1, boolean check2, boolean check3) {
        String query = "INSERT INTO resume (email, resume_pdf, check1, check2, check3) VALUES (?, ?, ?, ?, ?)";
        try {
            byte[] resumePdfBytes = resumePdf.getBytes();
            int update = jdbcTemplate.update(query, email, resumePdfBytes, check1, check2, check3);
            System.out.println(update + " row(s) added to resume");
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        } catch (DataAccessException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    // Retrieve all student data from personal_info table
    public List<Map<String, Object>> getStudents() {
        String query = "SELECT * FROM personal_info";
        return jdbcTemplate.queryForList(query);
    }

    public Map<String, Object> getStudentByEmail(String email) {
        String query = "SELECT * FROM student_reg WHERE email = ?";
        return jdbcTemplate.queryForMap(query, email);
    }
    
}