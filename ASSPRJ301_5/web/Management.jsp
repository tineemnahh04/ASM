<%-- 
    Document   : Management
    Created on : Mar 15, 2025, 4:06:33 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager</title>
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #222;
                color: white;
                flex-direction: column;
            }
            .container {
                display: flex;
                flex-direction: column;
                align-items: center;
                gap: 15px;
            }
            .custom-button {
                background-color: #00C853;
                color: white;
                border: none;
                padding: 15px 30px;
                font-size: 18px;
                cursor: pointer;
                border-radius: 5px;
                transition: 0.3s;
            }
            .custom-button:hover {
                background-color: #009624;
            }
            .logout-button {
                position: absolute;
                top: 20px;
                right: 20px;
                background-color: #FF5252;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                border-radius: 5px;
                transition: 0.3s;
            }
            .logout-button:hover {
                background-color: #D32F2F;
            }
        </style>
    </head>
    <body>
        <button class="logout-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/logout'">Logout</button>
        <div class="container">
            <button class="custom-button" onclick="window.location.href = 'Form.jsp'">
                <span>
                    <span class="icon"></span> Tạo Đơn Xin Nghỉ Phép <span>➝</span>
                </span>
                <button class="custom-button" onclick="window.location.href='http://localhost:8080/ASSPRJ301_5/View'">
                    <span>
                        <span class="icon">📋</span> Xem Đơn Của Tôi<span>➝</span>
                            
                    </span>
                </button>
                <button class="custom-button" onclick="window.location.href='http://localhost:8080/ASSPRJ301_5/Management'">
                    <span>
                        <span class="icon"></span> Xét Duyệt Đơn Nhân Viên <span>➝</span>
                    </span>
                </button>
        </div>
    </body>
</html>
