package com.example.swh_back.ForgotPassword;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/password-reset")
@CrossOrigin(origins = "http://localhost:3000")

public class PasswordResetController {

    @Autowired
    private PasswordresetTokenService tokenService;

    @Autowired
    private UserDao2 userDao; // Access your UserDao for updating the password

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token,
                                                @RequestParam("newPassword") String newPassword) {
        Optional<String> emailOpt = tokenService.getEmailByToken(token);

        if (emailOpt.isPresent()) {
            String email = emailOpt.get();

            // Validate and hash the new password
            String hashedPassword = passwordEncoder.encode(newPassword);

            // Update the password in the database
            userDao.updatePassword(email, hashedPassword);

            // Delete the used token
            tokenService.deleteToken(token);

            return ResponseEntity.ok("Password reset successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }
}
