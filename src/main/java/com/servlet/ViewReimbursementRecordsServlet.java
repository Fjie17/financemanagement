package com.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.ReimbursementRecord;

@WebServlet("/ViewReimbursementRecordsServlet")
public class ViewReimbursementRecordsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ReimbursementRecord> records = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 数据库连接信息
            String url = "jdbc:mysql://localhost:3306/linyi";
            String user = "root";
            String password = "123456";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // 查询报销记录
            String sql = "SELECT * FROM Reimbursement_Records";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            // 封装结果集到 ReimbursementRecord 对象中
            while (rs.next()) {
                ReimbursementRecord record = new ReimbursementRecord();
                record.setReimbursementId(rs.getInt("Reimbursement_ID"));
                record.setReimbursingClub(rs.getString("Reimbursing_Club"));
                record.setReimbursementContent(rs.getString("Reimbursement_Content"));
                record.setReimbursementAmount(rs.getDouble("Reimbursement_Amount"));
                record.setReimbursementStatus(rs.getString("Reimbursement_Status"));
                record.setLastStatusUpdateTime(rs.getTimestamp("Last_Status_Update_Time"));
                record.setRecordCreationTime(rs.getTimestamp("Record_Creation_Time"));
                records.add(record);
            }

            // 将记录列表设置为请求属性
            request.setAttribute("records", records);

            // 转发到 JSP 页面
            request.getRequestDispatcher("ViewReimbursementRecords.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "数据加载失败: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
