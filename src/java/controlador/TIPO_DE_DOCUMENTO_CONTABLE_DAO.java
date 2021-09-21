package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.TIPO_DE_DOCUMENTO_CONTABLE;

public class TIPO_DE_DOCUMENTO_CONTABLE_DAO {

    public String AdicionarTipo_de_Documento_Contable(TIPO_DE_DOCUMENTO_CONTABLE Tipo_de_Documento_Contable) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia=null;

        try {

            String Query = "Insert into TIPO_DE_DOCUMENTO_CONTABLE (descripcion,naturaleza)" + "values (?,?);";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Tipo_de_Documento_Contable.getDescripcion());
            sentencia.setString(2, Tipo_de_Documento_Contable.getNaturaleza());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en AdicionarTipo_de_Documento_Contable\n" + miRespuesta);

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

    public String ModificarTipo_de_documento_contable(TIPO_DE_DOCUMENTO_CONTABLE Tipo_de_documento_contable) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia=null;

        try {

            String Query = "update TIPO_DE_DOCUMENTO_CONTABLE set descripcion=?, naturaleza=? where id_tipo_de_documento_contable=?";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Tipo_de_documento_contable.getDescripcion());
            sentencia.setString(2, Tipo_de_documento_contable.getNaturaleza());
            sentencia.setString(3, Tipo_de_documento_contable.getId_tipo_de_documento_contable());
            sentencia.executeUpdate();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error \n" + miRespuesta);

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

    public TIPO_DE_DOCUMENTO_CONTABLE ConsultarTipo_de_documento_contable(String id_tipo_de_documento_contable) {

        TIPO_DE_DOCUMENTO_CONTABLE miTipodeDocumento = null;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        try {

            sentencia = nuevaCon.prepareStatement("select id_tipo_de_documento_contable,descripcion,naturaleza from  TIPO_DE_DOCUMENTO_CONTABLE where id_tipo_de_documento_contable  = " + id_tipo_de_documento_contable);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                miTipodeDocumento = new TIPO_DE_DOCUMENTO_CONTABLE();
                miTipodeDocumento.setId_tipo_de_documento_contable(rs.getString(1));
                miTipodeDocumento.setDescripcion(rs.getString(2));
                miTipodeDocumento.setNaturaleza(rs.getNString(3));

            }
            return miTipodeDocumento;

        } catch (Exception ex) {

            System.out.println("ocurrio un problema en la consultaConsultarTipo_documento_contable_TIPO_DOCUMENTO_CONTABLE_DAO_CONSULTAR\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return miTipodeDocumento;

    }

    public ArrayList<TIPO_DE_DOCUMENTO_CONTABLE> ConsultarListadoTipoDocumento(String id_tipo_de_documento_contable, String descripcion) {
        ArrayList<TIPO_DE_DOCUMENTO_CONTABLE> mi_lista_tipo_de_documento = new ArrayList<TIPO_DE_DOCUMENTO_CONTABLE>();
        TIPO_DE_DOCUMENTO_CONTABLE mi_tipo_documento;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        System.out.println("Buscando Parametro" + descripcion);

        try {

            sentencia = nuevaCon.prepareStatement("select id_tipo_de_documento_contable,descripcion,naturaleza"
                    + " from TIPO_DE_DOCUMENTO_CONTABLE "
                    + " where id_tipo_de_documento_contable like '%" + id_tipo_de_documento_contable + "%' "
                    + " or (descripcion) like ('%" + descripcion + "%')"
                    + " or (descripcion) like ('%" + descripcion + "%') ORDER BY id_tipo_de_documento_contable; ");
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                mi_tipo_documento = new TIPO_DE_DOCUMENTO_CONTABLE();
                mi_tipo_documento.setId_tipo_de_documento_contable(rs.getString(1));
                mi_tipo_documento.setDescripcion(rs.getString(2));
                mi_tipo_documento.setNaturaleza(rs.getNString(3));
                mi_lista_tipo_de_documento.add(mi_tipo_documento);

            }
            return mi_lista_tipo_de_documento;

        } catch (Exception ex) {
            System.out.println("Error en la consulta tipo_de_documento_contable " + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return mi_lista_tipo_de_documento;
    }

    public String EliminarTipo_documento_contable(TIPO_DE_DOCUMENTO_CONTABLE Tipo_documento_contable) {

        String miRespuesta;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia=null;

        try {

            String Query = "delete from TIPO_DE_DOCUMENTO_CONTABLE where descripcion= ? and id_tipo_de_documento_contable= ? ;";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Tipo_documento_contable.getDescripcion());
            sentencia.setString(2, Tipo_documento_contable.getId_tipo_de_documento_contable());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.err.println("Ocurrio un error en PROVEEDORES_DAO.EliminarProveedor" + ex.getMessage());
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
