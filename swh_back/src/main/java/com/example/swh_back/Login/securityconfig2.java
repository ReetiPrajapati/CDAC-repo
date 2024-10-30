/* 
package com.example.swh_back.Login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityconfig2 {

    @Bean
    public PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Adjusted to new method of disabling CSRF protection.
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/users/login").permitAll() // Allow access to the login endpoint.
                        .anyRequest().authenticated() // Require authentication for all other requests.
                );

        return http.build();
    }
}
*/