package com.example.dao;

import com.example.model.User;
import com.example.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    return user;
                }
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                users.add(user);
            }
        }
        return users;
    }

    public List<User> searchUsers(String query) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE username LIKE ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + query + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    users.add(user);
                }
            }
        }
        return users;
    }
}
