/*package com.example.swh_back.Registration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class AdminInitializer {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            // Define admin credentials
            String adminEmail = "admin@gmail.com";
            String adminPassword = "admin123";

            
            // Hash the password
            String hashedPassword = passwordEncoder.encode(adminPassword);

            // Insert admin data
            userDao.insertAdmin(adminEmail, hashedPassword);

            System.out.println("Admin added successfully.");
        };
    }
}*/
