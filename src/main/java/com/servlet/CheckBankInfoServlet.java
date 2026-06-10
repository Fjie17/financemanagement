package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DatabaseUtil;

@WebServlet("/CheckBankInfoServlet")
public class CheckBankInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clubName = request.getParameter("clubName");

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Club_Bank_Info WHERE Club_Name = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, clubName);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // 已绑定银行卡信息，跳转到提交报销申请逻辑
                        request.getRequestDispatcher("SubmitReimbursementServlet").forward(request, response);
                    } else {
                        // 未绑定银行卡信息，跳转到添加银行卡页面
                        response.sendRedirect("AddBankInfo.jsp?clubName=" + clubName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("SubmitReimbursementForm.jsp?status=error");
        }
    }
}
