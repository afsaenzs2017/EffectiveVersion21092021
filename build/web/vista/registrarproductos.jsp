
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="controlador.conexion"%>
<%@page import="controlador.conexion"%>
<%@page import="modelo.MARCA_PRODUCTO"%>
<%@page import="controlador.MARCA_PRODUCTO_DAO"%>
<%@page import="modelo.ESTADO_PRODUCTO"%>
<%@page import="controlador.ESTADO_PRODUCTO_DAO"%>
<%@page import="modelo.CATEGORIA_PRODUCTO"%>
<%@page import="controlador.CATEGORIA_PRODUCTO_DAO"%>
<%@page import="modelo.PROVEEDORES"%>
<%@page import="controlador.PROVEEDORES_DAO"%>
<%@page import="modelo.UNIDAD_DE_MEDIDA_PRODUCTO"%>
<%@page import="controlador.UNIDAD_DE_MEDIDA_PRODUCTO_DAO"%>
<%@page import="modelo.TIPO_DE_DOCUMENTO_CONTABLE"%>
<%@page import="controlador.TIPO_DE_DOCUMENTO_CONTABLE_DAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.PRODUCTOS"%>
<%@page import="controlador.PRODUCTOS_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    /*Permite controlar el inicio de sesión*/
    if (session.getAttribute("username") == null) {
        out.print("<script>alert('No ha iniciado sesión')</script>");
        String urls = "../index.html";
        out.print("<script>window.location.href='" + urls + "';</script>");
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
        <script src="../javascript/RegistroProductos.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="../javascript/jquery-1.11.0.min.js" type="text/javascript"></script>
        <script src="../javascript/visualizaOculta.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../css/estilos.css" rel="stylesheet" type="text/css"/>

        <title>Registro producto</title>
    </head>



    <body onload="visualizaOculta('<%=request.getParameter("Vista")%>');">


        <% String buscando = request.getParameter("buscando");
            if (buscando == null) {
                buscando = "";
            }
        %>

        <div class="row justify-content-md-center">
            <div class="btn-group">
                <button type="button" class="btn btn-secondary" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"
                        onClick="visualizaOculta('Registrar')">
                    Registrar
                </button>
                <button type="button" class="btn btn-secondary" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"
                        onClick="visualizaOculta('Listado')">
                    Consultar
                </button>
                <button type="button" class="btn btn-secondary" ><a href="../vista/menu.jsp" class="text-decoration-none" style="color: white">Volver al inicio</a></button>
            </div>
        </div>

        <br>
        <br>

        <div id="Registrar" class="bd-example d-none">
            <div class="container">
                <div class="modal-content">  
                    <div  class="bd-example">
                        <h1>Registrar Productos</h1>
                        <br>
                        <div align="center">
                            <img src="../imagenes/orden.png" alt="..."/>
                        </div>
                        <br>
                        <form action="/Effective/RegistrarProductos" method="post" onsubmit="return RegistrarProductos()" class="col-12">
                            <div class="form-row"> 
                                <div class="form-group col-md-6"> 
                                    <h5>Nombre</h5>
                                    <input type="text" name="nombre_producto" class="form-control " placeholder="Ingrese el nombre del producto" id="txt_nombre_producto_Reg"> 
                                </div>
                                <br>
                                <div class="form-group col-md-6">
                                    <h5>Identificacion Lote</h5>
                                    <input type="text" name="identificacion_producto" class="form-control" placeholder="Ingrese la identificacion del producto" id="txt_identificacion_producto_Reg"> 
                                </div>
                            </div>
                            <div class="form-row"> 
                                <div class="form-group col-md-6">
                                    <h5>Descripcion</h5>
                                    <input type="text" name="descripcion_producto" class="form-control" placeholder="Ingrese la descripcion del producto" id="txt_descripcion_producto_Reg">
                                </div>
                                <div class="form-group col-md-6">
                                    <h5>Imagen</h5>
                                    <!--<input type="file" class="form-control" id="file" accept="image/*" onchage="getFilePath()"/>--->
                                    <input id="image1" type="file" class="form-control inputfile hidden-xs hidden-md" value="image1" accept="image/*" name="image1" />
                                    <script>

                                        document.getElementById('image1').onchange = function ()
                                        {
                                            console.log(this.value);
                                            //alert(document.getElementById('image1').files[0].name);
                                            archivo = "../imagenes/" + document.getElementById('image1').files[0].name
                                            $("#txt_imagen_producto_Reg").val(archivo);
                                        }
                                    </script>
                                    <input type="hidden" name="imagen_producto" class="form-control" readonly id="txt_imagen_producto_Reg">
                                </div>
                            </div>  
                            <div class="form-row"> 
                                <div class="form-group col-md-4">
                                    <h5>Precio</h5>
                                    <input type="text" name="precio_producto" class="form-control" placeholder="Ingrese el precio del producto" id="txt_precio_producto_Reg"> 
                                </div>
                                <br>
                                <div class="form-group col-md-4">
                                    <h5>Cantidad</h5>
                                    <input type="text" name="cantidad_producto" class="form-control" placeholder="Ingrese la cantidad del producto" id="txt_cantidad_producto_Reg" required>
                                </div>
                                <br>
                                <div class="form-group col-md-4">
                                    <h5>Iva</h5>
                                    <input type="text" name="iva_producto" class="form-control" placeholder="Ingrese el iva del producto" id="txt_iva_producto_Reg">
                                </div>
                                <br>
                            </div>


                            <div class="form-row"> 
                                <div class="form-group col-md-6">
                                    <label for="IdUnidadMedida"><h5>Unidad Medida</h5></label>
                                    <input type="text" name="UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto" class="form-control d-none" placeholder="unidad de medida" id="txt_UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto_Reg">
                                    <%
                                        UNIDAD_DE_MEDIDA_PRODUCTO_DAO mi_estado_unidad_medidaDao = new UNIDAD_DE_MEDIDA_PRODUCTO_DAO();
                                        ArrayList<UNIDAD_DE_MEDIDA_PRODUCTO> mi_lista_unidad_medida = mi_estado_unidad_medidaDao.ConsultarListarUnidad_de_medida("", "");
                                        out.println("<select class='form-control'id ='SelectUnidadMedida' size = '1' >");
                                        out.println("<option onclick='setIdUnidadMedida(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                        for (UNIDAD_DE_MEDIDA_PRODUCTO U : mi_lista_unidad_medida) {
                                            out.println("<option onclick='setIdUnidadMedida(\"" + U.getId_unidad_de_medida_producto() + "\")'>" + U.getDescripcion_unidad_de_medida_producto() + "</option>");

                                        }

                                        out.println("</select>");
                                    %>
                                </div>
                                <br>
                                <div class="form-group col-md-6"> 
                                    <label for="IdProveedor"><h5>Proveedores</h5></label>
                                    <input type="Text" name="PROVEEDORES_id_proveedor" class="form-control d-none" placeholder="proveedor" id="txt_PROVEEDORES_id_proveedor_Reg">
                                    <br>

                                    <%
                                        PROVEEDORES_DAO mi_proveedor_dao = new PROVEEDORES_DAO();
                                        ArrayList<PROVEEDORES> mi_lista_proveedor = mi_proveedor_dao.ConsultarListadoProveedor("", "");
                                        out.println("<select class='form-control'id ='SelectProveedores' size = '1' >");
                                        out.println("<option onclick='setIdProveedor(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                        for (PROVEEDORES P : mi_lista_proveedor) {
                                            out.println("<option onclick='setIdProveedor(\"" + P.getId_proveedor() + "\")'>" + P.getNombre_proveedor() + "</option>");

                                        }

                                        out.println("</select>");
                                    %>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="IdCategoria"><h5>Categoria</h5></label>
                                    <input type="number" name="CATEGORIA_PRODUCTO_id_categoria_producto" class="form-control d-none" placeholder="Categoria" id="txt_CATEGORIA_PRODUCTO_id_categoria_producto_reg">

                                    <br>
                                    <%
                                        CATEGORIA_PRODUCTO_DAO mi_Categoria_producto_dao = new CATEGORIA_PRODUCTO_DAO();
                                        ArrayList<CATEGORIA_PRODUCTO> mi_lista_Categoria_producto = mi_Categoria_producto_dao.ConsultarListarCategoria_producto("", "");
                                        out.println("<select class='form-control'id ='SelectCategoria' size = '1' >");
                                        out.println("<option onclick='setIdCategoria(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                        for (CATEGORIA_PRODUCTO C : mi_lista_Categoria_producto) {
                                            out.println("<option onclick='setIdCategoria(\"" + C.getId_categoria_producto() + "\")'>" + C.getNombre_categoria_producto() + "</option>");

                                        }

                                        out.println("</select>");
                                    %>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="IdEstadoP"><h5>Estado</h5></label>
                                    <input type="text" name="ESTADO_PRODUCTO_id_estado_producto" class="form-control d-none" placeholder="Estado Producto" id="txt_ESTADO_PRODUCTO_id_estado_producto_reg">
                                    <br> 

                                    <%
                                        ESTADO_PRODUCTO_DAO mi_estadoproducto_dao = new ESTADO_PRODUCTO_DAO();
                                        ArrayList<ESTADO_PRODUCTO> mi_lista_estado_producto = mi_estadoproducto_dao.ConsultarListadoEstadoProducto("", "");
                                        out.println("<select class='form-control'id ='SelectEstado' size = '1' >");
                                        out.println("<option onclick='setIdEstadoP(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                        for (ESTADO_PRODUCTO E : mi_lista_estado_producto) {
                                            out.println("<option onclick='setIdEstadoP(\"" + E.getId_estado_producto() + "\")'>" + E.getDescripcion_estado_producto() + "</option>");

                                        }

                                        out.println("</select>");
                                    %>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <br>
                                    <label for="IdMarca"><h5>Marca</h5></label>
                                    <input type="text" name="MARCA_PRODUCTO_id_marca_producto" class="form-control d-none" placeholder="Marca del producto" id="txt_MARCA_PRODUCTO_id_marca_producto_reg">
                                    <%
                                        MARCA_PRODUCTO_DAO mi_marcaproducto_dao = new MARCA_PRODUCTO_DAO();
                                        ArrayList<MARCA_PRODUCTO> mi_lista_marcaproducto = mi_marcaproducto_dao.ConsultarListadoMarcaProducto("", "");
                                        out.println("<select class='form-control'id ='SelectMarca' size = '1' >");
                                        out.println("<option onclick='setIdMarca(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                        for (MARCA_PRODUCTO M : mi_lista_marcaproducto) {
                                            out.println("<option onclick='setIdMarca(\"" + M.getId_marca_producto() + "\")'>" + M.getNombre_marca_producto() + "</option>");

                                        }

                                        out.println("</select>");
                                    %>
                                </div>
                                <br>                                 
                            </div>
                            <div id="controlRegistro" class="bd-example d-none">
                                <input id="txt_bandera_reg" type="text" class="form-control" name="banderaRegistro">
                            </div>
                            <br>
                            <div align="center"> 
                                <button class="btn btn-primary me-md-2" type="submit">Registrar</button>
                                <button class="btn btn-danger me-md-2" type="reset">Limpiar</button>
                            </div>  
                            <br>
                        </form>
                    </div>
                </div>
            </div>
        </div>



        <div id="Actualizar" class="bd-example d-none">
            <div class="container">
                <div class="modal-content">  
                    <div  class="bd-example">
                        <h1>Actualizar Productos</h1>
                        <br>
                        <div align="center">
                            <img src="../imagenes/orden.png" alt="..."/>
                        </div>
                        <br>
                        <form action="/Effective/ActualizarProductos" method="post" onsubmit="return ActProductos()" class="col-12">
                            <div class="form-row"> 
                                <div class="form-group col-md-6"> 
                                    <h5>Nombre</h5>
                                    <input type="text" name="nombre_producto" class="form-control " placeholder="Ingrese el nombre del producto" id="txt_nombre_producto_Act"  value="<%=request.getParameter("Nombres")%>" required/> 
                                </div>
                                <br>
                                <div class="form-group col-md-6">
                                    <h5>Identificacion Lote</h5>
                                    <input type="text" name="identificacion_producto" class="form-control" placeholder="Ingrese la identificacion del producto" id="txt_identificacion_producto_Act" readonly value="<%=request.getParameter("Identificacion")%>" required/> 
                                </div>
                            </div>
                            <div class="form-row"> 
                                <div class="form-group col-md-6">
                                    <h5>Descripcion</h5>
                                    <input type="text" name="descripcion_producto" class="form-control" placeholder="Ingrese la descripcion del producto" id="txt_descripcion_producto_Act"  value="<%=request.getParameter("Descripcion")%>" required/>
                                </div>
                                <div class="form-group col-md-6">
                                    <h5>Imagen <img src="<%=request.getParameter("Imagen")%>" alt="..." width="15%" height="15%"/></h5>
                                    <input id="image11" type="file" class="form-control inputfile hidden-xs hidden-md" value="image1" accept="image/*" name="image1" />
                                    <script>

                                        document.getElementById('image11').onchange = function ()
                                        {
                                            console.log(this.value);
                                            //alert(document.getElementById('image11').files[0].name);
                                            archivo = "../imagenes/" + document.getElementById('image11').files[0].name
                                            $("#txt_imagen_producto_Act").val(archivo);
                                        }
                                    </script>
                                    <input type="hidden" name="imagen_producto" class="form-control" readonly id="txt_imagen_producto_Act" value="<%=request.getParameter("Imagen")%>">
                                </div>
                            </div>  
                            <br>
                            <div class="form-row"> 
                                <div class="form-group col-md-4">
                                    <h5>Precio</h5>
                                    <input type="text" name="precio_producto" class="form-control" placeholder="Ingrese el precio del producto" id="txt_precio_producto_Act" value="<%=request.getParameter("Precio")%>" required/> 
                                </div>
                                <br>
                                <div class="form-group col-md-4">
                                    <h5>Cantidad</h5>
                                    <input type="text" readonly name="cantidad_producto" class="form-control" placeholder="Ingrese la cantidad del producto" id="txt_cantidad_producto_Act" value="<%=request.getParameter("Cantidad")%>"/>
                                </div>
                                <br>
                                <div class="form-group col-md-4">
                                    <h5>Iva</h5>
                                    <input type="text" name="iva_producto" class="form-control" placeholder="Ingrese el iva del producto" id="txt_iva_producto_Act" value="<%=request.getParameter("Iva")%>" required/>
                                </div>
                                <br>
                            </div>
                            <br>
                            <div class="form-row"> 
                                <div class="form-group col-md-6">
                                    <label for="IdUnidadMedida"><h5>Unidad Medida</h5></label>
                                    <input type="text" name="UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto"  class="form-control d-none " placeholder="unidad de medida" id="txt_UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto_Act" readonly value="<%=request.getParameter("UnidadMedida")%>"/>

                                    <%

                                        out.println("<select selected disabled class='form-control'id ='txt_UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto_Act' size = '1' >");

                                        for (UNIDAD_DE_MEDIDA_PRODUCTO U : mi_lista_unidad_medida) {

                                            out.println("<option value=" + U.getId_unidad_de_medida_producto() + ">" + U.getDescripcion_unidad_de_medida_producto() + " " + "</option>");

                                        }

                                        out.println("</select>");

                                    %>
                                    <input id="txt_UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto_Act_aux" type="hidden" class="form-control" name= "txt_UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto_Act" value="<%=request.getParameter("UnidadMedida")%>" required />
                                </div>
                                <div class="form-group col-md-6"> 
                                    <label for="IdProveedor"><h5>Proveedores</h5></label>
                                    <input type="Text" name="PROVEEDORES_id_proveedor" class="form-control d-none " placeholder="proveedor" id="txt_PROVEEDORES_id_proveedor_Act" readonly value="<%=request.getParameter("Proveedores")%>"/>

                                    <%

                                        out.println("<select selected disabled class='form-control'id ='txt_PROVEEDORES_id_proveedor_Act' size = '1' >");

                                        for (PROVEEDORES P : mi_lista_proveedor) {

                                            out.println("<option value=" + Integer.toString(P.getId_proveedor()) + ">" + P.getNombre_proveedor() + " " + "</option>");

                                        }


                                    %>
                                    <input id="txt_PROVEEDORES_id_proveedor_Act_aux" type="hidden" class="form-control" name= "PROVEEDORES_id_proveedor" value="<%=request.getParameter("Proveedores")%>" required />
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="IdCategoria"><h5>Categoria</h5></label>
                                    <input type="" name="CATEGORIA_PRODUCTO_id_categoria_producto" class="form-control d-none" placeholder="Categoria" id="txt_CATEGORIA_PRODUCTO_id_categoria_producto_Act"  readonly value="<%=request.getParameter("Categoria_producto")%>"/>

                                    <%

                                        out.println("<select selected disabled class='form-control'id ='txt_CATEGORIA_PRODUCTO_id_categoria_producto_Act' size = '1' >");

                                        for (CATEGORIA_PRODUCTO C : mi_lista_Categoria_producto) {

                                            out.println("<option value=" + C.getId_categoria_producto() + ">" + C.getNombre_categoria_producto() + " " + "</option>");

                                        }
                                    %>
                                    <input id="txt_CATEGORIA_PRODUCTO_id_categoria_producto_Act_aux" type="hidden" class="form-control" name= "CATEGORIA_PRODUCTO_id_categoria_producto" value="<%=request.getParameter("Proveedores")%>" required />
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="IdEstadoP"><h5>Estado</h5></label>
                                    <input type="text" name="ESTADO_PRODUCTO_id_estado_producto" class="form-control d-none " placeholder="Estado Producto" id="txt_ESTADO_PRODUCTO_id_estado_producto_Act" readonly value="<%=request.getParameter("Categoria_producto")%>"/>

                                    <%

                                        out.println("<select selected disabled class='form-control'id ='txt_ESTADO_PRODUCTO_id_estado_producto_Act' size = '1' >");

                                        for (ESTADO_PRODUCTO E : mi_lista_estado_producto) {

                                            out.println("<option value=" + E.getId_estado_producto() + ">" + E.getDescripcion_estado_producto() + " " + "</option>");

                                        }


                                    %>
                                    <input id="txt_ESTADO_PRODUCTO_id_estado_producto_Act_aux" type="hidden" class="form-control" name= "ESTADO_PRODUCTO_id_estado_producto" value="<%=request.getParameter("Estado_producto")%>" required />
                                    <br> 
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-12">         
                                    <label for="IdMarca"><h5>Marca</h5></label>
                                    <input type="text" name="MARCA_PRODUCTO_id_marca_producto" class="form-control d-none" placeholder="Marca del producto" id="txt_MARCA_PRODUCTO_id_marca_producto_Act" readonly value="<%=request.getParameter("Marca_producto")%>"/>

                                    <%

                                        out.println("<select selected disabled class='form-control'id ='txt_MARCA_PRODUCTO_id_marca_producto_Act' size = '1' >");

                                        for (MARCA_PRODUCTO M : mi_lista_marcaproducto) {

                                            out.println("<option value=" + M.getId_marca_producto() + ">" + M.getNombre_marca_producto() + " " + "</option>");

                                        }


                                    %>
                                    <input id="txt_MARCA_PRODUCTO_id_marca_producto_Act_aux" type="hidden" class="form-control" name= "MARCA_PRODUCTO_id_marca_producto" value="<%=request.getParameter("Marca_producto")%>" required />
                                </div>
                                <br>                                 
                            </div>
                            <div id="controlRegistro" class="bd-example d-none">
                                <input id="txt_bandera_reg" type="text" class="form-control" name="banderaRegistro">
                            </div>
                            <br>
                            <div align="center"> 
                                <button class="btn btn-primary me-md-2" onclick="return confirmarAct()" type="submit">Actualizar</button>
                                <button class="btn btn-danger me-md-2" type="reset">Limpiar</button>
                            </div>  
                            <br>
                        </form>
                    </div>
                </div>
            </div>
        </div>



        <div class="container">
            <form action="/Effective/ConsultarProductos" method="post">
                <div class="row justify-content-md-center">
                    <div id="Listado" class="bd-example">
                        <div>
                            <h1>Listado Productos</h1>
                            <div id="ControlRegistro" class="input-group">
                                <input id="txt_Id_Consultado" type="text" class="placeholder form-control d-none" name="IdConsultado" value="<%=buscando%>" autofocus placeholder="   Buscar por Identificacion del proveedor"/>
                                <button type="button" class="btn btn-info d-none" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"onClick="realizarBusqueda()">Buscar</button>
                            </div>
                            <br>
                            <div id="Tabla_Productos"></div>
                            <%
                                PRODUCTOS_DAO productos_dao = new PRODUCTOS_DAO();
                                ArrayList<PRODUCTOS> productosList = new ArrayList<PRODUCTOS>();
                                productosList = productos_dao.ConsultarListadoProductos(buscando, buscando);
                                out.println("<table id='tablita' class='display table table-primary table-striped table-hover table-bordered border-danger'><thead><tr><th>Nombre Producto</th><th>Numero referencia</th><th>Precio</th><th>Descripcion</th><th>Cantidad</th><th>Iva</th><th>Unidad_medida</th><th>Proveedor</th><th>categoria</th><th>Estado</th><th>Marca</th><th>Actualizar</th><th>Eliminar</th></tr></thead>");

                                for (PRODUCTOS P : productosList) {
                                    out.println("<tr>");
                                    out.println("<td>" + P.getNombre_producto() + "</td>");
                                    out.println("<td>" + P.getIdentificacion_producto() + "</td>");
                                    out.println("<td>" + P.getPrecio_producto() + "</td>");
                                    out.println("<td>" + P.getDescripcion_producto() + "</td>");
                                    out.println("<td>" + P.getCantidad_producto() + "</td>");
                                    out.println("<td>" + P.getIva_producto() + "</td>");

                                    conexion miConexion = new conexion();
                                    Connection nuevaCon;
                                    nuevaCon = miConexion.getConn();
                                    //Procesar una sentencia SQL
                                    Statement stm = miConexion.getConn().createStatement();
                                    //Obtener los datos de columna de la tabla a ejecutar el query
                                    ResultSet rst = stm.executeQuery("SELECT * FROM unidad_de_medida_producto, proveedores, categoria_producto, estado_producto, marca_producto where id_unidad_de_medida_producto='" + P.getUNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto() + "' and id_proveedor= '" + P.getPROVEEDORES_id_proveedor() + "' and id_categoria_producto = '" + P.getCATEGORIA_PRODUCTO_id_categoria_producto() + "' and id_estado_producto = '" + P.getESTADO_PRODUCTO_id_estado_producto() + "'and id_marca_producto= '" + P.getMARCA_PRODUCTO_id_marca_producto() + "'");

                                    while (rst.next()) {
                                        out.println("<td>" + rst.getString(2) + "</td>");
                                        out.println("<td>" + rst.getString(5) + "</td>");
                                        out.println("<td>" + rst.getString(11) + "</td>");
                                        out.println("<td>" + rst.getString(13) + "</td>");
                                        out.println("<td>" + rst.getString(15) + "</td>");

                                    }

                                    out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-primary form-control btn btn-login' value='Actualizar   'name='Actualizar'onclick='SetIdConsulta(" + P.getIdentificacion_producto() + ")'/>" + "</td>");
                                    //Validar por acceso eliminacion de productos
                                    int tipousu = Integer.parseInt(session.getAttribute("tipousu").toString());
                                    if (tipousu == 1) {
                                        out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-danger form-control btn btn-login ' value='' name='Eliminar' onclick='return confirmar (SetIdConsulta(" + P.getIdentificacion_producto() + "))'/>" + "</td>");
                                    } else {
                                        out.println("<td>" + "<input type = 'submit' disabled='true' class=' placeholder btn btn-danger form-control btn btn-login ' value='' name='Eliminar' onclick='return confirmar (SetIdConsulta(" + P.getIdentificacion_producto() + "))'/>" + "</td>");
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

        <script>
            function confirmarAct(procodigo)
            {
//Confirmar cierre de sesión retornará verdadero o falso    
                pregunta = confirm("¿Desea actualizar este registro?");
                if (pregunta)
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        </script>


        <script>
            function confirmar(procodigo)
            {
//Confirmar cierre de sesión retornará verdadero o falso    
                pregunta = confirm("¿Desea eliminar este registro?");
                if (pregunta)
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        </script>




        <style>

            body{
                background-image: url(../imagenes/imagensitanueve.jpg);
                background-repeat: no-repeat;
                background-size: 130%;
                font-family: 'Acme', sans-serif;
                /*font-size: 20px;*/
                font-size: 16px;
            }

            h1{

                color: whitesmoke; 
            }


            .modal-content{

                background-color: #494f54;
                opacity: .92;
                padding: 30px;
                display: flex;
                margin-top: 10px;
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

<script type="text/javascript">
            function getFilePath() {
                $('input[type=file]').change(function () {
                    var filePath = $('#fileUpload').val();
                    alert(filePath);
                });
            }
</script>
