<%-- 
    Document   : books
    Created on : 11-03-2024, 10:45:21 AM
    Author     : steve_y
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Books</title>
  
  <link rel="stylesheet" href="../assets/css/books.css">
</head>
<body>
  <div class="container">
    <!-- Barra Lateral -->
    <aside class="sidebar">
      <div class="logo">WorldBook</div>
      <nav class="nav-menu">
        <ul>
          <li>
              <img src="../assets/img/leon.png" alt="Inicio">
            <a href="#">Profile</a></li>
          <li>
              <img src="../assets/img/libro.png" alt="Inicio">
            <a href="#">Books</a></li>
          <li>
              <img src="../assets/img/diagrama.png" alt="Inicio">
            <a href="#">Popular books</a></li>
          <li>
              <img src="../assets/img/pizarron.png" alt="Inicio">
            <a href="#">Popular genres</a></li>
          <li>
              <img src="../assets/img/grafico-de-lineas.png" alt="Inicio">
            <a href="#">Evolution of interest</a></li>
          <li>
              <img src="../assets/img/cerrar-sesion.png" alt="Inicio">
            <a href="#">Log out</a></li>
        </ul>
      </nav>
    </aside>

    <!-- Contenido Principal -->
    <main class="main-content">
      <!-- Encabezado con Tarjetas -->
      <section class="stats">
        <div class="card">
          <img src="../assets/img/diagrama.png" alt="Inicio" height="32px">>
          <p>Popular books</p>
        </div>
        <div class="card">
          <img src="../assets/img/pizarron.png" alt="Inicio" height="32px">
          <p>Popular genres</p>
        </div>
        <div class="card">
            <img src="../assets/img/grafico-de-lineas.png" alt="Inicio" height="32px">
          <p>Evolution of interest</p>
        </div>
      </section>

      <!-- Lista de Libros -->
      <section class="books">
        <h2>Books</h2>
        <div class="book">
            <img src="../assets/img/libros.png" alt="Avatar">
          <div class="details">
            <h3>Titulo</h3>
            <p>Autor</p>
            <p>Genero</p>
          </div>
          <button class="btn-like"><img src="../assets/img/corazon(4).png" alt="Avatar"></button> 
        </div>

      </section>
    </main>  


  </div>
</body>
</html>

