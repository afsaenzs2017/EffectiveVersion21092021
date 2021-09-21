package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.CLIENTES;

public class CLIENTES_DAO {

    public String AdicionarClientes(CLIENTES Clientes) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {

            String Query = "Insert into CLIENTES (identificacionCliente, nombreCliente, apellidoCliente, correoCliente, telefonoCliente, direccionCliente, USUARIOS_id_usuario )" + "values (?, ?, ?, ?, ?, ?, ?);";
            sentencia = nuevaCon.prepareStatement(Query);

            sentencia.setString(1, Clientes.getIdentificacionCliente());
            sentencia.setString(2, Clientes.getNombreCliente());
            sentencia.setString(3, Clientes.getApellidoCliente());
            sentencia.setString(4, Clientes.getCorreoCliente());
            sentencia.setString(5, Clientes.getTelefonoCliente());
            sentencia.setString(6, Clientes.getDireccionCliente());
            sentencia.setInt(7, Clientes.getId_usuario());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en Adicionar_CLIENTES_DAO\n" + miRespuesta);

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

    public String ModificarClientes(CLIENTES Clientes) {

        String miRespuesta;
        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia = null;

        try {
            String Query = "update CLIENTES set identificacionCliente=?, nombreCliente=?, apellidoCliente=?, correoCliente=?, telefonoCliente=?, direccionCliente=?, USUARIOS_id_usuario=? where identificacionCliente=?";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, Clientes.getIdentificacionCliente());
            sentencia.setString(2, Clientes.getNombreCliente());
            sentencia.setString(3, Clientes.getApellidoCliente());
            sentencia.setString(4, Clientes.getCorreoCliente());
            sentencia.setString(5, Clientes.getTelefonoCliente());
            sentencia.setString(6, Clientes.getDireccionCliente());
            sentencia.setInt(7, Clientes.getId_usuario());
            sentencia.setString(8, Clientes.getIdentificacionCliente());

            sentencia.executeUpdate();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.out.println("Ocurrio un error en ModificarClientes_CLIENTES_DAO_MODIFICAR\n" + miRespuesta);

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

    public CLIENTES ConsultarClientes(String identificacionCliente) {

        CLIENTES micliente = null;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        try {

            sentencia = nuevaCon.prepareStatement("select idCliente,nombreCliente,apellidoCliente,correoCliente,telefonoCliente,direccionCliente,identificacionCliente,USUARIOS_id_usuario from CLIENTES where identificacionCliente = " + identificacionCliente);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                micliente = new CLIENTES();
                micliente.setIdCliente(rs.getInt(1));
                micliente.setNombreCliente(rs.getString(2));
                micliente.setApellidoCliente(rs.getString(3));
                micliente.setCorreoCliente(rs.getString(4));
                micliente.setTelefonoCliente(rs.getString(5));
                micliente.setDireccionCliente(rs.getString(6));
                micliente.setIdentificacionCliente(rs.getString(7));
                micliente.setId_usuario(rs.getInt(8));

            }
            return micliente;

        } catch (Exception ex) {

            System.out.println("ocurrio un problema en ConsultarClientes Dao\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }
        return micliente;
    }

    public ArrayList<CLIENTES> ConsultarListadoClientes(String identificacionCliente, String nombreCliente, String apellidoCliente) {

        ArrayList<CLIENTES> mi_lista_cliente = new ArrayList<CLIENTES>();
        CLIENTES mi_clientes;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();
        PreparedStatement sentencia = null;

        System.out.println("Buscando parametro" + identificacionCliente);

        try {

            sentencia = nuevaCon.prepareStatement("select idCliente,nombreCliente,apellidoCliente,correoCliente,"
                    + " telefonoCliente,direccionCliente,identificacionCliente,USUARIOS_id_usuario"
                    + " from CLIENTES"
                    + " where identificacionCliente like '%" + identificacionCliente + "%'"
                    + "  or (nombreCliente) like ('%" + nombreCliente + "%')"
                    //+ "  or (apellidoCliente) like ('%" + apellidoCliente + "%') ORDER BY identificacionCliente;";
                    + "  or (apellidoCliente) like ('%" + apellidoCliente + "%') ORDER BY idCliente;");
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                mi_clientes = new CLIENTES();
                mi_clientes.setIdCliente(rs.getInt(1));
                mi_clientes.setNombreCliente(rs.getString(2));
                mi_clientes.setApellidoCliente(rs.getString(3));
                mi_clientes.setCorreoCliente(rs.getString(4));
                mi_clientes.setTelefonoCliente(rs.getString(5));
                mi_clientes.setDireccionCliente(rs.getString(6));
                mi_clientes.setIdentificacionCliente(rs.getString(7));
                mi_clientes.setId_usuario(rs.getInt(8));
                mi_lista_cliente.add(mi_clientes);

            }

            return mi_lista_cliente;

        } catch (Exception ex) {

            System.out.println("Error en la consulta de listado Clientes" + "\n" + ex.getMessage());

        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                }
            }

        }

        return mi_lista_cliente;

    }

    public String EliminarClientes(CLIENTES clientes) {

        String miRespuesta;

        conexion miConexion = new conexion();
        Connection nuevaCon;
        nuevaCon = miConexion.getConn();

        PreparedStatement sentencia=null;

        try {

            String Query = "delete from CLIENTES where identificacionCliente = ? and idCliente = ? ;";
            sentencia = nuevaCon.prepareStatement(Query);
            sentencia.setString(1, clientes.getIdentificacionCliente());
            sentencia.setInt(2, clientes.getIdCliente());
            sentencia.execute();
            miRespuesta = "";

        } catch (Exception ex) {

            miRespuesta = ex.getMessage();
            System.err.println("Ocurrio un error en eliminar Clientes" + ex.getMessage());
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
