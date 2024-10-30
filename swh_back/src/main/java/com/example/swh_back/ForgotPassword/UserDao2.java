package com.example.swh_back.ForgotPassword;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao2 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Other methods...

    public void updatePassword(String email, String newPassword) {
        String query = "UPDATE student_reg SET password = ? WHERE email = ?";
        jdbcTemplate.update(query, newPassword, email);

        query = "UPDATE mentor_reg SET password = ? WHERE email = ?";
        jdbcTemplate.update(query, newPassword, email);

        query = "UPDATE recruiter_reg SET password = ? WHERE email = ?";
        jdbcTemplate.update(query, newPassword, email);

        query = "UPDATE ia_reg SET password = ? WHERE email = ?";
        jdbcTemplate.update(query, newPassword, email);
    }
}
