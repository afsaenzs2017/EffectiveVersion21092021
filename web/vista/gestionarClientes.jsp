<%@page import="java.sql.*"%>
<%@page import="controlador.conexion"%>
<%@page import="modelo.CLIENTES"%>
<%@page import="controlador.CLIENTES_DAO"%>
<%@page import="controlador.USUARIOS_DAO"%>
<%@page import="modelo.USUARIOS"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% if (session.getAttribute("username") == null) {
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

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../javascript/jquery-1.11.0.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="../javascript/RegistroClientes.js" type="text/javascript"></script>
        <script src="../javascript/visualizaOculta.js" type="text/javascript"></script>
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion de cliente</title>
    </head>
    <body onload="visualizaOculta('<%=request.getParameter("Vista")%>');">

        <% String buscando = request.getParameter("buscando");
            if (buscando == null) {
                buscando = "";
            }
        %>

        <div class="row justify-content-md-center sticky-top">
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

        <div id="Registrar" class="bd-example d-none">
            <div class="container">
                <div class="modal-content">      
                    <div align="center">
                        <h1>Registrar Clientes</h1>
                    </div>
                    <br>
                    <div align="center">
                        <img src="../imagenes/agregar-usuario.png" alt="..."/>
                    </div>
                    <br>
                    <form action="/Effective/RegistrarCliente" method="post" onsubmit="return RegistrarClientes()" class="col-12" class="row g-3">
                        <div class="form-row"> 
                            <div class="col-md-6">
                                <h5>Nombres</h5>
                                <input type="text" name="nombreCliente" class="form-control" placeholder="Nombres" id="txt_nombreCliente_reg" > 
                            </div>
                            <br>
                            <div class="col-md-6">
                                <h5>Apellidos</h5>
                                <input type="text" name="apellidoCliente" class="form-control" placeholder="Apellidos" id="txt_apellidoCliente_reg">
                            </div>
                        </div>
                        <br>
                        <div class="form-row"> 
                            <div class="col-md-6">
                                <h5>Identificacion</h5>
                                <input type="text" name="identificacionCliente" class="form-control" placeholder="Identificacion" id="txt_identificacionCliente_reg">
                                <br>                                           
                            </div>
                            <div class="col-md-6">
                                <h5>Correo</h5>
                                <div class="input-group flex-nowrap">
                                    <span class="input-group-text" id="addon-wrapping">@</span>
                                    <input type="email" name="correoCliente" class="form-control" placeholder="Correo" id="txt_correoCliente_reg" aria-describedby="addon-wrapping" required pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}">
                                </div>
                                <br>
                            </div>    
                        </div>
                        <div class="form-row"> 
                            <div class="col-md-6">
                                <h5>Telefono</h5>
                                <input type="text" name="telefonoCliente" class="form-control" placeholder="Telefono" id="txt_telefonoCliente_reg">
                                <br>
                            </div>
                            <div class="col-md-6">
                                <h5>Dirección</h5>
                                <input type="text" name="direccionCliente" class="form-control" placeholder="Direccion" id=txt_direccionCliente_reg">
                                <br>
                            </div>
                        </div>
                        <div class="form-row"> 
                            <div class="col-md-12">
                                <label for="IdTipoDocumento"><h5>Tipo Documento</h5></label>
                                <input id="txt_id_usuario_reg" type="text" class="form-control d-none" name= "id_usuario"/> 
                                <br>

                                <%
                                    USUARIOS_DAO usuario_dao = new USUARIOS_DAO();
                                    ArrayList<USUARIOS> mitipoUsuario = usuario_dao.ConsultarListadoUsuario("", "", "");
                                    out.println("<select class='form-control'id ='SelectsetIdUsuario' size = '1' >");
                                    //out.println("<option onclick='setIdUsuario(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                    for (USUARIOS C : mitipoUsuario) {
                                        out.println("<option onclick='setIdUsuario(\"" + C.getId_usuario() + "\")'>" + C.getNombres_usuario() + " " + C.getApellidos_usuario() + "</option>");

                                    }

                                    out.println("</select>");
                                %>
                                <br>                                
                            </div>
                        </div>
                        <div id="controlRegistro" class="bd-example d-none">
                            <input id="txt_bandera_reg" type="text" class="form-control" name="banderaRegistro">
                        </div>
                        <br>
                        <div align="center"> 
                            <button class="btn btn-primary me-md-2 botoncito" type="submit">Registrar</button>
                            <button class="btn btn-danger me-md-2 botoncito" type="reset">Limpiar</button>
                        </div>
                        <br> 
                    </form>
                </div>
            </div>
        </div>


        <div id="Actualizar" class="bd-example d-none">
            <div class="container">
                <div class="modal-content">      
                    <div align="center">
                        <h1>Actualizar Clientes</h1>
                    </div>
                    <br>
                    <div align="center">
                        <img src="../imagenes/agregar-usuario.png" alt="..."/>
                    </div>
                    <br>
                    <form action="/Effective/ActualizarClientes" method="post" onsubmit="return RegistroClientes()" class="col-12" class="row g-3">
                        <div class="form-row"> 
                            <div class="col-md-6">
                                <h5>Nombres</h5>
                                <input type="text" name="nombreCliente" class="form-control" placeholder="Nombres" id="txt_nombreCliente_reg" value="<%=request.getParameter("Nombres")%>" required> 
                            </div>
                            <br>
                            <div class="col-md-6">
                                <h5>Apellidos</h5>
                                <input type="text" name="apellidoCliente" class="form-control" placeholder="Apellidos" id="txt_apellidoCliente_reg" value="<%=request.getParameter("Apellidos")%>" required>
                            </div>
                        </div>
                        <br>
                        <div class="form-row"> 
                            <div class="col-md-6">
                                <h5>Identificacion</h5>
                                <input type="text" name="identificacionCliente" class="form-control" placeholder="clave" readonly id="txt_identificacionCliente_reg" value="<%=request.getParameter("Identificacion")%>" required>
                                <br>                                           
                            </div>
                            <div class="col-md-6">
                                <h5>Correo</h5>
                                <div class="input-group flex-nowrap">
                                    <span class="input-group-text" id="addon-wrapping">@</span>
                                    <input type="email" name="correoCliente" class="form-control" placeholder="Correo" id="txt_correoCliente_reg" aria-describedby="addon-wrapping" required pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}" value="<%=request.getParameter("Correo")%>" required>
                                </div>
                                <br>
                            </div>    
                        </div>
                        <div class="form-row"> 
                            <div class="col-md-6">
                                <h5>Telefono</h5>
                                <input type="text" name="telefonoCliente" class="form-control" placeholder="Telefono" id="txt_telefonoCliente_reg" value="<%=request.getParameter("Telefono")%>" required>
                                <br>
                            </div>
                            <div class="col-md-6">
                                <h5>Dirección</h5>
                                <input type="text" name="direccionCliente" class="form-control" placeholder="Direccion" id=txt_direccionCliente_reg" value="<%=request.getParameter("Direccion")%>" required>
                                <br>
                            </div>
                            <div class="col-md-12">
                                <label for="IdTipoDocumento"><h5>Usuario</h5></label>
                                <!--- <input id="txt_id_usuario_reg" type="text" class="form-control d-none" name= "id_usuario"/> -->
                                <br>

                                <%
                                    //out.println("<select class='form-control'id ='SelectsetIdUsuario' size = '1' onchange=cambiar();>");
                                    out.println("<select selected disabled class='form-control'id ='txt_id_usuario_reg' size = '1' >");
                                    //out.println("<option onclick='setIdUsuario(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");
                                    for (USUARIOS C : mitipoUsuario) {

                                        //Llenar el combo para recuperar el dato seleccioando
                                        out.println("<option value=" + Integer.toString(C.getId_usuario()) + ">" + C.getNombres_usuario() + " " + C.getApellidos_usuario() + "</option>");
                                    }

                                    out.println("</select>");
                                %>
                                <input id="txt_id_usuario_reg_aux" type="hidden" class="form-control" name= "id_usuario" value="<%=request.getParameter("id_usuario")%>" required />
                                <br>
                            </div>       
                        </div>

                        <div id="controlRegistro" class="bd-example d-none">
                            <input id="txt_bandera_reg" type="text" class="form-control" name="banderaRegistro">
                        </div>
                        <br>
                        <div align="center"> 
                            <button class="btn btn-primary me-md-2 botoncito" onclick="return confirmarAct()" type="submit">Actualizar</button>
                            <button class="btn btn-danger me-md-2 botoncito" type="reset">Limpiar</button>
                        </div>
                        <br> 
                    </form>
                </div>
            </div>
        </div>

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



        <div class="container">
            <form action="/Effective/ConsultarClientes" method="post">
                <div class="row justify-content-md-center">
                    <div id="Listado" class="bd-example">
                        <br>
                        <br>
                        <div>
                            <h1>Listado Clientes</h1>
                            <div id="ControlRegistro" class="input-group">
                                <input id="txt_Id_Consultado" type="text" class="placeholder form-control d-none" name="IdConsultado" value="<%=buscando%>" autofocus placeholder="   Buscar por Identificacion"/>
                                <button type="button" class="btn btn-info d-none" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"onClick="realizarBusqueda()">Buscar</button>
                            </div>
                            <br>                          
                            <div id="Tabla_Clientes">

                                <%
                                    CLIENTES_DAO clientes_dao = new CLIENTES_DAO();
                                    ArrayList<CLIENTES> clientesList = new ArrayList<CLIENTES>();
                                    clientesList = clientes_dao.ConsultarListadoClientes(buscando, buscando, buscando);
                                    //Se oculta la cabecera del campo código
                                    out.println("<table id='tablita' class='display table table-primary table-striped table-hover table-bordered border-danger'><thead><tr><th>Identificacion</th><th>Nombre</th><th>Apellidos</th><th>Correo</th><th>Telefono</th><th>Direccion</th><th style=display:none;>Id usuario que lo registro</th><th>Usuario que lo registro</th><th>Actualizar</th><th>Eliminar</th></tr></thead>");

                                    for (CLIENTES C : clientesList) {

                                        out.println("<tr>");
                                        out.println("<td>" + C.getIdentificacionCliente() + "</td>");
                                        out.println("<td>" + C.getNombreCliente() + "</td>");
                                        out.println("<td>" + C.getApellidoCliente() + "</td>");
                                        out.println("<td>" + C.getCorreoCliente() + "</td>");
                                        out.println("<td>" + C.getTelefonoCliente() + "</td>");
                                        out.println("<td>" + C.getDireccionCliente() + "</td>");
                                        //Se oculta la columna que usan pàra mostrar el código
                                        out.println("<td style=display:none;>" + C.getId_usuario() + "</td>");

                                        //Hago la conexión y busco el código del usuario para recuperar el nombre y apellido
                                        conexion miConexion = new conexion();
                                        Connection nuevaCon;
                                        nuevaCon = miConexion.getConn();
                                        //Procesar una sentencia SQL
                                        Statement stm = miConexion.getConn().createStatement();
                                        //Obtener los datos de columna de la tabla a ejecutar el query
                                        ResultSet rst = stm.executeQuery("select * from usuarios where id_usuario = '" + C.getId_usuario() + "'");

                                        while (rst.next()) {
                                            out.println("<td>" + rst.getString(2) + " " + rst.getString(3) + "</td>");
                                        }

                                        out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-primary form-control btn btn-login' value='Actualizar   'name='Actualizar'onclick='SetIdConsulta(" + C.getIdentificacionCliente() + ")'/>" + "</td>");
                                        //Validar por acceso eliminacion de productos
                                        int tipousu = Integer.parseInt(session.getAttribute("tipousu").toString());
                                        if (tipousu == 1) {
                                            out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-danger form-control btn btn-login ' value='' name='Eliminar' onclick='return confirmar(SetIdConsulta(" + C.getIdentificacionCliente() + "))'/>" + "</td>");
                                        } else {
                                            out.println("<td>" + "<input type = 'submit' disabled='true' class=' placeholder btn btn-danger form-control btn btn-login ' value='' name='Eliminar' onclick='return confirmar(SetIdConsulta(" + C.getIdentificacionCliente() + "))'/>" + "</td>");
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



<script>

            //Recuperar el dato seleccionado en el combo
            $(document).ready(function () {
                var valor =<%=request.getParameter("id_usuario")%>
                //$("#SelectsetIdUsuario option[value='"+valor+"'").attr("selected",true);     
                $("#txt_id_usuario_reg option[value='" + valor + "'").attr("selected", true);
            });

            $(function () {
                //Detectamos el evento change
                $(document).on('change', '#txt_id_usuario_reg', function () {
                    //Obtenemos el valor del select    
                    var value = $(this).val();
                    //Cambiamos el valor del input
                    $('#txt_id_usuario_reg_aux').val(value);
                });
            });

</script>
