<%-- 
    Document   : registroUsuarios
    Created on : 23/11/2020, 04:03:55 PM
    Author     : AndresSaenz
--%>

<%@page import="controlador.ENCRIPDECRIP"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="controlador.conexion"%>
<%@page import="controlador.conexion"%>
<%@page import="modelo.GENERO_USUARIO"%>
<%@page import="controlador.GENERO_USUARIO_DAO"%>
<%@page import="modelo.ESTADO_USUARIO"%>
<%@page import="controlador.ESTADO_USUARIO_DAO"%>
<%@page import="modelo.TIPO_DOC_IDENTIFICACION_USUARIO"%>
<%@page import="controlador.TIPO_DOC_IDENTIFICACION_USUARIO_DAO"%>
<%@page import="modelo.TIPO_USUARIO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controlador.TIPO_USUARIO_DAO"%>
<%@page import="modelo.USUARIOS"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controlador.USUARIOS_DAO"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../javascript/jquery-1.11.0.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="../javascript/RegistroUsuarios.js" type="text/javascript"></script>
        <script src="../javascript/visualizaOculta.js" type="text/javascript"></script>
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Gestión de usuarios</title>
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
                        <h1>Registrar Usuarios</h1>
                    </div>
                    <br>
                    <div align="center">
                        <img src="../imagenes/agregar-usuario.png" alt="Foto usuario"/>
                    </div>
                    <br>
                    <form action="/Effective/RegistroUsuarios" method="post" onsubmit="return RegistroUsuarios()" class="col-12" class="row g-3">
                        <div class="form-row"> 
                            <div class="col-md-6">
                                <h5>Nombres</h5>
                                <input type="text" name="nombres_usuario" class="form-control" placeholder="Nombres" id="txt_nombres_usuario_reg"> 
                            </div>
                            <br>
                            <div class="col-md-6">
                                <h5>Apellidos</h5>
                                <input type="text" name="apellidos_usuario" class="form-control" placeholder="Apellidos" id="txt_apellidos_usuario_reg">
                            </div>
                        </div>
                        <br>
                        <div class="form-row"> 
                            <div class="col-md-4">
                                <h5>Clave</h5>
                                <input type="password" name="clave" class="form-control" placeholder="clave" id="txt_clave_reg">
                                <br>                                           
                            </div>
                            <div class="col-md-4">
                                <h5>Telefono</h5>
                                <input type="text" name="telefono_usuario" class="form-control" placeholder="Telefono" id="txt_telefono_usuario_reg">
                                <br>
                            </div>
                            <div class="col-md-4">
                                <h5>Dirección</h5>
                                <input type="text" name="direccion_usuario" class="form-control" placeholder="Direccion" id="txt_direccion_usuario_reg">
                                <br>
                            </div>
                        </div>
                        <div class="form-row"> 
                            <div class="col-md-6">
                                <h5>Correo</h5>
                                <div class="input-group flex-nowrap">
                                    <span class="input-group-text" id="addon-wrapping">@</span>
                                    <input type="email" name="correo_usuario" class="form-control" placeholder="Correo" id="txt_correo_usuario_reg" aria-describedby="addon-wrapping" required pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}">
                                </div>
                                <br>
                            </div>
                            <div class="col-md-6">   
                                <h5>Identificación</h5>
                                <input type="text" name="identificacion_usuario" class="form-control" placeholder="Numero identificación" id="txt_identificacion_usuario_reg">
                                <br>
                                <br>
                            </div>
                        </div>
                        <div class="form-row">   
                            <div class="col-md-6">
                                <label for="IdTipoDocumento"><h5>Tipo Documento</h5></label>
                                <input id="txt_TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario_reg" type="text" class="form-control d-none" name= "TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario"/> 
                                <br>

                                <%
                                    TIPO_DOC_IDENTIFICACION_USUARIO_DAO tipoDocumento_dao = new TIPO_DOC_IDENTIFICACION_USUARIO_DAO();
                                    ArrayList<TIPO_DOC_IDENTIFICACION_USUARIO> mitipoDocumento = tipoDocumento_dao.ConsultarListadoTipo_doc_usuario("", "");
                                    out.println("<select class='form-control'id ='SelectTipoDocumento' size = '1' >");
                                    out.println("<option onclick='setIdTipoDocumentoo(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                    for (TIPO_DOC_IDENTIFICACION_USUARIO C : mitipoDocumento) {
                                        out.println("<option onclick='setIdTipoDocumento(\"" + C.getId_doc_identificacion_usuario() + "\")'>" + C.getTipo_doc_identificacion_usuario() + "</option>");

                                    }

                                    out.println("</select>");
                                %>
                                <br>
                            </div>
                            <div class="col-md-6">  
                                <label for="IdTipoUsuario"><h5>Tipo Usuario</h5></label>
                                <input id="txt_TIPO_USUARIO_id_tipo_usuario_reg" type="text" class="form-control d-none" name= "TIPO_USUARIO_id_tipo_usuario"/> 
                                <br>

                                <%
                                    TIPO_USUARIO_DAO tipoUsuario_dao = new TIPO_USUARIO_DAO();
                                    ArrayList<TIPO_USUARIO> mitipoUsuario = tipoUsuario_dao.ConsultarListarTipo_usuario("", "");
                                    out.println("<select class='form-control'id ='SelectTipoUsuario' size = '1' >");
                                    out.println("<option onclick='setIdTipoUsuario(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                    for (TIPO_USUARIO C : mitipoUsuario) {
                                        out.println("<option onclick='setIdTipoUsuario(\"" + C.getId_tipo_usuario() + "\")'>" + C.getDescripcion_tipo_usuario() + "</option>");

                                    }

                                    out.println("</select>");
                                %>
                                <br>
                            </div>                                    
                        </div>                         
                        <div class="form-row">                                                                  
                            <div class="col-md-6">
                                <label for="IdEstadoUsuario"><h5>Estado Usuario</h5></label>
                                <input id="txt_ESTADO_USUARIO_id_estado_usuario_reg" type="text" class="form-control d-none" name= "ESTADO_USUARIO_id_estado_usuario"/> 
                                <br>

                                <%
                                    ESTADO_USUARIO_DAO tipoEstado_dao = new ESTADO_USUARIO_DAO();
                                    ArrayList<ESTADO_USUARIO> miestadoEstado = tipoEstado_dao.ConsultarListarEstado_usuario("", "");
                                    out.println("<select class='form-control'id ='SelectEstadoUsuario' size = '1' >");
                                    out.println("<option onclick='setIdEstadoUsuario(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                    for (ESTADO_USUARIO C : miestadoEstado) {
                                        out.println("<option onclick='setIdEstadoUsuario(\"" + C.getId_estado_usuario() + "\")'>" + C.getDescripcion_estado_usuario() + "</option>");

                                    }

                                    out.println("</select>");
                                %>     
                            </div>
                            <div class="col-md-6">
                                <label for="IdGeneroUsuario"><h5>Genero Usuario</h5></label>
                                <input id="txt_GENERO_USUARIO_id_genero_usuario_reg" type="text" class="form-control d-none" name= "GENERO_USUARIO_id_genero_usuario"/> 
                                <br>

                                <%
                                    GENERO_USUARIO_DAO tipoGenero_dao = new GENERO_USUARIO_DAO();
                                    ArrayList<GENERO_USUARIO> mitipoGenero = tipoGenero_dao.ConsultarListarGenero_usuario("", "");
                                    out.println("<select class='form-control'id ='SelectGeneroUsuario' size = '1' >");
                                    out.println("<option onclick='setIdGeneroUsuario(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");

                                    for (GENERO_USUARIO C : mitipoGenero) {

                                        out.println("<option onclick='setIdGeneroUsuario(\"" + C.getId_genero_usuario() + "\")'>" + C.getDescripcion_genero_usuario() + "</option>");

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
                        <h1>Actualización Usuarios</h1>
                    </div>
                    <br>
                    <div align="center">
                        <img src="../imagenes/agregar-usuario.png" alt="Foto usuario"/>
                    </div>
                    <br>
                    <form action="/Effective/ActualizarUsuario" method="post" onsubmit="return RegistroUsuariosAct()" class="col-12" class="row g-3">
                        <div class="form-row"> 
                            <div class="col-md-6">
                                <h5>Nombres</h5>
                                <input type="text" name="nombres_usuario" class="form-control" placeholder="Nombres" id="txt_nombres_usuario_Act" value="<%=request.getParameter("Nombres")%>" required/> 
                            </div>
                            <br>
                            <div class="col-md-6">
                                <h5>Apellidos</h5>
                                <input type="text" name="apellidos_usuario" class="form-control" placeholder="Apellidos" id="txt_apellidos_usuario_Act" value="<%=request.getParameter("Apellidos")%>" required/>
                            </div>
                        </div>
                        <br>
                        <div class="form-row"> 
                            <div class="col-md-4">
                                <h5>Clave</h5>
                                <%
                                    //Desencriptar clave de acceso
                                    String secretKey = "md5";
                                    ENCRIPDECRIP mMain = new ENCRIPDECRIP();
                                    String Claveaux = mMain.deecnode(secretKey, request.getParameter("Clave"));
                                %>    
                                <!---<input type="password" name="clave" class="form-control" placeholder="clave" id="txt_clave_Act" value="<%=request.getParameter("Clave")%>" required/>--->
                                <input type="password" name="clave" class="form-control" placeholder="clave" size="100" id="txt_clave_Act" value="<%=Claveaux%>" required/>
                                <br>                                           
                            </div>
                            <div class="col-md-4">
                                <h5>Telefono</h5>
                                <input type="text" name="telefono_usuario" class="form-control" placeholder="Telefono" id="txt_telefono_usuario_Act" value="<%=request.getParameter("Telefono")%>" required/>
                                <br>
                            </div>
                            <div class="col-md-4">
                                <h5>Dirección</h5>
                                <input type="text" name="direccion_usuario" class="form-control" placeholder="Direccion" id="txt_direccion_usuario_Act" value="<%=request.getParameter("Direccion")%>" required/>
                                <br>
                            </div>
                            <div class="col-md-6">
                                <h5>Correo</h5>
                                <div class="input-group flex-nowrap">
                                    <span class="input-group-text" id="addon-wrapping">@</span>
                                    <input type="email"  name="correo_usuario" class="form-control" placeholder="Correo" id="txt_correo_usuario_Act" aria-describedby="addon-wrapping" required pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}" value="<%=request.getParameter("Correo")%> "/>
                                </div>
                                <br>
                            </div>
                            <div class="col-md-6">   
                                <h5>Identificación</h5>
                                <input type="text" name="identificacion_usuario" class="form-control " placeholder="Numero identificación" readonly id="txt_identificacion_usuario_Act" value="<%=request.getParameter("Identificacion")%>" required/>
                            </div>
                        </div>
                        <div class="form-row">   
                            <div class="col-md-6">
                                <label for="IdTipoDocumento"><h5>Tipo Documento</h5></label>
                                <input id="txt_TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario_Act" type="text" class="form-control d-none" readonly name= "TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario" value="<%=request.getParameter("Tipo_doc")%>"/> 

                                <%
                                    //out.println("<select class='form-control'id ='SelectsetIdUsuario' size = '1' onchange=cambiar();>");
                                    out.println("<select selected disabled class='form-control'id ='txt_TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario_Act' size = '1' >");
                                    //out.println("<option onclick='setIdUsuario(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");
                                    for (TIPO_DOC_IDENTIFICACION_USUARIO C : mitipoDocumento) {

                                        //Llenar el combo para recuperar el dato seleccioando
                                        out.println("<option value=" + C.getId_doc_identificacion_usuario() + ">" + " " + C.getTipo_doc_identificacion_usuario() + "</option>");
                                    }

                                    out.println("</select>");
                                %>
                                <input id="txt_TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario_Act_aux" type="hidden" class="form-control" name= "TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario" value="<%=request.getParameter("TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario")%>" required />
                            </div>
                            <div class="col-md-6">  
                                <label for="IdTipoUsuario"><h5>Tipo Usuario</h5></label>
                                <input id="txt_TIPO_USUARIO_id_tipo_usuario_Act" type="text" class="form-control d-none" readonly name= "TIPO_USUARIO_id_tipo_usuario" value="<%=request.getParameter("Tipo_usuario")%>"/> 

                                <%
                                    //out.println("<select class='form-control'id ='SelectsetIdUsuario' size = '1' onchange=cambiar();>");
                                    out.println("<select selected disabled class='form-control'id ='txt_TIPO_USUARIO_id_tipo_usuario_Actt' size = '1' >");
                                    //out.println("<option onclick='setIdUsuario(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");
                                    for (TIPO_USUARIO C : mitipoUsuario) {

                                        //Llenar el combo para recuperar el dato seleccioando
                                        out.println("<option value=" + C.getId_tipo_usuario() + ">" + " " + C.getDescripcion_tipo_usuario() + "</option>");
                                    }

                                    out.println("</select>");
                                %>
                                <input id="txt_TIPO_USUARIO_id_tipo_usuario_Act_aux" type="hidden" class="form-control" name= "TIPO_USUARIO_id_tipo_usuario" value="<%=request.getParameter("Tipo_usuario")%>" required />
                                <br>
                            </div>                                    
                        </div>                         
                        <div class="form-row">                                                                  
                            <div class="col-md-6">
                                <label for="IdEstadoUsuario"><h5>Estado Usuario</h5></label>
                                <input id="txt_ESTADO_USUARIO_id_estado_usuario_Act" type="text" class="form-control d-none" readonly name= "ESTADO_USUARIO_id_estado_usuario" value="<%=request.getParameter("Estado_usuario")%>"/> 
                                <%
                                    //out.println("<select class='form-control'id ='SelectsetIdUsuario' size = '1' onchange=cambiar();>");
                                    out.println("<select selected disabled class='form-control'id ='txt_ESTADO_USUARIO_id_estado_usuario_Act' size = '1' >");
                                    //out.println("<option onclick='setIdUsuario(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");
                                    for (ESTADO_USUARIO C : miestadoEstado) {

                                        //Llenar el combo para recuperar el dato seleccioando
                                        out.println("<option value=" + C.getId_estado_usuario() + ">" + " " + C.getDescripcion_estado_usuario() + "</option>");
                                    }

                                    out.println("</select>");
                                %>
                                <input id="txt_ESTADO_USUARIO_id_estado_usuario_Act_aux" type="hidden" class="form-control" name= "ESTADO_USUARIO_id_estado_usuario" value="<%=request.getParameter("Estado_usuario")%>" required />   
                            </div>
                            <div class="col-md-6">
                                <label for="IdGeneroUsuario"><h5>Genero Usuario</h5></label>
                                <input id="txt_GENERO_USUARIO_id_genero_usuario_Act" type="text" class="form-control d-none" readonly name= "GENERO_USUARIO_id_genero_usuario" value="<%=request.getParameter("Genero_usuario")%>"/> 

                                <%
                                    //out.println("<select class='form-control'id ='SelectsetIdUsuario' size = '1' onchange=cambiar();>");
                                    out.println("<select selected disabled class='form-control'id ='txt_GENERO_USUARIO_id_genero_usuario_Act' size = '1' >");
                                    //out.println("<option onclick='setIdUsuario(\"" + "" + "\")'>" + "--Seleccionar--" + "</option>");
                                    for (GENERO_USUARIO C : mitipoGenero) {

                                        //Llenar el combo para recuperar el dato seleccioando
                                        out.println("<option value=" + C.getId_genero_usuario() + ">" + " " + C.getDescripcion_genero_usuario() + "</option>");
                                    }

                                    out.println("</select>");
                                %>
                                <input id="txt_GENERO_USUARIO_id_genero_usuario_Act_aux" type="hidden" class="form-control" name= "GENERO_USUARIO_id_genero_usuario" value="<%=request.getParameter("Genero_usuario")%>" required />   
                            </div>
                        </div>
                        <div id="controlRegistro" class="bd-example d-none">
                            <input id="txt_bandera_reg" type="text" class="form-control" name="banderaRegistro">
                        </div>
                        <br>
                        <div align="center"> 
                            <button class="btn btn-primary me-md-2 botoncito" onclick="return confirmarAct()">Actualizar</button>
                            <button class="btn btn-danger me-md-2 botoncito" type="reset">Limpiar</button>
                        </div>
                        <br> 
                    </form>
                </div>
            </div>
        </div>
        <div class="container">
            <br>
            <br>
            <form action="/Effective/ConsultarUsuario" method="post">
                <div class="row justify-content-md-center">
                    <div id="Listado" class="bd-example">
                        <div>
                            <h1>Listado Usuarios</h1>
                            <div id="ControlRegistro" class="input-group">
                                <input id="txt_Id_Consultado" type="text" class="placeholder form-control d-none" name="IdConsultado" value="<%=buscando%>" autofocus placeholder="   Buscar por Identificacion del proveedor"/>
                                <button type="button" class="btn btn-info d-none" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"onClick="realizarBusqueda()">Buscar</button>

                            </div>
                            <br>
                            <div id="Tabla_Usuarios"></div>

                            <%
                                USUARIOS_DAO usuario_dao = new USUARIOS_DAO();
                                ArrayList<USUARIOS> usuarioList = new ArrayList<USUARIOS>();
                                usuarioList = usuario_dao.ConsultarListadoUsuario(buscando, buscando, buscando);
                                out.println("<table id='tablita' class='display table table-primary table-striped table-hover table-bordered border-danger'><thead><tr><th>Identificación</th><th>Nombres</th><th>Apellidos</th><th style=display:none;>Clave</th><th>Telefono</th><th>Direccion</th><th>Correo</th><th>Tipo Usuario</th><th>Estado</th><th style=display:none;>Genero</th><th style=display:none;>Tipo_doc</th><th>Actualizar</th><th>Eliminar</th></tr></thead>");

                                for (USUARIOS U : usuarioList) {

                                    out.println("<tr>");
                                    out.println("<td>" + U.getIdentificacion_usuario() + "</td>");
                                    out.println("<td>" + U.getNombres_usuario() + "</td>");
                                    out.println("<td>" + U.getApellidos_usuario() + "</td>");
                                    out.println("<td style=display:none;>" + U.getClave() + "</td>");
                                    out.println("<td>" + U.getTelefono_usuario() + "</td>");
                                    out.println("<td>" + U.getDireccion_usuario() + "</td>");
                                    out.println("<td>" + U.getCorreo_usuario() + "</td>");

                                    conexion miConexion = new conexion();
                                    Connection nuevaCon;
                                    nuevaCon = miConexion.getConn();
                                    //Procesar una sentencia SQL
                                    Statement stm = miConexion.getConn().createStatement();
                                    //Obtener los datos de columna de la tabla a ejecutar el query
                                    ResultSet rst = stm.executeQuery("SELECT * FROM tipo_usuario, estado_usuario, genero_usuario, tipo_doc_identificacion_usuario where id_tipo_usuario='" + U.getTIPO_USUARIO_id_tipo_usuario() + "' and id_estado_usuario= '" + U.getESTADO_USUARIO_id_estado_usuario() + "' and id_genero_usuario = '" + U.getGENERO_USUARIO_id_genero_usuario() + "' and id_doc_identificacion_usuario = '" + U.getTIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario() + "'");

                                    while (rst.next()) {
                                        out.println("<td>" + rst.getString(2) + "</td>");
                                        out.println("<td>" + rst.getString(4) + "</td>");
                                        out.println("<td style=display:none;>" + rst.getString(6) + "</td>");
                                        out.println("<td style=display:none;>" + rst.getString(8) + "</td>");

                                    }

                                    out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-primary form-control btn btn-login' value='Actualizar   'name='Actualizar'onclick='SetIdConsulta(" + U.getIdentificacion_usuario() + ")'/>" + "</td>");
                                    out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-danger form-control btn btn-login ' value='' name='Eliminar' onclick='return confirmar (SetIdConsulta(" + U.getIdentificacion_usuario() + "))'/>" + "</td>");
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
                background-image: url(../imagenes/desk.jpg);
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
