/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.PRODUCTOS_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.PRODUCTOS;

/**
 *
 * @author AndresSaenz
 */
@WebServlet(name = "ActualizarProductos", urlPatterns = {"/ActualizarProductos"})
public class ActualizarProductos extends HttpServlet {

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
        
         System.out.println("identificacion_producto: " + identificacion_producto);
        String respuestaRegistrada = mi_producto_dao.ModificarProductos(mi_producto);
        if (respuestaRegistrada.length() == 0) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('" + "Actualización Realizada." + "');");
// Se agrega el redreccionamiento la url para visualice la tabla.
            out.println("window.location.href = '/Effective/vista/registrarproductos.jsp';");
            out.println("</script>");
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('" + "No se ha podido relizar la actualizacion." + "\n" + respuestaRegistrada + "');");

            out.println("</script>");
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
