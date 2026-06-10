<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Us</title>
</head>
<body>
	<body>
        <style>
            .html, body {
                margin: 0; /* 清除默认外边距 */
                padding: 0; /* 清除默认内边距 */
                width: 100%; /* 设置宽度为 100% */
                height: 100%; /* 设置高度为 100% */
                overflow: hidden; /* 防止溢出 */
            }
            .form {
                position: relative;
                top: 0; 
                left: 0; 
                width: 100vw; 
                height: 100vh;
                background-color: #391484;
                overflow: hidden; 
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .form img{
                position: absolute;
                top: 0;
                left: 0;
                width: 100%; /* 撑满容器宽度 */
                height: 100%; /* 撑满容器高度 */
                object-fit: cover; /* 确保图片保持比例填充整个容器 */
                opacity: 0.2; /* 设置透明度为 20% */
                pointer-events: none; /* 确保图片不影响鼠标交互 */
            }

            .logo {
                position: absolute;
                top: 20px;
                left: 33px;
                width: 90px;
                height: 30px;
                flex-shrink: 0;
            }
            .logo img {
                opacity: 1;
            }

            .contact-form {
                position: absolute; 
                top: 50%; 
                left: 50%; 
                transform: translate(-50%, -50%);
                width: 830px; 
                height: 640px; 
                background-color: rgba(255, 255, 255, 0.2); 
                border-radius: 32px; 
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
                    color: #fff; 
                    text-align: center; 
                    margin: 0; 
                }
            .input-box {
                display: flex; 
                flex-direction: column;
                justify-content: flex-start; 
                align-items: center; 
                width: 830px; 
                height: 400px; 
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
                height: 36px; /* 调整输入框高度 */
                background: transparent;
                border: none;
                color: #000; /* 输入框文字颜色 */
                font-size: 16px;
            }
            .input-item2 {
                width: 620px;
                height: 300px;
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
                height: 260px; /* 调整输入框高度 */
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



        </style>
        <div class="form">
            <img class="Bcakground" alt="background" src="loginbackground.png" />
            <div class="logo">
                <img src="logo.png" alt="logo">
            </div>
            <form class="contact-form" action="SubmitSuggestionServlet" method="post">
                <div class="text-box">
                    <h1>Get Touch With Us</h1>
                </div>
                
                <div class="input-box">
                    <div class="input-item">
                        <input type="text" placeholder="Your Username" name="username" required>
                    </div>
                    <div class="input-item2">
                        <textarea type="text" placeholder="Your message" name="message" required></textarea>
                    </div>
                </div>
                <div class="input-bottom">
                    <button type="submit">Send a message</button>
                    <p onclick="window.location.href='userDashboard.jsp'">Back</p>
                </div>
            </form>
        </div>
    </body>
</body>
</html>