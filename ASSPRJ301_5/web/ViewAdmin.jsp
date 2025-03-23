<%-- 
    Document   : FormManagement
    Created on : Mar 17, 2025, 1:46:24 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách đơn cần xét duyệt</title>
    </head>
    <body>
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
            input[type="date"] {
                background-color: #2c2c2c;
                color: white;
                border: 2px solid #444;
                border-radius: 8px;
                padding: 10px;
            }

            input[type="date"]:focus {
                border-color: #4CAF50;
                box-shadow: 0 0 0 2px white, 0 0 0 4px #4CAF50;
                outline: none;
            }
            input[type="date"]::-webkit-calendar-picker-indicator {
                filter: invert(1);
            }
            .message {
                margin-top: 10px; /* Đẩy xuống một chút */
                font-size: 14px;
                font-weight: bold;
            }
            .success-message {
                color: #00c853; /* Màu xanh khi thành công */
            }
            .error-message {
                color: #ff5252; /* Màu đỏ khi thất bại */
            }
            .form-container {
                background-color: #2b2b2b;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                width: 400px;
            }
            h2 {
                text-align: center;
                margin-bottom: 20px;
                color: white;
            }
            label {
                margin-top: 10px;
                display: block;
                font-size: 14px;
                color: white;
            }
            input, textarea {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                margin-bottom: 15px;
                border-radius: 6px;
                border: 1px solid #444;
                background-color: #333;
                color: white;
            }
            input:focus, textarea:focus {
                border-color: #0aad4b;
                outline: none;
            }
            .submit-btn {
                background-color: #0aad4b;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 14px;
                border-radius: 6px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                display: block;
                margin: 20px auto 0;
                width: 80%;
            }
            .submit-btn:hover {
                background-color: #088b3b;
            }
            .error-message {
                color: red;
                margin-top: 10px;
                padding: 10px;
                border-radius: 6px;
            }
            .back-btn {
                background-color: #00c853; /* Màu xanh lá */
                color: white;
                border: none;
                padding: 8px 16px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 14px;
                position: absolute;
                top: 20px;
                left: 20px;
            }

            .back-btn:hover {
                background-color: #009624; /* Màu xanh đậm hơn khi hover */
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
         <button class="back-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/Home'">Back</button>
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
