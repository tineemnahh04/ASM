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
        <title>JSP Page</title>
    </head>
    <body>
        <button class="logout-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/logout'">Logout</button>
        <div class="container">
            <button class="custom-button" onclick="window.location.href='http://localhost:8080/ASSPRJ301_5/Management'">
                <span>
                    <span class="icon"></span> Xét Duyệt Đơn Manager <span>➝</span>
                </span>
            </button>
            <button class="custom-button" onclick="window.location.href = 'Agenda.jsp'">
                <span>
                    <span class="icon"></span> View Agenda <span>➝</span>
                </span>
        </div>
    </body>
</html>
