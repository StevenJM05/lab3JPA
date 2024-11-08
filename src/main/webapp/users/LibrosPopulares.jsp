<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelos.Libros" %>
<%@ page import="java.util.List" %>
<%
    List<Object[]> librosPopulares = (List<Object[]>) request.getAttribute("librosPopulares");

    // Verifica si librosPopulares es null o vacío
    String[] titulos = null;
    Long[] conteos = null;

    if (librosPopulares != null && !librosPopulares.isEmpty()) {
        titulos = new String[librosPopulares.size()];
        conteos = new Long[librosPopulares.size()];

        for (int i = 0; i < librosPopulares.size(); i++) {
            Object[] result = librosPopulares.get(i);
            Libros libro = (Libros) result[0];
            Long conteo = (Long) result[1];

            if (libro != null) {
                titulos[i] = libro.getTitulo(); // Asegúrate de que libro no sea null
            } else {
                titulos[i] = "Libro desconocido"; // Manejar el caso de libro nulo
            }
            conteos[i] = conteo != null ? conteo : 0; // Asegurarse de que conteo no sea null
        }
    } else {
        // Manejo si no hay libros populares
        titulos = new String[]{"Sin libros populares"};
        conteos = new Long[]{0L};
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Libros Populares</title>
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
        <h1>Libros Más Populares</h1>
        <canvas id="librosPopularesChart" width="400" height="200"></canvas>
        <script>
            const ctx = document.getElementById('librosPopularesChart').getContext('2d');
            const librosPopularesChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: <%= new com.google.gson.Gson().toJson(titulos)%>,
                    datasets: [{
                            label: 'Conteo de Preferencias',
                            data: <%= new com.google.gson.Gson().toJson(conteos)%>,
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
