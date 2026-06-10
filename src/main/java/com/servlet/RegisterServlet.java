package com.servlet;

import com.model.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从表单中获取用户输入的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role"); // 获取用户选择的角色

        // 检查必填字段是否为空
        if (username == null || username.isEmpty() || 
            password == null || password.isEmpty() || 
            email == null || email.isEmpty() || 
            role == null || role.isEmpty()) {
            response.getWriter().println("<script>alert('所有字段均为必填项！');history.back();</script>");
            return;
        }

        // 验证角色是否合法
        if (!role.equals("user") && !role.equals("finance") && !role.equals("admin")) {
            response.getWriter().println("<script>alert('非法的角色选择！');history.back();</script>");
            return;
        }

        // 创建 User 对象
        User user = new User();
        user.setUsername(username);

        // 使用加密存储密码（例如 BCrypt 加密）
        //String encryptedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        user.setPassword(password);

        user.setEmail(email);
        user.setRole(role); // 设置用户角色

        // 调用 UserService 的 register 方法
        UserService userService = new UserService(conn);
        boolean success = userService.register(user);
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (success) {
            // 注册成功，弹出提示并返回注册页面
            response.getWriter().println("<script>alert('注册成功！');window.location='login.jsp';</script>");
        } else {
            // 注册失败，弹出提示并返回注册页面
            response.getWriter().println("<script>alert('注册失败！用户名或邮箱已存在！');history.back();</script>");
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
