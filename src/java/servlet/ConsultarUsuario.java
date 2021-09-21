/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.USUARIOS_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.USUARIOS;

/**
 *
 * @author AndresSaenz
 */
@WebServlet(name = "ConsultarUsuario", urlPatterns = {"/ConsultarUsuario"})
public class ConsultarUsuario extends HttpServlet {

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

        String Accion = request.getParameter("Actualizar");
        System.out.println("Accion " + Accion);

        String Identificacion = request.getParameter("IdConsultado");

        USUARIOS_DAO miUsuario_dao = new USUARIOS_DAO();
        USUARIOS usuario = null;

        usuario = miUsuario_dao.ConsultarUsuarios(Identificacion);

        if (Accion != null) {

            if (usuario != null) {

                response.sendRedirect("/Effective/vista/registroUsuarios.jsp?Nombres=" + usuario.getNombres_usuario() + "&"
                        + "Apellidos=" + usuario.getApellidos_usuario() + "&"
                        + "Clave=" + usuario.getClave() + "&"
                        + "Telefono=" + usuario.getTelefono_usuario() + "&"
                        + "Direccion=" + usuario.getDireccion_usuario() + "&"
                        + "Correo=" + usuario.getCorreo_usuario() + "&"
                        + "Identificacion=" + usuario.getIdentificacion_usuario() + "&"
                        + "Tipo_usuario=" + usuario.getTIPO_USUARIO_id_tipo_usuario() + "&"
                        + "Estado_usuario=" + usuario.getESTADO_USUARIO_id_estado_usuario() + "&"
                        + "Genero_usuario=" + usuario.getGENERO_USUARIO_id_genero_usuario() + "&"
                        + "Tipo_doc=" + usuario.getTIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario() + "&"
                        + "Vista=" + "Actualizar" + "&");
                System.out.println("Salio");

            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "No se ha podido relizar la consulta." + "\n" + "Por favor verificar la identificacion: " + Identificacion + "');");
                out.println("</script>");
            }

        } else {

            if (usuario != null) {

                String respuestaRegistrada = miUsuario_dao.EliminarUsuarios(usuario);
                if (respuestaRegistrada.length() == 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('" + "Eliminacion Realizada." + "');");

                        out.println("window.location.href = '/Effective/vista/registroUsuarios.jsp';");
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
