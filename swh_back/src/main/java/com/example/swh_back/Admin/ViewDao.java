/*package com.example.swh_back.Admin;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ViewUserAdmin> getAllUsers() {
       // String sql = "SELECT salutation, fname, email, dob, phone_number, registering_as, is_verified FROM student_reg";
       String sql = """
    SELECT salutation, fname, email, dob, phone_number, 'Student' AS registering_as, is_verified 
    FROM student_reg
    UNION ALL
    SELECT salutation, fname, email, dob, phone_number, 'Mentor' AS registering_as, is_verified 
    FROM mentor_reg
    UNION ALL
    SELECT salutation, fname, email, dob, phone_number, 'Recruiter' AS registering_as, is_verified 
    FROM recruiter_reg
    UNION ALL
    SELECT salutation, fname, email, dob, phone_number, 'IA' AS registering_as, is_verified 
    FROM ia_reg;
""";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ViewUserAdmin user = new ViewUserAdmin();
            user.setSalutation(rs.getString("salutation"));
            user.setFirstName(rs.getString("fname"));  // Updated to match field
            user.setEmail(rs.getString("email"));
            user.setDob(rs.getDate("dob").toLocalDate());
            user.setPhoneNumber(rs.getString("phone_number"));  // Updated to match field
            user.setRole(rs.getString("registering_as"));  // Updated to match field
            user.setVerified(rs.getBoolean("is_verified"));
            return user;
        });
    }
}
*/

package com.example.swh_back.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ViewUserAdmin> getAllUsers(String role) {
        StringBuilder sql = new StringBuilder();
       // sql.append("SELECT salutation, fname, email, dob, phone_number, 'Student' AS registering_as, is_verified FROM student_reg ");

        // Check the role and modify the SQL accordingly
        if (role != null && !role.equalsIgnoreCase("all")) {
            sql.append("UNION ALL ");
            if (role.equalsIgnoreCase("student")) {
                sql.append("SELECT salutation, fname, email, dob, phone_number, 'Student' AS registering_as, is_verified FROM student_reg ");
            }
            else if (role.equalsIgnoreCase("mentor")) {
                sql.append("SELECT salutation, fname, email, dob, phone_number, 'Mentor' AS registering_as, is_verified FROM mentor_reg ");
            } else if (role.equalsIgnoreCase("recruiter")) {
                sql.append("SELECT salutation, fname, email, dob, phone_number, 'Recruiter' AS registering_as, is_verified FROM recruiter_reg ");
            } else if (role.equalsIgnoreCase("ia")) {
                sql.append("SELECT salutation, fname, email, dob, phone_number, 'IA' AS registering_as, is_verified FROM ia_reg ");
            }
        } else {
            // Include all roles if "all" is selected
            sql.append("SELECT salutation, fname, email, dob, phone_number, 'Student' AS registering_as, is_verified FROM student_reg ");
            sql.append("UNION ALL SELECT salutation, fname, email, dob, phone_number, 'Mentor' AS registering_as, is_verified FROM mentor_reg ");
            sql.append("UNION ALL SELECT salutation, fname, email, dob, phone_number, 'Recruiter' AS registering_as, is_verified FROM recruiter_reg ");
            sql.append("UNION ALL SELECT salutation, fname, email, dob, phone_number, 'IA' AS registering_as, is_verified FROM ia_reg ");
        }

        return jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
            ViewUserAdmin user = new ViewUserAdmin();
            user.setSalutation(rs.getString("salutation"));
            user.setFirstName(rs.getString("fname"));
            user.setEmail(rs.getString("email"));
            user.setDob(rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null);
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setRole(rs.getString("registering_as"));
            user.setVerified(rs.getBoolean("is_verified"));
            return user;
        });
    }
}
