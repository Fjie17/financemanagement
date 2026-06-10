<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.model.User,com.service.UserService" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    UserService userService = new UserService();
    User user = userService.getUserById(id);
%>
<!DOCTYPE html>
<html>
<head>
    <title>修改用户信息</title>
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
            height: 8%;
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
            height: 10%;
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
            width: 100%;
            height: 82%;
            padding: 20px;
            flex: 1; /* 占据剩余空间 */
            overflow-y: auto; 
            background-color: #391484;
        }  
        .contact-form {
                position: absolute; 
                top: 50%; 
                left: 50%; 
                transform: translate(-50%, -50%);
                width: 830px; 
                height: 550px; 
                background-color:rgba(255, 255, 255, 0.2); 
                border-radius: 32px; 
                display: flex;
                flex-direction: column;
                align-items: center;
                }
                .text-box {
                    width: 830px; 
                    height: 120px; 
                } 
                .text-box h1 {
                    position: relative;
                    top: 50%; 
                    transform: translateY(-50%);
                    font-size: 36px; 
                    font-weight: bold; 
                    color: #ffffff; 
                    text-align: center; 
                    margin: 0; 
                }
            .input-box {
                display: flex; 
                flex-direction: column;
                justify-content: flex-start; 
                align-items: center; 
                width: 830px; 
                height: 500px; 
                background-color: transparent;
                text-align: center;
                font-size: 18px; 
                font-weight: bold;
            }
            .input-bottom {
                display: flex; 
                align-items: center;   
                flex-direction: column;
                justify-content: space-around;
                width: 830px; 
                height: 120px; 
                background-color: transparent;
                text-align: center;
                font-size: 18px; 
                font-weight: bold;
            }
            .input-item {
                width: 620px;
                height: 56px;
                background-color:rgba(231, 229, 234, 0.5);
                border-radius: 28px; /* 圆角半径 */
                margin-top: 30px;
                display: flex; /* 可选：设置内部居中 */
                align-items: center; /* 垂直居中 */
                justify-content: space-around; /* 水平居中 */
                box-sizing: border-box; /* 保证宽高包含边框 */
            }
            .input-item input {
                width: 600px; 
                height: 46px; /* 调整输入框高度 */
                background: transparent;
                border: none;
                color: #000; /* 输入框文字颜色 */
                font-size: 16px;
            }
            .input-item2 {
                width: 620px;
                height: 100px;
                background-color:rgba(231, 229, 234, 0.5);
                border-radius: 28px; /* 圆角半径 */
                margin-top: 30px;
                display: flex; /* 可选：设置内部居中 */
                align-items: center; /* 垂直居中 */
                justify-content: space-around; /* 水平居中 */
                box-sizing: border-box; /* 保证宽高包含边框 */
            }
            .input-item2 textarea {
                width: 600px; /* 减去左右 padding */
                height: 90px; /* 调整输入框高度 */
                background: transparent;
                border: none;
                color: #000; /* 输入框文字颜色 */
                font-size: 16px;
                font-family: auto;
            }

            .input-bottom button {
                display: flex;
                width: 410px;
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
            .input-bottom p {
                color:#fff;
                font-size: 16px;
            }

            .input-item-bottom {
                background-color:rgba(231, 229, 234, 0.5);
                border-radius: 28px; /* 圆角半径 */
                margin-top: 30px;
                box-sizing: border-box; /* 保证宽高包含边框 */
                width: 150px;
                height: 56px;
                display: flex;
                align-items: center; 
                justify-content: flex-start; 
                margin-top: 30px;
            }

            label {
                margin-left: 8px;
                margin-right: 8px; /* 设置适当的间距 */
                color: rgb(0, 0, 0);
            }
            .select-box {
                width: 110px;
                height: 56px;
                background-color:rgba(231, 229, 234, 0);
                border-radius: 28px; /* 圆角半径 */
            }

            .select-box select {
                width: 80%; /* 使select最大宽度填满容器 */
                height: 100%; 
                background: transparent;
                border: none;
                color: #000; /* 输入框文字颜色 */
                font-size: 16px;
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
                <p onclick="window.location.href='adminDashboard.jsp'">HOME</p>
                <p>NEW</p>
                <p onclick="window.location.href='contact.jsp'">CONTACTS</p>
                <p class="user" onclick="window.location.href='UserInformation.jsp'">Welcome,${username}</p>
                <img src="user.png" alt="user">
                <p onclick="window.location.href='login.jsp'">LOGOUT</p>
            </div>
        </header>
        <main>
            <form  class="contact-form" action="UpdateUserServlet" method="post">
                <div class="text-box">
                    <h1>修改用户信息</h1>
                </div>
                <div class="input-box">
                    <div class="input-item">
                        <input type="text" name="username" value="${user.username}" placeholder="请输入用户名" required>
                    </div>
                    <div class="input-item">
                        <input type="password" name="password" value="" placeholder="请输入密码" required>
                    </div>
                    <div class="input-item">
                        <input type="email" name="email" value="${user.email}" placeholder="请输入邮箱" required>
                    </div>
                    <div class="input-item-bottom">
                        <label>Role:</label>
                        <select name="role">
                            <option value="user" ${user.role == 'user' ? 'selected' : ''}>普通用户</option>
                            <option value="finance" ${user.role == 'finance' ? 'selected' : ''}>财务人员</option>
                            <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>管理员</option>
                        </select>
                    </div>
                    <div class="input-bottom">
                        <button type="submit">Submit</button>
                    </div>
                </div>
            </form>

        </main>
        <footer>
            <div class="footer-menu">
                <div class="footer-menu-left">
                    <p onclick="window.location.href='adminDashboard.jsp'">HOME</p>
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