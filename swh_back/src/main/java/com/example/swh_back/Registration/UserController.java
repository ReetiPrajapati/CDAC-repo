package com.example.swh_back.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.text.ParseException;
/* 
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public void insertData(@RequestBody Map<String, String> student) {
        String salutation = student.get("salutation");
        String fname = student.get("fname");
        String mname = student.get("mname");
        String lname = student.get("lname");
        String email = student.get("email");
        String password = student.get("password");
        String confirm_password = student.get("confirm_password");
        boolean check1 = Boolean.parseBoolean(student.get("check1"));
        java.sql.Date dob = null;
        try {
            dob = new java.sql.Date(
                    new SimpleDateFormat("yyyy-dd-MM").parse(student.get("dob")).getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // handle the exception as needed
        }
        String phone_number = student.get("phone_number");
        String registering_as = student.get("registering_as");

        // Hash the password before saving
        if (password.equals(confirm_password)) {
            // Hash the password before saving

            String hashedPassword = passwordEncoder.encode(password);

            switch (registering_as) {
                case "student":
                    userDao.insertData1(salutation, fname, mname, lname, email, hashedPassword, null, dob,
                            phone_number, registering_as, check1, false);
                    break;
                case "mentor":
                    userDao.insertData2(salutation, fname, mname, lname, email, hashedPassword, null, dob,
                            phone_number, registering_as, check1, false);
                    break;
                case "recruiter":
                    userDao.insertData3(salutation, fname, mname, lname, email, hashedPassword, null, dob,
                            phone_number, registering_as, check1, false);
                    break;
                case "industry alignment":
                    userDao.insertData4(salutation, fname, mname, lname, email, hashedPassword, null, dob,
                            phone_number, registering_as, check1, false);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid registration type.");
            }

            // Generate verification token
            String token = jwtUtil.generateToken(email);
            String verificationLink = "http://localhost:8080/api/users/verify?token=" + token + "&registering_as="
                    + registering_as;

            // Send verification email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("shrutiroy2212@gmail.com");
            message.setTo(email);
            message.setSubject("Email Verification");
            message.setText("Please click the following link to verify your email address: " + verificationLink);
            mailSender.send(message);

        } else {
            // Handle the case where passwords do not match
            throw new IllegalArgumentException("Passwords do not match.");
        }

    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("token") String token,
            @RequestParam("registering_as") String registering_as) {
        String email;
        try {
            email = jwtUtil.extractEmail(token);

            if (!jwtUtil.validateToken(token, email)) {
                return "Invalid or expired token.";
            }

            userDao.updateVerificationStatus(email, true, registering_as);
            return "Email verified successfully.";
        } catch (Exception e) {
            return "Invalid or expired token.";
        }
    }
}
*/
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public ResponseEntity<AuthResponse> insertData(@RequestBody Map<String, String> student) {
        String salutation = student.get("salutation");
        String fname = student.get("fname");
        String mname = student.get("mname");
        String lname = student.get("lname");
        String email = student.get("email");
        String password = student.get("password");
        String confirm_password = student.get("confirm_password");
        boolean check1 = Boolean.parseBoolean(student.get("check1"));
        java.sql.Date dob = null;
        try {
            dob = new java.sql.Date(
                    new SimpleDateFormat("yyyy-dd-MM").parse(student.get("dob")).getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
        String phone_number = student.get("phone_number");
        String registering_as = student.get("registering_as");

        // Hash the password before saving
        if (password.equals(confirm_password)) {
            String hashedPassword = passwordEncoder.encode(password);

            switch (registering_as) {
                case "student":
                    userDao.insertData1(salutation, fname, mname, lname, email, hashedPassword, null, dob,
                            phone_number, registering_as, check1, false);
                    break;
                case "mentor":
                    userDao.insertData2(salutation, fname, mname, lname, email, hashedPassword, null, dob,
                            phone_number, registering_as, check1, false);
                    break;
                case "recruiter":
                    userDao.insertData3(salutation, fname, mname, lname, email, hashedPassword, null, dob,
                            phone_number, registering_as, check1, false);
                    break;
                case "industry alignment":
                    userDao.insertData4(salutation, fname, mname, lname, email, hashedPassword, null, dob,
                            phone_number, registering_as, check1, false);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid registration type.");
            }

            // Generate JWT token
            String token = jwtUtil.generateToken(email);

            // Send verification email
            String verificationLink = "http://localhost:8080/api/users/verify?token=" + token + "&registering_as="
                    + registering_as;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("msnigdha40@gmail.com");
            message.setTo(email);
            message.setSubject("Email Verification");
            message.setText("Please click the following link to verify your email address: " + verificationLink);
            mailSender.send(message);

            // Return the JWT token in the response
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            // Handle the case where passwords do not match
            throw new IllegalArgumentException("Passwords do not match.");
        }
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("token") String token,
            @RequestParam("registering_as") String registering_as) {
        String email;
        try {
            email = jwtUtil.extractEmail(token);

            if (!jwtUtil.validateToken(token, email)) {
                return "Invalid or expired token.";
            }

            userDao.updateVerificationStatus(email, true, registering_as);
            return "Email verified successfully.";
        } catch (Exception e) {
            return "Invalid or expired token.";
        }
    }
}
