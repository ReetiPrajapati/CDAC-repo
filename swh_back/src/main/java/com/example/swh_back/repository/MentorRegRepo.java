package com.example.swh_back.repository;

import com.example.swh_back.model.MentorReg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentorRegRepo extends JpaRepository<MentorReg, String> {

    MentorReg findByEmail(String email);

    Optional<MentorReg> findByFirstName(String name);
}
