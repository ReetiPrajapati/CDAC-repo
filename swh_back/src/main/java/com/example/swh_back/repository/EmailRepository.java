package com.example.swh_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.swh_back.model.EmailRecord;

@Repository
public interface EmailRepository extends JpaRepository<EmailRecord,Long> {

    @Query(value = "SELECT n.first_name FROM personal_info n JOIN email_record e ON n.email = e.personal_email WHERE e.email = :email", nativeQuery = true)
    String findNameByEmail(@Param("email") String email);
}
