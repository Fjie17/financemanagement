package com.service;

import com.model.User;
import com.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private Connection conn;

    public UserService(Connection conn) {
        this.conn = conn;
    }

    public UserService() {
		// TODO Auto-generated constructor stub
	}

	public User authenticate(String username, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        }
        return null;
    }

    public User authenticateWithRole(String username, String password, String role) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND role = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        }
        return null;
    }

    public boolean register(User user) {
        String checkUserQuery = "SELECT COUNT(*) FROM user WHERE username = ? OR email = ?";
        String insertUserQuery = "INSERT INTO user (username, password, email, role) VALUES (?, ?, ?, ?)";

        try {
            // 检查用户名和邮箱是否已经存在
            try (PreparedStatement checkStmt = conn.prepareStatement(checkUserQuery)) {
                checkStmt.setString(1, user.getUsername());
                checkStmt.setString(2, user.getEmail());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        return false; // 用户名或邮箱已存在
                    }
                }
            }

            // 插入新用户
            try (PreparedStatement insertStmt = conn.prepareStatement(insertUserQuery)) {
                insertStmt.setString(1, user.getUsername());
                insertStmt.setString(2, user.getPassword()); // 假设密码已经加密
                insertStmt.setString(3, user.getEmail());
                insertStmt.setString(4, user.getRole()); // 默认为普通用户 'user'
                int rowsInserted = insertStmt.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<User> getUsersByRole() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, email, role, created_at FROM user WHERE role IN ('user', 'finance')";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

        // 删除用户方法
        public boolean deleteUser(int id) {
            String sql = "DELETE FROM user WHERE id = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        // 更新用户信息方法
        public boolean updateUser(User user) {
            String sql = "UPDATE user SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword()); // Assume password is hashed
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getRole());
                stmt.setInt(5, user.getId());
                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        public User getUserById(int id) {
            String sql = "SELECT id, username, email, role FROM user WHERE id = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                        user.setRole(rs.getString("role"));
                        return user;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        public List<User> getAllAdmins() {
            List<User> admins = new ArrayList<>();
            String sql = "SELECT * FROM user WHERE role = 'admin'";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password")); // 可选，通常不需要返回密码
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    admins.add(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return admins;
        }
        
        public User getUserByUsername(String username) {
            String sql = "SELECT * FROM user WHERE username = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {
            	stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setEmail(rs.getString("email"));
                        user.setRole(rs.getString("role"));
                        return user;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

}
