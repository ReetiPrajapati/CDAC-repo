package com.example.swh_back.ForgotPassword;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

//import com.example.swh_back.Registration.JwtUtil;

import java.util.UUID;

@RestController
@RequestMapping("/api/password-reset")
@CrossOrigin(origins = "http://localhost:3000")
public class PasswordResetRequestController {

    @Autowired
    private PasswordresetTokenService tokenService;

    //@Autowired
  // private JwtUtil jwtUtil;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam("email") String email) {
        // Generate a password reset token
        String token = UUID.randomUUID().toString();
        tokenService.saveToken(email, token);

        // Send the password reset email
        String resetLink = "http://localhost:3000/reset-password?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("msnigdha40@gmail.com");
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("Please click the following link to reset your password: " + resetLink);
        mailSender.send(message);

        return ResponseEntity.ok("Password reset email sent.");
    }
}
