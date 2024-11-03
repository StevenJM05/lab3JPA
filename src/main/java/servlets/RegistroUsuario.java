/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import controladores.UsuariosJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Usuarios;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "RegistroUsuario", urlPatterns = {"/RegistroUsuario"})
public class RegistroUsuario extends HttpServlet {

    private UsuariosJpaController usuariosController;
    
     @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        usuariosController = new UsuariosJpaController(emf);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistroUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("name");
        String apellido = request.getParameter("lastname");
        String email = request.getParameter("email");
        String clave = request.getParameter("password");
        
        String hashedClave = BCrypt.hashpw(clave, BCrypt.gensalt());
        
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setClave(hashedClave);
        nuevoUsuario.setTipo("user");
        
        try {
            usuariosController.create(nuevoUsuario);
            response.sendRedirect("login.jsp");  
        } catch (IOException e) {
            request.setAttribute("error", "Error al registrar usuario: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
