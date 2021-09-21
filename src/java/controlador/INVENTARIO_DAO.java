package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.INVENTARIO;

public class INVENTARIO_DAO {

    public String AdicionarInventario(INVENTARIO Inventario) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "Insert INVENTARIO (entradas,salidas,saldo)" + "values (?,?,?);";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setInt(1, Inventario.getEntradas());
            sentencia.setInt(2, Inventario.getSalidas());
            sentencia.setInt(3, Inventario.getSaldo());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en AdicionarInventario_INVENTARIO_DAO\n" + miRespuesta);

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

    public String ModificarInventario(INVENTARIO Inventario) {

        String miRespuesta = "";
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {
            String Query = "update  INVENTARIO set entradas=?, salidas=?, saldo=? where id_inventario=?";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setInt(1, Inventario.getEntradas());
            sentencia.setInt(2, Inventario.getSalidas());
            sentencia.setInt(3, Inventario.getSaldo());
            sentencia.setString(4, Inventario.getId_inventario());

            sentencia.executeUpdate();

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("ocurrio un problema en ActualizarInventario_INVENTARIO_DAO  \n" + ex.getMessage());
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

    public INVENTARIO ConsultarInventario(String id_inventario) {

        INVENTARIO miinventario = null;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        try {

            sentencia = nuevaCon.prepareStatement("select id_inventario, entradas,salidas, saldo, PRODUCTOS_id_producto, DETALLE_MOVIMIENTO_id_detalle_movimiento from INVENTARIO where id_inventario = " + id_inventario);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                miinventario = new INVENTARIO();
                miinventario.setId_inventario(rs.getString(1));
                miinventario.setEntradas(rs.getInt(2));
                miinventario.setSalidas(rs.getInt(3));
                miinventario.setSaldo(rs.getInt(4));
                miinventario.setPRODUCTOS_id_producto(rs.getInt(5));
                miinventario.setDETALLE_MOVIMIENTO_id_detalle_movimiento(rs.getInt(6));

            }
            return miinventario;

        } catch (Exception ex) {

            System.out.println("ocurrio un problema en ConsultarInventario_INVENTARIO_DAO_CONSULTAR\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return miinventario;
    }

    public ArrayList<INVENTARIO> ConsultarListadoInventario(String id_inventario) {

        ArrayList<INVENTARIO> mi_lista_inventario = new ArrayList<INVENTARIO>();
        INVENTARIO mi_inventario;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        System.out.println("Buscando parametro" + id_inventario);

        try {

            sentencia = nuevaCon.prepareStatement("select id_inventario, entradas, salidas, saldo, PRODUCTOS_id_producto, DETALLE_MOVIMIENTO_id_detalle_movimiento"
                    + " from INVENTARIO"
                    + " where id_inventario like '%" + id_inventario + "%'"
                    + "  or (id_inventario) like ('%" + id_inventario + "%')"
                    + "  or (id_inventario) like ('%" + id_inventario + "%') ORDER BY id_inventario;");
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                mi_inventario = new INVENTARIO();
                mi_inventario.setId_inventario(rs.getString(1));
                mi_inventario.setEntradas(rs.getInt(2));
                mi_inventario.setSalidas(rs.getInt(3));
                mi_inventario.setSaldo(rs.getInt(4));
                mi_inventario.setPRODUCTOS_id_producto(rs.getInt(5));
                mi_inventario.setDETALLE_MOVIMIENTO_id_detalle_movimiento(rs.getInt(6));
                mi_lista_inventario.add(mi_inventario);

            }

            return mi_lista_inventario;

        } catch (Exception ex) {

            System.out.println("Error en la consulta de listado en inventario" + "\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return mi_lista_inventario;

    }

    public String EliminarInventario(INVENTARIO Inventario) {

        String miRespuesta;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia=null;

        try {

            String Query = "delete from INVENTARIO where id_inventario = ?  ";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Inventario.getId_inventario());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.err.println("Ocurrio un error en INVENTARIO_DAO.EliminarInventario" + ex.getMessage());
        }finally {
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
