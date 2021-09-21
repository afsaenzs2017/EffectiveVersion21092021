/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.TIPO_DE_DOCUMENTO_CONTABLE_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TIPO_DE_DOCUMENTO_CONTABLE;

/**
 *
 * @author AndresSalazar
 */
@WebServlet(name = "consultarDocContable", urlPatterns = {"/consultarDocContable"})
public class consultarDocContable extends HttpServlet {

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

        TIPO_DE_DOCUMENTO_CONTABLE_DAO midoc_dao = new TIPO_DE_DOCUMENTO_CONTABLE_DAO();
        TIPO_DE_DOCUMENTO_CONTABLE tipo_doc = null;

        tipo_doc = midoc_dao.ConsultarTipo_de_documento_contable(Identificacion);

        if (Accion != null) {

            if (tipo_doc != null) {

                response.sendRedirect("/Effective/vista/registrarDocContable.jsp?descripcion=" + tipo_doc.getDescripcion() + "&"
                        + "naturaleza=" + tipo_doc.getNaturaleza() + "&"
                        + "id=" + tipo_doc.getId_tipo_de_documento_contable() + "&"
                        + "Vista=" + "Actualizar" + "&");
                System.out.println("Salio");

            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "No se ha podido relizar la consulta." + "\n" + "Por favor verificar la identificacion: " + Identificacion + "');");
                out.println("</script>");
            }

        } else {

            if (tipo_doc != null) {

                String respuestaRegistrada = midoc_dao.EliminarTipo_documento_contable(tipo_doc);
                if (respuestaRegistrada.length() == 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('" + "Eliminacion Realizada." + "');");
                    out.println("window.location.href = '/Effective/vista/registrarDocContable.jsp';");
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
