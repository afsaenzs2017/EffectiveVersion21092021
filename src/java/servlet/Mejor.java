/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Cubero
 */
@WebServlet(name = "Mejor", urlPatterns = {"/Mejor"})
public class Mejor extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Mejor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Mejor at " + request.getContextPath() + "</h1>");
            out.println("<script>");
                out.println("alert('Hola');");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            int FORMAS_DE_PAGO_id_forma_de_pago=2 ,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable=2, CLIENTES_idCliente=2;
            conexion con = new conexion();
            Connection cn = con.getConn();
             //stm = cn.createStatement();
                //query="insert into movimiento(numero_documento,fecha,total_factura,FORMAS_DE_PAGO_id_forma_de_pago,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable,CLIENTES_idCliente) values(1,1,'"+session.getAttribute("Ftotal")+"',1,1,'"+session.getAttribute("FCliente")+"')";                
               // int cli = Integer.parseInt((String) session.getAttribute("FCliente"));
                //query="insert movimiento(FORMAS_DE_PAGO_id_forma_de_pago,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable,CLIENTES_idCliente) values(1,1,1)";                
                String query="INSERT INTO movimiento(FORMAS_DE_PAGO_id_forma_de_pago ,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable, CLIENTES_idCliente) VALUES (?,?,?)";
                //'"+cli+"'
                 try 
                 {
                 PreparedStatement pst=cn.prepareStatement(query);
                 FORMAS_DE_PAGO_id_forma_de_pago=1;
                 TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable=1;
                 CLIENTES_idCliente=1;
                 //ResultSet rst=stm.executeQuery(query);
                 //pst.setString(1,numero_de_documento);
                 pst.setInt(1,FORMAS_DE_PAGO_id_forma_de_pago);
                 pst.setInt(2,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable);
                 pst.setInt(3,CLIENTES_idCliente);
                   
                 int n=pst.executeUpdate();
                 if (n>0){
                  //response.sendRedirect("insertarlibros.jsp");        
                 }else{
                      out.println("Error a la hora de registrar el libro dos");
                 }
                 }
                 catch (SQLException ex) 
                 {
                   out.println("Error al insertar " + ex.getMessage());
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
