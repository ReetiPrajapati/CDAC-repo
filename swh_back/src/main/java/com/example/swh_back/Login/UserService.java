package com.example.swh_back.Login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<String> authenticateUser(String email, String password) {
        Optional<String> role = userRepository.authenticate(email);
        if (role.isPresent()) {
            // Fetch the password based on the role
            Optional<String> storedHashedPassword = userRepository.getHashedPassword(email, role.get());
            if (storedHashedPassword.isPresent() && passwordEncoder.matches(password, storedHashedPassword.get())) {
                return role;
            }
        }
        return Optional.empty();
    }
}

