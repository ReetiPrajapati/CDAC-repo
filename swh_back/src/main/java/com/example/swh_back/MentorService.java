
/*package com.example.swh_back;

import com.example.swh_back.model.MentorReg;
import com.example.swh_back.repository.MentorRegRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {

    @Autowired
    private MentorRegRepo mentorRepository;

    public List<MentorReg> getAllMentors() {
        return mentorRepository.findAll();
    }

    public MentorReg updateMentor(String email, String college, Boolean isAllotted) {
        Optional<MentorReg> existingMentor = mentorRepository.findById(email);

        if (existingMentor.isPresent()) {
            MentorReg mentor = existingMentor.get();

            // Check if the mentor is already allotted a college
            if (mentor.getIsAllotted() != null && mentor.getIsAllotted()) {
                throw new IllegalArgumentException("This mentor is already allotted a college.");
            }

            // Allot the college to the mentor
            mentor.setCollege(college);
            mentor.setIsAllotted(true);
            return mentorRepository.save(mentor);
        } else {
            throw new IllegalArgumentException("Mentor not found.");
        }
    }
}*/


//main code
/*package com.example.swh_back;
import com.example.swh_back.model.MentorReg;
import com.example.swh_back.repository.MentorRegRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {

    @Autowired
    private MentorRegRepo mentorRepository;

    public List<MentorReg> getAllMentors() {
        return mentorRepository.findAll();
    }

    public MentorReg updateMentor(String email, String college, Boolean isAllotted) {
        Optional<MentorReg> existingMentor = mentorRepository.findById(email);

        if (existingMentor.isPresent()) {
            MentorReg mentor = existingMentor.get();

            if (mentor.getIsAllotted() != null && mentor.getIsAllotted()) {
                throw new IllegalArgumentException("This mentor is already allotted a college.");
            }

            // Check if the college is already assigned to another mentor
            boolean isCollegeAssigned = mentorRepository.findAll().stream()
                    .anyMatch(m -> college.equals(m.getCollege()) && Boolean.TRUE.equals(m.getIsAllotted()));
            if (isCollegeAssigned) {
                throw new IllegalArgumentException("This college is already allotted to another mentor.");
            }

            mentor.setCollege(college);
            mentor.setIsAllotted(true);
            return mentorRepository.save(mentor);
        } else {
            throw new IllegalArgumentException("Mentor not found.");
        }
    }
}*/


package com.example.swh_back;

import com.example.swh_back.model.MentorReg;
import com.example.swh_back.repository.MentorRegRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {

    @Autowired
    private MentorRegRepo mentorRepository;

    public List<MentorReg> getAllMentors() {
        return mentorRepository.findAll();
    }

    public MentorReg updateMentor(String email, String college, Boolean isAllotted) {
        Optional<MentorReg> existingMentor = mentorRepository.findById(email);
        if (existingMentor.isPresent()) {
            MentorReg mentor = existingMentor.get();
            mentor.setCollege(college);
            mentor.setIsAllotted(isAllotted);
            return mentorRepository.save(mentor);
        } else {
            throw new IllegalArgumentException("Mentor not found.");
        }
    }
}


