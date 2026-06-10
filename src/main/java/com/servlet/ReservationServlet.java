package com.servlet;

import com.model.ReservationRecord;
import com.service.ReservationRecordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
    private ReservationRecordService ReservationRecordService;

    /* public ReservationServlet() {
        this.ReservationService = new ReservationService();
    }*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reservingClub = request.getParameter("reservingClub");
        String venue = request.getParameter("venue");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        ReservationRecord record = new ReservationRecord();
        record.setReservingClub(reservingClub);
        record.setVenue(venue);
        record.setReservationTimeStart(startTime);
        record.setReservationTimeEnd(endTime);
        record.setReservationStatus("Pending"); // 初始状态

        boolean success = ReservationRecordService.addReservation(record);

        if (success) {
            response.getWriter().println("<script>alert('预约成功！');window.location='ReservationForm.jsp';</script>");
        } else {
            response.getWriter().println("<script>alert('预约失败！请稍后再试。');history.back();</script>");
        }
    }
}
