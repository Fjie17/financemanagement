package com.servlet;

import com.model.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserInformation")
public class UserInformationServlet extends HttpServlet {
    private UserService userService;

    public UserInformationServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从 session 获取当前登录用户名
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            // 通过用户名查询用户信息
            User user = userService.getUserByUsername(username);
            if (user != null) {
                // 将用户信息传递到 JSP
                request.setAttribute("user", user);
                request.getRequestDispatcher("/UserInformation.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp"); // 若用户信息不存在，跳转到登录页面
            }
        } else {
            response.sendRedirect("login.jsp"); // 未登录则跳转到登录页面
        }
    }
}
