package com.example.swh_back.industry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectDao projectDao;

    @PostMapping("/save")
public ResponseEntity<String> submitProject(
        @RequestParam String studentName,
        @RequestParam String studentEmail,
        @RequestParam String title,
        @RequestParam String description,
        @RequestParam String startDate,
        @RequestParam String endDate,
        @RequestParam String status,
        @RequestParam String technologiesUsed,
        @RequestParam(required = false) String reportSubmissionDate,
        @RequestParam(required = false) String documentationLink) {

            System.out.println("Received request with parameters:");
    System.out.println("Student Name: " + studentName);

    

    projectDao.saveProject(studentName, studentEmail, title, description, startDate, endDate, status, technologiesUsed, reportSubmissionDate, documentationLink);
    return ResponseEntity.ok("Project saved successfully.");
}
    

@GetMapping("/all")
public ResponseEntity<List<Project>> getAllProjects() {
    List<Project> projects = projectDao.getAllProjects();
    return ResponseEntity.ok(projects);
}
}
