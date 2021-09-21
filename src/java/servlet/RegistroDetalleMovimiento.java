/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.DETALLE_MOVIMIENTO_DAO;
import controlador.conexion;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
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

/**
 *
 * @author AndresSaenz
 */
@WebServlet(name = "RegistroDetalleMovimiento", urlPatterns = {"/RegistroDetalleMovimiento"})
public class RegistroDetalleMovimiento extends HttpServlet {

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
        this.doPost(request, response);
        String Cantidad = request.getParameter("cantidad");
        String Precio_productos = request.getParameter("precio_productos");
        String Iva_productos = request.getParameter("iva_productos");
        String Total_productos = request.getParameter("total_productos");
        String PRODUCTOS_id_producto = request.getParameter("PRODUCTOS_id_producto");
        String MOVIMIENTO_id_movimiento = request.getParameter("MOVIMIENTO_id_movimiento");
        String BanderaEstado = request.getParameter("banderaRegistro");
        
        int cantidad = Integer.parseInt(Cantidad);
        float precio_productos = Integer.parseInt(Precio_productos);
        int iva_productos = Integer.parseInt(Iva_productos);
        float total_productos = Integer.parseInt(Total_productos);
        int id_producto = Integer.parseInt(PRODUCTOS_id_producto);
        int id_movimiento = Integer.parseInt(MOVIMIENTO_id_movimiento);
        
        DETALLE_MOVIMIENTO_DAO detalleMovimientoDao = new DETALLE_MOVIMIENTO_DAO();
        DETALLE_MOVIMIENTO mi_detalle_movimiento = new DETALLE_MOVIMIENTO();
        
        mi_detalle_movimiento.setCantidad(cantidad);
        mi_detalle_movimiento.setPrecio_productos(precio_productos);
        mi_detalle_movimiento.setIva_productos(iva_productos);
        mi_detalle_movimiento.setTotal_productos(total_productos);
        mi_detalle_movimiento.setPRODUCTOS_id_producto(id_producto);
        mi_detalle_movimiento.setMOVIMIENTO_id_movimiento(id_movimiento);
        
        System.out.println("El valor es " + BanderaEstado);
        if (BanderaEstado.equals("Correcto")) {
            String respuestaRegistrada = detalleMovimientoDao.AdicionarDetalle_movimiento(mi_detalle_movimiento);
            System.out.println("Res " + respuestaRegistrada);
            System.out.println("Res " + respuestaRegistrada.length());
            if (respuestaRegistrada.length() == 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "Detalle movimiento Registrado con éxito." + "');");
                out.println("window.location.href = '/Effective/menu.jsp';");
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
        ArrayList<DETALLE_MOVIMIENTO> lista;
        HttpSession session=request.getSession();
        lista=(ArrayList<DETALLE_MOVIMIENTO>)session.getAttribute("carrito");
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        String query;
        int poriva,codmov=0;
        double valoriva=0;
        Statement stm;
        try {
            stm = miConexion.getConn().createStatement();
            query="insert into movimiento(numero_documento,fecha,total_factura,FORMAS_DE_PAGO_id_forma_de_pago,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable,CLIENTES_idCliente) values(1,1,'"+session.getAttribute("Ftotal")+"',1,1,'"+session.getAttribute("FCliente")+"')";                
            ResultSet rst=stm.executeQuery(query);
            
            query="select * from movimiento order by id_movimiento desc limit 1";
            rst=stm.executeQuery(query);
            while(rst.next())
            {
                codmov=rst.getInt(1);
            }
            session.getAttribute("FCliente");
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDetalleMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (lista!=null)
         {
            for(DETALLE_MOVIMIENTO d: lista)
            {
            
                try 
                {
                    stm = miConexion.getConn().createStatement();
                    poriva=(int) d.getIva_productos();
                    valoriva = d.getTotal_productos()/(1+(poriva/100));
                    query="insert into DetalleMovimiento(cantidad,precio_productos,iva_productos,total_productos,PRODUCTOS_id_producto,MOVIMIENTO_id_movimiento) values('"+d.getCantidad()+"','"+d.getPrecio_productos()+"','"+valoriva+"','"+d.getTotal_productos()+"','"+d.getPRODUCTOS_id_producto()+"','"+codmov+"')";
                    ResultSet rst=stm.executeQuery(query);
                    request.getSession().removeAttribute("carrito");
                    request.getRequestDispatcher("gestionMovimientos.jsp").forward(request,response);
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(RegistroDetalleMovimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.println("window.location.href = '/Effective/gestionarMovimiento.jsp';");
            }
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
