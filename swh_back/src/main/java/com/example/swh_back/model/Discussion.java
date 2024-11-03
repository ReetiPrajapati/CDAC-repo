

package com.example.swh_back.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "discussion")
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="student_name", nullable = false)
    private String studentName;

    @Column(name="mentor_name", nullable = false)
    private String mentorName;

    @Column(name="message", columnDefinition = "TEXT", nullable = false)
    private String message;
   

    @Column(name="timestamp", nullable = false)
    private LocalDateTime timestamp;

    // Constructors
    public Discussion() {}

    public Discussion(String studentName, String mentorName, String message, LocalDateTime timestamp) {
        this.studentName = studentName;
        this.mentorName = mentorName;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    // (Alternatively, use Lombok's @Data annotation to generate them automatically)
    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}


