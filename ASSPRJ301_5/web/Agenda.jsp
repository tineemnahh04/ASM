<%-- 
    Document   : Agenda
    Created on : Mar 15, 2025, 4:10:17 AM
    Author     : admin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lịch làm việc</title>
    <style>
            body {
                background-color: #1e1e1e;
                color: white;
                font-family: Arial, sans-serif;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
                margin: 0;
                padding: 20px;
            }
            h2 {
                color: #0aad4b;
                text-shadow: 0 0 5px rgba(0, 255, 0, 0.5);
                margin-bottom: 20px;
                font-size: 38px;
            }
            .date-filter {
                margin-bottom: 30px;
                display: flex;
                gap: 15px;
                align-items: center;
            }
            .date-filter label {
                font-size: 18px;
            }
            .date-filter input[type="date"] {
                background-color: #2c2c2c;
                color: white;
                border: 2px solid #444;
                border-radius: 5px;
                padding: 8px;
                font-size: 16px;
                width: 200px;
            }
            .date-filter input[type="date"]:focus {
                border-color: #4CAF50;
                outline: none;
            }
            .date-filter input[type="date"]::-webkit-calendar-picker-indicator {
                filter: invert(1);
            }
            .date-filter button {
                background-color: #0aad4b;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }
            .date-filter button:hover {
                background-color: #088b3b;
            }
            table {
                width: auto; /* Tự điều chỉnh theo nội dung */
                border-collapse: separate; /* Dùng border-collapse: separate để tạo khoảng cách giữa các ô */
                border-spacing: 3px; /* Tăng khoảng cách giữa các ô */
                background: #2b2b2b; /* Màu nền bảng */
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* Đổ bóng cho bảng */
            }
            th, td {
                padding: 8px; /* Tăng padding để ô lớn hơn */
                text-align: center;
                border: none; /* Xóa đường viền */
                width: 80px; /* Tăng chiều rộng để ô to hơn */
                height: 40px; /* Tăng chiều cao để ô to hơn, nhưng vẫn bẹt (chiều rộng > chiều cao) */
                box-sizing: border-box; /* Đảm bảo padding không làm tăng kích thước ô */
                font-size: 16px; /* Tăng kích thước chữ để dễ đọc */
            }
            th {
                background-color: #333; /* Màu nền tiêu đề */
                font-weight: bold;
                border-radius: 5px; /* Bo góc cho ô tiêu đề */
            }
            td {
                border-radius: 5px; /* Bo góc cho ô dữ liệu */
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Đổ bóng cho từng ô */
            }
            /* Cột "Nhân sự" có thể rộng hơn một chút để chứa tên */
            th:first-child, td:first-child {
                width: 160px; /* Tăng chiều rộng cho cột "Nhân sự" */
                text-align: left; /* Căn trái cho tên nhân viên */
            }
            .available {
                background-color: #4CAF50; /* Màu xanh lá cho trạng thái đi làm */
            }
            .unavailable {
                background-color: #FF5252; /* Màu đỏ cho trạng thái nghỉ */
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
        <button class="back-button" onclick="window.location.href='${pageContext.request.contextPath}/Home'">Quay lại</button>
        <h2>Lịch làm việc</h2>

        <!-- Bộ lọc ngày -->
        <div class="date-filter">
            <form action="Agenda" method="post">
                <label for="fromDate">Từ ngày:</label>
                <input type="date" id="fromDate" name="fromDate" value="${param.fromDate}">
                <label for="toDate">Đến ngày:</label>
                <input type="date" id="toDate" name="toDate" value="${param.toDate}">
                <button type="submit">Lọc</button>
            </form>
        </div>

        <!-- Bảng lịch làm việc -->
        <!-- Chỉ hiển thị bảng nếu đã nhấn nút "Lọc" (có tham số fromDate và toDate) -->
        <c:if test="${not empty param.fromDate and not empty param.toDate}">
            <table id="scheduleTable">
                <tr>
                    <th>Nhân sự</th> <!-- Giữ cột "Nhân sự" -->
                    <!-- Hiển thị ngày ở hàng tiêu đề -->
                    <c:forEach var="date" items="${dateRange}">
                        <th><fmt:formatDate value="${date}" pattern="d/M"/></th>
                    </c:forEach>
                </tr>
                <c:forEach var="employee" items="${employees}">
                    <!-- Bỏ qua nhân viên có tên "Admin User" -->
                    <c:if test="${employee.name != 'Admin User'}">
                        <tr>
                            <td>${employee.name}</td> <!-- Hiển thị tên nhân viên -->
                            <c:forEach var="date" items="${dateRange}">
                                <c:set var="isAvailable" value="true"/>
                                <c:set var="reason" value=""/> <!-- Biến tạm để lưu lý do nghỉ -->
                                <c:forEach var="request" items="${approvedRequests}">
                                    <c:if test="${request.eName eq employee.name}">
                                        <c:if test="${date ge request.dateFrom and date le request.dateTo}">
                                            <c:if test="${request.status eq 'Approved'}">
                                                <c:set var="isAvailable" value="false"/>
                                                <c:set var="reason" value="${request.reason}"/>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                                <td class="${isAvailable ? 'available' : 'unavailable'}" 
                                    title="${isAvailable ? '' : reason}"></td>
                            </c:forEach>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>