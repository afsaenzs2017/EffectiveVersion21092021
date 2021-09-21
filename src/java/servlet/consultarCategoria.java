/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.CATEGORIA_PRODUCTO_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CATEGORIA_PRODUCTO;

/**
 *
 * @author AndresSalazar
 */
@WebServlet(name = "consultarCategoria", urlPatterns = {"/consultarCategoria"})
public class consultarCategoria extends HttpServlet {

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

        CATEGORIA_PRODUCTO_DAO micategoria_dao = new CATEGORIA_PRODUCTO_DAO();
        CATEGORIA_PRODUCTO categoria_producto = null;

        categoria_producto = micategoria_dao.ConsultarCategoria_producto(Identificacion);

        if (Accion != null) {

            if (categoria_producto != null) {

                response.sendRedirect("/Effective/vista/registrarCategoria.jsp?descripcion=" + categoria_producto.getNombre_categoria_producto()+ "&"
                        + "Id=" + categoria_producto.getId_categoria_producto() + "&"
                        + "Vista=" + "Actualizar" + "&");
                System.out.println("Salio");

            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "No se ha podido relizar la consulta." + "\n" + "Por favor verificar la identificacion: " + Identificacion + "');");
                out.println("</script>");
            }

        } else {

            if (categoria_producto != null) {

                String respuestaRegistrada = micategoria_dao.EliminarCategoria_producto(categoria_producto);
                if (respuestaRegistrada.length() == 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('" + "Eliminacion Realizada." + "');");
                    out.println("window.location.href = '/Effective/vista/registrarCategoria.jsp';");
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
