<%-- 
    Document   : Accept
    Created on : Mar 15, 2025, 4:12:44 AM
    Author     : admin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="form-container">
            <h2 class="text-center">Duyệt đơn xin nghỉ phép</h2>

            <p><strong>Duyệt bởi User:</strong> <%= request.getAttribute("nguoiDuyet") %>, <strong>Role:</strong> <%= request.getAttribute("role") %></p>
            <p><strong>Tạo bởi:</strong> <%= request.getAttribute("nguoiTao") %></p>
            <p><strong>Từ ngày:</strong> <%= request.getAttribute("tuNgay") %></p>
            <p><strong>Tới ngày:</strong> <%= request.getAttribute("toiNgay") %></p>

            <p><strong>Lý do:</strong></p>
            <div class="reason-box"><%= request.getAttribute("lyDo") %></div>

            <div class="d-flex justify-content-between mt-3">
                <form action="XuLyDuyetDonServlet" method="post">
                    <input type="hidden" name="idDon" value="<%= request.getAttribute("idDon") %>">
                    <button type="submit" name="action" value="reject" class="btn-reject">Reject</button>
                    <button type="submit" name="action" value="approve" class="btn-approve">Approve</button>
                </form>
            </div>
        </div>
    </body>
</html>
