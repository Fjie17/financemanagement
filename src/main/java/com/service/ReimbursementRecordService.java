package com.service;

import com.model.ReimbursementRecord;
import com.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRecordService {
    public List<ReimbursementRecord> getReimbursementRecords(String clubName) {
        List<ReimbursementRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM Reimbursement_Records WHERE Reimbursing_Club = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, clubName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReimbursementRecord record = new ReimbursementRecord();
                    record.setReimbursementId(rs.getInt("Reimbursement_ID"));
                    record.setReimbursingClub(rs.getString("Reimbursing_Club"));
                    record.setReimbursementContent(rs.getString("Reimbursement_Content"));
                    record.setReimbursementAmount(rs.getDouble("Reimbursement_Amount"));
                    record.setReimbursementStatus(rs.getString("Reimbursement_Status"));
                    record.setLastStatusUpdateTime(rs.getTimestamp("Last_Status_Update_Time"));
                    record.setRecordCreationTime(rs.getTimestamp("Record_Creation_Time"));
                    records.add(record);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}
