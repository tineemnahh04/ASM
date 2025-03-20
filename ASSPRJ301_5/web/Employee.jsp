<%-- 
    Document   : Employee
    Created on : Mar 15, 2025, 4:08:34 AM
    Author     : admin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee</title>
        <style>
            body {
                background-color: #1e1e1e;
                color: #fff;
                font-family: Arial, sans-serif;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
                margin: 0;
            }
            .container {
                display: flex;
                flex-direction: column;
                gap: 15px;
                align-items: center;
            }
            .custom-button {
                background-color: #0aad4b;
                color: white;
                border: none;
                padding: 12px 24px;
                font-size: 16px;
                border-radius: 6px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .custom-button:hover {
                background-color: #088b3b;
            }
            .logout-button {
                background-color: #ff4444;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 14px;
                border-radius: 6px;
                cursor: pointer;
                position: absolute;
                top: 20px;
                right: 20px;
                transition: background-color 0.3s ease;
            }
            .logout-button:hover {
                background-color: #cc0000;
            }
            .custom-button span {
                display: flex;
                align-items: center;
                gap: 5px;
            }
        </style>
    </head>
    <body>
        <button class="logout-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/logout'">Logout</button>
        <div class="container">
            <button class="custom-button" onclick="window.location.href = 'Form.jsp'">
                <span> Tạo Đơn Xin Nghỉ Phép ➝</span>
            </button>
            <button class="custom-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/View'">
                <span> Xem Đơn ➝</span>
            </button>
        </div>
    </body>
</html>
