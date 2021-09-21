/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.TIPO_USUARIO_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TIPO_USUARIO;

/**
 *
 * @author AndresSalazar
 */
@WebServlet(name = "consultarRoles", urlPatterns = {"/consultarRoles"})
public class consultarRoles extends HttpServlet {

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
        /* TODO output your page here. You may use following sample code. */

        String Accion = request.getParameter("Actualizar");
        System.out.println("Accion " + Accion);

        String Identificacion = request.getParameter("IdConsultado");

        TIPO_USUARIO_DAO mitipo_dao = new TIPO_USUARIO_DAO();
        TIPO_USUARIO tipo_usuario = null;

        tipo_usuario = mitipo_dao.ConsultarTipo_usuario(Identificacion);

        if (Accion != null) {

            if (tipo_usuario != null) {

                response.sendRedirect("/Effective/vista/registrarroles.jsp?descripcion=" + tipo_usuario.getDescripcion_tipo_usuario() + "&"
                        + "Id=" + tipo_usuario.getId_tipo_usuario() + "&"
                        + "Vista=" + "Actualizar" + "&");
                System.out.println("Salio");

            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "No se ha podido relizar la consulta." + "\n" + "Por favor verificar la identificacion: " + Identificacion + "');");
                out.println("</script>");
            }

        } else {

            if (tipo_usuario != null) {

                String respuestaRegistrada = mitipo_dao.EliminarTipo_usuario(tipo_usuario);
                if (respuestaRegistrada.length() == 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('" + "Eliminacion Realizada." + "');");
                    out.println("window.location.href = '/Effective/vista/registrarroles.jsp';");
                    out.println("</script>");

                } else {

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('" + "No se ha podido relizar la eliminacion." + respuestaRegistrada.replace("'", "") + "');");
                    out.println("</script>");
                }
            } else {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "No se ha podido relizar la consulta." + "\n" + "Por favor verificar la identificacion: " + Identificacion + "');");
                out.println("</script>");

            }
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
