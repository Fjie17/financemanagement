package com.servlet;


import com.model.ReservationRecord;
import com.service.ReservationRecordService1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewReservationRecords")
public class ViewReservationRecordsServlet extends HttpServlet {
    private ReservationRecordService1 recordService = new ReservationRecordService1();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前登录用户的用户名
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            // 获取当前用户的场地预约记录
            List<ReservationRecord> records = recordService.getRecordsByUsername(username);

            // 将记录传递给 JSP
            request.setAttribute("reservationRecords", records);
            request.getRequestDispatcher("ViewReservationRecords.jsp").forward(request, response);
        } else {
            // 如果用户未登录，重定向到登录页面
            response.sendRedirect("login.jsp");
        }
    }
}
