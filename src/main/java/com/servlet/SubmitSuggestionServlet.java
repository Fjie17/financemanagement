package com.servlet;

import com.model.Suggestion;
import com.service.SuggestionService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubmitSuggestionServlet")
public class SubmitSuggestionServlet extends HttpServlet {
    private SuggestionService suggestionService = new SuggestionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据
        String username = request.getParameter("username");
        String content = request.getParameter("content");

        // 封装成 Suggestion 对象
        Suggestion suggestion = new Suggestion();
        suggestion.setClubName(username);
        suggestion.setContent(content);

        // 调用服务层保存到数据库
        boolean success = suggestionService.addSuggestion(suggestion);

        // 根据结果跳转页面
        response.setContentType("text/html; charset=UTF-8");
        
        if (success) {
            response.getWriter().println("<script>alert('建议提交成功！');window.location='contact.jsp';</script>");
        } else {
            response.getWriter().println("<script>alert('建议提交失败，请重试！');history.back();</script>");
        }
    }
}
