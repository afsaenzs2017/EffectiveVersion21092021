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
 * @author andre
 */
@WebServlet(name = "RegistroUsuarios", urlPatterns = {"/RegistroUsuarios"})
public class RegistroUsuarios extends HttpServlet {

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

        String nombres_usuario = request.getParameter("nombres_usuario");
        String apellidos_usuario = request.getParameter("apellidos_usuario");
        String identificacion_usuario = request.getParameter("identificacion_usuario");
        String clave = request.getParameter("clave");
        String telefono_usuario = request.getParameter("telefono_usuario");
        String direccion_usuario = request.getParameter("direccion_usuario");
        String correo_usuario = request.getParameter("correo_usuario");
        String TIPO_USUARIO_id_tipo_usuario = request.getParameter("TIPO_USUARIO_id_tipo_usuario");
        String ESTADO_USUARIO_id_estado_usuario = request.getParameter("ESTADO_USUARIO_id_estado_usuario");
        String GENERO_USUARIO_id_genero_usuario = request.getParameter("GENERO_USUARIO_id_genero_usuario");
        String TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario = request.getParameter("TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario");
        String BanderaEstado = request.getParameter("banderaRegistro");

        int EstadoUsuario = Integer.parseInt(ESTADO_USUARIO_id_estado_usuario);
        int TipoUsuario = Integer.parseInt(TIPO_USUARIO_id_tipo_usuario);
        int TipoDocumento = Integer.parseInt(TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario);
        int TipoGenero = Integer.parseInt(GENERO_USUARIO_id_genero_usuario);

        USUARIOS_DAO mi_usuario_dao = new USUARIOS_DAO();
        USUARIOS mi_usuario = new USUARIOS();

        mi_usuario.setNombres_usuario(nombres_usuario);
        mi_usuario.setApellidos_usuario(apellidos_usuario);
        mi_usuario.setIdentificacion_usuario(identificacion_usuario);
        mi_usuario.setClave(clave);
        mi_usuario.setTelefono_usuario(telefono_usuario);
        mi_usuario.setDireccion_usuario(direccion_usuario);
        mi_usuario.setCorreo_usuario(correo_usuario);
        mi_usuario.setTIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario(TipoDocumento);
        mi_usuario.setTIPO_USUARIO_id_tipo_usuario(TipoUsuario);
        mi_usuario.setESTADO_USUARIO_id_estado_usuario(EstadoUsuario);
        mi_usuario.setGENERO_USUARIO_id_genero_usuario(TipoGenero);

        System.out.println("El valor es " + BanderaEstado);
        if (BanderaEstado.equals("Correcto")) {
            String respuestaRegistrada = mi_usuario_dao.AdicionarUsuarios(mi_usuario);
            System.out.println("Res " + respuestaRegistrada);
            System.out.println("Res " + respuestaRegistrada.length());
            if (respuestaRegistrada.length() == 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "Usuario Registrado con éxito." + "');");
                out.println("window.location.href = '/Effective/vista/registroUsuarios.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                //out.println("alert('" + respuestaRegistrada + "');");
                out.println("alert('" + "Error encontrado: " + respuestaRegistrada.replace("'", "") + "');");
                out.println("window.history.back();");
                out.println("</script>");

            }
        } else {
            System.out.println("El valor no es correcto " + BanderaEstado);
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
