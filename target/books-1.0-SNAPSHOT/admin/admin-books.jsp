<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
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
                                <img src="${pageContext.request.contextPath}/assets/img/leon.png" alt="Inicio">
                                <a href="#">Profile</a>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}/assets/img/libro.png" alt="Inicio">
                                <a href="#">Books</a>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}/assets/img/administracion.png"
                                    alt="Inicio">
                                <a href="#">Users</a>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}/assets/img/diagrama.png" alt="Inicio">
                                <a href="#">Popular books</a>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}/assets/img/pizarron.png" alt="Inicio">
                                <a href="#">Popular genres</a>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}/assets/img/grafico-de-lineas.png"
                                    alt="Inicio">
                                <a href="#">Evolution of interest</a>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}/assets/img/cerrar-sesion.png" alt="Inicio">
                                <a href="#">Log out</a>
                            </li>
                        </ul>
                    </nav>
                </aside>

                <!-- Contenido Principal -->
                <main class="main-content">
                    <!-- Encabezado con Tarjetas -->
                    <section class="stats">
                        <div class="card">
                            <img src="${pageContext.request.contextPath}/assets/img/diagrama.png" alt="Inicio"
                                height="32px">
                            <p>Popular books</p>
                        </div>
                        <div class="card">
                            <img src="${pageContext.request.contextPath}/assets/img/pizarron.png" alt="Inicio"
                                height="32px">
                            <p>Popular genres</p>
                        </div>
                        <div class="card">
                            <img src="${pageContext.request.contextPath}/assets/img/grafico-de-lineas.png" alt="Inicio"
                                height="32px">
                            <p>Evolution of interest</p>
                        </div>
                    </section>

                    <!-- Lista de libros -->
                    <section class="books">
                        <h2>Books</h2>
                        <button class="appointment-btn">Agregar Libro</button>
                        <hr>
                        <c:forEach var="libro" items="${listaLibros}">
                            <div class="book">
                                <img src="${pageContext.request.contextPath}/assets/img/libros.png" alt="Avatar">
                                <div class="details">
                                    <h3>${libro.titulo}</h3>
                                    <p>${libro.autor}</p>
                                    <p>${libro.genero}</p>
                                </div>
                                <button class="btn-actualizar" type="button"
                                    onclick="actualizarLibro(${libro.id}, '${libro.titulo}', '${libro.autor}', '${libro.genero}')">
                                    Actualizar
                                </button>
                                <button class="btn-eliminar" type="button"
                                    onclick="eliminarLibro(${libro.id})">Eliminar</button>
                            </div>
                        </c:forEach>

                    </section>
                </main>
            </div>

            <!-- Modal para agregar-->
            <div class="modal" id="profileModal">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="close-btn" onclick="cerrar()">&times;</span>
                        <h2>Agregar Libro</h2>
                    </div>
                    <form action="AgregarLibro"  method="post">
                        <label for="name">Titulo:</label>
                        <input type="text" id="titulo" name="titulo" required>

                        <label for="name">Autor:</label>
                        <input type="text" id="autor" name="autor" required>

                        <label for="email">Genero:</label>
                        <input type="text" id="genero" name="genero" required>

                        <div class="modal-footer">
                            <button class="btn-enviar" type="submit">Save</button>
                            <button class="btn-cancelar" type="submit" onclick="cerrar()">Exit</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Modal para Actualizar-->
            <div class="modalAct" id="modalActu">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="close-btn" onclick="cerrar()">&times;</span>
                        <h2>Actualizar Libro</h2>
                    </div>
                    <form action="ActualizarLibro" method="post">
                        <input type="hidden" id="idAct" name="id">
                        <label for="Titulo">Titulo:</label>
                        <input type="text" id="tituloAct" name="titulo">

                        <label for="Autor">Autor:</label>
                        <input type="text" id="autorAct" name="autor">

                        <label for="genero">Genero:</label>
                        <input type="text" id="generoAct" name="genero">

                        <div class="modal-footer">
                            <button class="btn-enviar" type="submit">Save</button>
                            <button class="btn-cancelar" onclick="cerrar()">Exit</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Modal para Eliminar-->
            <div class="modalEliminar" id="modalEliminar">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="close-btn" onclick="cerrar()">&times;</span>
                        <h2>Eliminar Libro</h2>
                    </div>
                    <form>
                        <label>Desea eliminar el libro?</label>
                        <div class="modal-footer">
                            <button class="btn-enviar" type="submit">Save</button>
                            <button class="btn-cancelar" onclick="cerrar()">Exit</button>
                        </div>
                    </form>
                </div>
            </div>

            <script src="${pageContext.request.contextPath}/assets/js/book.js"></script>
        </body>

        </html>