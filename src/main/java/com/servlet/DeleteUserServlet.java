package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("id"); // 从请求中获取用户ID
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            // 数据库连接信息
            String url = "jdbc:mysql://localhost:3306/linyi";
            String user = "root";
            String password = "123456";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // 删除用户 SQL 语句
            String sql = "DELETE FROM users WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(userId)); // 设置用户ID到 SQL 语句中

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // 删除成功后，跳转回用户列表页面
                response.sendRedirect("UserListServlet");
            } else {
                // 若用户未被删除，显示错误信息
                request.setAttribute("error", "用户删除失败，请重试！");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "系统错误: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
