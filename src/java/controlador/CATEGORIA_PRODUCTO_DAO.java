package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.CATEGORIA_PRODUCTO;

public class CATEGORIA_PRODUCTO_DAO {

    public String AdicionarCategoria_producto(CATEGORIA_PRODUCTO Categoria_producto) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "Insert CATEGORIA_PRODUCTO (nombre_categoria_producto)" + "values (?);";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Categoria_producto.getNombre_categoria_producto());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en Adicionarcategoria_producto_CATEGORIA_PRODUCTO_DAO\n" + miRespuesta);

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

    public String ModificarCategoria_producto(CATEGORIA_PRODUCTO Categoria_producto) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "update CATEGORIA_PRODUCTO set nombre_categoria_producto=? where id_categoria_producto=?";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Categoria_producto.getNombre_categoria_producto());
            sentencia.setString(2, Categoria_producto.getId_categoria_producto());
            sentencia.executeUpdate();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en ModificarCategoria_producto_CATEGORIA_PRODUCTO_DAO_MODIFICAR\n" + miRespuesta);

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

    public CATEGORIA_PRODUCTO ConsultarCategoria_producto(String id_categoria_producto) {

        CATEGORIA_PRODUCTO micategoria_producto = null;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            sentencia = nuevaCon.prepareStatement("select id_categoria_producto, nombre_categoria_producto from CATEGORIA_PRODUCTO where id_categoria_producto= " + id_categoria_producto);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {

                micategoria_producto = new CATEGORIA_PRODUCTO();
                micategoria_producto.setId_categoria_producto(rs.getString(1));
                micategoria_producto.setNombre_categoria_producto(rs.getString(2));
            }

            return micategoria_producto;

        } catch (Exception ex) {

            System.out.println("ocurrio un problema en ConsultarTipo_usuarios_TIPO_USUARIOS_DAO_CONSULTAR\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return micategoria_producto;

    }

    public ArrayList<CATEGORIA_PRODUCTO> ConsultarListarCategoria_producto(String id_categoria_producto, String nombre_categoria_producto) {

        ArrayList<CATEGORIA_PRODUCTO> mi_lista_categoria_producto = new ArrayList<CATEGORIA_PRODUCTO>();
        CATEGORIA_PRODUCTO mi_categoria_producto;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        System.out.println("Buscando parametro" + nombre_categoria_producto);

        try {

            sentencia = nuevaCon.prepareStatement("select id_categoria_producto,nombre_categoria_producto"
                    + " from CATEGORIA_PRODUCTO"
                    + " where id_categoria_producto like '%" + id_categoria_producto + "%'"
                    + "  or (nombre_categoria_producto) like ('%" + nombre_categoria_producto + "%')"
                    + "  or (nombre_categoria_producto) like ('%" + nombre_categoria_producto + "%') ORDER BY id_categoria_producto;");

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                mi_categoria_producto = new CATEGORIA_PRODUCTO();
                mi_categoria_producto.setId_categoria_producto(rs.getString(1));
                mi_categoria_producto.setNombre_categoria_producto(rs.getString(2));
                mi_lista_categoria_producto.add(mi_categoria_producto);

            }
            return mi_lista_categoria_producto;

        } catch (Exception ex) {

            System.out.println("Error en la consulta de listado categoria producto" + "\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }
        return mi_lista_categoria_producto;
    }

    public String EliminarCategoria_producto(CATEGORIA_PRODUCTO Categoria_producto) {

        String miRespuesta;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "delete from CATEGORIA_PRODUCTO where nombre_categoria_producto = ? and id_categoria_producto = ? ;";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Categoria_producto.getNombre_categoria_producto());
            sentencia.setString(2, Categoria_producto.getId_categoria_producto());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.err.println("Ocurrio un error en TIPO_USUARIO_DAO.EliminarTipo_usuario" + ex.getMessage());
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
