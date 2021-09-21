package pruebas;

import controlador.UNIDAD_DE_MEDIDA_PRODUCTO_DAO;
import java.util.Scanner;
import modelo.UNIDAD_DE_MEDIDA_PRODUCTO;

/**
 *
 * @author YesidValencia
 */
public class pruebaAdicionarUNIDAD_DE_MEDIDA_PRODUCTO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        UNIDAD_DE_MEDIDA_PRODUCTO_DAO mi_unidad_de_medida_producto_dao = new UNIDAD_DE_MEDIDA_PRODUCTO_DAO();
        UNIDAD_DE_MEDIDA_PRODUCTO mi_unidad_de_medida_producto = new UNIDAD_DE_MEDIDA_PRODUCTO();

        Scanner teclado = new Scanner(System.in);

        String UnidadMedida = "";

        System.out.println("Por favor ingrese la unidad de medida del producto");
        UnidadMedida = teclado.nextLine();

        mi_unidad_de_medida_producto.setDescripcion_unidad_de_medida_producto(UnidadMedida);
        String miRespuesta = mi_unidad_de_medida_producto_dao.AdicionarUnidad_de_medida_producto(mi_unidad_de_medida_producto);

        if (miRespuesta.length() == 0) {

            System.out.println("Registro exitoso");

        } else {

            System.out.println("Ocurrio un error" + miRespuesta);

        }

    }

}
