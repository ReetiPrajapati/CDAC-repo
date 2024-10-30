/*package com.example.swh_back.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, String> {

    @Query(value = "SELECT " +
            "CASE " +
            "WHEN EXISTS (SELECT 1 FROM ia_reg WHERE email = :email AND password = :password) THEN 'ia' " +
            "WHEN EXISTS (SELECT 1 FROM mentor_reg WHERE email = :email AND password = :password) THEN 'mentor' " +
            "WHEN EXISTS (SELECT 1 FROM student_reg WHERE email = :email AND password = :password) THEN 'student' " +
            "WHEN EXISTS (SELECT 1 FROM recruiter_reg WHERE email = :email AND password = :password) THEN 'recruiter' "
            +
            "WHEN EXISTS (SELECT 1 FROM admin WHERE email = :email AND password = :password) THEN 'admin' "
            +
            "ELSE NULL END", nativeQuery = true)
    Optional<String> authenticate(@Param("email") String email, @Param("password") String password);
}*/


package com.example.swh_back.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, String> {

    @Query(value = "SELECT " +
            "CASE " +
            "WHEN EXISTS (SELECT 1 FROM ia_reg WHERE email = :email) THEN 'ia' " +
            "WHEN EXISTS (SELECT 1 FROM mentor_reg WHERE email = :email) THEN 'mentor' " +
            "WHEN EXISTS (SELECT 1 FROM student_reg WHERE email = :email) THEN 'student' " +
            "WHEN EXISTS (SELECT 1 FROM recruiter_reg WHERE email = :email) THEN 'recruiter' " +
            "WHEN EXISTS (SELECT 1 FROM admin WHERE email = :email) THEN 'admin' " +
            "ELSE NULL END", nativeQuery = true)
    Optional<String> authenticate(@Param("email") String email);

    @Query(value = "SELECT password FROM ia_reg WHERE email = :email AND :role = 'ia' " +
            "UNION ALL " +
            "SELECT password FROM mentor_reg WHERE email = :email AND :role = 'mentor' " +
            "UNION ALL " +
            "SELECT password FROM student_reg WHERE email = :email AND :role = 'student' " +
            "UNION ALL " +
            "SELECT password FROM recruiter_reg WHERE email = :email AND :role = 'recruiter' " +
            "UNION ALL " +
            "SELECT password FROM admin WHERE email = :email AND :role = 'admin'", nativeQuery = true)
    Optional<String> getHashedPassword(@Param("email") String email, @Param("role") String role);
}

