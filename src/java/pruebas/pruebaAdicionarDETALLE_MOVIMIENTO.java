package pruebas;

import controlador.DETALLE_MOVIMIENTO_DAO;
import java.util.Scanner;
import modelo.DETALLE_MOVIMIENTO;

public class pruebaAdicionarDETALLE_MOVIMIENTO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DETALLE_MOVIMIENTO_DAO mi_detalle_movimiento_dao = new DETALLE_MOVIMIENTO_DAO();
        DETALLE_MOVIMIENTO mi_detalle_movimiento = new DETALLE_MOVIMIENTO();

        Scanner teclado = new Scanner(System.in);

        int cantidad;
        float precio_productos;
        float iva_productos;
        float total_productos;
        int PRODUCTOS_id_producto;
        int MOVIMIENTO_id_movimiento;
        
        System.out.println("Por favor ingrese la cantidad de productos");
        cantidad = teclado.nextInt();

        System.out.println("Por favor ingrese el precio del producto");
        precio_productos = teclado.nextFloat();

        System.out.println("Por favor ingrese el iva del producto");
        iva_productos = teclado.nextInt();

        System.out.println("Por favor ingrese el total de los productos");
        total_productos = teclado.nextFloat();

        System.out.println("Por favor ingrese el id del producto");
        PRODUCTOS_id_producto = teclado.nextInt();
        
        System.out.println("Por favor ingrese el id del movimiento");
        MOVIMIENTO_id_movimiento = teclado.nextInt();

        mi_detalle_movimiento.setCantidad(cantidad);
        mi_detalle_movimiento.setPrecio_productos(precio_productos);
        mi_detalle_movimiento.setIva_productos(iva_productos);
        mi_detalle_movimiento.setTotal_productos(total_productos);
        mi_detalle_movimiento.setPRODUCTOS_id_producto(PRODUCTOS_id_producto);
        mi_detalle_movimiento.setMOVIMIENTO_id_movimiento(MOVIMIENTO_id_movimiento);
        String miRespuesta = mi_detalle_movimiento_dao.AdicionarDetalle_movimiento(mi_detalle_movimiento);

        if (miRespuesta.length() == 0) {

            System.out.println("Registro exitoso");

        } else {

            System.out.println("Ocurrio un error" + miRespuesta);

        }

    }

}
