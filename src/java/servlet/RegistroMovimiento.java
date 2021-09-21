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
 * @author andre
 */
@WebServlet(name = "RegistroMovimiento", urlPatterns = {"/RegistroMovimiento"})
public class RegistroMovimiento extends HttpServlet {

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
            
        String numero_de_documento =request.getParameter("numero_de_documento");
        String fecha =request.getParameter("fecha");
        String subtotal =request.getParameter("subtotal");
        String total_iva =request.getParameter("total_iva");
        String descuento =request.getParameter("descuento");
        String total_factura =request.getParameter("total_factura");
        String FORMAS_DE_PAGO_id_forma_de_pago =request.getParameter("FORMAS_DE_PAGO_id_forma_de_pago");
        String TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable =request.getParameter("TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable");
        String USUARIOS_id_usuario =request.getParameter("USUARIOS_id_usuario");
        String BanderaEstado = request.getParameter("banderaRegistro");
        
     
        int SubTotal = Integer.parseInt(subtotal);
        int TotalIva = Integer.parseInt(total_iva);
        int Descuento = Integer.parseInt(descuento);
        int TotalFactura = Integer.parseInt(total_factura);
        int FormasdePago = Integer.parseInt(FORMAS_DE_PAGO_id_forma_de_pago);
        int TipodeDocumentoContable = Integer.parseInt(TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable);
        int Clientes = Integer.parseInt(USUARIOS_id_usuario);
        
        
        MOVIMIENTO_DAO mi_movimiento_dao = new MOVIMIENTO_DAO();
        MOVIMIENTO mi_movimiento = new MOVIMIENTO(); 
        
        mi_movimiento.setNumero_de_documento(numero_de_documento);
        mi_movimiento.setFecha(fecha);
        mi_movimiento.setSubtotal(SubTotal);
        mi_movimiento.setTotal_iva(TotalIva);
        mi_movimiento.setTotal_factura(TotalFactura);
        mi_movimiento.setFORMAS_DE_PAGO_id_forma_de_pago(FormasdePago);
        mi_movimiento.setTIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable(TipodeDocumentoContable);
        mi_movimiento.setCLIENTES_idCliente(Clientes);
        
        System.out.println("El valor es " + BanderaEstado);
        if (BanderaEstado.equals("Correcto")) {
            String respuestaRegistrada = mi_movimiento_dao.Adicionarmovimiento(mi_movimiento);
            System.out.println("Res " + respuestaRegistrada);
            System.out.println("Res " + respuestaRegistrada.length());
            if (respuestaRegistrada.length() == 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "Movimiento Registrado con éxito." + "');");
                out.println("window.location.href = '/Effective/vista/RegistroMovimiento.jsp';");
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
