package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.util.DatabaseUtil;

@WebServlet("/UploadInvoiceServlet")
public class UploadInvoiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invoiceName = request.getParameter("invoiceName");
        Part filePart = request.getPart("file");

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String filePath = "uploaded_files/" + fileName; // 本地保存路径
        File uploads = new File("E:/uploaded_files/");  // 文件保存目录
        if (!uploads.exists()) {
            uploads.mkdirs();
        }

        try (InputStream fileContent = filePart.getInputStream()) {
            File file = new File(uploads, fileName);
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            try (Connection connection = DatabaseUtil.getConnection()) {
                String sql = "INSERT INTO Invoice_File (File_Name, File_Path) VALUES (?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, invoiceName);
                    ps.setString(2, filePath);
                    ps.executeUpdate();
                }
            }

            response.sendRedirect("SubmitReimbursementForm.jsp?status=upload_success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("SubmitReimbursementForm.jsp?status=upload_error");
        }
    }
}
