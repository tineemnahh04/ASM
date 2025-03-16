<%-- 
    Document   : Detail
    Created on : Mar 16, 2025, 6:09:45 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết đơn nghỉ phép</title>
        <style>
            body {
                background-color: #121212;
                color: white;
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                position: relative;
            }
            .container {
                text-align: center;
            }
            .back-button {
                background-color: #D32F2F; /* Đổi màu đỏ */
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                border-radius: 5px;
                position: absolute;
                top: 20px;
                left: 20px; /* Di chuyển sang bên trái */
            }

            .back-button:hover {
                background-color: #B71C1C; /* Màu đỏ đậm hơn khi hover */
            }

            .form-box {
                background-color: #1E1E1E;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 200, 83, 0.5);
                width: 400px;
            }
            .form-title {
                font-size: 24px;
                margin-bottom: 20px;
            }
            .info-box {
                background-color: #333;
                padding: 10px;
                border-radius: 5px;
                word-wrap: break-word;
                margin-bottom: 10px;
            }
            .error-message {
                color: red;
            }
        </style>
    </head>
    <body>
        <button class="back-button" onclick="window.location.href = 'View'">Quay lại</button>
        <div class="container">
            <div class="form-box">
                <h2 class="form-title">Đơn xin nghỉ phép</h2>
                <c:if test="${not empty requestDetail}">
                    <div class="info-box"><strong>ID đơn: </strong> ${requestDetail.getId()}</div>
                    <div class="info-box"><strong>Nhân viên tạo: </strong> ${requestDetail.getEmployeeId()}</div>
                    <div class="info-box"><strong>Từ ngày: </strong> ${requestDetail.getDateFrom()}</div>
                    <div class="info-box"><strong>Tới ngày: </strong> ${requestDetail.getDateTo()}</div>
                    <div class="info-box"><strong>Ngày tạo: </strong> ${requestDetail.getDateCreate()}</div>
                    <div class="info-box"><strong>Lý do: </strong>${requestDetail.getReason()}</div>
                    <div class="info-box"><strong>Trạng thái: </strong> ${requestDetail.getStatus()}</div>
                </c:if>
                <c:if test="${empty requestDetail}">
                    <p class="error-message">Không tìm thấy đơn xin nghỉ phép!</p>
                </c:if>
            </div>
        </div>
    </body>
</html>
