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
public class PruebaEliminarCLIENTES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CLIENTES_DAO mi_cliente_dao = new CLIENTES_DAO();
        ArrayList<CLIENTES> mi_lista_cliente = mi_cliente_dao.ConsultarListadoClientes("4684351", "pilar", "gonsalez");

        for (CLIENTES C : mi_lista_cliente) {

            System.out.println( "Nombre Cliente - " + C.getNombreCliente() + "\n" + "Apellidos cliente - " + C.getApellidoCliente() + "Corre cliente - " + C.getCorreoCliente() + "\n" + "Telefono cliente - " + C.getTelefonoCliente()+ "\n" + "Direccion cliente - " + C.getDireccionCliente() + "\n" + "Identificacion cliente - " + C.getIdentificacionCliente()+ "\n" + "Id usuario - " + C.getId_usuario()+ "\n");

        }

        System.out.println("El registro se elimino satisfactoriamente");
        mi_cliente_dao.EliminarClientes(mi_lista_cliente.get(0));
        mi_lista_cliente = mi_cliente_dao.ConsultarListadoClientes("4684351", "pilar", "gonsalez");

        for (CLIENTES C : mi_lista_cliente) {

            System.out.println( "Nombre Cliente - " + C.getNombreCliente() + "\n" + "Apellidos cliente - " + C.getApellidoCliente() + "Corre cliente - " + C.getCorreoCliente() + "\n" + "Telefono cliente - " + C.getTelefonoCliente()+ "\n" + "Direccion cliente - " + C.getDireccionCliente() + "\n" + "Identificacion cliente - " + C.getIdentificacionCliente()+ "\n" + "Id usuario - " + C.getId_usuario()+ "\n");

        }
    }
    
}
