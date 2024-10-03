package com.modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gym_management_system"; // Update with your database name
    private static final String USER = "root"; // Update with your database username
    private static final String PASSWORD = "root"; // Update with your database password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace(); // Print the error to the console
        }
        return connection;
    }
}





















