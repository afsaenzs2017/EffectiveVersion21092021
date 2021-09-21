package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.ESTADO_USUARIO;

/**
 *
 * @author AndresSaenz
 */
public class ESTADO_USUARIO_DAO {

    public String AdicionarEstado_usuario(ESTADO_USUARIO Estado_usuario) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "Insert ESTADO_USUARIO (descripcion_estado_usuario)" + "values (?);";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Estado_usuario.getDescripcion_estado_usuario());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en AdicionarEstado_usuario_ESTADO_USUARIO_DAO\n" + miRespuesta);

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

    public String ModificarEstado_usuario(ESTADO_USUARIO Estado_usuario) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "update ESTADO_USUARIO set descripcion_estado_usuario=? where id_estado_usuario=?";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Estado_usuario.getDescripcion_estado_usuario());
            sentencia.setString(2, Estado_usuario.getId_estado_usuario());
            sentencia.executeUpdate();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en ModificarEstado_usuario_ESTADO_USUARIO_DAO_MODIFICAR\n" + miRespuesta);

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

    public ESTADO_USUARIO ConsultarEstado_usuario(String id_estado_usuario) {

        ESTADO_USUARIO miestado_usuario = null;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        try {

            sentencia = nuevaCon.prepareStatement("select id_estado_usuario, descripcion_estado_usuario from ESTADO_USUARIO where id_estado_usuario = " + id_estado_usuario);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {

                miestado_usuario = new ESTADO_USUARIO();
                miestado_usuario.setId_estado_usuario(rs.getString(1));
                miestado_usuario.setDescripcion_estado_usuario(rs.getString(2));

            }

            return miestado_usuario;

        } catch (Exception ex) {

            System.out.println("ocurrio un problema en ConsultarEstado_usuarios_ESTADO_USUARIO_DAO_CONSULTAR\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }
        return miestado_usuario;
    }

    public ArrayList<ESTADO_USUARIO> ConsultarListarEstado_usuario(String id_estado_usuario, String descripcion_estado_usuario) {

        ArrayList<ESTADO_USUARIO> mi_lista_estado_usuario = new ArrayList<ESTADO_USUARIO>();
        ESTADO_USUARIO mi_estado_usurio;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia=null;

        System.out.println("Buscando parametro" + id_estado_usuario);

        try {

            sentencia = nuevaCon.prepareStatement("select id_estado_usuario,descripcion_estado_usuario"
                    + " from ESTADO_USUARIO"
                    + " where id_estado_usuario like '%" + id_estado_usuario + "%'"
                    + "  or (descripcion_estado_usuario) like ('%" + descripcion_estado_usuario + "%') ORDER BY id_estado_usuario;");

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                mi_estado_usurio = new ESTADO_USUARIO();
                mi_estado_usurio.setId_estado_usuario(rs.getString(1));
                mi_estado_usurio.setDescripcion_estado_usuario(rs.getString(2));
                mi_lista_estado_usuario.add(mi_estado_usurio);

            }
            return mi_lista_estado_usuario;

        } catch (Exception ex) {

            System.out.println("Error en la consulta de listado estado usuario" + "\n" + ex.getMessage());
            

        }finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }
        
        return mi_lista_estado_usuario;

    }

    public String EliminarEstado_usuario(ESTADO_USUARIO Estado_usuario) {

        String miRespuesta;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia=null;

        try {

            String Query = "delete from ESTADO_USUARIO where descripcion_estado_usuario = ? and id_estado_usuario = ? ;";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Estado_usuario.getDescripcion_estado_usuario());
            sentencia.setString(2, Estado_usuario.getId_estado_usuario());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en ESTADO_USUARIO_DAO.EliminarEstado_usuario" + ex.getMessage());
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
