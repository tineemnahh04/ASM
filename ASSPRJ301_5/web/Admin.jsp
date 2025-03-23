<%-- 
    Document   : Admin
    Created on : Mar 15, 2025, 4:11:44 AM
    Author     : admin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <style>
            body {
                background-color: #1e1e1e;
                color: white;
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
            h2 {
                color: #0aad4b;
                text-align: center;
                margin-bottom: 20px;
                text-shadow: 0 0 5px rgba(0, 255, 0, 0.5);
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
            .back-button {
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
            }
            .back-button:hover {
                background-color: #E53935;
            }
        </style>
    </head>
    <body>
        <button class="back-button" onclick="window.location.href = '${pageContext.request.contextPath}/logout'">Logout</button>
        <div class="container">
            <h2>Xin chào, ${account.username}</h2>
            <button class="custom-button" onclick="window.location.href = '${pageContext.request.contextPath}/Management'">
                Xét Duyệt Đơn của Manager ➝
            </button>
            <button class="custom-button" onclick="window.location.href = 'Agenda.jsp'">
                Xem Lịch Làm Việc ➝
            </button>
        </div>
    </body>
</html>
