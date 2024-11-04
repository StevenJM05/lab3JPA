<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gráfico de Preferencias</title>
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
        <h2>Evolución de Preferencias por Libro a lo Largo del Tiempo</h2>
        <canvas id="lineChart" width="800" height="400"></canvas>

        <script>
            // Datos pasados desde el servlet
            const preferenciasOverTime = [
            <% for (Object[] row : (List<Object[]>) request.getAttribute("preferenciasOverTime")) {%>
                {libroNombre: "<%= row[1]%>", fecha: "<%= row[2]%>", cantidad: <%= row[3]%>},
            <% }%>
            ];

            // Procesar los datos para Chart.js
            const libros = {};
            preferenciasOverTime.forEach(item => {
                const fecha = item.fecha;
                const libroNombre = item.libroNombre;
                const cantidad = item.cantidad;

                if (!libros[libroNombre]) {
                    libros[libroNombre] = {fechas: [], cantidades: []};
                }
                libros[libroNombre].fechas.push(fecha);
                libros[libroNombre].cantidades.push(cantidad);
            });

            // Preparar los datasets para cada libro
            const datasets = Object.keys(libros).map(libroNombre => ({
                    label: libroNombre, // Nombre del libro en la leyenda
                    data: libros[libroNombre].cantidades,
                    borderColor: `#${Math.floor(Math.random()*16777215).toString(16)}`, // Color aleatorio para cada línea
                    fill: false,
                    tension: 0.1
                }));

            // Configuración del gráfico
            const config = {
                type: 'line',
                data: {
                    labels: libros[Object.keys(libros)[0]].fechas, // Las fechas para el eje x
                    datasets: datasets
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top'
                        }
                    },
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Fecha de Selección'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: 'Cantidad de Preferencias'
                            }
                        }
                    }
                }
            };

            // Renderizar el gráfico
            const lineChart = new Chart(
                    document.getElementById('lineChart'),
                    config
                    );
        </script>
    </body>
</html>
