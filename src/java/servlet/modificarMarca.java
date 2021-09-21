/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.MARCA_PRODUCTO_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.MARCA_PRODUCTO;

/**
 *
 * @author 57320
 */
@WebServlet(name = "modificarMarca", urlPatterns = {"/modificarMarca"})
public class modificarMarca extends HttpServlet {

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
        
        String id = request.getParameter("marca_id");
        String marca_producto = request.getParameter("marca_producto");
        String Bandera = request.getParameter("BanderaReg");

        MARCA_PRODUCTO mi_marca_producto = new MARCA_PRODUCTO();
        MARCA_PRODUCTO_DAO mi_marca_producto_dao = new MARCA_PRODUCTO_DAO();

        mi_marca_producto.setNombre_marca_producto(marca_producto);
        mi_marca_producto.setId_marca_producto(id);

        System.out.println("Identificacion: " + id);
        String respuestaRegistrada = mi_marca_producto_dao.ModificarMarcaProducto(mi_marca_producto);
        if (respuestaRegistrada.length() == 0) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('" + "Actualización Realizada." + "');");
// Se agrega el redreccionamiento la url para visualice la tabla.
            out.println("window.location.href = '/Effective/vista/registrarMarca.jsp';");
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
