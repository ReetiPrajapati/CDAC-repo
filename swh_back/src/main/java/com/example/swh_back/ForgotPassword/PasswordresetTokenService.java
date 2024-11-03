package com.example.swh_back.ForgotPassword;



import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class PasswordresetTokenService {

    @Autowired
    private DataSource dataSource;

    public void saveToken(String email, String token) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO password_reset_tokens (email, token) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, token);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
    }

    public Optional<String> getEmailByToken(String token) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT email FROM password_reset_tokens WHERE token = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, token);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(rs.getString("email"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
        return Optional.empty();
    }

    public void deleteToken(String token) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM password_reset_tokens WHERE token = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, token);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
    }
}
