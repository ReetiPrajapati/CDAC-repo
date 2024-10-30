/*package com.example.swh_back;

import com.example.swh_back.model.Discussion;
import com.example.swh_back.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    public Discussion saveMessage(String studentName, String mentorName, String discussion) {
        Discussion newDiscussion = new Discussion(studentName, mentorName, discussion, LocalDateTime.now());
        return discussionRepository.save(newDiscussion);
    }

    public List<Discussion> getDiscussionsBetweenStudentAndMentor(String studentName, String mentorName) {
        return discussionRepository.findDiscussionsBetweenStudentAndMentor(studentName, mentorName);
    }
}*/

// src/main/java/com/example/swh_back/service/DiscussionService.java

package com.example.swh_back;

import com.example.swh_back.model.Discussion;
import com.example.swh_back.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    public Discussion saveMessage(String studentName, String mentorName, String message) {
        Discussion newDiscussion = new Discussion(studentName, mentorName, message, LocalDateTime.now());
        return discussionRepository.save(newDiscussion);
    }

    public List<Discussion> getDiscussionsBetweenStudentAndMentor(String studentName, String mentorName) {
        return discussionRepository.findByStudentNameAndMentorNameOrderByTimestampAsc(studentName, mentorName);
    }
}

