/* 
package com.example.swh_back.Query_Support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/helpdesk")
public class QueryController {

    @Autowired
    private QueryDao service;

    @PostMapping("/submit")
    public ResponseEntity<String> submitQuery(
            @RequestParam String email,
            @RequestParam String issueRelated,
            @RequestParam String description) {
        service.saveQuery(email, issueRelated, description);
        return ResponseEntity.ok("Query submitted successfully");
    }

    @GetMapping("/queries")
    public ResponseEntity<List<Query>> getQueries(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role) {

                System.out.println("Received email: " + email); // Debugging
    System.out.println("Received role: " + role);   // Debugging

    if (role == null && email == null) {
        return ResponseEntity.badRequest().body(null);
    }
        
        List<Query> queries;
        if ("admin".equalsIgnoreCase(role)) {
            queries = service.getQueries();
            System.out.println("Admin role, returning all queries.");
        } else if (email != null) {
            queries = service.getQueriesByEmail(email);
            System.out.println("User role, returning queries for email: " + email);
        } else {
            System.out.println("Invalid request: no email or role provided.");
            return ResponseEntity.badRequest().body(null);
        }
    
        return ResponseEntity.ok(queries);
    }
    
}
*/

package com.example.swh_back.Query_Support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/helpdesk")
public class QueryController {

    @Autowired
    private QueryDao service;

    @PostMapping("/submit")
    public ResponseEntity<String> submitQuery(
            @RequestParam String email,
            @RequestParam String issueRelated,
            @RequestParam String description
            ) {
        service.saveQuery(email, issueRelated, description);
        return ResponseEntity.ok("Query submitted successfully");
    }

    // Fetch queries based on role
    @GetMapping("/queries")
    public ResponseEntity<List<Query>> getQueries(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role) {

                System.out.println("Received request for /queries with email: " + email + " and role: " + role);

        if (role == null && email == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Query> queries;
        if ("admin".equalsIgnoreCase(role)) {
            queries = service.getQueries();
        } else if (email != null) {
            queries = service.getQueriesByEmail(email);
        } else {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(queries);
    }

    // Admin updates the reply and status of a query
    @PostMapping("/reply/{id}")
public ResponseEntity<String> updateReply(
        @PathVariable int id,
        @RequestBody UpdateReplyRequest request) {

    service.updateReply(id, request.getReply(), request.getStatus());
    return ResponseEntity.ok("Reply updated successfully");
}

// Create a new class for the request body
public static class UpdateReplyRequest {
    private String reply;
    private String status;

    // Getters and Setters
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

}

