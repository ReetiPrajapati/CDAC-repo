package com.example.swh_back.Registration;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class UserDao {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        // Insert student data
        public void insertData1(String salutation, String fname, String mname, String lname, String email,
                        String password, String confirm_password, Date dob, String phone_number, String registering_as,
                        Boolean check1, Boolean isVerified) {
                String query = "INSERT INTO student_reg (salutation, fname, mname, lname, email, password, confirm_password, dob, phone_number, registering_as, check1, is_verified) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                int update = jdbcTemplate.update(query, salutation, fname, mname, lname, email, password,
                                confirm_password,
                                dob, phone_number, registering_as, check1, isVerified);
                System.out.println(update + " rows added");
        }

        public void insertData2(String salutation, String fname, String mname, String lname, String email,
                        String password, String confirm_password, Date dob, String phone_number, String registering_as,
                        Boolean check1, Boolean isVerified) {
                String query = "INSERT INTO mentor_reg (salutation, fname, mname, lname, email, password, confirm_password, dob, phone_number, registering_as, check1, is_verified) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                int update = jdbcTemplate.update(query, salutation, fname, mname, lname, email, password,
                                confirm_password,
                                dob, phone_number, registering_as, check1, isVerified);
                System.out.println(update + " rows added");
        }

        public void insertData3(String salutation, String fname, String mname, String lname, String email,
                        String password, String confirm_password, Date dob, String phone_number, String registering_as,
                        Boolean check1, Boolean isVerified) {
                String query = "INSERT INTO recruiter_reg (salutation, fname, mname, lname, email, password, confirm_password, dob, phone_number, registering_as, check1, is_verified) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                int update = jdbcTemplate.update(query, salutation, fname, mname, lname, email, password,
                                confirm_password,
                                dob, phone_number, registering_as, check1, isVerified);
                System.out.println(update + " rows added");
        }

        public void insertData4(String salutation, String fname, String mname, String lname, String email,
                        String password, String confirm_password, Date dob, String phone_number, String registering_as,
                        Boolean check1, Boolean isVerified) {
                String query = "INSERT INTO ia_reg (salutation, fname, mname, lname, email, password, confirm_password, dob, phone_number, registering_as, check1, is_verified) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                int update = jdbcTemplate.update(query, salutation, fname, mname, lname, email, password,
                                confirm_password,
                                dob, phone_number, registering_as, check1, isVerified);
                System.out.println(update + " rows added");
        }

        public void insertAdmin(String email, String password) {
                String query = "INSERT INTO admin (email, password) VALUES (?, ?)";
                int update = jdbcTemplate.update(query, email, password);
                System.out.println(update + " admin row added");
            }
            

        public void updateVerificationStatus(String email, Boolean isVerified, String registering_as) {
                String query;

                switch (registering_as) {
                        case "student":
                                query = "UPDATE student_reg SET is_verified = ? WHERE email = ?";
                                break;
                        case "mentor":
                                query = "UPDATE mentor_reg SET is_verified = ? WHERE email = ?";
                                break;
                        case "recruiter":
                                query = "UPDATE recruiter_reg SET is_verified = ? WHERE email = ?";
                                break;
                        case "industry alignment":
                                query = "UPDATE ia_reg SET is_verified = ? WHERE email = ?";
                                break;
                        default:
                                throw new IllegalArgumentException("Invalid user type: " + registering_as);
                }

                jdbcTemplate.update(query, isVerified, email);
        }

}