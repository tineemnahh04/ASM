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
            .form-container {
                background-color: #2b2b2b;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                width: 400px;
                text-align: center;
            }
            .info-container {
                background-color: #3a3a3a;
                padding: 20px;
                border-radius: 8px;
                margin-top: 15px;
                text-align: left;
                line-height: 1.8;
            }
            .info-container p {
                margin: 10px 0;
                font-size: 16px;
            }
            .submit-btn {
                display: block;
                width: 80%;
                margin: 10px auto;
                padding: 10px;
                border-radius: 6px;
                border: none;
                cursor: pointer;
                font-size: 14px;
                text-align: center;
            }
            .submit-btn {
                background-color: #0aad4b;
                color: white;
            }
            .submit-btn:hover {
                background-color: #088b3b;
            }
            .message {
                margin-top: 10px;
                font-size: 14px;
                font-weight: bold;
                text-align: center;
            }
            .success-message {
                color: #00c853;
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
          .title {
                background-color: #0aad4b;
                color: white;
                border: none;
                padding: 12px 24px;
                font-size: 16px;
                border-radius: 6px;
                transition: background-color 0.3s ease;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h2 class="title" >Chi tiết đơn xin nghỉ phép </h2>
            <c:if test="${not empty requestDetail}">
                <div class="info-container">
                    <p><strong>ID Đơn:</strong> ${requestDetail.getId()}</p> 
                    <p><strong>ID Nhân viên :</strong> ${requestDetail.getEmployeeId()}</p>
                    <p><strong>Từ ngày:</strong> ${requestDetail.getDateFrom()}</p>
                    <p><strong>Tới ngày:</strong> ${requestDetail.getDateTo()}</p>
                    <p><strong>Ngày tạo:</strong> ${requestDetail.getDateCreate()}</p>
                    <p><strong>Lý do:</strong> ${requestDetail.getReason()}</p>
                    <p><strong>Trạng thái:</strong> ${requestDetail.getStatus()}</p>
                </div>
            <c:choose>
                    <c:when test="${requestDetail.getId() == 3}">
                        <button class="back-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/View'">Quay lại</button>
                    </c:when>
                    <c:when test="${requestDetail.getId() == 2}">
                        <button class="back-button" onclick="window.location.href = 'http://localhost:8080/ASSPRJ301_5/Manager'">Quay lại</button>
                    </c:when>
                </c:choose>
                        </c:if>
        </div>
    </body>
</html>
