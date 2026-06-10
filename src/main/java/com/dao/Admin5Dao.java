package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.model.Suggestion;
import com.util.DatabaseUtil;

public class Admin5Dao {
    private Connection connection;

    public Admin5Dao() {
        // Initialize database connection
        this.connection = DatabaseUtil.getConnection();
    }

    public List<Suggestion> getAllSuggestions() {
        List<Suggestion> suggestions = new ArrayList<>();
        String sql = "SELECT * FROM Suggestions";

        try (PreparedStatement ps = connection.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Suggestion suggestion = new Suggestion();
                suggestion.setId(rs.getInt("Suggestion_ID"));
                suggestion.setClubName(rs.getString("Club_Name"));
                suggestion.setContent(rs.getString("Suggestion_Content"));
                suggestion.setSubmissionTime(rs.getTimestamp("Submission_Time"));
                suggestions.add(suggestion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suggestions;
    }
}
