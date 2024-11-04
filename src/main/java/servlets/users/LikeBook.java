/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.users;

import controladores.LibrosJpaController;
import controladores.PreferenciasJpaController;
import controladores.UsuariosJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Libros;
import modelos.Preferencias;
import modelos.Usuarios;

/**
 *
 * @author steve_y
 */
@WebServlet(name = "LikeBook", urlPatterns = {"/LikeBook"})
public class LikeBook extends HttpServlet {

    private PreferenciasJpaController preferenciasJpaController;

    @Override
    public void init() {

        preferenciasJpaController = new PreferenciasJpaController();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LikeBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LikeBook at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String libroIdParam = request.getParameter("libroId");

        // Verificar que el ID no sea nulo o vacío
        if (libroIdParam == null || libroIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del libro no puede ser nulo o vacío.");
            return;
        }

        try {
            // Convertir el ID del libro a entero
            int libroId = Integer.parseInt(libroIdParam);

            // Obtener el ID del usuario de la sesión (asegurarse de que esté autenticado)
            Integer usuarioId = (Integer) request.getSession().getAttribute("id");
            if (usuarioId == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autenticado.");
                return;
            }

            // Crear una nueva instancia de Preferencias
            Preferencias preferencia = new Preferencias();

            // Configurar los atributos de la preferencia
            Libros libro = new Libros();
            libro.setId(libroId); // ID del libro al que se le da "me gusta"

            Usuarios usuario = new Usuarios();
            usuario.setId(usuarioId); // ID del usuario que ha iniciado sesión

            preferencia.setIdLibro(libro);
            preferencia.setUsuarioId(usuario);

            // Registrar la fecha de selección
            preferencia.setFechaSeleccion(new Date()); // Asegúrate de que tengas este método en la entidad Preferencias

            // Registrar la preferencia en la base de datos
            preferenciasJpaController.create(preferencia);

            // Redirigir a la página o servlet de "userbooks" después de registrar la preferencia
            response.sendRedirect("userbooks");
        } catch (NumberFormatException e) {
            // Manejar el error si el ID del libro no es un número válido
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de ID de libro no válido.");
        } catch (Exception e) {   
            e.printStackTrace(); 
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al registrar la preferencia: " + e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
