<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Books</title>

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
                            <img src="${pageContext.request.contextPath}/assets/img/leon.png" alt="Profile">
                            <a href="Profile">Profile</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/libro.png" alt="Books">
                            <a href="userbooks">Books</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/diagrama.png" alt="Popular Books">
                            <a href="LibrosPopulares">Popular books</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/pizarron.png" alt="Popular Genres">
                            <a href="GenerosPopulares">Popular genres</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/grafico-de-lineas.png" alt="Evolution of Interest">
                            <a href="PreferenciasG">Evolution of interest</a></li>
                        <li>
                            <img src="${pageContext.request.contextPath}/assets/img/cerrar-sesion.png" alt="Log Out">
                            <a href="LogOut">Log out</a></li>
                    </ul>
                </nav>
            </aside>

            <!-- Contenido Principal -->
            <main class="main-content">
                <!-- Encabezado con Tarjetas -->
                <section class="stats">
                    <div class="card">
                        <img src="${pageContext.request.contextPath}/assets/img/diagrama.png" alt="Popular Books" height="32px">
                        <p>Popular books</p>
                    </div>
                    <div class="card">
                        <img src="${pageContext.request.contextPath}/assets/img/pizarron.png" alt="Popular Genres" height="32px">
                        <p>Popular genres</p>
                    </div>
                    <div class="card">
                        <img src="${pageContext.request.contextPath}/assets/img/grafico-de-lineas.png" alt="Evolution of Interest" height="32px">
                        <p>Evolution of interest</p>
                    </div>
                </section>

                <!-- Lista de Libros -->
                <section class="books">
                    <h2>Books</h2>
                    <c:forEach var="libro" items="${listaLibros}">
                        <div class="book">
                            <img src="${pageContext.request.contextPath}/assets/img/libros.png" alt="Book Cover">
                            <div class="details">
                                <h3>${libro.titulo}</h3>
                                <p>${libro.autor}</p>
                                <p>${libro.genero}</p>
                            </div>
                            <form action="${pageContext.request.contextPath}/LikeBook" method="POST">
                                <input type="hidden" name="libroId" value="${libro.id}">
                                <button type="submit">
                                    <img src="${pageContext.request.contextPath}/assets/img/corazon(4).png" alt="Like">
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                </section>
            </main>  
        </div>
        <script src="${pageContext.request.contextPath}../assets/js/preferencia.js"></script>
    </body>
</html>
