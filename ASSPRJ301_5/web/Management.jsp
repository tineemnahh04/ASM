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
              h2 {
                color: #0aad4b;
                text-align: center;
                margin-bottom: 20px;
                text-shadow: 0 0 5px rgba(0, 255, 0, 0.5);
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
        <button class="logout-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/logout'">
            Logout
        </button>
        <div class="container">
            <h2>Xin chào, ${account.username}</h2>
            <button class="custom-button" onclick="window.location.href = 'Form.jsp'">
                Tạo Đơn Xin Nghỉ Phép ➝
            </button>
            <button class="custom-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/View'">
                Xem Đơn Của Tôi ➝
            </button>
            <button class="custom-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/Management'">
                Xét Duyệt Đơn Nhân Viên ➝
            </button>
        </div>
    </body>
</html>
