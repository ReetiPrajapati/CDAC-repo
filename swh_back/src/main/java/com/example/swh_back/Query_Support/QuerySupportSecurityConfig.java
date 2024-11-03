/*package com.example.swh_back.Query_Support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class QuerySupportSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain4(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/api/faqs", "/submit", "/queries", "/api/new-query", "/api/helpdesk")
                        .permitAll()  // Public access to these endpoints
                    .requestMatchers("/api/helpdesk/submit")
                        .hasRole("USER")  // Restrict access to this endpoint to users with the "USER" role
                    .anyRequest().authenticated()  // Secure other endpoints
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/helpdesk/submit")  // Disable CSRF for this endpoint
            );  

        return http.build();
    }
}*/
