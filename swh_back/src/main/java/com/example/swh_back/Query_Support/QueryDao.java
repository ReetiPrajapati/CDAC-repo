/* 
package com.example.swh_back.Query_Support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveQuery(String email, String issueRelated, String description) {
        String sql = "INSERT INTO query (email, issue_related, description, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, email, issueRelated, description, "Pending");
    }

    public List<Query> getQueries() {
        String sql = "SELECT * FROM query";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Query query = new Query();
            query.setEmail(rs.getString("email"));  // Make sure to set the email field as well
            query.setIssueRelated(rs.getString("issue_related"));
            query.setDescription(rs.getString("description"));
            query.setStatus(rs.getString("status"));
            return query;
        });
    }

    // Fetch queries by email (for specific users)
    @SuppressWarnings("deprecation")
    public List<Query> getQueriesByEmail(String email) {
        String sql = "SELECT * FROM query WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> {
            Query query = new Query();
            query.setEmail(rs.getString("email"));  // Add this line if you want to display the email
            query.setIssueRelated(rs.getString("issue_related"));
            query.setDescription(rs.getString("description"));
            query.setStatus(rs.getString("status"));
            return query;
        });
    }
}
*/

package com.example.swh_back.Query_Support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new query
    public void saveQuery(String email, String issueRelated, String description) {
        String sql = "INSERT INTO query (email, issue_related, description, status) VALUES ( ?, ?, ?, ?)";
        jdbcTemplate.update(sql, email, issueRelated, description, "Pending");
    }

    // Retrieve all queries (for admin)
    public List<Query> getQueries() {
        String sql = "SELECT * FROM query";
        List<Query> queries = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Query query = new Query();
            query.setId(rs.getInt("id"));
            query.setEmail(rs.getString("email"));
            query.setIssueRelated(rs.getString("issue_related"));
            query.setDescription(rs.getString("description"));
            query.setStatus(rs.getString("status"));
            query.setReply(rs.getString("reply"));
            return query;
        });
        // Log or debug queries here
        System.out.println(queries);
        return queries;
    }

    // Fetch queries by email (for specific users)
    @SuppressWarnings("deprecation")
    public List<Query> getQueriesByEmail(String email) {
        String sql = "SELECT * FROM query WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> {
            Query query = new Query();
            query.setId(rs.getInt("id"));
            query.setEmail(rs.getString("email"));
            query.setIssueRelated(rs.getString("issue_related"));
            query.setDescription(rs.getString("description"));
            query.setStatus(rs.getString("status"));
            query.setReply(rs.getString("reply"));
            return query;
        });
    }

    // Update the reply and status of a query
    
public void updateReply(int id, String reply, String status) {
    // Ensure that status is set to "Answered" if a reply is provided
    String newStatus = (reply != null && !reply.trim().isEmpty()) ? "Answered" : status;
    String sql = "UPDATE query SET reply = ?, status = ? WHERE id = ?";
    jdbcTemplate.update(sql, reply, newStatus, id);
}

}
