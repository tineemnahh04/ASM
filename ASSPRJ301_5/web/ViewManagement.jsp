<%-- 
    Document   : FormManager
    Created on : Mar 17, 2025, 12:36:52 AM
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách đơn cần xét duyệt</title>
        <style>
            body {
                background-color: #000;
                color: #fff;
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                flex-direction: column;
            }
            .container {
                background: #121212;
                padding: 30px;
                border-radius: 10px;
                text-align: center;
                width: 80%;
            }
            h2 {
                margin-bottom: 10px;
                color: #1ed760;
                text-shadow: 0 0 5px rgba(0, 255, 0, 0.7);
                font-size: 35px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
                background: #1a1a1a;
                border-radius: 15px;
                overflow: hidden;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.7);
            }
            th, td {
                padding: 10px;
                border-bottom: 1px solid #333;
                text-align: left;
            }
            th {
                background: linear-gradient(145deg, #1ed760, #13a148);
                color: white;
                font-size: 20px;
                text-shadow: 0 0 6px rgba(0, 255, 0, 0.8);
            }
            td {
                color: #b3ffb3;
            }
            .back-button {
                position: absolute;
                top: 20px;
                left: 20px;
                background-color: #FF5252;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                border-radius: 5px;
                transition: all 0.3s;
            }
            .back-button:hover {
                background-color: #E53935;
            }
            .action-button {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                font-size: 14px;
                font-weight: bold;
                cursor: pointer;
                transition: all 0.3s;
                margin: 5px;
                display: block;
                width: 100%;
            }
            .approve {
                background-color: #1db954;
                color: white;
            }
            .approve:hover {
                background-color: #17a344;
            }
            .reject {
                background-color: #FF5252;
                color: white;
            }
            .reject:hover {
                background-color: #E53935;
            }
            .action-cell {
                display: flex;
                justify-content: space-around;
            }
        </style>
    </head>
    <body>
        <!-- Nút quay lại -->
        <button class="back-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/Home'">
            Back
        </button>
        <div class="container">
            <h2>Danh sách đơn cần xét duyệt</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ngày tạo</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th>Lý do</th>
                        <th>Trạng thái</th>
                        <th>Id nhân viên</th>
                        <th>Tên nhân viên</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="req" items="${requests}">
                        <c:if test="${req.getStatus() eq 'Inprogress'}">
                            <tr>
                                <td>${req.getId()}</td>
                                <td style="white-space: nowrap;">${req.getDateCreate()}</td>
                                <td style="white-space: nowrap;">${req.getDateFrom()}</td>
                                <td style="white-space: nowrap;">${req.getDateTo()}</td>
                                <td>${req.getReason()}</td>
                                <td>${req.getStatus()}</td>
                                <td>${req.geteId()}</td>
                                <td>${req.geteName()}</td>
                                <td>
                                    <!-- Form Approve -->
                                    <form action="Management" method="post" style="display: inline;">
                                        <input type="hidden" name="requestId" value="${req.getId()}"/>
                                        <input type="submit" name="action" value="Approve"
                                               class="action-button approve" />
                                    </form>
                                    <!-- Form Reject -->
                                    <form action="Management" method="post" style="display: inline;">
                                        <input type="hidden" name="requestId" value="${req.getId()}"/>
                                        <input type="submit" name="action" value="Reject"
                                               class="action-button reject" />
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
