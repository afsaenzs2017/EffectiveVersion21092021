<%@page import="controlador.PRODUCTOS_DAO"%>
<%@page import="modelo.PRODUCTOS"%>
<%@page import="java.util.*"%>
<%@page import="controlador.conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
<%
    if (session.getAttribute("username") == null) {
        out.print("<script>alert('No ha iniciado sesión')</script>");
        String urls = "../index.html";
        out.print("<script>window.location.href='" + urls + "';</script>");
    }
    String codigos = request.getParameter("id");
    PRODUCTOS_DAO miProducto_dao = new PRODUCTOS_DAO();
    PRODUCTOS producto = miProducto_dao.ConsultarProductos(codigos);


%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="icon"  type="image/png" href="../imagenes/LYDM.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Producto a Carrito</title>
    </head>
    <body>
        <table border="0" width="1000" align="center">
            <tr bgcolor="skyblue">
                <th scope="col"><div align="center"><a href="../../Effective/vista/gestionarMovimientos.jsp" class='btn btn-success sm'><strong>Catálogo</strong></a><a href="../../Effective/vista/menu.jsp" class='btn btn-warning sm'><strong>Regresar Menú</strong></a></div></th>               
            </tr>

        </table>



        <div class="contenedor">        
            <div class="row">
                <div class="col-sm-5 mx-auto">
                    <h2 align="center">
                        <p class="text-light bg-dark">Añadir Producto a Carrito</p>
                    </h2>
                    <table class='table table-striped' width="500" align="center">
                        <form action="/Effective/AnadirCarrito" method="Post">
                            <tr>
                                <th scope="col"><label class="form-control"><strong>Código</strong></label></th>
                                <th scope="col"><input type="text" class="form-control" name="txtCodigo" value="<%=producto.getId_producto()%>" readonly></th>
                            </tr>
                            <tr>
                                <th scope="col"><label class="form-control"><strong>Descripción</strong></label></th>
                                <th scope="col"><input type="text" class="form-control" name="txtNombre" value="<%=producto.getDescripcion_producto()%>" readonly></th>
                            <tr>
                                <th scope="col"><label class="form-control"><strong>Precio</strong></label></th>
                                <th scope="col"><input type="text" class="form-control" name="txtPrecio" value="<%=producto.getPrecio_producto()%>" readonly></th>
                            </tr>
                            <tr>
                                <th scope="col"><label class="form-control"><strong>Cantidad</strong></label></th>
                                <th scope="col"><input type="number" class="form-control" name="txtCantidad" min="1" value="1" pattern="^[0-9]+"></th>
                            </tr>    
                            <tr>
                                <th scope="col" style=display:none;>Iva:</th>
                                <th scope="col"><input type="hidden" name="txtIva" value="<%=producto.getIva_producto()%>"></th>
                            </tr>
                            <tr>
                                <th scope="col" colspan="3"><div align="center"><input type="submit" class='btn btn-primary md' name="btnAnadir" value="Añadir"></div>
                                    <input type="hidden" name="accion" value="anadirCarrito">
                            </tr>

                        </form>
                    </table>


                </div>
            </div>
        </div>


    </body>
</html>
