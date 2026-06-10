package com.servlet;

import com.model.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        UserService userService = new UserService(conn);

        try {
            // 验证用户名、密码和身份是否匹配
            User user = userService.authenticateWithRole(username, password, role);
            if (user != null) {
                // 如果验证成功，跳转到相应界面
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("username", user.getUsername());


                switch (role) {
                    case "user":
                        response.sendRedirect("userDashboard.jsp");
                        break;
                    case "finance":
                        response.sendRedirect("financeDashboard.jsp");
                        break;
                    case "admin":
                        response.sendRedirect("adminDashboard.jsp");
                        break;
                    default:
                        response.sendRedirect("login.jsp?error=unknown_role");
                }
            } else {
                // 用户名或密码正确，但身份不匹配
                User partialUser = userService.authenticate(username, password);
                if (partialUser != null) {
                    response.sendRedirect("login.jsp?error=role_mismatch");
                } else {
                    // 用户名或密码错误
                    response.sendRedirect("login.jsp?error=invalid_credentials");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=server_error");
        }
    }

    @Override
    public void destroy() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
