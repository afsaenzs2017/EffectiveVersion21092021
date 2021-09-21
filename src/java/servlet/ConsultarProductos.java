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
@WebServlet(name = "ConsultarProductos", urlPatterns = {"/ConsultarProductos"})
public class ConsultarProductos extends HttpServlet {

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

        PRODUCTOS_DAO miProducto_dao = new PRODUCTOS_DAO();
        PRODUCTOS producto = null;

        producto = miProducto_dao.ConsultarProductos(Identificacion);

        if (Accion != null) {

            if (producto != null) {

                response.sendRedirect("/Effective/vista/registrarproductos.jsp?Nombres=" + producto.getNombre_producto() + "&"
                        + "Identificacion=" + producto.getIdentificacion_producto() + "&"
                        + "Precio=" + producto.getPrecio_producto() + "&"
                        + "Descripcion=" + producto.getDescripcion_producto()+ "&"
                        + "Cantidad=" + producto.getCantidad_producto()+ "&"
                        + "Iva=" + producto.getIva_producto() + "&"
                        + "UnidadMedida=" + producto.getUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto() + "&"
                        + "Proveedores=" + producto.getPROVEEDORES_id_proveedor() + "&"
                        + "Categoria_producto=" + producto.getCATEGORIA_PRODUCTO_id_categoria_producto() + "&"
                        + "Estado_producto=" + producto.getESTADO_PRODUCTO_id_estado_producto() + "&"
                        + "Marca_producto=" + producto.getMARCA_PRODUCTO_id_marca_producto() + "&"    
                        + "Imagen=" + producto.getIMAGEN_PRODUCTO() + "&"
                        + "Vista=" + "Actualizar" + "&");
                System.out.println("Salio");

            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "No se ha podido relizar la consulta." + "\n" + "Por favor verificar la identificacion: " + Identificacion + "');");
                out.println("</script>");
            }

        } else {

            if (producto != null) {

                String respuestaRegistrada = miProducto_dao.EliminarProductos(producto);
                if (respuestaRegistrada.length() == 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('" + "Eliminacion Realizada." + "');");

                    out.println("window.location.href = '/Effective/vista/registrarproductos.jsp';");
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
