<%-- 
    Document   : profile
    Created on : 11-03-2024, 07:27:58 PM
    Author     : steve_y
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.css">
    </head>

    <body>
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
                            <a href="${pageContext.request.contextPath}/AdminBooks">Books</a>
                        </li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/administracion.png"
                                 alt="Inicio">
                            <a href="${pageContext.request.contextPath}/ListarUsuarios">Users</a>
                        </li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/diagrama.png" alt="Inicio">
                            <a href="${pageContext.request.contextPath}/LPopulares">Popular books</a>
                        </li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/pizarron.png" alt="Inicio">
                            <a href="${pageContext.request.contextPath}/GenPopulares">Popular genres</a>
                        </li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/grafico-de-lineas.png"
                                 alt="Inicio">
                            <a href="${pageContext.request.contextPath}/EvolucionA">Evolution of interest</a>
                        </li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/cerrar-sesion.png" alt="Inicio">
                            <a href="#">Log out</a>
                        </li>
                    </ul>
                </nav>
            </aside>
        <div class="dashboard">
            <!-- Tarjeta de Perfil -->
            <aside class="profile-card">
                <div class="profile-image">
                    <img src="${pageContext.request.contextPath}/assets/img/hombre.png" alt="Harriet Nunez">
                </div>
                <h2>${user.nombre} ${user.apellido}</h2>
                <button class="appointment-btn">Profile Config</button>
                <div class="profile-info">
                    <div class="info-item">
                        <span>Nombre</span>
                        <p>${user.nombre}</p>
                    </div>
                    <div class="info-item">
                        <span>Apellido</span>
                        <p>${user.apellido}</p>
                    </div>
                    <div class="info-item">
                        <span>Email</span>
                        <p>${user.email}</p>
                    </div>
                </div>
            </aside>

            <!-- Modal -->
            <div class="modal" id="profileModal" style="display:none;">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="close-btn" onclick="cerrar()">&times;</span>
                        <h2>Actualizar Información</h2>
                    </div>
                    <form>
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name">

                        <label for="lastname">Last Name:</label>
                        <input type="text" id="lastname" name="lastname">

                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email">

                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password">

                        <div class="modal-footer">
                            <button class="btn-enviar" type="submit">Save</button>
                            <button class="btn-cancelar" type="button" onclick="cerrar()">Exit</button>
                        </div>    
                    </form>
                </div>
            </div>

            <div class="colum2">
                <!-- Sección de Estadísticas -->
                <section class="stats-cards">
                    <div class="card bookings">
                        <h3>5</h3>
                        <p>Libros favoritos</p>
                    </div>
                    <div class="card completed">
                        <h3>2</h3>
                        <p>Géneros</p>
                    </div>
                </section>
                <!-- Sección de Preferencias -->
                <section class="appointments-section">
                    <div class="appointments-header">
                        <h3>Preferencias</h3>
                        <div class="tabs">
                            <span>Libros</span>
                        </div>
                    </div>
                    <div class="appointments">
                        <c:forEach var="preferencia" items="${preferenciasList}">
                            <div class="appointment">
                                <p class="date">${preferencia.fechaSeleccion}</p>
                                <div class="details">
                                    <p>${preferencia.idLibro.titulo}</p>
                                    <p class="time">${preferencia.idLibro.autor}</p>
                                </div>
                                <div class="price">${preferencia.idLibro.genero}</div>
                                
                            </div>
                        </c:forEach>
                    </div>
                </section>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/assets/js/profile.js"></script>
    </body>

</html>

