package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.service.UserService;

public class UpdateUserServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Assume password is hashed
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);

        boolean success = userService.updateUser(user);

        if (success) {
            response.getWriter().println("<script>alert('用户信息修改成功！');window.location='Admin_3.jsp';</script>");
        } else {
            response.getWriter().println("<script>alert('用户信息修改失败！');history.back();</script>");
        }
    }
}
