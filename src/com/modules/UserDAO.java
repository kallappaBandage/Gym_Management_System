package com.modules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Method to validate user credentials
    public boolean validateCredentials(String username, String password) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM users WHERE username = ? AND password = ?"; // Ensure table and column names match your DB schema
     
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if credentials are valid
        } catch (SQLException e) {
            e.printStackTrace();  // Properly handle the SQL error
            throw e; // Re-throw exception after logging
        }
    }

    // Method to change user's password
    public boolean changePassword(String username, String newPassword) throws SQLException {
        String query = "UPDATE users SET password = ? WHERE username = ?"; // Ensure table and column names match your DB schema

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newPassword);  // Set the new password
            statement.setString(2, username);     // Set the username

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();  // Properly handle the SQL error
            throw e; // Re-throw exception after logging
        }
    }
}








