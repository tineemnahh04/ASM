<%-- 
    Document   : Login
    Created on : Mar 14, 2025, 11:51:53 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loginn</title>
        <style>
            body {
                background-color: #1e1e1e;
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .login-container {
                background-color: #121212;
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                text-align: center;
                width: 350px;
            }
            h2 {
                color: white;
                margin-bottom: 20px;
            }
            input {
                background-color: #1e1e1e;
                color: white;
                border: 1px solid #444;
                padding: 10px;
                width: 90%;
                margin-bottom: 15px;
                border-radius: 8px;
                transition: border-color 0.3s, box-shadow 0.3s;
            }

            input:focus {
                outline: none;
                border-color: #4CAF50;
                box-shadow: 0 0 3px #4CAF50;
                background-color: #1e1e1e;
            }

            .btn {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.3s;
                width: 100%;
                font-weight: bold;
            }
            .btn:hover {
                background-color: #45a049;
            }
            .error {
                color: red;
                margin-bottom: 10px;
            }
            .error-list {
                color: #ff5252;
                text-align: center;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>Login</h2>
            <% String error = request.getParameter("error"); %>
            <% if (error != null) { %>
            <p class="error">Sai thông tin đăng nhập. Vui lòng thử lại.</p>
            <% } %>
            <form action="Login" method="POST">
                <input type="text"  name="username" placeholder="User" required>
                <input type="password" name="password" placeholder="Password" required>
                <button type="submit" class="btn">Sign in</button>
            </form>
    </body>
</html>