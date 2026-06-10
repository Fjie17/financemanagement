package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessReimbursementApprovalServlet")
public class ProcessReimbursementApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String reimbursementId = request.getParameter("reimbursementId");
        String status = request.getParameter("status");

        // 数据库连接信息
        String dbUrl = "jdbc:mysql://localhost:3306/linyi";
        String dbUser = "root";
        String dbPassword = "123456";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // SQL 更新语句
            String sql = "UPDATE Reimbursement_Records SET Reimbursement_Status = ?, Last_Status_Update_Time = ? WHERE Reimbursement_ID = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                ps.setString(3, reimbursementId);

                // 执行更新
                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    response.sendRedirect("ReviewReimbursementForm.jsp?success=true");
                } else {
                    response.sendRedirect("ReviewReimbursementForm.jsp?error=true");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ReviewReimbursementForm.jsp?error=true");
        }
    }
}
