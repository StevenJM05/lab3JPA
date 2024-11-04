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
import javax.servlet.http.HttpSession;
import modelos.Usuarios;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author steve_y
 */
@WebServlet(name = "LoginUsuario", urlPatterns = {"/LoginUsuario"})
public class LoginUsuario extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginUsuario at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        String clave = request.getParameter("password");
        
        try{
            Usuarios usuario = usuariosController.findUsuarioByEmail(email);
            if(usuario != null && BCrypt.checkpw(clave, usuario.getClave())){
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                
                if("admin".equals(usuario.getTipo())){
                    response.sendRedirect("AdminBooks");
                }else{
                    response.sendRedirect("userbooks");
                }
            }else{
                request.setAttribute("error", "Email o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }catch(IOException | ServletException e){
             request.setAttribute("error", "Error al procesar el login: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
