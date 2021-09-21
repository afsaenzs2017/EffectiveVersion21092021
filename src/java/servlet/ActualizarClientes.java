/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.CLIENTES_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CLIENTES;

/**
 *
 * @author AndresSalazar
 */
@WebServlet(name = "ActualizarClientes", urlPatterns = {"/ActualizarClientes"})
public class ActualizarClientes extends HttpServlet {

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
        
        String nombreCliente = request.getParameter("nombreCliente");
        String apellidoCliente = request.getParameter("apellidoCliente");
        String identificacionCliente = request.getParameter("identificacionCliente");
        String correoCliente = request.getParameter("correoCliente");
        String telefonoCliente = request.getParameter("telefonoCliente");
        String direccionCliente = request.getParameter("direccionCliente");
        String id_usuario = request.getParameter("id_usuario");
        String BanderaEstado = request.getParameter("banderaRegistro");
        
        int IdUsuario = Integer.parseInt(id_usuario);
        
        CLIENTES_DAO mi_cliente_dao = new CLIENTES_DAO();
        CLIENTES mi_cliente = new CLIENTES();
        
        mi_cliente.setNombreCliente(nombreCliente);
        mi_cliente.setApellidoCliente(apellidoCliente);
        mi_cliente.setIdentificacionCliente(identificacionCliente);
        mi_cliente.setCorreoCliente(correoCliente);
        mi_cliente.setTelefonoCliente(telefonoCliente);
        mi_cliente.setDireccionCliente(direccionCliente);
        mi_cliente.setId_usuario(IdUsuario);
        
         System.out.println("Identificacion: " + identificacionCliente);
        String respuestaRegistrada = mi_cliente_dao.ModificarClientes(mi_cliente);
        if (respuestaRegistrada.length() == 0) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('" + "Actualización Realizada." + "');");
// Se agrega el redreccionamiento la url para visualice la tabla.
            out.println("window.location.href = '/Effective/vista/gestionarClientes.jsp';");
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
