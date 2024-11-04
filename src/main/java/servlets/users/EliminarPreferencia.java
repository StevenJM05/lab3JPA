/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.users;

import controladores.PreferenciasJpaController;
import controladores.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author steve_y
 */
@WebServlet(name = "EliminarPreferencia", urlPatterns = {"/EliminarPreferencia"})
public class EliminarPreferencia extends HttpServlet {

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
            out.println("<title>Servlet EliminarPreferencia</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EliminarPreferencia at " + request.getContextPath() + "</h1>");
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
        // Obtener el ID de la preferencia a eliminar
        String preferenciaIdParam = request.getParameter("preferenciaId");

        if (preferenciaIdParam != null) {
            int preferenciaId = Integer.parseInt(preferenciaIdParam);
            PreferenciasJpaController preferenciasJpaController = new PreferenciasJpaController();
            
            try {
                preferenciasJpaController.destroy(preferenciaId); // Elimina la preferencia por ID
                // Redirigir a la página de perfil o la misma página para ver la lista actualizada
                response.sendRedirect("Profile"); // Cambia la ruta según sea necesario
            } catch (NonexistentEntityException e) {
                request.setAttribute("error", "La preferencia no existe.");
                request.getRequestDispatcher("Profile").forward(request, response);
            } catch (IOException e) {
                request.setAttribute("error", "Ocurrió un error al eliminar la preferencia.");
                request.getRequestDispatcher("users/profile.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("users/profile.jsp"); // Redirigir si no hay ID
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
