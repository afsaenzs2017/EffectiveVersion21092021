/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.PRODUCTOS_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.PRODUCTOS;
import controlador.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author AndresSaenz
 */
@WebServlet(name = "RegistrarProductos", urlPatterns = {"/RegistrarProductos"})
public class RegistrarProductos extends HttpServlet {

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

        String nombre_producto = request.getParameter("nombre_producto");
        String identificacion_producto = request.getParameter("identificacion_producto");
        String precio_producto = request.getParameter("precio_producto");
        String descripcion_producto = request.getParameter("descripcion_producto");
        String cantidad_producto = request.getParameter("cantidad_producto");
        String iva_producto = request.getParameter("iva_producto");
        String UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto = request.getParameter("UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto");
        String PROVEEDORES_id_proveedor = request.getParameter("PROVEEDORES_id_proveedor");
        String CATEGORIA_PRODUCTO_id_categoria_producto = request.getParameter("CATEGORIA_PRODUCTO_id_categoria_producto");
        String ESTADO_PRODUCTO_id_estado_producto = request.getParameter("ESTADO_PRODUCTO_id_estado_producto");
        String MARCA_PRODUCTO_id_marca_producto = request.getParameter("MARCA_PRODUCTO_id_marca_producto");
        String imagen_producto = request.getParameter("imagen_producto");
        

        String BanderaEstado = request.getParameter("banderaRegistro");

        
        int precio = Integer.parseInt(precio_producto);
        int iva = Integer.parseInt(iva_producto);
        int cantidad = Integer.parseInt(cantidad_producto);
        int UnidadMedida = Integer.parseInt(UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto);
        int Proveedores = Integer.parseInt(PROVEEDORES_id_proveedor);
        int Categoria = Integer.parseInt(CATEGORIA_PRODUCTO_id_categoria_producto);
        int EstadoProducto = Integer.parseInt(ESTADO_PRODUCTO_id_estado_producto);
        int MarcaProducto = Integer.parseInt(MARCA_PRODUCTO_id_marca_producto);
        

        PRODUCTOS_DAO mi_producto_dao = new PRODUCTOS_DAO();
        PRODUCTOS mi_producto = new PRODUCTOS();

        mi_producto.setNombre_producto(nombre_producto);
        mi_producto.setIdentificacion_producto(identificacion_producto);
        mi_producto.setPrecio_producto(precio);
        mi_producto.setDescripcion_producto(descripcion_producto);
        mi_producto.setCantidad_producto(cantidad);
        mi_producto.setIva_producto(iva);
        mi_producto.setUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto(UnidadMedida);
        mi_producto.setPROVEEDORES_id_proveedor(Proveedores);
        mi_producto.setCATEGORIA_PRODUCTO_id_categoria_producto(Categoria);
        mi_producto.setESTADO_PRODUCTO_id_estado_producto(EstadoProducto);
        mi_producto.setMARCA_PRODUCTO_id_marca_producto(MarcaProducto);
        mi_producto.setIMAGEN_PRODUCTO(imagen_producto);
       

        System.out.println("El valor es " + BanderaEstado);
        if (BanderaEstado.equals("Correcto")) {
            String respuestaRegistrada = mi_producto_dao.AdicionarProveedores(mi_producto);
            System.out.println("Res " + respuestaRegistrada);
            System.out.println("Res " + respuestaRegistrada.length());
            if (respuestaRegistrada.length() == 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "Producto registrado con éxito." + "');");
                
                //Registrar Kardex
                conexion con = new conexion();
                Connection cn = con.getConn();
                String query="";
                int n=0,codmov=0,coddetmov=0,codprod=0,cantidadpro=0;
                try 
                {
                //Fecha actual
                Calendar fecha = new GregorianCalendar();
                int ano = fecha.get(Calendar.YEAR);
                int mes = fecha.get(Calendar.MONTH);
                int dia = fecha.get(Calendar.DAY_OF_MONTH);
                String mesaux = String.valueOf(mes+1);
                if( mesaux.length() == 1)
                  {
                  mesaux="0"+String.valueOf(mes+1);          
                  }                  
                String fechaact=String.valueOf(ano)+"-"+mesaux+"-"+String.valueOf(dia);
                //Crear primer movimiento
                query="INSERT INTO movimiento(numero_de_documento,fecha,subtotal,total_iva,total_factura,FORMAS_DE_PAGO_id_forma_de_pago,TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable,CLIENTES_idCliente,estado_movimiento) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst=cn.prepareStatement(query);
                pst.setString(1,"Inicio");
                pst.setString(2,fechaact);
                pst.setFloat(3,0);
                pst.setFloat(4,0);
                pst.setFloat(5,0);
                pst.setInt(6,1);
                pst.setInt(7,1);
                pst.setInt(8,1);
                pst.setInt(9,3);
                n=pst.executeUpdate();    
                
                //Crear primer detalle movimiento
                PreparedStatement stmtr = cn.prepareStatement("select * from movimiento order by id_movimiento desc limit 1");
                ResultSet rsr = stmtr.executeQuery();
                if(rsr.next())
                  {
                  codmov = rsr.getInt("id_movimiento");
                  }     
                stmtr = cn.prepareStatement("select * from productos order by id_producto desc limit 1");
                rsr = stmtr.executeQuery();
                if(rsr.next())
                  {
                  codprod = rsr.getInt("id_producto");
                  cantidadpro= rsr.getInt("cantidad_producto");
                  }
                
                //Actualizar Documento del Movimiento
                query="Update movimiento set numero_de_documento=? where id_movimiento = ?";
                pst=cn.prepareStatement(query);
                pst.setString(1,"Inicio "+codprod);
                pst.setInt(2,codmov);
                n=pst.executeUpdate();
                
                query="INSERT INTO detalle_movimiento(cantidad,precio_productos,subtotal_productos,iva_productos,total_productos,PRODUCTOS_id_producto,MOVIMIENTO_id_movimiento) VALUES (?,?,?,?,?,?,?)";
                pst=cn.prepareStatement(query);
                pst.setInt(1,cantidadpro);
                pst.setFloat(2,0);
                pst.setFloat(3,0);
                pst.setFloat(4,0);
                pst.setFloat(5,0);
                pst.setInt(6,codprod);
                pst.setInt(7,codmov);
                n=pst.executeUpdate();
                
                //Obtener ultimo registro para el kardex
                stmtr = cn.prepareStatement("select * from detalle_movimiento order by id_detalle_movimiento desc limit 1");
                rsr = stmtr.executeQuery();
                if(rsr.next())
                 {
                 coddetmov = rsr.getInt("id_detalle_movimiento");
                 }
                
                //Crear primer inventario
                query="Insert into inventario(entradas,salidas,saldo,PRODUCTOS_id_producto,DETALLE_MOVIMIENTO_id_detalle_movimiento) values (?,?,?,?,?) ";
                pst=cn.prepareStatement(query);
                pst.setInt(1,mi_producto.getCantidad_producto());
                pst.setInt(2,0);
                pst.setInt(3,cantidadpro);
                pst.setInt(4,codprod);
                pst.setInt(5,coddetmov);
                n=pst.executeUpdate();

                }
                catch (SQLException ex) 
                 {
                   out.println("Error al insertar " + ex.getMessage());
                 }
                
                out.println("window.location.href = '/Effective/vista/registrarproductos.jsp';");
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
