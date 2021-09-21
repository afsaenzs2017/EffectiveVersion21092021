
package modelo;

/**
 *
 * @author LadyChitivo
 */
public class INVENTARIO {
    
    private String id_inventario;
    private int entradas;
    private int salidas;
    private int saldo;
    private int PRODUCTOS_id_producto;
    private int DETALLE_MOVIMIENTO_id_detalle_movimiento;

    public String getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(String id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getPRODUCTOS_id_producto() {
        return PRODUCTOS_id_producto;
    }

    public void setPRODUCTOS_id_producto(int PRODUCTOS_id_producto) {
        this.PRODUCTOS_id_producto = PRODUCTOS_id_producto;
    }

    public int getDETALLE_MOVIMIENTO_id_detalle_movimiento() {
        return DETALLE_MOVIMIENTO_id_detalle_movimiento;
    }

    public void setDETALLE_MOVIMIENTO_id_detalle_movimiento(int DETALLE_MOVIMIENTO_id_detalle_movimiento) {
        this.DETALLE_MOVIMIENTO_id_detalle_movimiento = DETALLE_MOVIMIENTO_id_detalle_movimiento;
    }
        
}
