<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>场地预约</title>
    </head>
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

            .login-form {
                position: absolute; 
                top: 50%; 
                left: 50%; 
                transform: translate(-50%, -50%);
                width: 540px; 
                height: 540px; 
                background-color: rgba(255, 255, 255, 0.2); 
                border-radius: 32px; 
                }
            .title {
                display: flex; 
                justify-content: center; 
                align-items: center; 
                width: 540px; 
                height: 100px; 
                background-color: transparent;
                color: rgba(255, 255, 255, 1);
                text-align: center;
                font-size: 24px; 
                font-weight: bold;
            }
            .vertical-line {
                width: 100%; 
                height: 2px; 
                background-color: black;
            }
            .input-box {
                display: flex; 
                flex-direction: column;
                justify-content: flex-start; 
                align-items: center; 
                width: 540px; 
                height: 310px; 
                background-color: transparent;
                text-align: center;
                font-size: 18px; 
                font-weight: bold;
            }
            .input-bottom {
                display: flex; 
                align-items: center;   
                flex-direction: column;
                justify-content: flex-start;
                width: 540px; 
                height: 130px; 
                background-color: transparent;
                text-align: center;
                font-size: 18px; 
                font-weight: bold;
            }
            .input-item {
                width: 410px;
                height: 56px;
                background-color:rgba(231, 229, 234, 0.5);
                border-radius: 28px; /* 圆角半径 */
                margin-top: 15px;
                display: flex; /* 可选：设置内部居中 */
                align-items: center; /* 垂直居中 */
                justify-content: space-around; /* 水平居中 */
                box-sizing: border-box; /* 保证宽高包含边框 */
            }
            .input-item img {
                position: relative;
                width: 24px;
                height: 24px;
                margin-left: 10px;
                opacity: 1;
            }
            .input-item input {
                width: 320px; /* 减去左右 padding */
                height: 36px; /* 调整输入框高度 */
                background: transparent;
                border: none;
                color: #000; /* 输入框文字颜色 */
                font-size: 16px;
            }
            .input-item-bottom {
                width: 410px;
                height: 56px;
                display: flex;
                align-items: center; 
                justify-content: flex-start; 
                margin-top: 15px;
            }

            label {
                margin-left: 8px;
                margin-right: 8px; /* 设置适当的间距 */
                color: white;
            }
            .select-box {
                width: 100px;
                height: 56px;
                background-color:rgba(231, 229, 234, 0.5);
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

            .input-bottom a {
                margin-top: 15px;
                color:#391484;
                font-size: 16px;
            }
            .title {
                width: 540px;
                height: 60px;
            }



        </style>
        <div class="form" >
            <img class="Bcakground" alt="background" src="loginbackground.png" />
            <div class="logo">
                <img src="logo.png" alt="logo">
            </div>
            <form class="login-form" action="ReservationServlet" method="post">
                <h1 class="title">提交预约申请</h1>
                <div class="input-box">
                    <div class="input-item">
                        <input type="text" placeholder="预约社团用户名" name="reservingClub" required>
                    </div>
                    <div class="input-item">
                        <input type="text" placeholder="预约地点" name="venue" required>
                    </div>
                    <div class="input-item">
                        <input type="datetime-local" placeholder="预约开始时间" name="startTime" required>
                    </div>
                    <div class="input-item">
                        <input type="datetime-local" placeholder="预约结束时间" name="endTime" required>
                    </div>
                </div>
                <div class="input-bottom">
                    <button type="submit">Submit</button>
                    <p onclick="window.location.href='userDashboard.jsp'">Back</p>
                </div>
            </form>
        </div>
    </body>
</html>