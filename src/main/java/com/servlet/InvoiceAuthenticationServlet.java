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

@WebServlet("/InvoiceAuthenticationServlet")
public class InvoiceAuthenticationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invoiceId = request.getParameter("invoiceId");

        String dbUrl = "jdbc:mysql://localhost:3306/linyi";
        String dbUser = "root";
        String dbPassword = "123456";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "UPDATE Invoice_Authentication SET Authentication_Status = ?, Authentication_Time = ? WHERE Invoice_ID = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, "Authenticated");
                ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                ps.setString(3, invoiceId);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    response.sendRedirect("InvoiceAuthentication.jsp?success=true");
                } else {
                    response.sendRedirect("InvoiceAuthentication.jsp?error=true");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("InvoiceAuthentication.jsp?error=true");
        }
    }
}
