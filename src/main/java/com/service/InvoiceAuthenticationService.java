package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DatabaseUtil;

public class InvoiceAuthenticationService {
    public void addInvoice(String clubName, String fileName, String filePath) {
        String sql = "INSERT INTO Invoice_Authentication (Reimbursing_Club, Invoice_Name, Authentication_Status) VALUES (?, ?, 'Not Authenticated')";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, clubName);
            ps.setString(2, fileName);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int invoiceId = rs.getInt(1);

                // 插入发票文件路径到 Invoice_File 表
                String fileSql = "INSERT INTO Invoice_File (File_Name, File_Path, Invoice_ID) VALUES (?, ?, ?)";
                try (PreparedStatement filePs = conn.prepareStatement(fileSql)) {
                    filePs.setString(1, fileName);
                    filePs.setString(2, filePath);
                    filePs.setInt(3, invoiceId);
                    filePs.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
