package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import com.model.ReimbursementRecord;
import com.service.ReimbursementService;

@WebServlet("/admin2")
public class Admin2Servlet extends HttpServlet {
    private Connection conn;

    
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/linyi", "root", "123456");
        } catch (Exception e) {
            throw new ServletException("Database connection failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ReimbursementService service = new ReimbursementService(conn);
        List<ReimbursementRecord> records1 = service.getAllReimbursementRecords();

        // 将记录传递给 JSP
        request.setAttribute("records1", records1);
        request.getRequestDispatcher("Admin_2.jsp").forward(request, response);
    }
    
    @Override
    public void destroy() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
