/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.users;

import controladores.PreferenciasJpaController;
import controladores.UsuariosJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Preferencias;
import modelos.Usuarios;

/**
 *
 * @author steve_y
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {

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
            out.println("<title>Servlet Profile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Profile at " + request.getContextPath() + "</h1>");
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
        // Obtener el ID del usuario como Integer directamente de la sesión
        Integer userId = (Integer) request.getSession().getAttribute("id");

        // Verificar si el ID del usuario no es nulo
        if (userId != null) {
            UsuariosJpaController usuariosJpaController = new UsuariosJpaController();
            PreferenciasJpaController preferenciasJpaController = new PreferenciasJpaController();

            // Obtener el usuario correspondiente al ID
            Usuarios user = usuariosJpaController.findUsuarios(userId);

            // Establecer el usuario en el atributo de la solicitud
            request.setAttribute("user", user);

            // Obtener las preferencias del usuario
            List<Preferencias> preferenciasList = preferenciasJpaController.findPreferenciasByUsuarioId(userId);
            request.setAttribute("preferenciasList", preferenciasList);

            // Redirigir a la página de perfil del usuario
            RequestDispatcher dispatcher = request.getRequestDispatcher("users/profile.jsp");
            dispatcher.forward(request, response);
        } else {
            // Si el usuario no está autenticado, redirigir a la lista de usuarios
            RequestDispatcher dispatcher = request.getRequestDispatcher("userbooks");
            dispatcher.forward(request, response);
        }
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
        processRequest(request, response);
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
