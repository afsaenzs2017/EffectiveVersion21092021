/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.conexion;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DETALLE_MOVIMIENTO;
import modelo.MOVIMIENTO;
import controlador.conexion;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Luis Cubero
 */
@WebServlet(name = "GrabarFactura", urlPatterns = {"/GrabarFactura"})
public class GrabarFactura extends HttpServlet 
{
Statement stm;
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
            throws ServletException, IOException, SQLException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
        ArrayList<DETALLE_MOVIMIENTO> lista;
        HttpSession session=request.getSession();
        lista=(ArrayList<DETALLE_MOVIMIENTO>)session.getAttribute("carrito");
        conexion con = new conexion();
        Connection cn = con.getConn();
        String query,numero_de_documento = "101";
        int FORMAS_DE_PAGO_id_forma_de_pago=1,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable=1,CLIENTES_idCliente=1;
        int poriva,codmov=0,cantaux=0,coddetmov=0,cantpro=0;
        float ivac=0,totalc=0,subtotalc=0;
        String fecha,numdoc;
        Statement stm;
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registrar Factura</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<script>");
        totalc=Float.parseFloat(request.getParameter("txttotal"));
        subtotalc=Float.parseFloat(request.getParameter("txtsubtotal"));
        ivac=Float.parseFloat(request.getParameter("txtiva"));
        fecha=request.getParameter("txtfecha");
        numdoc=request.getParameter("txtdocumento");        
        String clpost = request.getParameter("Clientepost");
        String fppost = request.getParameter("Formapost");
        String tdpost = request.getParameter("Tipodpost");
            //out.println("alert('"+fppost+"');");
        out.println("</script>");
        CLIENTES_idCliente=Integer.parseInt(clpost);
        FORMAS_DE_PAGO_id_forma_de_pago=Integer.parseInt(fppost);
        TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable=Integer.parseInt(tdpost);
        //Registrar Cabecera del Movimiento
        query="INSERT INTO movimiento(numero_de_documento,fecha,subtotal,total_iva,total_factura,FORMAS_DE_PAGO_id_forma_de_pago,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable,CLIENTES_idCliente,estado_movimiento) VALUES (?,?,?,?,?,?,?,?,?)";
            try 
                {         
                PreparedStatement pst=cn.prepareStatement(query);
                pst.setString(1,numdoc);
                pst.setString(2,fecha);
                pst.setFloat(3,subtotalc);
                pst.setFloat(4,ivac);
                pst.setFloat(5,totalc);
                pst.setInt(6,FORMAS_DE_PAGO_id_forma_de_pago);
                pst.setInt(7,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable);
                pst.setInt(8,CLIENTES_idCliente);
                pst.setInt(9,1);
                int n=pst.executeUpdate();
                if (n>0)
                {
                    //Obtener el último registro para los detalles
                    PreparedStatement stmtr = cn.prepareStatement("select * from movimiento order by id_movimiento desc limit 1");
                    ResultSet rsr = stmtr.executeQuery();
                    if(rsr.next())
                     {
                     codmov = rsr.getInt("id_movimiento");
                     }
                   //Registrar detalles del movimiento
                   if (lista!=null)
                     {
                      for(DETALLE_MOVIMIENTO d: lista)
                        {
                            cantaux=0;
                            query="INSERT INTO detalle_movimiento(cantidad,precio_productos,subtotal_productos,iva_productos,total_productos,PRODUCTOS_id_producto,MOVIMIENTO_id_movimiento) VALUES (?,?,?,?,?,?,?)";
                            pst=cn.prepareStatement(query);
                            pst.setInt(1,d.getCantidad());
                            pst.setFloat(2,d.getPrecio_productos());
                            pst.setFloat(3,d.getSubtotal_productos());
                            pst.setFloat(4,d.getIva_productos());
                            pst.setFloat(5,d.getTotal_productos());
                            pst.setInt(6,d.getPRODUCTOS_id_producto());
                            pst.setInt(7,codmov);
                            n=pst.executeUpdate();
                            
                            //Obtener cantidad existente del producto
                            stmtr = cn.prepareStatement("select * from productos where id_producto = '"+d.getPRODUCTOS_id_producto()+"'");
                            rsr = stmtr.executeQuery();
                            if(rsr.next())
                             {
                             cantpro = rsr.getInt("cantidad_producto");
                             }
                            
                            //Actualizar Inventario
                            cantaux = cantpro - d.getCantidad();
                            query="Update productos set cantidad_producto = ? where id_producto = ?";
                            pst=cn.prepareStatement(query);
                            pst.setInt(1,cantaux);
                            pst.setInt(2,d.getPRODUCTOS_id_producto());
                            n=pst.executeUpdate();
                            
                            //Obtener ultimo registro para el kardex
                            stmtr = cn.prepareStatement("select * from detalle_movimiento order by id_detalle_movimiento desc limit 1");
                            rsr = stmtr.executeQuery();
                            if(rsr.next())
                             {
                             coddetmov = rsr.getInt("id_detalle_movimiento");
                             }
                            
                            //Actualizar Kardex
                            query="Insert into inventario(entradas,salidas,saldo,PRODUCTOS_id_producto,DETALLE_MOVIMIENTO_id_detalle_movimiento) values (?,?,?,?,?) ";
                            pst=cn.prepareStatement(query);
                            pst.setInt(1,0);
                            pst.setInt(2,d.getCantidad());
                            pst.setInt(3,cantaux);
                            pst.setInt(4,d.getPRODUCTOS_id_producto());
                            pst.setInt(5,coddetmov);
                            n=pst.executeUpdate();
                            
                        }
                     
                     out.println("<script>");
                         out.println("alert('Venta Registrada');");
                         //Borrar Carrito
                         request.getSession().removeAttribute("carrito");
                         out.println("window.location.href = '../../Effective/vista/gestionarMovimientos.jsp';");
                     out.println("</script>");
                     } 
                     
                   }
                   else
                   {
                   out.println("Error a la hora de registrar el movimiento");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GrabarFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GrabarFactura.class.getName()).log(Level.SEVERE, null, ex);
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
