package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Part;

import com.util.DatabaseUtil;

public class ReimbursementApplicationService {
    public int saveReimbursementApplication(String clubName, String content, double amount, Part invoiceFile) {
        String sql = "INSERT INTO Reimbursement_application (Reimbursing_Club, Reimbursement_Content, Reimbursement_Amount, Reimbursement_Status, Invoice_Attached) VALUES (?, ?, ?, 'Not Submitted', ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, clubName);
            ps.setString(2, content);
            ps.setDouble(3, amount);
            ps.setBoolean(4, invoiceFile != null);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int reimbursementId = rs.getInt(1);

                    // 保存发票文件到发票认证表
                    saveInvoiceFile(reimbursementId, clubName, invoiceFile);
                    return reimbursementId;
                }
            }
        } catch (SQLException | IOException e) {
            ((Throwable) e).printStackTrace();
        }
        return -1;
    }

    private void saveInvoiceFile(int reimbursementId, String clubName, Part invoiceFile) throws IOException {
        if (invoiceFile != null) {
            String sql = "INSERT INTO Invoice_Authentication (Reimbursing_Club, Invoice_Name, Invoice_File) VALUES (?, ?, ?)";
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, clubName);
                ps.setString(2, invoiceFile.getSubmittedFileName());
                ps.setBinaryStream(3, invoiceFile.getInputStream());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
