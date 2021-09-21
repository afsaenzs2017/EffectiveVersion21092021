
package pruebas;

import controlador.CLIENTES_DAO;
import java.util.Scanner;
import modelo.CLIENTES;


public class PruebaAdicionarCLIENTES {

    
    public static void main(String[] args) {
        
        CLIENTES_DAO mi_cliente_dao = new CLIENTES_DAO();
        CLIENTES mi_cliente = new CLIENTES();
        
        Scanner teclado = new Scanner(System.in);
        
        String identificacionCliente = "";
        String nombreCliente = "";
        String apellidoCliente = "";
        String correoCliente = "";
        String telefonoCliente = "";
        String direccionCliente = "";
        int id_usuario;
        
        System.out.println("Por favor ingrese el número de identificación del cliente");
        identificacionCliente = teclado.nextLine();
        
        System.out.println("Por favor ingrese el nombre del cliente");
        nombreCliente = teclado.nextLine();
        
        System.out.println("Por favor ingrese el apellido del cliente");
        apellidoCliente = teclado.nextLine();
        
        System.out.println("Por favor ingrese el correo del cliente");
        correoCliente = teclado.nextLine();
        
        System.out.println("Por favor ingrese la direccon del cliente");
        direccionCliente = teclado.nextLine();
        
        System.out.println("Por favor ingrese el teléfono del cliente");
        telefonoCliente = teclado.nextLine();
        
        System.out.println("Ingrese el id del usuario que lo registro");
        id_usuario = teclado.nextInt();
        
        mi_cliente.setIdentificacionCliente(identificacionCliente);
        mi_cliente.setNombreCliente(nombreCliente);
        mi_cliente.setApellidoCliente(apellidoCliente);
        mi_cliente.setCorreoCliente(correoCliente);
        mi_cliente.setTelefonoCliente(telefonoCliente);
        mi_cliente.setDireccionCliente(direccionCliente);
        mi_cliente.setId_usuario(id_usuario);
        String miRespuesta = mi_cliente_dao.AdicionarClientes(mi_cliente);
       
       
        
        if (miRespuesta.length() == 0) {
            
            System.out.println("Registro exitoso");
            
        } else {
            
            System.out.println("Ocurrio un error" + miRespuesta);
            
        }
      
    }
    
}
