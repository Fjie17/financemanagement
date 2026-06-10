package com.service;

import com.model.ReservationRecord;
import com.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationRecordService1 {
    public List<ReservationRecord> getRecordsByUsername(String username) {
        List<ReservationRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM Reservation_Records WHERE Reserving_Club = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReservationRecord record2 = new ReservationRecord();
                    record2.setReservationId(rs.getInt("Reservation_ID"));
                    record2.setReservingClub(rs.getString("Reserving_Club"));
                    record2.setVenue(rs.getString("Venue"));
                    record2.setReservationTimeStart(rs.getString("Reservation_Time_Start"));
                    record2.setReservationTimeEnd(rs.getString("Reservation_Time_End"));
                    record2.setReservationStatus(rs.getString("Reservation_Status"));
                    record2.setRecordCreationTime(rs.getString("Record_Creation_Time"));
                    records.add(record2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
}
