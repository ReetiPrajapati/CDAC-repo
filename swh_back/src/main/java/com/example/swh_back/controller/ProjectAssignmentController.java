

package com.example.swh_back.controller;

import com.example.swh_back.model.Project;
import com.example.swh_back.repository.ProjectRepository;
import com.example.swh_back.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/projectAssignment")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectAssignmentController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/name")
    public ResponseEntity<String> getNameByEmail(@RequestParam String email) {
        String name = emailRepository.findNameByEmail(email);
        if (name != null) {
            return ResponseEntity.ok(name);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Name not found for the given email");
        }
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping("/addProject")
    public ResponseEntity<String> addProject(@RequestBody Project project) {
        if (project.getDescription() == null || project.getDescription().trim().isEmpty()) {
            return new ResponseEntity<>("Project description cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        projectRepository.save(project);
        return new ResponseEntity<>("Project added successfully.", HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProject/{projectName}")
    public ResponseEntity<String> deleteProject(@PathVariable String projectName) {
        Optional<Project> project = projectRepository.findById(projectName);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            return new ResponseEntity<>("Project deleted successfully.", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Project not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/allot/{projectName}")
    public ResponseEntity<String> allotProjectToStudents(
            @PathVariable String projectName,
            @RequestBody List<String> students) {

        // Validate the list of students
        if (students == null || students.isEmpty() || students.stream().anyMatch(student -> student.trim().isEmpty())) {
            return new ResponseEntity<>("Student names cannot be empty or blank.", HttpStatus.BAD_REQUEST);
        }

        Optional<Project> project = projectRepository.findById(projectName);

        if (project.isPresent()) {
            Project existingProject = project.get();
            existingProject.setIsAssigned(true);
            existingProject.setStudents(String.join(",", students));

            projectRepository.save(existingProject);

            return new ResponseEntity<>("Project assigned to students successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Project not found.", HttpStatus.NOT_FOUND);
        }
    }
}
