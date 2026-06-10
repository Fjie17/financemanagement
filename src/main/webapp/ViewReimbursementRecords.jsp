<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.model.ReimbursementRecord" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看报销记录</title>
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
                <p onclick="window.location.href='userDashboard.jsp'">HOME</p>
                <p>NEW</p>
                <p onclick="window.location.href='contact.jsp'">CONTACTS</p>
                <p class="user" onclick="window.location.href='UserInformation.jsp'">Welcome,${username}</p>
                <img src="user.png" alt="user">
                <p onclick="window.location.href='login.jsp'">LOGOUT</p>
            </div>
        </header>
        <main>
            <div class="user-info">
                <h2>报销记录</h2>
                <table border="1">
                    <tr>
                        <th>报销ID</th>
                        <th>报销社团</th>
                        <th>报销内容</th>
                        <th>报销金额</th>
                        <th>报销状态</th>
                        <th>最后状态更新时间</th>
                        <th>记录生成时间</th>
                    </tr>
                    <%
                        List<ReimbursementRecord> records = (List<ReimbursementRecord>) request.getAttribute("records");
                        if (records != null && !records.isEmpty()) {
                            for (ReimbursementRecord record : records) {
                    %>
                    <tr>
                        <td><%= record.getReimbursementId() %></td>
                        <td><%= record.getReimbursingClub() %></td>
                        <td><%= record.getReimbursementContent() %></td>
                        <td><%= record.getReimbursementAmount() %></td>
                        <td><%= record.getReimbursementStatus() %></td>
                        <td><%= record.getLastStatusUpdateTime() %></td>
                        <td><%= record.getRecordCreationTime() %></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <%
                        }
                    %>
                </table>
            </div>
        </main>
        <footer>
            <div class="footer-menu">
                <div class="footer-menu-left">
                    <p onclick="window.location.href='userDashboard.jsp'">HOME</p>
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