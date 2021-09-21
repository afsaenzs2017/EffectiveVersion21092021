package pruebas;

import controlador.PRODUCTOS_DAO;
import java.util.ArrayList;
import modelo.PRODUCTOS;

/**
 *
 * @author AndresSaenz
 */
public class pruebaEliminarPRODUCTOS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PRODUCTOS_DAO mi_producto_dao = new PRODUCTOS_DAO();
        ArrayList<PRODUCTOS> mi_lista_productos = mi_producto_dao.ConsultarListadoProductos("10000002", "Esferos");

        for (PRODUCTOS P : mi_lista_productos) {

            System.out.println("Nombre del producto - " + P.getNombre_producto() + "\n" + " Identificacion_producto - " + P.getIdentificacion_producto()  + "Número de id - " + P.getId_producto() + "\n" + "\n" + "Precio del producto - " + P.getPrecio_producto() + "\n" + "Descripcion del producto- " + P.getDescripcion_producto() + "\n" + "Cantidad del producto - " + P.getCantidad_producto() + "\n" + "Iva del producto - " + P.getIva_producto() + "\n" + "Unidad de medida del producto - " + P.getUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto() + "\n" + "Proveedores del producto - " + P.getPROVEEDORES_id_proveedor() + "\n" + "Categoria del producto - " + P.getCATEGORIA_PRODUCTO_id_categoria_producto() + "\n" + "Estado del producto - " + P.getESTADO_PRODUCTO_id_estado_producto() + "\n" + "Marca del producto - " + P.getMARCA_PRODUCTO_id_marca_producto() + "\n" + "\n");
        }

        System.out.println("El registro se elimino satisfactoriamente");
        mi_producto_dao.EliminarProductos(mi_lista_productos.get(0));
        mi_lista_productos = mi_producto_dao.ConsultarListadoProductos("103", "Cinta");

        for (PRODUCTOS P : mi_lista_productos) {

            System.out.println("Nombre del producto - " + P.getNombre_producto() + "\n" + " Identificacion_producto - " + P.getIdentificacion_producto() + "Número de id - " + P.getId_producto() + "\n" + "Precio del producto - " + P.getPrecio_producto() + "\n" + "Descripcion del producto- " + P.getDescripcion_producto() + "\n" + "Cantidad del producto - " + P.getCantidad_producto() + "\n" + "Iva del producto - " + P.getIva_producto() + "\n" + "Unidad de medida del producto - " + P.getUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto() + "\n" + "Proveedores del producto - " + P.getPROVEEDORES_id_proveedor() + "\n" + "Categoria del producto - " + P.getCATEGORIA_PRODUCTO_id_categoria_producto() + "\n" + "Estado del producto - " + P.getESTADO_PRODUCTO_id_estado_producto() + "\n" + "Marca del producto - " + P.getMARCA_PRODUCTO_id_marca_producto() + "\n" + "\n");
        }

    }

}
