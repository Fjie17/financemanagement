<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>审核报销表</title>
<style>
        body,html {
            margin: 0; /* 清除默认外边距 */
            padding: 0; /* 清除默认内边距 */
            width: 100%; /* 设置宽度为 100% */
            height: 100%; /* 设置高度为 100% */
            overflow: hidden; /* 防止溢出 */
        }
        body img {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%; /* 撑满容器宽度 */
            height: 100%; /* 撑满容器高度 */
            object-fit: cover; /* 确保图片保持比例填充整个容器 */
            opacity: 0.2; /* 设置透明度为 20% */
            pointer-events: none; /* 确保图片不影响鼠标交互 */
        }
        header {
            margin: 0;
            width: 100%;
            height: 80px;
            background-color: #391484;
            color: white;
            display: flex;
            padding: 20px;
            text-align: center;
            font-weight: bold;
        }
        .logo {
                position: absolute;
                top: 40px;
                left: 33px;
                width: 90px;
                height: 30px;
                flex-shrink: 0;
            }
            .logo img {
                opacity: 1;
            }
        .menu {
            height: 48px;
            width: 630px;
            display: flex;

            right: 20px;
            position: absolute;
            top: 40px;
        }
       .menu p {
            width: 100px;
            height: 48px;
            text-align: center;
            align-items: center;
       }
       .menu p.user {
            width: 350px;
            height: 48px;
            text-align: center;
            align-items: center;
       }
       .menu img {
            width: 48px;
            height: 48px;
            opacity: 1;
            position: relative;
       }
        footer {
            background-color: #391484;
            color: white;
            padding: 10px;
            text-align: center;
            position: fixed;
            width: 100%;
            height: 120px;
            bottom: 0;
            display: flex;
        }
        .footer-menu {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            height: 80px;
        }
        .footer-menu p {
            width: 100px;
            height: 48px;
            text-align: center;
            font-weight: bold;
        }
       .footer-menu-left {
            display: flex;
            align-items: center;
            height: 80px;
        }
       .footer-menu-right {
            display: flex;
            height: 80px;
        }
       .footer-menu-right img {
            width: 48px;
            height: 48px;
            margin-right: 10px;
            position: relative;
            opacity: 1;
        }
        .footer-bottom {
            width: 100%;
            height: 40px;
            position: absolute;
            bottom: 20px;
        }
        .footer-bottom p {
            color: white;
            width: 100%;
            bottom: 10px;
            font-size: 16px;
            text-align: center;
        }

        main {
            padding: 20px;
            min-height: calc(100vh - 80px); /* Adjust to leave space for header and footer */
            box-sizing: border-box;
            overflow-x: auto;
        }
        h1 {
            text-align: center;
            margin-top: 50px;
        }
        table {
            margin: 50px auto;
            border-collapse: collapse;
            width: 80%;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }
        .user-info {
            width: 80%;
            margin: 50px auto;
        }
        .user-bottom {
            width: 80%;
            margin: 50px auto;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .user-bottom button {
                display: flex;
                width: 120px;
                height: 56px;
                background-color: #FFDDA9;
                border-radius: 28px;
                justify-content: center;
                align-items: center;
                flex-shrink: 0;
                color: #12022F;
                font-size: 18px;
                font-weight: bold;
            }

    </style>
</head>
    <body>
        <img class="Bcakground" alt="background" src="loginbackground.png" />
        <header>
            <div class="logo">
                <img src="logo.png" alt="logo">
            </div>
            <div class="menu">
                <p onclick="window.location.href='financeDashboard.jsp'">HOME</p>
                <p>NEW</p>
                <p onclick="window.location.href='contact.jsp'">CONTACTS</p>
                <p class="user" onclick="window.location.href='UserInformation.jsp'">Welcome,${username}</p>
                <img src="user.png" alt="user">
                <p onclick="window.location.href='login.jsp'">LOGOUT</p>
            </div>
        </header>
        <main>
            <div class="user-info">
                <h1>审核报销记录</h1>

                <%
                    // 数据库连接信息
                    String dbUrl = "jdbc:mysql://localhost:3306/linyi";
                    String dbUser = "root";
                    String dbPassword = "123456";
                    
                    Connection connection = null;
                    Statement statement = null;
                    ResultSet rs = null;
                    
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                        
                        // 查询报销记录
                        String sql = "SELECT * FROM Reimbursement_Records";
                        statement = connection.createStatement();
                        rs = statement.executeQuery(sql);
                %>
                <table>
                    <thead>
                        <tr>
                            <th>报销ID</th>
                            <th>报销社团</th>
                            <th>报销内容</th>
                            <th>报销金额</th>
                            <th>报销状态</th>
                            <th>上次状态更新时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            while (rs.next()) {
                        %>
                        <tr>
                            <td><%= rs.getInt("Reimbursement_ID") %></td>
                            <td><%= rs.getString("Reimbursing_Club") %></td>
                            <td><%= rs.getString("Reimbursement_Content") %></td>
                            <td><%= rs.getBigDecimal("Reimbursement_Amount") %></td>
                            <td><%= rs.getString("Reimbursement_Status") %></td>
                            <td><%= rs.getTimestamp("Last_Status_Update_Time") %></td>
                            <td class="action-buttons">
                                <form action="ProcessReimbursementApprovalServlet" method="post" style="display: inline;">
                                    <input type="hidden" name="reimbursementId" value="<%= rs.getInt("Reimbursement_ID") %>">
                                    <button type="submit" name="status" value="Approved by Finance">通过</button>
                                </form>
                                <form action="ProcessReimbursementApprovalServlet" method="post" style="display: inline;">
                                    <input type="hidden" name="reimbursementId" value="<%= rs.getInt("Reimbursement_ID") %>">
                                    <button type="submit" name="status" value="Rejected by Finance">拒绝</button>
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
    <%
        } catch (Exception e) {
            out.println("<p>发生错误：" + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    %>
    </main>
        <footer>
            <div class="footer-menu">
                <div class="footer-menu-left">
                    <p onclick="window.location.href='financeDashboard.jsp'">HOME</p>
                    <p>NEW</p>
                </div>
                <div class="footer-menu-right">
                    <p onclick="window.location.href='contact.jsp'">CONTACTS</p>
                    <img src="user.png" alt="user">
                </div>
            </div>
            <div class="footer-bottom">
                <p>&copy; 2024 My Website. All rights reserved.</p>
            </div>
        </footer>
</body>
</html>
