package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DatabaseUtil;

public class ClubBankInfoService {
    public boolean checkBankInfoExists(String clubName) {
        String sql = "SELECT 1 FROM Club_Bank_Info WHERE Club_Name = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, clubName);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // 如果有记录，返回 true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
