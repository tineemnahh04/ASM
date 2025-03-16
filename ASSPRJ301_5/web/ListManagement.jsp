<%-- 
    Document   : FormManager
    Created on : Mar 17, 2025, 12:36:52 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <div class="container">
            <button class="back-button" onclick="window.location.href = 'Management.jsp'">Quay lại</button>
            <h2>Danh Sách Đơn Nghỉ Phép</h2>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nhân Viên</th>
                    <th>Từ Ngày</th>
                    <th>Đến Ngày</th>
                    <th>Lý Do</th>
                    <th>Trạng Thái</th>
                    <th>Chi Tiết</th>
                </tr>
                </thead>
                <c:forEach var="request" items="${requests}">
                                <tr>
                                    <td>
                                        <!-- Tạo liên kết đến trang chi tiết cho mọi trạng thái -->
                                        <a href="Request?action=detail&id=${request.getId()}">
                                            ${request.getReason()}
                                        </a>
                                    </td>
                                    <td>${request.dateFrom}</td>
                                    <td>${request.dateTo}</td>
                                    <td>${request.dateCreate}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${request.getStatus() eq 'Inprogress'}">
                                                <span class="status-inprogress">Inprogress</span>
                                            </c:when>
                                            <c:when test="${request.getStatus() eq 'Rejected'}">
                                                <span class="status-rejected">Rejected</span>
                                            </c:when>
                                            <c:otherwise>
                                                ${request.getStatus()}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="Request?action=edit&id=${request.id}">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>