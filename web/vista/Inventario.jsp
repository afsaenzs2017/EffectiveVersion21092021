<%-- 
    Document   : Inventario
    Created on : 15/09/2021, 06:51:01 PM
    Author     : AndresSalazar
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="controlador.conexion"%>
<%@page import="modelo.INVENTARIO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controlador.INVENTARIO_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% if (session.getAttribute("username") == null) {
        out.print("<script>alert('No ha iniciado sesión')</script>");
        String urls = "../index.html";
        out.print("<script>window.location.href='" + urls + "';</script>");
    }
%>

<html lang="es">
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../javascript/jquery-1.11.0.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="../javascript/visualizaOculta.js" type="text/javascript"></script>
        <script src="../javascript/ValidacionInventario.js" type="text/javascript"></script>
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Inventario</title>
    </head>
    <body onload="visualizaOculta('<%=request.getParameter("Vista")%>');">

        <% String buscando = request.getParameter("buscando");
            if (buscando == null) {
                buscando = "";
            }
        %>

        <div class="row justify-content-md-center sticky-top">
            <div class="btn-group">
                <button type="button" class="btn btn-secondary" ><a href="../vista/menu.jsp" class="text-decoration-none" style="color: white">Volver al inicio</a></button>
            </div>
        </div>





        <div class="container">
            <br>
            <br>
            <form action="/Effective/ConsultarInventario" method="post">
                <div class="row justify-content-md-center">
                    <div id="Listado" class="bd-example">
                        <br>
                        <div>
                            <h1>Listado Inventario</h1>

                            <br>

                            <div id="Tabla_Usuarios"></div>

                            <%
                                INVENTARIO_DAO inventario_dao = new INVENTARIO_DAO();
                                ArrayList<INVENTARIO> inventarioList = new ArrayList<INVENTARIO>();
                                inventarioList = inventario_dao.ConsultarListadoInventario(buscando);
                                out.println("<table id='tablita' class='display table table-primary table-striped table-hover table-bordered border-danger'><thead><tr><th>Id</th><th>Entradas</th><th>Salidad</th><th>Saldo</th><th>Id Producto</th><th>Id detalle movimiento</th></tr></thead>");

                                for (INVENTARIO U : inventarioList) {

                                    out.println("<tr>");
                                    out.println("<td>" + U.getId_inventario() + "</td>");
                                    out.println("<td>" + U.getEntradas() + "</td>");
                                    out.println("<td>" + U.getSalidas() + "</td>");
                                    out.println("<td>" + U.getSaldo() + "</td>");

                                    conexion miConexion = new conexion();
                                    Connection nuevaCon;
                                    nuevaCon = miConexion.getConn();
                                    //Procesar una sentencia SQL
                                    Statement stm = miConexion.getConn().createStatement();
                                    //Obtener los datos de columna de la tabla a ejecutar el query
                                    ResultSet rst = stm.executeQuery("SELECT * FROM productos, detalle_movimiento where id_producto='" + U.getPRODUCTOS_id_producto() + "' and id_detalle_movimiento= '" + U.getDETALLE_MOVIMIENTO_id_detalle_movimiento() + "'");

                                    while (rst.next()) {
                                        out.println("<td>" + rst.getString(5) + "</td>");
                                        out.println("<td>" + rst.getString(6) + "</td>");

                                    }

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
                background-image: url(../imagenes/imagensitanueve.jpg);
                background-repeat: no-repeat;
                background-size: 130%;
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


            .placeholder{

                font-family: "Font Awesome 5 Free"; 
                font-weight: 900;

            }

        </style>


        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>

    </body>
</html>
