<%-- 
   Document   : View
   Created on : Mar 15, 2025, 4:05:56 AM
   Author     : admin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách đơn nghỉ phép</title>
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
                border-radius: 15px;
                text-align: center;
                width: 80%;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.8);
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
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #333;
            }
            th {
                background: linear-gradient(145deg, #1ed760, #13a148);
                color: white;
                font-size: 20px;
                text-shadow: 0 0 6px rgba(0, 255, 0, 0.8), 0 0 12px rgba(0, 255, 0, 0.5);
            }
            td {
                color: #b3ffb3;
            }
            td a {
                color: #1db954;
                text-decoration: none;
                font-weight: bold;
                transition: all 0.3s;
            }
            td a:hover  {
                text-shadow: 0 0 5px rgba(0, 255, 0, 0.8); /* Hiệu ứng phát sáng khi hover */
            }
            /* Nút Edit (vẫn giữ màu xanh) */
            .action-buttons a.edit {
                color: #1db954;
                text-decoration: none;
                font-weight: bold;
                transition: all 0.3s;
            }
            .action-buttons a.edit:hover {
                text-shadow: 0 0 5px rgba(0, 255, 0, 0.8);
            }
            /* Nút Delete (màu đỏ) */
            .action-buttons a.delete {
                color: #FF5252;
                text-decoration: none;
                font-weight: bold;
                transition: all 0.3s;
            }
            .action-buttons a.delete:hover {
                text-shadow: 0 0 5px rgba(255, 82, 82, 0.8);
            }

            .status-inprogress {
                color: yellow;
                font-weight: bold;
            }
            .status-rejected {
                color: red;
                font-weight: bold;
            }
            .status-approved {
                color: lightgreen;
                font-weight: bold;
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
        </style>
    </head>
    <body>
        <button class="back-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/Home'"> Quay lại </button>
        <div class="container">
            <h2>Danh sách đơn nghỉ phép</h2>
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Create By</th>
                        <th>Status</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="request" items="${requests}">
                        <tr>
                            <!-- Title -->
                            <td>
                                <c:choose>
                                    <c:when test="${request.getStatus() eq 'Inprogress'}">
                                        <a href="detail?id=${request.getId()}">${request.getReason()}</a>
                                    </c:when>
                                    <c:otherwise>
                                        ${request.getReason()}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <!-- From Date -->
                            <td style="white-space: nowrap;">${request.getDateFrom()}</td>
                            <!-- To Date -->
                            <td style="white-space: nowrap;">${request.getDateTo()}</td>
                            <!-- Create By -->
                            <td>${request.dateCreate}</td>
                            <!-- Status -->
                            <td>
                                <c:choose>
                                    <c:when test="${request.getStatus() eq 'Inprogress'}">
                                        <span class="status-inprogress">Inprogress</span>
                                    </c:when>
                                    <c:when test="${request.getStatus() eq 'Rejected'}">
                                        <span class="status-rejected">Rejected</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status-approved">${request.getStatus()}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:if test="${request.getStatus() eq 'Inprogress'}">
                                    <div class="action-buttons">
                                        <a href="${pageContext.request.contextPath}/Edit?id=${request.getId()}" class="edit">Edit</a>
                                        <span> | </span>
                                        <a href="${pageContext.request.contextPath}/Delete?id=${request.getId()}" 
                                           class="delete"
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa đơn này?');">Delete</a>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
