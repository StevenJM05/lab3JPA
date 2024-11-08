<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%
    Map<String, Long> contadorGeneros = (Map<String, Long>) request.getAttribute("contadorGeneros");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Géneros Populares</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/books.css">
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
        <h1>Géneros Más Populares</h1>
        <canvas id="generosPopularesChart" width="400" height="200"></canvas>
        <script>
            const ctx = document.getElementById('generosPopularesChart').getContext('2d');
            const generosPopularesChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: <%= new com.google.gson.Gson().toJson(contadorGeneros.keySet())%>,
                    datasets: [{
                            label: 'Conteo de Libros',
                            data: <%= new com.google.gson.Gson().toJson(contadorGeneros.values())%>,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
    </body>
</html>
