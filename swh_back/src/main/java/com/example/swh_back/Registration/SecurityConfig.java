/*package com.example.swh_back.Registration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/public/**", "/api/users/**", "/api/users/verify/**", "/api/auth/**",
                                "/error","/api/faqs")
                        .permitAll() // Allow
                        // unauthenticated
                        // access
                        // to this endpoint
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

/*
 * package com.example.reg_back;
 * 
 * import org.springframework.context.annotation.Bean;
 * import org.springframework.context.annotation.Configuration;
 * import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.security.crypto.password.PasswordEncoder;
 * import org.springframework.security.web.SecurityFilterChain;
 * import org.springframework.security.web.authentication.
 * UsernamePasswordAuthenticationFilter;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.security.config.http.SessionCreationPolicy;
 * import org.springframework.security.web.authentication.HttpStatusEntryPoint;
 * import org.springframework.http.HttpStatus;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity
 * public class SecurityConfig {
 * 
 * @Autowired
 * private JwtRequestFilter jwtRequestFilter;
 * 
 * @Bean
 * public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
 * Exception {
 * http
 * .cors(cors -> cors.disable()) // Handle CORS separately in WebConfig or
 * customize here
 * .csrf(csrf -> csrf.disable()) // Disable CSRF since we're using JWT
 * .authorizeHttpRequests(authorizeRequests -> authorizeRequests
 * .requestMatchers("/public/**", "/api/users/verify/**", "/api/auth/**",
 * "/error").permitAll() // Allow
 * // unauthenticated
 * // access
 * // to
 * // these
 * // endpoints
 * .requestMatchers("/api/**").authenticated() // Protect API endpoints
 * .anyRequest().permitAll() // Allow other requests (like static resources)
 * )
 * .sessionManagement(
 * sessionManagement ->
 * sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //
 * Use
 * // stateless
 * // sessions
 * // for
 * // JWT
 * .exceptionHandling(exceptionHandling -> exceptionHandling
 * .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN))) //
 * Handle
 * // unauthorized
 * // access with 403
 * .addFilterBefore(jwtRequestFilter,
 * UsernamePasswordAuthenticationFilter.class); // Add JWT filter before
 * // username
 * 
 * return http.build();
 * }
 * 
 * @Bean
 * public PasswordEncoder passwordEncoder() {
 * return new BCryptPasswordEncoder();
 * }
 * }
 */