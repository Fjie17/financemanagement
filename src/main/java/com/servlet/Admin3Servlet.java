package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.service.UserService;

@WebServlet("/Admin_3")
public class Admin3Servlet extends HttpServlet {
    private UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询用户数据
        List<User> users = userService.getUsersByRole();

        // 将用户数据存储到请求属性中
        request.setAttribute("users", users);

        request.getRequestDispatcher("Admin_3.jsp").forward(request, response);
    }
}