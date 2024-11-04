/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import controladores.LibrosJpaController;
import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author steve_y
 */
@WebServlet(name = "EliminarLibro", urlPatterns = {"/EliminarLibro"})
public class EliminarLibro extends HttpServlet {
    
    private LibrosJpaController librosController;

    @Override
    public void init() {
        librosController = new LibrosJpaController();
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
            out.println("<title>Servlet EliminarLibro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EliminarLibro at " + request.getContextPath() + "</h1>");
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
        // Obtener el ID del libro a eliminar
        String idStr = request.getParameter("id");

        try {
            Integer id = Integer.valueOf(idStr);
            // Llamar al método destroy del controlador para eliminar el libro
            librosController.destroy(id);
            // Redirigir a la página donde se muestra la lista de libros
            response.sendRedirect("AdminBooks");
        } catch (NumberFormatException e) {
            // Manejo de errores si el ID no es válido
            Logger.getLogger(EliminarLibro.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "El ID del libro es inválido.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (NonexistentEntityException e) {
            // Manejo de errores si el libro no existe
            Logger.getLogger(EliminarLibro.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "El libro no existe.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (IllegalOrphanException e) {
            // Manejo de errores si hay dependencias que impiden la eliminación
            Logger.getLogger(EliminarLibro.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "No se puede eliminar el libro debido a dependencias.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (IOException e) {
            // Manejo de errores genéricos
            Logger.getLogger(EliminarLibro.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "Ocurrió un error al eliminar el libro.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
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
