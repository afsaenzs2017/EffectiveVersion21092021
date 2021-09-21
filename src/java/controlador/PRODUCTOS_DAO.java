package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.PRODUCTOS;

public class PRODUCTOS_DAO {

    public String AdicionarProveedores(PRODUCTOS Productos) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "Insert PRODUCTOS (identificacion_producto,nombre_producto,precio_producto,descripcion_producto,cantidad_producto,iva_producto,UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto,PROVEEDORES_id_proveedor,CATEGORIA_PRODUCTO_id_categoria_producto,ESTADO_PRODUCTO_id_estado_producto,MARCA_PRODUCTO_id_marca_producto,imagen_producto)" + "values (?,?,?,?,?,?,?,?,?,?,?,?);";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Productos.getIdentificacion_producto());
            sentencia.setString(2, Productos.getNombre_producto());
            sentencia.setInt(3, Productos.getPrecio_producto());
            sentencia.setString(4, Productos.getDescripcion_producto());
            sentencia.setInt(5, Productos.getCantidad_producto());
            sentencia.setInt(6, Productos.getIva_producto());
            sentencia.setInt(7, Productos.getUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto());
            sentencia.setInt(8, Productos.getPROVEEDORES_id_proveedor());
            sentencia.setInt(9, Productos.getCATEGORIA_PRODUCTO_id_categoria_producto());
            sentencia.setInt(10, Productos.getESTADO_PRODUCTO_id_estado_producto());
            sentencia.setInt(11, Productos.getMARCA_PRODUCTO_id_marca_producto());
            sentencia.setString(12, Productos.getIMAGEN_PRODUCTO());

            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en AdicionarPRODUCTOS_DAO\n" + miRespuesta);

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }
        return miRespuesta;

    }

    public String ModificarProductos(PRODUCTOS Productos) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {
            String Query = "update PRODUCTOS set identificacion_producto=?, nombre_producto=?, precio_producto=?, descripcion_producto=?, cantidad_producto=?,iva_producto=?, UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto=?, PROVEEDORES_id_proveedor=?, ESTADO_PRODUCTO_id_estado_producto=?, CATEGORIA_PRODUCTO_id_categoria_producto=?, MARCA_PRODUCTO_id_marca_producto=?, imagen_producto=? where identificacion_producto=?";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Productos.getIdentificacion_producto());
            sentencia.setString(2, Productos.getNombre_producto());
            sentencia.setInt(3, Productos.getPrecio_producto());
            sentencia.setString(4, Productos.getDescripcion_producto());
            sentencia.setInt(5, Productos.getCantidad_producto());
            sentencia.setInt(6, Productos.getIva_producto());
            sentencia.setInt(7, Productos.getUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto());
            sentencia.setInt(8, Productos.getPROVEEDORES_id_proveedor());
            sentencia.setInt(9, Productos.getCATEGORIA_PRODUCTO_id_categoria_producto());
            sentencia.setInt(10, Productos.getESTADO_PRODUCTO_id_estado_producto());
            sentencia.setInt(11, Productos.getMARCA_PRODUCTO_id_marca_producto());
            sentencia.setString(12, Productos.getIMAGEN_PRODUCTO());
            sentencia.setString(13, Productos.getIdentificacion_producto());

            sentencia.executeUpdate();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en ModificarProveedores_PRODUCTOS_DAO_MODIFICAR\n" + miRespuesta);

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return miRespuesta;

    }

    public PRODUCTOS ConsultarProductos(String identificacion_producto) {

        PRODUCTOS miproducto = null;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;
        try {

            sentencia = nuevaCon.prepareStatement("select id_producto, identificacion_producto ,nombre_producto, precio_producto, descripcion_producto, cantidad_producto, iva_producto, UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto,PROVEEDORES_id_proveedor,CATEGORIA_PRODUCTO_id_categoria_producto,ESTADO_PRODUCTO_id_estado_producto,MARCA_PRODUCTO_id_marca_producto, imagen_producto from PRODUCTOS where identificacion_producto = " + identificacion_producto);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {

                miproducto = new PRODUCTOS();
                miproducto.setId_producto(rs.getInt(1));
                miproducto.setIdentificacion_producto(rs.getString(2));
                miproducto.setNombre_producto(rs.getString(3));
                miproducto.setPrecio_producto(rs.getInt(4));
                miproducto.setDescripcion_producto(rs.getString(5));
                miproducto.setCantidad_producto(rs.getInt(6));
                miproducto.setIva_producto(rs.getInt(7));
                miproducto.setUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto(rs.getInt(8));
                miproducto.setPROVEEDORES_id_proveedor(rs.getInt(9));
                miproducto.setCATEGORIA_PRODUCTO_id_categoria_producto(rs.getInt(10));
                miproducto.setESTADO_PRODUCTO_id_estado_producto(rs.getInt(11));
                miproducto.setMARCA_PRODUCTO_id_marca_producto(rs.getInt(12));
                miproducto.setIMAGEN_PRODUCTO(rs.getString(13));

            }

            return miproducto;

        } catch (Exception ex) {

            System.out.println("ocurrio un problema en ConsultarProveedor_PRODUCTOS_DAO_CONSULTAR\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return miproducto;

    }

    public ArrayList<PRODUCTOS> ConsultarListadoProductos(String identificacion_producto, String nombre_producto) {

        ArrayList<PRODUCTOS> mi_lista_producto = new ArrayList<PRODUCTOS>();
        PRODUCTOS mi_producto;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        System.out.println("Buscando parametro" + identificacion_producto);

        try {

            sentencia = nuevaCon.prepareStatement("select id_producto,identificacion_producto, nombre_producto,"
                    + " precio_producto, descripcion_producto, cantidad_producto, iva_producto, UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto, PROVEEDORES_id_proveedor,  "
                    + " CATEGORIA_PRODUCTO_id_categoria_producto,ESTADO_PRODUCTO_id_estado_producto, MARCA_PRODUCTO_id_marca_producto, imagen_producto"
                    + " from PRODUCTOS"
                    + " where identificacion_producto like '%" + identificacion_producto + "%'"
                    + "  or (nombre_producto) like ('%" + nombre_producto + "%')"
                    + "  or (nombre_producto) like ('%" + nombre_producto + "%') ORDER BY identificacion_producto;");
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                mi_producto = new PRODUCTOS();
                mi_producto.setId_producto(rs.getInt(1));
                mi_producto.setIdentificacion_producto(rs.getString(2));
                mi_producto.setNombre_producto(rs.getString(3));
                mi_producto.setPrecio_producto(rs.getInt(4));
                mi_producto.setDescripcion_producto(rs.getString(5));
                mi_producto.setCantidad_producto(rs.getInt(6));
                mi_producto.setIva_producto(rs.getInt(7));
                mi_producto.setUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto(rs.getInt(8));
                mi_producto.setPROVEEDORES_id_proveedor(rs.getInt(9));
                mi_producto.setCATEGORIA_PRODUCTO_id_categoria_producto(rs.getInt(10));
                mi_producto.setESTADO_PRODUCTO_id_estado_producto(rs.getInt(11));
                mi_producto.setMARCA_PRODUCTO_id_marca_producto(rs.getInt(12));
                mi_producto.setIMAGEN_PRODUCTO(rs.getString(13));
                mi_lista_producto.add(mi_producto);

            }

            return mi_lista_producto;

        } catch (Exception ex) {

            System.out.println("Error en la consulta de listado productos" + "\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return mi_lista_producto;

    }

    public ArrayList<PRODUCTOS> ConsultarCarrito(String categoria, String marca) {

        ArrayList<PRODUCTOS> mi_lista_producto = new ArrayList<PRODUCTOS>();
        PRODUCTOS mi_producto;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        //System.out.println("Buscando parametro" + identificacion_producto);
        try {

            sentencia = nuevaCon.prepareStatement("select * from PRODUCTOS"
                    + " where CATEGORIA_PRODUCTO_id_categoria_producto like '%" + Integer.parseInt(categoria) + "%'"
                    + "and MARCA_PRODUCTO_id_marca_producto like '%" + Integer.parseInt(marca) + "%'"
                    + "ORDER BY id_producto;");
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                mi_producto = new PRODUCTOS();
                mi_producto.setId_producto(rs.getInt(1));
                mi_producto.setIdentificacion_producto(rs.getString(2));
                mi_producto.setNombre_producto(rs.getString(3));
                mi_producto.setPrecio_producto(rs.getInt(4));
                mi_producto.setDescripcion_producto(rs.getString(5));
                mi_producto.setCantidad_producto(rs.getInt(6));
                mi_producto.setIva_producto(rs.getInt(7));
                mi_producto.setUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto(rs.getInt(8));
                mi_producto.setPROVEEDORES_id_proveedor(rs.getInt(9));
                mi_producto.setCATEGORIA_PRODUCTO_id_categoria_producto(rs.getInt(10));
                mi_producto.setESTADO_PRODUCTO_id_estado_producto(rs.getInt(11));
                mi_producto.setMARCA_PRODUCTO_id_marca_producto(rs.getInt(12));
                mi_producto.setIMAGEN_PRODUCTO(rs.getString(13));
                mi_lista_producto.add(mi_producto);

            }

            return mi_lista_producto;

        } catch (Exception ex) {

            System.out.println("Error en la consulta de listado productos" + "\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return mi_lista_producto;

    }

    public String EliminarProductos(PRODUCTOS Productos) {

        String miRespuesta;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "delete from PRODUCTOS where identificacion_producto = ? and id_producto= ? ;";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Productos.getIdentificacion_producto());
            sentencia.setInt(2, Productos.getId_producto());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.err.println("Ocurrio un error en PRODUCTOS_DAO.EliminarProveedor" + ex.getMessage());
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }
        return miRespuesta;

    }

}
