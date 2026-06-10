package com.servlet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.service.ClubBankInfoService;
import com.service.InvoiceAuthenticationService;
import com.service.ReimbursementApplicationService;
import com.util.DatabaseUtil;

@WebServlet("/SubmitReimbursementServlet")
public class SubmitReimbursementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clubName = request.getParameter("clubName");
        String content = request.getParameter("content");
        double amount = Double.parseDouble(request.getParameter("amount"));
        Part invoiceFile = request.getPart("invoiceFile");

        // 检查社团是否有银行卡信息
        ClubBankInfoService bankService = new ClubBankInfoService();
        boolean hasBankInfo = bankService.checkBankInfoExists(clubName);

        if (!hasBankInfo) {
            // 如果没有银行卡信息，跳转到添加银行卡界面
            request.setAttribute("clubName", clubName);
            request.getRequestDispatcher("AddBankInfo.jsp").forward(request, response);
            return;
        }

        // 保存报销申请到数据库
        ReimbursementApplicationService reimbursementService = new ReimbursementApplicationService();
        int reimbursementId = reimbursementService.saveReimbursementApplication(clubName, content, amount, invoiceFile);

        // 返回确认页面
        request.setAttribute("message", "报销申请提交成功，编号：" + reimbursementId);
        request.getRequestDispatcher("SubmitConfirmation.jsp").forward(request, response);
    }
}

