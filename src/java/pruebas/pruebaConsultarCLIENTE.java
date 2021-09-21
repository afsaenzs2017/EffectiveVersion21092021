package pruebas;

import controlador.CLIENTES_DAO;
import modelo.CLIENTES;

public class pruebaConsultarCLIENTE {

    public static void main(String[] args) {

        CLIENTES_DAO clienteDao = new CLIENTES_DAO();
        CLIENTES mi_cliente = clienteDao.ConsultarClientes("100568974");

        if (mi_cliente != null) {

            System.out.println("Se realizo la consulta satisfactoriamente del numero de identificación - " + mi_cliente.getIdentificacionCliente());
            System.out.println("Id del cliente- " + mi_cliente.getIdCliente());
            System.out.println("Nombre - " + mi_cliente.getNombreCliente());
            System.out.println("Apellidos - " + mi_cliente.getApellidoCliente());
            System.out.println("Correo - " + mi_cliente.getCorreoCliente());
            System.out.println("Teléfono - " + mi_cliente.getTelefonoCliente());
            System.out.println("Dirección - " + mi_cliente.getDireccionCliente());
            System.out.println("Id del usuario que lo registro - " + mi_cliente.getId_usuario());

        } else {

            System.out.println("Ha ocurrido un error durante la consulta");

        }

    }

}
