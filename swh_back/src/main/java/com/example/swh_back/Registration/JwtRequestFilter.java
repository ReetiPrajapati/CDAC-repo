package com.example.swh_back.Registration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        System.out.println("Request URI: " + request.getRequestURI());

        // Skip filtering if the path does not require authentication
        if (request.getRequestURI().startsWith("/api/helpdesk/")) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().startsWith("/api/faq/")) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().startsWith("/api/users/")) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().startsWith("/api/users/login")) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().startsWith("/api/adminnn/usersss")) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/api/studentssave/")) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().startsWith("/api/studentssave/studenttt/")) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/api/projects")) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/api/projects/save")) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/api/projectAssignment/getAllProjects")) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/api/projectAssignment/addProject")) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/api/projectAssignment/deleteProject/{projectName}")) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/api/mentors/getAllMentors")) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/api/mentors/{name}")) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().startsWith("/api/mentors")) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().startsWith("/api/projects/all")) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().startsWith("/api/discussion/send")) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getRequestURI().startsWith("/api/discussion/messages")) {
            chain.doFilter(request, response);
            return;
        }
        
        String email = null;
        String jwtToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);

            try {
                // Validate the token format and extract the email
                if (jwtToken.split("\\.").length == 3) {
                    email = jwtUtil.extractEmail(jwtToken);
                } else {
                    throw new IllegalArgumentException("Invalid JWT token format.");
                }
            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token format.");
                return;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access.");
                return;
            }
        }

        // Validate the extracted email and set the authentication if valid
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if (jwtUtil.validateToken(jwtToken, email)) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token.");
                return;
            }
        }

        // Proceed with the next filter in the chain
        chain.doFilter(request, response);
    }
}
