/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.UNIDAD_DE_MEDIDA_PRODUCTO_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.UNIDAD_DE_MEDIDA_PRODUCTO;

/**
 *
 * @author AndresSaenz
 */
@WebServlet(name = "RegistrarUnidadDeMedida", urlPatterns = {"/RegistrarUnidadDeMedida"})
public class RegistrarUnidadDeMedida extends HttpServlet {

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
           
        String descripcion_unidad_de_medida_producto = request.getParameter("descripcion_unidad_de_medida_producto");
        String Bandera = request.getParameter("BanderaReg");

        UNIDAD_DE_MEDIDA_PRODUCTO mi_unidad_medida = new UNIDAD_DE_MEDIDA_PRODUCTO();
        UNIDAD_DE_MEDIDA_PRODUCTO_DAO mi_unidad_medida_dao = new UNIDAD_DE_MEDIDA_PRODUCTO_DAO();

        mi_unidad_medida.setDescripcion_unidad_de_medida_producto(descripcion_unidad_de_medida_producto);
       

        System.out.println("El valor es " + Bandera);

        if (Bandera.equals("Correcto")) {
            String respuestaRegistrada = mi_unidad_medida_dao.AdicionarUnidad_de_medida_producto(mi_unidad_medida);
            System.out.println("Res " + respuestaRegistrada);
            System.out.println("Res " + respuestaRegistrada.length());
            if (respuestaRegistrada.length() == 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "Registro Exitoso." + "');");
                out.println("window.location.href = '/Effective/vista/registrarUnidadMedida.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                //out.println("alert('"+respuestaRegistrada+"');");
                out.println("alert('" + "Error Encontrado: " + respuestaRegistrada.replace("'", "") + "');");
                out.println("window.history.back();");
                out.println("</script>");
            }
        } else {

            System.out.println("El valor no es correcto " + Bandera);
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
