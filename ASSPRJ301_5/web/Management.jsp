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
        <title>JSP Page</title>
    </head>
    <body>
        <button class="logout-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/logout'">Logout</button>
        <div class="container">
            <button class="custom-button" onclick="window.location.href = 'Form.jsp'">
                <span>
                    <span class="icon">✨</span> Tạo Đơn Xin Nghỉ Phép <span>➝</span>
                </span>
            </button>
            <button class="custom-button" onclick="window.location.href = 'View.jsp'">
                <span>
                    <span class="icon">✨</span> Xem Đơn <span>➝</span>
                </span>
            </button>
            <button class="custom-button" onclick="window.location.href = 'Accept.jsp'">
                <span>
                    <span class="icon">✨</span> Xét Duyệt Đơn <span>➝</span>
                </span>
            </button>
        </div>
    </body>
</html>
