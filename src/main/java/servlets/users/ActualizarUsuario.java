/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.users;

import controladores.UsuariosJpaController;
import controladores.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Usuarios;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author steve_y
 */
@WebServlet(name = "ActualizarUsuario", urlPatterns = {"/ActualizarUsuario"})
public class ActualizarUsuario extends HttpServlet {

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
            out.println("<title>Servlet ActualizarUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarUsuario at " + request.getContextPath() + "</h1>");
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
        // Obtener los datos del formulario
        String userIdParam = request.getParameter("userId");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); 

        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            UsuariosJpaController usuariosJpaController = new UsuariosJpaController();

            try {
                // Buscar el usuario existente
                Usuarios user = usuariosJpaController.findUsuarios(userId);

                if (user != null) {
                    // Actualizar los campos del usuario
                    user.setNombre(nombre);
                    user.setApellido(apellido);
                    user.setEmail(email);
                    if (!password.isEmpty()) { 
                        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                        user.setClave(hashedPassword);
                    }

                    // Actualizar el usuario en la base de datos
                    usuariosJpaController.edit(user);

                    // Redirigir de nuevo al perfil del usuario
                    response.sendRedirect("Profile");
                } else {
                    request.setAttribute("error", "El usuario no existe.");
                    request.getRequestDispatcher("Profile").forward(request, response);
                }
            } catch (NonexistentEntityException e) {
                request.setAttribute("error", "El usuario no existe.");
                request.getRequestDispatcher("Profile").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", "Ocurrió un error al actualizar la información.");
                request.getRequestDispatcher("Profile").forward(request, response);
            }
        } else {
            response.sendRedirect("Profile"); // Redirigir si no hay ID
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
