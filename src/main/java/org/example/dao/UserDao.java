package org.example.dao;

import org.example.model.User;

import java.sql.*;

public class UserDao {

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/teststruts2";
        String jdbcName = "root";
        String jdbcPassword = "asdas-123";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(jdbcUrl, jdbcName, jdbcPassword);
    }

    public User findUser(String username){
        User user = null;
        String query = "SELECT id, username, password FROM users WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password")); // You should store a hashed password
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return user;

    }

}
