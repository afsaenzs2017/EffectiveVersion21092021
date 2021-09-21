package servlet;

import controlador.TIPO_USUARIO_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TIPO_USUARIO;

/**
 *
 * @author AndresSaenz
 */
@WebServlet(name = "RegistrarRoles", urlPatterns = {"/RegistrarRoles"})
public class RegistrarRoles extends HttpServlet {

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

        String tipo_usuario = request.getParameter("tipo_usuario");
        String Bandera = request.getParameter("BanderaReg");

        TIPO_USUARIO mi_tipo_usuario = new TIPO_USUARIO();
        TIPO_USUARIO_DAO mi_tipo_usuario_dao = new TIPO_USUARIO_DAO();

        mi_tipo_usuario.setDescripcion_tipo_usuario(tipo_usuario);
       

        System.out.println("El valor es " + Bandera);

        if (Bandera.equals("Correcto")) {
            String respuestaRegistrada = mi_tipo_usuario_dao.AdicionarTipo_usuario(mi_tipo_usuario);
            System.out.println("Res " + respuestaRegistrada);
            System.out.println("Res " + respuestaRegistrada.length());
            if (respuestaRegistrada.length() == 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + "Registro Realizado." + "');");
                out.println("window.location.href = '/Effective/vista/registrarroles.jsp';");
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


