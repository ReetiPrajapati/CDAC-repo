/*package com.example.swh_back.Registration;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class AdminController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Map<String, String> admin) {
        String email = admin.get("email");
        String password = admin.get("password");

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(password);

        // Insert admin data
        userDao.insertAdmin(email, hashedPassword);

        return ResponseEntity.ok("Admin registered successfully.");
    }
}
*/