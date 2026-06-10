package com.service;

import com.model.ReservationRecord;
import com.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationRecordService {
    private Connection conn;

    public ReservationRecordService(Connection conn) {
        this.conn = conn;
    }

    public List<ReservationRecord> getAllRecords() {
        List<ReservationRecord> records = new ArrayList<>();
        String query = "SELECT * FROM Reservation_Records";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ReservationRecord record = new ReservationRecord();
                record.setReservationId(rs.getInt("Reservation_ID"));
                record.setReservingClub(rs.getString("Reserving_Club"));
                record.setVenue(rs.getString("Venue"));
                record.setReservationTimeStart(rs.getString("Reservation_Time_Start"));
                record.setReservationTimeEnd(rs.getString("Reservation_Time_End"));
                record.setReservationStatus(rs.getString("Reservation_Status"));
                record.setRecordCreationTime(rs.getString("Record_Creation_Time"));
                records.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
    
        public boolean addReservation(ReservationRecord record) {
            String sql = "INSERT INTO Reservation_Records (Reserving_Club, Venue, Reservation_Time_Start, Reservation_Time_End, Reservation_Status) " +
                         "VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, record.getReservingClub());
                stmt.setString(2, record.getVenue());
                stmt.setString(3, record.getReservationTimeStart());
                stmt.setString(4, record.getReservationTimeEnd());
                stmt.setString(5, "Pending"); // 默认状态为 "Pending"
                return stmt.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
}
