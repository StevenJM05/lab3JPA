/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import controladores.LibrosJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Libros;

/**
 *
 * @author steve_y
 */
@WebServlet(name = "AgregarLibro", urlPatterns = {"/AgregarLibro"})
public class AgregarLibro extends HttpServlet {
    
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
            out.println("<title>Servlet AgregarLibro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarLibro at " + request.getContextPath() + "</h1>");
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
                // Obtener los parámetros del formulario
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String genero = request.getParameter("genero");

        // Crear una nueva instancia de Libros
        Libros libro = new Libros();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setGenero(genero);

        try {
            // Persistir el libro utilizando el controlador
            librosController.create(libro);
            // Redirigir a la página donde se muestra la lista de libros
            response.sendRedirect("AdminBooks");
        } catch (IOException e) {
            
            Logger.getLogger(AgregarLibro.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "Ocurrió un error al agregar el libro.");
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
