/*package com.example.swh_back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.swh_back.Registration.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/projects/save", "/api/projects/all", "/api/adminnn/usersss",
                                "/api/projectAssignment",
                                "/api/projectAssignment/getAllProjects", "/api/projectAssignment/addProject",
                                "/api/projectAssignment/deleteProject/{projectName}")
                        .permitAll()
                        .requestMatchers("/api/**", "/api/helpdesk/**", "/api/faq/**", "/api/users/**",
                                "/api/studentssave/**", "/api/mentors", "/api/mentors/getAllMentors",
                                "/api/mentors/{name}")
                        .permitAll() // Allow unauthenticated access
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}*/


package com.example.swh_back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.swh_back.Registration.JwtRequestFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF as we are using JWT for authentication
            .csrf(csrf -> csrf.disable())
            
            // Configure CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Set session management to stateless
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // Configure authorization rules
            .authorizeHttpRequests(auth -> auth
                // Permit all requests to these endpoints
                .requestMatchers(
                    "/api/projects/save",
                    "/api/projects/all",
                    "/api/adminnn/usersss",
                    "/api/projectAssignment",
                    "/api/projectAssignment/getAllProjects",
                    "/api/projectAssignment/addProject",
                    "/api/projectAssignment/deleteProject/**", // Allows path variables
                    "/api/discussion/**",
                    "/api/helpdesk/**",
                    "/api/faq/**",
                    "/api/users/**",
                    "/api/studentssave/**",
                    "/api/mentors",
                    "/api/mentors/getAllMentors",
                    "/api/mentors/**", // Allows path variables
                    "/api/discussion/send", //
                    "/api/discussion/messages"

                ).permitAll()
                
                // Any other request requires authentication
                .anyRequest().authenticated()
            )
            
            // Add JWT filter before the UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Defines the CORS configuration.
     *
     * @return CorsConfigurationSource with specified settings.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Specify the allowed origins. Replace with your frontend's origin.
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        
        // Specify the allowed HTTP methods
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Specify the allowed headers
        configuration.setAllowedHeaders(List.of("*"));
        
        // Allow credentials (e.g., cookies, authorization headers)
        configuration.setAllowCredentials(true);
        
        // Optionally, specify exposed headers
        configuration.setExposedHeaders(List.of("Authorization", "Content-Type"));
        
        // Set the maximum age for preflight requests
        configuration.setMaxAge(3600L);
        
        // Register the CORS configuration for all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }

    /**
     * Defines the password encoder bean.
     *
     * @return PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

