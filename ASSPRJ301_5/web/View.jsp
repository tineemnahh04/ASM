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
                border-radius: 10px;
                text-align: center;
                width: 80%;
            }
            h2 {
                margin-bottom: 10px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
                background: #222;
                border-radius: 5px;
            }
            th, td {
                padding: 10px;
                border-bottom: 1px solid #333;
                text-align: left;
            }
            th {
                background: #1db954;
            }
            .status-inprogress {
                color: yellow;
            }
            .status-rejected {
                color: red;
            }
            a {
                color: #1db954;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
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
            }
            .back-button:hover {
                background-color: #E53935;
            }

        </style>
    </head>
    <body>
        <button class="btn-back" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/Home'">
            Quay lại
        </button>
        <div class="container">
            <h2>Danh sách đơn nghỉ phép</h2>
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Created By</th>
                        <th>Status</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="request" items="${requests}">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${request.getStatus() eq 'Inprogress'}">
                                        <a href="detail?id=${request.getId()}">
                                            ${request.getReason()}
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        ${request.getReason()}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td style="white-space: nowrap;">${request.getDateFrom()}</td>
                            <td style="white-space: nowrap;">${request.getDateTo()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${request.getStatus() eq 'Inprogress'}">
                                        <span class="status-pending">Inprogess</span>
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
                                <c:if test="${request.getStatus() eq 'Inprogess'}">
                                    <div class="action-buttons">
                                        <a href="${pageContext.request.contextPath}/Edit?id=${request.getId()}">Edit</a>
                                        <span>|</span>
                                        <a href="${pageContext.request.contextPath}/Delete?id=${request.getId()}" 
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa đơn này?');">
                                            Delete
                                        </a>
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
