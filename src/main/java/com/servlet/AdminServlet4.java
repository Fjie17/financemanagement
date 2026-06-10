package com.servlet;

import com.model.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminServlet4")
public class AdminServlet4 extends HttpServlet {
    private UserService userService;

    public AdminServlet4() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> adminUsers = userService.getAllAdmins();
        request.setAttribute("adminUsers", adminUsers);
        request.getRequestDispatcher("Admin_4.jsp").forward(request, response);
    }
}
