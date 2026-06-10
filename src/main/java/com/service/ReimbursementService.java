package com.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.model.ReimbursementRecord;

public class ReimbursementService {
    private Connection conn;

    public ReimbursementService(Connection conn) {
        this.conn = conn;
    }


	public List<ReimbursementRecord> getAllReimbursementRecords(){
        String query = "SELECT * FROM Reimbursement_Records";
        List<ReimbursementRecord> records1 = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ReimbursementRecord record1 = new ReimbursementRecord();
                record1.setReimbursementId(rs.getInt("Reimbursement_ID"));
                record1.setReimbursingClub(rs.getString("Reimbursing_Club"));
                record1.setReimbursementContent(rs.getString("Reimbursement_Content"));
                record1.setReimbursementAmount(rs.getDouble("Reimbursement_Amount"));
                record1.setReimbursementStatus(rs.getString("Reimbursement_Status"));
                record1.setLastStatusUpdateTime(rs.getTimestamp("Last_Status_Update_Time"));
                record1.setRecordCreationTime(rs.getTimestamp("Record_Creation_Time"));
                records1.add(record1);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

        return records1;
    }
}
