/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.GENERO_USUARIO_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GENERO_USUARIO;

/**
 *
 * @author AndresSalazar
 */
@WebServlet(name = "modificarGenero", urlPatterns = {"/modificarGenero"})
public class modificarGenero extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("genero_usuario_id");
        String genero_usuario = request.getParameter("genero_usuario");
        String Bandera = request.getParameter("BanderaReg");

        GENERO_USUARIO mi_genero_usuario = new GENERO_USUARIO();
        GENERO_USUARIO_DAO mi_genero_usuario_dao = new GENERO_USUARIO_DAO();

        mi_genero_usuario.setDescripcion_genero_usuario(genero_usuario);
        mi_genero_usuario.setId_genero_usuario(id);

        System.out.println("Identificacion: " + id);
        String respuestaRegistrada = mi_genero_usuario_dao.ModificarGenero_usuario(mi_genero_usuario);
        if (respuestaRegistrada.length() == 0) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('" + "Actualización Realizada." + "');");
// Se agrega el redreccionamiento la url para visualice la tabla.
            out.println("window.location.href = '/Effective/vista/registrargenerousuario.jsp';");
            out.println("</script>");
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('" + "No se ha podido relizar la actualizacion." + "\n" + respuestaRegistrada + "');");

            out.println("</script>");

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
