package com.dao;

import com.model.Suggestion;
import com.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuggestionDao {
    public boolean insertSuggestion(Suggestion suggestion) {
        String sql = "INSERT INTO Suggestions (Club_Name, Suggestion_Content) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, suggestion.getClubName());
            ps.setString(2, suggestion.getContent());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
