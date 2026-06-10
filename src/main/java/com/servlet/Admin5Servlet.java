package com.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Suggestion;
import com.service.Admin5Service;

@WebServlet("/Admin5")
public class Admin5Servlet extends HttpServlet {
    private Admin5Service admin5Service;

    public Admin5Servlet() {
        admin5Service = new Admin5Service();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Suggestion> suggestions = admin5Service.getAllSuggestions();
        request.setAttribute("suggestions", suggestions);
        request.getRequestDispatcher("/Admin_5.jsp").forward(request, response);
    }
}
