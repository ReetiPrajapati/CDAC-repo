
/*package com.example.swh_back.repository;

import com.example.swh_back.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    @Query("SELECT d FROM Discussion d WHERE d.studentName = :studentName AND d.mentorName = :mentorName ORDER BY d.timestamp ASC")
    List<Discussion> findDiscussionsBetweenStudentAndMentor(String studentName, String mentorName);
}*/


// src/main/java/com/example/swh_back/repository/DiscussionRepository.java

// src/main/java/com/example/discussionapp/repository/DiscussionRepository.java

package com.example.swh_back.repository;

import com.example.swh_back.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findByStudentNameAndMentorNameOrderByTimestampAsc(String studentName, String mentorName);
}

