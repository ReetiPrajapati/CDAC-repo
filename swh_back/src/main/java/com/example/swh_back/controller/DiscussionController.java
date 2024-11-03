
/*package com.example.swh_back.controller;

import com.example.swh_back.model.Discussion;
import com.example.swh_back.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discussion")

public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @PostMapping("/send")
    public Discussion sendMessage(@RequestParam String studentName, @RequestParam String mentorName, @RequestParam String discussion) {
        return discussionService.saveMessage(studentName, mentorName, discussion);
    }

    @GetMapping("/messages")
    public List<Discussion> getDiscussions(@RequestParam String studentName, @RequestParam String mentorName) {
        return discussionService.getDiscussionsBetweenStudentAndMentor(studentName, mentorName);
    }
}*/



// src/main/java/com/example/swh_back/controller/DiscussionController.java

// src/main/java/com/example/discussionapp/controller/DiscussionController.java

package com.example.swh_back.controller;

import com.example.swh_back.model.Discussion;
import com.example.swh_back.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/discussion")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from frontend
public class DiscussionController {

    @Autowired
    private DiscussionRepository discussionRepository;

    /**
     * Endpoint to send a message.
     *
     * @param discussion Discussion object containing studentName, mentorName, and message.
     * @return The saved Discussion object.
     */
    @PostMapping("/send")
    public Discussion sendMessage(@RequestBody Discussion discussion) {
        discussion.setTimestamp(LocalDateTime.now());
        return discussionRepository.save(discussion);
    }

    /**
     * Endpoint to retrieve messages between a student and mentor.
     *
     * @param studentName Name of the student.
     * @param mentorName  Name of the mentor.
     * @return List of Discussion objects.
     */
    @GetMapping("/messages")
    public List<Discussion> getMessages(@RequestParam String studentName, @RequestParam String mentorName) {
        return discussionRepository.findByStudentNameAndMentorNameOrderByTimestampAsc(studentName, mentorName);
    }
}



