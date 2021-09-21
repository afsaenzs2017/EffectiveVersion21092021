
<%@page import="modelo.MARCA_PRODUCTO"%>
<%@page import="controlador.MARCA_PRODUCTO_DAO"%>
<%@page import="modelo.CATEGORIA_PRODUCTO"%>
<%@page import="controlador.CATEGORIA_PRODUCTO_DAO"%>
<%@page import="java.util.Vector"%>
<%@page import="java.sql.*"%>
<%@page import="controlador.conexion"%>
<%@page import="modelo.MOVIMIENTO"%>
<%@page import="modelo.CLIENTES"%>
<%@page import="modelo.PRODUCTOS"%>
<%@page import="controlador.CLIENTES_DAO"%>
<%@page import="controlador.PRODUCTOS_DAO"%>
<%@page import="controlador.MOVIMIENTO_DAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int dato;
    String a = null;
    String b = null;
    /*Permite controlar el inicio de sesión*/
    if (session.getAttribute("username") == null) {
        out.print("<script>alert('No ha iniciado sesión')</script>");
        String urls = "../index.html";
        out.print("<script>window.location.href='" + urls + "';</script>");
    } else {
        HttpSession sesion = request.getSession();

        //Vector<Carrito> carritoCompra=(Vector<Carrito>)sesion.getAttribute("carrito");
    }

%>
<!DOCTYPE html>
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
        <title>Movimientos - Facturación</title>
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

        <div id="Registrar" class="bd-example">
            <div class="container">
                <div class="modal-content">      
                    <div align="center">
                        <h1>Factura</h1>
                    </div>
                    <style>
                        .productosca
                        {
                            font-size: 18px;
                        }
                    </style>
                    <!----<form action="/Effective/RegistrarMovimiento" method="post" onsubmit="return RegistraMovimiento()" class="col-12" class="row g-3">---->
                    <form action="" class="col-12" class="row g-3">
                        <div align="center"><a href="RegistrarVenta.jsp" class='btn btn-primary btn-md' type='submit'><strong>Registrar Venta</strong></a></div>    
                        <div class="form-row"> 
                            <div class="col-md-12">
                                <div class="contenedor">        
                                    <div class="row">
                                        <div class="col-sm-12 mx-auto">
                                            <table table class='table table-striped' align="center">
                                                <td>
                                                <th scope="col"><label class="form-control"><strong>Categorias</strong></label></th>
                                                <th scope="col">
                                                    <%
                                                        CATEGORIA_PRODUCTO_DAO categoria_producto_dao = new CATEGORIA_PRODUCTO_DAO();
                                                        ArrayList<CATEGORIA_PRODUCTO> micategoria = categoria_producto_dao.ConsultarListarCategoria_producto("", "");
                                                        out.println("<select class='form-control' id='SCategoria' name='SCategoria' required size = '1' required style='width: 150px'>");
                                                        for (CATEGORIA_PRODUCTO C : micategoria) {
                                                            out.println("<option value='" + C.getId_categoria_producto() + "'>" + C.getNombre_categoria_producto() + "</option>");
                                                        }
                                                        out.println("</select>");
                                                    %>

                                                </th>
                                                <th scope="col"><label class="form-control"><strong>Marcas</strong></label></th>
                                                <th scope="col">

                                                    <%
                                                        MARCA_PRODUCTO_DAO marca_producto_dao = new MARCA_PRODUCTO_DAO();
                                                        ArrayList<MARCA_PRODUCTO> mimarca = marca_producto_dao.ConsultarListadoMarcaProducto("", "");
                                                        out.println("<select class='form-control' id='SMarca' name='SMarca' required size = '1' required style='width: 150px'>");
                                                        for (MARCA_PRODUCTO M : mimarca) {
                                                            out.println("<option value='" + M.getId_marca_producto() + "'>" + M.getNombre_marca_producto() + "</option>");
                                                        }
                                                        out.println("</select>");
                                                    %>
                                                </th>
                                                <th scope="col">

                                                    <input type="submit" value="Filtrar" class="btn btn-dark sm">

                                                </th>
                                                <th scope="col">
                                                    <a href="gestionarMovimientos.jsp" type="submit" class="btn btn-success sm"><strong>Todos</strong></a>
                                                </th>
                                                <% a = request.getParameter("SCategoria");
                                                    b = request.getParameter("SMarca");
                                                    //out.println(a);
                                                    //out.println(b);
                                                %>
                                                </td>
                                            </table>    
                                        </div>
                                    </div>
                                </div>    


                                <br> 
                                <%
                                    out.println("<table class='table table-primary table-striped table-hover table-bordered border-danger'>");
                                    PRODUCTOS_DAO productos_dao = new PRODUCTOS_DAO();
                                    ArrayList<PRODUCTOS> miproducto;
                                    if ((a != null) && (b != null)) {
                                        miproducto = productos_dao.ConsultarCarrito(a, b);
                                    } else {
                                        miproducto = productos_dao.ConsultarListadoProductos("", "");
                                    }
                                    int cont = 1;
                                    out.println("<tr>");
                                    for (PRODUCTOS P : miproducto) {
                                        
                                        out.println("<td>");
                                        if(P.getCantidad_producto() > 0)
                                        {
                                        out.println("<div align='center'><a href=anadirCarrito.jsp?id='" + P.getIdentificacion_producto() + "' class='btn btn-warning btn-sm' type='submit'>Añadir Carrito</a></div>");    
                                        }
                                        else
                                        {
                                        out.println("<div align='center'><Font Color='Red'>Sin Inventario</Font></div>");
                                        }
                                        out.println("<br><div align='center'><div class='productosca'>" + P.getDescripcion_producto() + "</div></div>");
                                        String imagenfinal = P.getIMAGEN_PRODUCTO();
                                        out.println("<br><div align='center'><img src='" + imagenfinal + "' width='90px' heigth='150px'></div>");
                                        String codigos = P.getIdentificacion_producto();

                                        out.println("</td>");
                                        cont++;
                                        if (cont > 3) {
                                            cont = 1;
                                            out.println("</tr>");
                                            out.println("<tr>");
                                        }
                                    }
                                    out.println("</table>");
                                %>     
                            </div>
                        </div>
                        <div id="controlRegistro" class="bd-example d-none">
                            <input id="txt_bandera_reg" type="text" class="form-control" name="banderaRegistro">
                        </div>

                        <br>
                        <br> 
                    </form>
                </div>
            </div>
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

    </body>
</html>