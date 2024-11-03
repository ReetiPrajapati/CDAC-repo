package com.example.swh_back.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collections;
import java.util.Map;
import java.util.Optional;
/* 


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class AuthController {

    @Autowired
    private UserService userService;

    // POST method for login
    @PostMapping("/login")
public ResponseEntity<Map<String, String>> authenticate(@RequestBody UserLoginRequest loginRequest) {
    Optional<String> role = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
    if (role.isPresent()) {
        return ResponseEntity.ok(Collections.singletonMap("role", role.get()));
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid credentials"));
    }
}

    // Example of GET method for testing purposes or other actions
    @GetMapping("/authenticate")
    public String checkAuthentication() {
        return "This is a test GET endpoint";
    }
}
*/

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaService captchaService;

    // POST method for login
    @PostMapping("/login")
    
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody UserLoginRequest loginRequest) {
        // Verify CAPTCHA
        if (!captchaService.verifyCaptcha(loginRequest.getCaptchaResponse())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid CAPTCHA"));
        }

        // Authenticate user
        Optional<String> role = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (role.isPresent()) {
            return ResponseEntity.ok(Collections.singletonMap("role", role.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Invalid credentials"));
        }
    }

    // Example of GET method for testing purposes or other actions
    @GetMapping("/authenticate")
    public String checkAuthentication() {
        return "This is a test GET endpoint";
    }
}