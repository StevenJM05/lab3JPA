<%-- 
    Document   : users
    Created on : 11-03-2024, 06:47:32 PM
    Author     : steve_y
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Users</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/books.css">
    </head>
    <body>
        <div class="container">
            <!-- Barra Lateral -->
            <aside class="sidebar">
                <div class="logo">WorldBook</div>
                <nav class="nav-menu">
                    <ul>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/leon.png" alt="Inicio">
                            <a href="#">Profile</a>
                        </li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/libro.png" alt="Inicio">
                            <a href="#">Books</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/administracion.png" alt="Inicio">
                            <a href="#">Users</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/diagrama.png" alt="Inicio">
                            <a href="#">Popular books</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/pizarron.png" alt="Inicio">
                            <a href="#">Popular genres</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/grafico-de-lineas.png" alt="Inicio">
                            <a href="#">Evolution of interest</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/cerrar-sesion.png" alt="Inicio">
                            <a href="#">Log out</a></li>
                    </ul>
                </nav>
            </aside>

            <!-- Contenido Principal -->
            <main class="main-content">
                <!-- Encabezado con Tarjetas -->
                <section class="stats">
                    <div class="card">
                        <img src="${pageContext.request.contextPath}/assets/img/diagrama.png" alt="Inicio" height="32px">
                        <p>Número de Usuarios</p>
                    </div>
                    <div class="card">
                        <img src="${pageContext.request.contextPath}/assets/img/pizarron.png" alt="Inicio" height="32px">
                        <p>Número de Libros</p>
                    </div>
                </section>

                <!-- Lista de Usuarios -->
                <section class="books">
                    <h2>Users</h2>
                    <button class="btn-agregar">Agregar Usuario</button>
                    <hr>
                    <c:forEach var="user" items="${listaUsuarios}">
                        <div class="book">
                            <img src="${pageContext.request.contextPath}/assets/img/hombre.png" alt="Avatar">
                            <div class="details">
                                <h3>${user.nombre}</h3>
                                <p>${user.email}</p>
                                <p>${user.tipo}</p>
                            </div>
                            <a href="${pageContext.request.contextPath}/ProfileUser?id=${user.id}">
                                <button id="btn-plus">Ver más</button>
                            </a>
                        </div>
                    </c:forEach>
                </section>
            </main>  
        </div>

        <!-- Modal para agregar-->
        <div class="modal" id="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close-btn" onclick="cerrar()">&times;</span>
                    <h2>Agregar Usuario</h2>
                </div>
                <form method="post"  action="AgregarUsuario">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" placeholder="Enter your name" required>

                    <label for="name">Last Name</label>
                    <input type="text" id="apellido" name="lastname" placeholder="Enter your last name" required>

                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>

                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="8+ characters" required>

                    <label for="tipo">Tipo Usuario</label>
                    <select id="tipo" name="tipo" class="selectmodal">
                        <option value="admin">Admin</option>
                        <option value="user">User</option>
                    </select>
                    <div class="modal-footer">
                        <button class="btn-enviar" type="submit">Save</button>
                        <button class="btn-cancelar" type="button" onclick="cerrar()">Exit</button>
                    </div>
                </form>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/assets/js/users.js"></script>
    </body>
</html>
