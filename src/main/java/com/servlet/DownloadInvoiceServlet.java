package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DatabaseUtil;

@WebServlet("/DownloadInvoiceServlet")
public class DownloadInvoiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invoiceId = request.getParameter("invoiceId");

        String dbUrl = "jdbc:mysql://localhost:3306/linyi";
        String dbUser = "root";
        String dbPassword = "123456";

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT Invoice_Name, Invoice_File FROM Invoice_Authentication WHERE Invoice_ID = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, invoiceId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String fileName = rs.getString("Invoice_Name");
                        Blob fileBlob = rs.getBlob("Invoice_File");

                        // 设置响应头
                        response.setContentType("application/octet-stream");
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

                        // 输出文件
                        try (InputStream inputStream = fileBlob.getBinaryStream();
                             OutputStream outputStream = response.getOutputStream()) {

                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }
                    } else {
                        response.getWriter().write("发票记录未找到");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("下载发票时发生错误: " + e.getMessage());
        }
    }
}
