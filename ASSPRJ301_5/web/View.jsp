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
        .status-inprogress { color: yellow; }
        .status-rejected { color: red; }
        a {
            color: #1db954;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
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
                                <c:when test="${request.status eq 'Pending'}">
                                    <a href="Request?action=edit&id=${request.id}">${request.reason}</a>
                                </c:when>
                                <c:otherwise>
                                    ${request.reason}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${request.dateFrom}</td>
                        <td>${request.dateTo}</td>
                        <td>${request.dateCreate}</td>
                        <td>
                            <c:choose>
                                <c:when test="${request.status eq 'Pending'}">
                                    <span class="status-inprogress">Pending</span>
                                </c:when>
                                <c:when test="${request.status eq 'Rejected'}">
                                    <span class="status-rejected">Rejected</span>
                                </c:when>
                                <c:otherwise>
                                    ${request.status}
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
