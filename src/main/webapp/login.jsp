<%-- 
    Document   : login
    Created on : 11-03-2024, 09:39:45 AM
    Author     : steve_y
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="container">
        <div class="left-panel">
            <img src="assets/img/login.jpg" alt="Illustration" class="illustration">
        </div>
        <div class="right-panel">
            <h1>Welcome to WorldBook!</h1>
            <p>Log in your account</p>
            <form action="LoginUsuario" method="post">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="8+ characters" required>
                <button type="submit" class="login-btn">Login</button>
                <a href="register.jsp" class="a-register">¿No tienes cuenta? Registrate</a>
            </form>
        </div>
    </div>
</body>
</html>

