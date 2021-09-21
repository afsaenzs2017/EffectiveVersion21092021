
package modelo;

/**
 *
 * @author AndresSossa
 */
public class DETALLE_MOVIMIENTO {
    
    private int id_detalle_movimiento;
    private int cantidad;
    private String nombre_producto;
    private float precio_productos;
    private float iva_productos;
    private float total_productos;
    private float subtotal_productos;
    private int PRODUCTOS_id_producto;
    private int MOVIMIENTO_id_movimiento;
      
    
    
    public int getId_detalle_movimiento() {
        return id_detalle_movimiento;
    }

    public void setId_detalle_movimiento(int id_detalle_movimiento) {
        this.id_detalle_movimiento = id_detalle_movimiento;
    }
    
    public String getNombre_Producto()
    {
        return nombre_producto;
    }
    
    public void setNombre_Producto(String nombre_producto)
    {
        this.nombre_producto = nombre_producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_productos() {
        return precio_productos;
    }

    public void setPrecio_productos(float precio_productos) {
        this.precio_productos = precio_productos;
    }

    public float getIva_productos() {
        return iva_productos;
    }

    public void setIva_productos(float iva_productos) {
        this.iva_productos = iva_productos;
    }
    
    public float getSubtotal_productos()
    {
        return subtotal_productos;
    }
    
    public void setSubtotal_productos(float subtotal_productos) {
        this.subtotal_productos = subtotal_productos;
    }

    public float getTotal_productos() {
        return total_productos;
    }

    public void setTotal_productos(float total_productos) {
        this.total_productos = total_productos;
    }

    public int getPRODUCTOS_id_producto() {
        return PRODUCTOS_id_producto;
    }

    public void setPRODUCTOS_id_producto(int PRODUCTOS_id_producto) {
        this.PRODUCTOS_id_producto = PRODUCTOS_id_producto;
    }

    public int getMOVIMIENTO_id_movimiento() {
        return MOVIMIENTO_id_movimiento;
    }

    public void setMOVIMIENTO_id_movimiento(int MOVIMIENTO_id_movimiento) {
        this.MOVIMIENTO_id_movimiento = MOVIMIENTO_id_movimiento;
    }

   
    

    
    
    
}
