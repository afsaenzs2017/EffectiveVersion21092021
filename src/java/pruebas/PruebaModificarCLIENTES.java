
package pruebas;

import controlador.CLIENTES_DAO;
import java.util.Scanner;
import modelo.CLIENTES;


public class PruebaModificarCLIENTES {

  
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
        
        
          
        System.out.println("Por favor ingrese el número de identificacion del cliente que quiere modificar");
        identificacionCliente = teclado.nextLine();
        
        System.out.println("Por favor ingrese el nuevo nombre del cliente");
        nombreCliente = teclado.nextLine();
        
        System.out.println("Por favor ingrese el nuevo apellido del cliente");
        apellidoCliente = teclado.nextLine();
        
        System.out.println("Por favor ingrese el correo nuevo del cliente");
        correoCliente = teclado.nextLine();
        
        
        System.out.println("Por favor ingrese el teléfono nuevo del cliente");
        telefonoCliente = teclado.nextLine();
        
        System.out.println("Por favor ingrese la nueva direccion del cliente");
        direccionCliente = teclado.nextLine();
        
        mi_cliente.setIdentificacionCliente(identificacionCliente);
        mi_cliente.setNombreCliente(nombreCliente);
        mi_cliente.setApellidoCliente(apellidoCliente);
        mi_cliente.setCorreoCliente(correoCliente);
        mi_cliente.setTelefonoCliente(telefonoCliente);
        mi_cliente.setDireccionCliente(direccionCliente);
        
        mi_cliente.setIdentificacionCliente(identificacionCliente);
        
        mi_cliente.setId_usuario(1);
        String miRespuesta = mi_cliente_dao.ModificarClientes(mi_cliente);

       
       
        
        if (miRespuesta.length() == 0) {
            
            System.out.println("Modificacion exitoso");
            
        } else {
            
            System.out.println("Ocurrio un error" + miRespuesta);
            
        }
       
        
    }
    
}
