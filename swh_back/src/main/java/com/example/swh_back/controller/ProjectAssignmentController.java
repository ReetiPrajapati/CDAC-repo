/*package com.example.swh_back.controller;

import com.example.swh_back.model.Project;
import com.example.swh_back.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/projectAssignment")


public class ProjectAssignmentController {


    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/getAllProjects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping("/addProject")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project savedProject = projectRepository.save(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProject/{projectName}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectName) {
        Optional<Project> project = projectRepository.findById(projectName);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/allot/{projectName}")
    public ResponseEntity<Project> allotProjectToStudents(
            @PathVariable String projectName,
            @RequestBody List<String> students) {

        Optional<Project> project = projectRepository.findById(projectName);

        if (project.isPresent()) {
            Project existingProject = project.get();

            existingProject.setIsAssigned(true);
            existingProject.setStudents(String.join(",", students));

            Project updatedProject = projectRepository.save(existingProject);

            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}*/


/*package com.example.swh_back.controller;

import com.example.swh_back.model.Project;
import com.example.swh_back.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/projectAssignment")
public class ProjectAssignmentController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/getAllProjects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping("/addProject")
    public ResponseEntity<String> addProject(@RequestBody Project project) {
        // Validate that no field is empty
        if (project.getProjectName() == null || project.getProjectName().trim().isEmpty() ||
            project.getDescription() == null || project.getDescription().trim().isEmpty() )
            //project.getMentorName() == null || project.getMentorName().trim().isEmpty() {

            return new ResponseEntity<>("All fields must be filled out.", HttpStatus.BAD_REQUEST);
        }

        Project savedProject = projectRepository.save(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }
       // Project savedProject = projectRepository.save(addProject(null));
        //return new ResponseEntity<>("Project added successfully.", HttpStatus.CREATED);


    @DeleteMapping("/deleteProject/{projectName}")
    public ResponseEntity<String> deleteProject(@PathVariable String projectName) {
        // Validate projectName is not null or empty
        if (projectName == null || projectName.trim().isEmpty()) {
            return new ResponseEntity<>("Project name cannot be empty.", HttpStatus.BAD_REQUEST);
        }

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

        // Validate projectName and students list
        if (projectName == null || projectName.trim().isEmpty()) {
            return new ResponseEntity<>("Project name cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (students == null || students.isEmpty()) {
            return new ResponseEntity<>("Student list cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        Optional<Project> project = projectRepository.findById(projectName);

        if (project.isPresent()) {
            Project existingProject = project.get();

            existingProject.setIsAssigned(true);
            existingProject.setStudents(String.join(",", students));

            Project updatedProject = projectRepository.save(existingProject);

            return new ResponseEntity<>("Project allotted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Project not found.", HttpStatus.NOT_FOUND);
        }
    }
}*/


package com.example.swh_back.controller;

import com.example.swh_back.model.Project;
import com.example.swh_back.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/projectAssignment")
public class ProjectAssignmentController {

    @Autowired
    private ProjectRepository projectRepository;

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
