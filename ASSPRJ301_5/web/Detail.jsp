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
                background-color: #2c2c2c;
                color: white;
                border: 2px solid #444;
                border-radius: 8px;
                padding: 10px;
                filter: invert(1);
                border-color: #4CAF50;
                box-shadow: 0 0 0 2px white, 0 0 0 4px #4CAF50;
                outline: none;
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
        <div class="form-container">
            <h2>Chi tiết đơn nghỉ</h2>
            <c:if test="${detail != null}">
                <label>Ngày tạo: ${detail.getDateCreate()}</label>
                <label>Từ ngày: ${detail.getDateFrom()}</label> 
                <label>Tới ngày: ${detail.getDateTo()}</label> 
                <label>Lý do: ${detail.getReason()}</label> 
                <label>Trạng thái: ${detail.getStatus()}</label>                      
        </div>
    </c:if>
    <button type="button" class="back-button" onclick="window.location.href = 'View'">
        Back
    </button>
</div>
</body>
</html>
