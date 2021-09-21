<%-- 
    Document   : ListadoMovimiento
    Created on : 16/09/2021, 12:38:03 AM
    Author     : AndresSalazar
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="controlador.conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon"  type="image/png" href="../imagenes/LYDM.png">
        <!-- JQUERY -->
        <script src="https://code.jquery.com/jquery-3.4.1.js"
                integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous">
        </script>
        <!-- DATATABLES -->
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js">
        </script>
        <!-- BOOTSTRAP -->
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js">
        </script>

        <script src="../javascript/jquery-1.11.0.min.js" type="text/javascript"></script>
        <script src="../javascript/validandoBotonProveedores.js"></script>
        <script src="../javascript/ValidacionProveedores.js"></script>
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../css/estilos.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Movimineto</title>
    </head>
    <body>

        <% String buscando = request.getParameter("buscando");
            if (buscando == null) {
                buscando = "";
            }
        %>

        <div class="row justify-content-md-center">
            <div class="btn-group">
                <a  href="../vista/gestionarMovimientos.jsp" class="btn btn-secondary">Registrar</a>
                <a href="../vista/ListadoMovimiento.jsp" class="btn btn-secondary">Consultar</a>
                <button type="button" class="btn btn-secondary" ><a href="../vista/menu.jsp" class="text-decoration-none" style="color: white">Regresar</a></button>
            </div>
        </div>

        <div class="container">
            <form action="/Effective/EliminaMovimiento" method="post">
                <div class="row justify-content-md-center">
                    <div id="Listado" class="bd-example">
                        <br>
                        <br>
                        <div id="ControlRegistro" class="input-group">
                            <input id="txt_Id_Consultado" type="text" class="form-control" name="IdConsultado" value="<%=buscando%>" autofocus placeholder="Buscar por Documento y Fecha..."/>
                            <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"onClick="realizarBusqueda()">Buscar</button>
                        </div>
                        <br>
                        <br>
                        <div>
                            <h1>Listado Facturas</h1>

                            <br>

                            <div id="Tabla_Movimiento"></div>
                            <%
                                conexion miConexion = new conexion();
                                Connection nuevaCon;
                                nuevaCon = miConexion.getConn();

                                Statement stm = miConexion.getConn().createStatement();
                                ResultSet rst = stm.executeQuery("select *,C.nombreCLiente,C.apellidoCliente from Movimiento M, Clientes C where M.CLIENTES_idCliente = C.idCliente and estado_movimiento != 3");
                                out.println("<table id='tablita' class='display table table-primary table-striped table-hover table-bordered border-danger'><thead><tr><th style='display:none;'>Codigo</th><th>Documento</th><th>Fecha</th>");
                                out.println("<th>Subtotal</th><th>Iva</th><th>Total</th><th>Estado</th><th>Cliente</th><th colspan='2'><div align='center'>Acciones</div></th></tr></thead>");
                                while (rst.next()) {
                                    out.println("<tr>");
                                    out.println("<td style='display:none;'>" + rst.getInt(1) + "</td>");
                                    out.println("<td>" + rst.getString(2) + "</td>");
                                    out.println("<td>" + rst.getString(3) + "</td>");
                                    out.println("<td>" + rst.getFloat(4) + "</td>");
                                    out.println("<td>" + rst.getFloat(5) + "</td>");
                                    out.println("<td>" + rst.getFloat(6) + "</td>");
                                    if (rst.getInt(10) == 1) {
                                        out.println("<td>" + "<p class='text-success'><small>Facturada</small></p>" + "</td>");
                                    }
                                    if (rst.getInt(10) == 2) {
                                        out.println("<td>" + "<p class='text-danger'><small>Anulada</small></p>" + "</td>");
                                    }
                                    out.println("<td>" + rst.getString(12) + " " + rst.getString(13) + "</td>");


                            %>
                            <td>
                                <form action="/Effective/EliminaMovimiento" method="post">    
                                    <input type='hidden' id='codmovimi' name='codmovimi' value='<%= rst.getInt(1)%>'/>
                                    <%
                                        if (rst.getInt(10) == 1) {
                                    %>
                                    <button id='btnenviar' class='btn btn-danger'>Anular</button>
                                    <%
                                        }
                                        if (rst.getInt(10) == 2) {
                                    %>
                                    <button id='btnenviar' disabled='true' class='btn btn-danger'>Anular</button>
                                    <%
                                        }
                                    %>
                                </form>    
                            </td>
                            <td>
                                <form action="/Effective/ImprimirFactura">    
                                    <input type='hidden' id='codmovimiento' name='codmovimiento' value='<%= rst.getInt(1)%>'/>
                                    <button id='btnenviarse' class='btn btn-warning' formtarget="_blank">Imprimir</button>
                                </form>    
                            </td>
                            <%
                                    out.println("</tr>");

                                }

                                out.println("</table>");
                            %>

                        </div>
                    </div>
                </div>
            </form>
        </div>

        <script>


            $(document).ready(function () {
                $('#tablita').DataTable();
            });

        </script>


        <style>

            body{
                background-image: url(../imagenes/desk.jpg);
                background-repeat: no-repeat;
                background-size: 110%;
                font-family: 'Acme', sans-serif;
                font-size: 20px;

            }

            h1{

                color: whitesmoke; 
            }


            .modal-content{

                background-color: #494f54;
                opacity: .92;
                padding: 30px;
                display: flex;
                margin-top: 120px;
                margin-left: 150px;
                border-radius: 3%;
                width: 70%;

            }

            h5{
                color: whitesmoke;
                font-size: 25px;
            }

            h1{
                text-shadow: 1px 1px 5px black;
                color: lightsteelblue;
            }

            .botoncito{

                width: 90px;
                height: 50px;
                margin: 10px 10px 10px 10px;

            }

            .btn-danger:hover {
                background-color: red;
            }

            .btn-primary:hover{

                background-color: blue;
            }



        </style>


        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>


    </body>
</html>


