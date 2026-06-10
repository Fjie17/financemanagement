package com.servlet;

import com.model.ReservationRecord;
import com.service.ReservationRecordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/admin1")
public class Admin1Servlet extends HttpServlet {
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
        ReservationRecordService service = new ReservationRecordService(conn);
        List<ReservationRecord> records = service.getAllRecords();

        // 将记录传递给 JSP
        request.setAttribute("records", records);
        request.getRequestDispatcher("Admin_1.jsp").forward(request, response);
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
