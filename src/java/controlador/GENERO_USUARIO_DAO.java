package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.GENERO_USUARIO;

/**
 *
 * @author AndresSaenz
 */
public class GENERO_USUARIO_DAO {

    public String AdicionarGenero_usuario(GENERO_USUARIO Genero_usuario) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "Insert GENERO_USUARIO (descripcion_genero_usuario)" + "values (?);";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Genero_usuario.getDescripcion_genero_usuario());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en AdicionarGenero_usuario_GENERO_USUARIO_DAO\n" + miRespuesta);

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

    public String ModificarGenero_usuario(GENERO_USUARIO Genero_usuario) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "update GENERO_USUARIO set descripcion_genero_usuario=? where id_genero_usuario=?";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Genero_usuario.getDescripcion_genero_usuario());
            sentencia.setString(2, Genero_usuario.getId_genero_usuario());
            sentencia.executeUpdate();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en ModificarGenero_usuario_GENERO_USUARIO_DAO_MODIFICAR\n" + miRespuesta);

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

    public GENERO_USUARIO ConsultarGenero_usuario(String id_genero_usuario) {

        GENERO_USUARIO migenero_usuario = null;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        try {

            sentencia = nuevaCon.prepareStatement("select id_genero_usuario, descripcion_genero_usuario from GENERO_USUARIO where id_genero_usuario = " + id_genero_usuario);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {

                migenero_usuario = new GENERO_USUARIO();
                migenero_usuario.setId_genero_usuario(rs.getString(1));
                migenero_usuario.setDescripcion_genero_usuario(rs.getString(2));
            }

            return migenero_usuario;

        } catch (Exception ex) {

            System.out.println("ocurrio un problema en ConsultarGenero_usuarios_GENERO_USUARIOS_DAO_CONSULTAR\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return migenero_usuario;

    }

    public ArrayList<GENERO_USUARIO> ConsultarListarGenero_usuario(String id_genero_usuario, String descripcion_genero_usuario) {

        ArrayList<GENERO_USUARIO> mi_lista_genero_usuario = new ArrayList<GENERO_USUARIO>();
        GENERO_USUARIO mi_genero_usurio;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        System.out.println("Buscando parametro" + descripcion_genero_usuario);

        try {

            sentencia = nuevaCon.prepareStatement("select id_genero_usuario,descripcion_genero_usuario"
                    + " from GENERO_USUARIO"
                    + " where id_genero_usuario like '%" + id_genero_usuario + "%'"
                    + "  or (descripcion_genero_usuario) like ('%" + descripcion_genero_usuario + "%')"
                    + "  or (descripcion_genero_usuario) like ('%" + descripcion_genero_usuario + "%') ORDER BY id_genero_usuario;");

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                mi_genero_usurio = new GENERO_USUARIO();
                mi_genero_usurio.setId_genero_usuario(rs.getString(1));
                mi_genero_usurio.setDescripcion_genero_usuario(rs.getString(2));
                mi_lista_genero_usuario.add(mi_genero_usurio);

            }
            return mi_lista_genero_usuario;

        } catch (Exception ex) {

            System.out.println("Error en la consulta de listado tipo usuario" + "\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return mi_lista_genero_usuario;

    }

    public String EliminarGenero_usuario(GENERO_USUARIO Tipo_genero) {

        String miRespuesta;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "delete from GENERO_USUARIO where descripcion_genero_usuario = ? and id_genero_usuario = ? ;";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Tipo_genero.getDescripcion_genero_usuario());
            sentencia.setString(2, Tipo_genero.getId_genero_usuario());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.err.println("Ocurrio un error en GENERO_USUARIO_DAO.EliminarGenero_usuario" + ex.getMessage());
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
