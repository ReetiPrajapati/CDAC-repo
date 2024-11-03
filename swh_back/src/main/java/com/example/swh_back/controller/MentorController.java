
package com.example.swh_back.controller;

import com.example.swh_back.MentorService;
import com.example.swh_back.model.MentorReg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping("/getAllMentors")
    public List<MentorReg> getAllMentors() {
        // This will return a list of mentors including their isAllotted status
        return mentorService.getAllMentors();
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> allotMentor(@PathVariable String email, @RequestBody MentorReg mentor) {
        try {
            MentorReg updatedMentor = mentorService.updateMentor(email, mentor.getCollege(), mentor.getIsAllotted());
            return ResponseEntity.ok(updatedMentor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
