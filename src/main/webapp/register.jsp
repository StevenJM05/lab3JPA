<%-- 
    Document   : register
    Created on : 11-03-2024, 10:04:00 AM
    Author     : steve_y
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="container">
        <div class="left-panel">
            <img src="assets/img/still-life-books-versus-technology.jpg" alt="Illustration" class="illustration">
        </div>
        <div class="right-panel">
            <h1>Welcome to WorldBook!</h1>
            <p>Register your account</p>
            <form action="RegistroUsuario" method="post">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" placeholder="Enter your name" required>

                <label for="name">Last Name</label>
                <input type="text" id="apellido" name="lastname" placeholder="Enter your last name" required>
                
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
                
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="8+ characters" required>
                
                <button type="submit" class="login-btn">Login</button>
                <a class="a-register" href="login.jsp">¿Ya tienes cuenta? Inicia Sesión</a>
                
            </form>
        </div>
    </div>
</body>
</html>

