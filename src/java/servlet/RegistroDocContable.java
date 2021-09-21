/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.TIPO_DE_DOCUMENTO_CONTABLE_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TIPO_DE_DOCUMENTO_CONTABLE;

/**
 *
 * @author AndresSalazar
 */
@WebServlet(name = "RegistroDocContable", urlPatterns = {"/RegistroDocContable"})
public class RegistroDocContable extends HttpServlet {

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

  
        String descripcion_doc = request.getParameter("descripcion_doc");
        String naturaleza_doc = request.getParameter("naturaleza_doc");
        String BanderaDoc = request.getParameter("BanderaReg");

        TIPO_DE_DOCUMENTO_CONTABLE mi_doc_contable = new TIPO_DE_DOCUMENTO_CONTABLE();
        TIPO_DE_DOCUMENTO_CONTABLE_DAO mi_doc_contable_dao = new TIPO_DE_DOCUMENTO_CONTABLE_DAO();

       
        mi_doc_contable.setDescripcion(descripcion_doc);
        mi_doc_contable.setNaturaleza(naturaleza_doc);
       

        System.out.println("El valor es " + BanderaDoc);
        
        if (BanderaDoc.equals("Correcto")) {
            String respuestaRegistrada = mi_doc_contable_dao.AdicionarTipo_de_Documento_Contable(mi_doc_contable);
            System.out.println("Res " + respuestaRegistrada);
            System.out.println("Res " + respuestaRegistrada.length());
            if (respuestaRegistrada.length() == 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "Registro Realizado." + "');");
                out.println("window.location.href = '/Effective/vista/registrarDocContable.jsp';");
                out.println("</script>");

            } else {

                out.println("<script type=\"text/javascript\">");
                //out.println("alert('"+respuestaRegistrada+"');");
                out.println("alert('" + "Error Encontrado: " + respuestaRegistrada.replace("'", "") + "');");
                out.println("window.history.back();");
                out.println("</script>");
            }
        } else {

            System.out.println("El valor no es correcto " + BanderaDoc);
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
