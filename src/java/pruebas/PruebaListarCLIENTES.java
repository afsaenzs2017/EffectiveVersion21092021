/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import controlador.CLIENTES_DAO;
import java.util.ArrayList;
import modelo.CLIENTES;

/**
 *
 * @author AndresSalazar
 */
public class PruebaListarCLIENTES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CLIENTES_DAO cliente_dao = new CLIENTES_DAO();
        ArrayList<CLIENTES> mi_cliente = new ArrayList<CLIENTES>();
        mi_cliente = cliente_dao.ConsultarListadoClientes("","","");

        int size = mi_cliente.size();
        System.out.println("<table border=\"1\")><br><td>idClientes</td><td>nombreCliente</td><td>apellidoCliente</td><td>correoCliente</td>" + "<td>telefonoCliente</td><td>direccionCliente</td><td>identificacionCliente</td><td>USUARIO_id_usuario</td>");

        for (CLIENTES C : mi_cliente) {

            System.out.println("<tr>");
            System.out.println("<td>" + C.getIdCliente() + "</td>");
            System.out.println("<td>" + C.getNombreCliente() + "</td>");
            System.out.println("<td>" + C.getApellidoCliente() + "</td>");
            System.out.println("<td>" + C.getCorreoCliente() + "</td>");
            System.out.println("<td>" + C.getTelefonoCliente() + "</td>");
            System.out.println("<td>" + C.getIdentificacionCliente() + "</td>");
            System.out.println("<td>" + C.getId_usuario() + "</td>");

            System.out.println("</tr>");

        }

        System.out.println("</table>");
    }
    
}
