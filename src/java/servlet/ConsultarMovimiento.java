/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.MOVIMIENTO_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.MOVIMIENTO;

/**
 *
 * @author AndresSaenz
 */
@WebServlet(name = "ConsultarMovimiento", urlPatterns = {"/ConsultarMovimiento"})
public class ConsultarMovimiento extends HttpServlet {

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
        
        String numero_documento = request.getParameter("IdConsultado");
        
        MOVIMIENTO_DAO mimovimiento_dao = new MOVIMIENTO_DAO();
        MOVIMIENTO movimiento = null;
        
        movimiento = mimovimiento_dao.ConsultarMovimiento(numero_documento);
        
        if (Accion != null) {

            if (movimiento != null) {

                response.sendRedirect("/Effective/vista/RegistroMovimiento.jsp?Numero_Documento=" + movimiento.getNumero_de_documento() + "&"
                        + "Fecha=" + movimiento.getFecha() + "&"
                        + "Subtotal=" + movimiento.getSubtotal() + "&"
                        + "Total_Iva=" + movimiento.getTotal_iva() + "&"
                        + "Total_Factura=" + movimiento.getTotal_factura() + "&"
                        + "Formas_Pago=" + movimiento.getFORMAS_DE_PAGO_id_forma_de_pago() + "&"
                        + "Tipo_Documento=" + movimiento.getTIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable() + "&"
                        + "Usuarios=" + movimiento.getCLIENTES_idCliente() + "&"
                        + "Vista=" + "Actualizar" + "&");
                System.out.println("Salio");

            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "No se ha podido relizar la consulta." + "\n" + "Por favor verificar la identificacion: " + numero_documento + "');");
                out.println("</script>");
            }

        } else {

            if (movimiento != null) {

                String respuestaRegistrada = mimovimiento_dao.EliminarMovimiento(movimiento);
                if (respuestaRegistrada.length() == 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('" + "Eliminacion Realizada." + "');");

                        out.println("window.location.href = '/Effective/vista/RegistroMovimiento.jsp';");
                    out.println("</script>");

                } else {

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('" + "No se ha podido relizar la eliminacion." + respuestaRegistrada.replace("'", "") + "');");
                    out.println("</script>");
                }
            } else {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "No se ha podido relizar la consulta." + "\n" + "Por favor verificar la identificacion: " + numero_documento + "');");
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
