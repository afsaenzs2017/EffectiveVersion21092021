package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.FORMAS_DE_PAGO;

public class FORMAS_DE_PAGO_DAO {

    public String AdicionarForma_de_Pago(FORMAS_DE_PAGO Forma_de_Pago) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "Insert FORMAS_DE_PAGO (Tipo_de_pago)" + "values (?);";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Forma_de_Pago.getTipo_de_pago());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en AdicionarForma_de_pago\n" + miRespuesta);

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

    public String ModificarFormas_de_pago(FORMAS_DE_PAGO Formas_de_pago) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "update FORMAS_DE_PAGO set tipo_de_pago=? where id_forma_de_pago=?";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Formas_de_pago.getTipo_de_pago());
            sentencia.setString(2, Formas_de_pago.getId_forma_de_pago());
            sentencia.executeUpdate();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en ModificarFormas_de_pago_FORMAS_DE_PAGO_MODIFICAR\n" + miRespuesta);

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

    public FORMAS_DE_PAGO ConsultarFormas_de_pago(String id_forma_de_pago) {

        FORMAS_DE_PAGO miforma_pago = null;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        try {

            sentencia = nuevaCon.prepareStatement("select id_forma_de_pago, tipo_de_pago from FORMAS_DE_PAGO where id_forma_de_pago = " + id_forma_de_pago);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {

                miforma_pago = new FORMAS_DE_PAGO();
                miforma_pago.setId_forma_de_pago(rs.getString(1));
                miforma_pago.setTipo_de_pago(rs.getString(2));

            }

            return miforma_pago;

        } catch (Exception ex) {

            System.out.println("ocurrio un problema en ConsultarFromas_de_pago_FROMAS_DE_PAGO_DAO_CONSULTAR\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }
        return miforma_pago;
    }

    public ArrayList<FORMAS_DE_PAGO> ConsultarListadoFormasdePago(String id_forma_de_pago, String tipo_de_pago) {
        ArrayList<FORMAS_DE_PAGO> mi_lista_formas_de_pago = new ArrayList<FORMAS_DE_PAGO>();
        FORMAS_DE_PAGO mi_forma_de_pago;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        System.out.println("Buscando Parametro" + id_forma_de_pago);

        try {

            sentencia = nuevaCon.prepareStatement(" select id_forma_de_pago,tipo_de_pago "
                    + " from FORMAS_DE_PAGO "
                    + " where id_forma_de_pago like '%" + id_forma_de_pago + "%' "
                    + " or (tipo_de_pago) like ('%" + tipo_de_pago + "%') ORDER BY id_forma_de_pago; ");
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                mi_forma_de_pago = new FORMAS_DE_PAGO();
                mi_forma_de_pago.setId_forma_de_pago(rs.getString(1));
                mi_forma_de_pago.setTipo_de_pago(rs.getString(2));
                mi_lista_formas_de_pago.add(mi_forma_de_pago);

            }
            return mi_lista_formas_de_pago;

        } catch (Exception ex) {
            System.out.println("Error en la consulta de formas de pago " + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }
        return mi_lista_formas_de_pago;
    }

    public String EliminarFormas_de_pago(FORMAS_DE_PAGO Formas_de_pago) {

        String miRespuesta;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia=null;

        try {

            String Query = "delete from FORMAS_DE_PAGO where tipo_de_pago = ? and id_forma_de_pago = ? ;";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Formas_de_pago.getTipo_de_pago());
            sentencia.setString(2, Formas_de_pago.getId_forma_de_pago());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.err.println("Ocurrio un error en FORMAS_DE_PAGO_DAO.EliminarFormas_de_pago" + ex.getMessage());
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
