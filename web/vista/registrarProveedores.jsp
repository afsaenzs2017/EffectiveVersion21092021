<%@page import="modelo.PROVEEDORES"%>
<%@page import="controlador.PROVEEDORES_DAO"%>
<%@page import="java.util.ArrayList"%>
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
    <head lang="es">
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="../javascript/validandoBotonProveedores.js"></script>
        <script src="../javascript/ValidacionProveedores.js"></script>
        <script src="../javascript/visualizaOculta.js" type="text/javascript"></script>
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../css/estilos.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar proveedores</title>
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
                <button type="button" class="btn btn-secondary" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"onClick="visualizaOculta('Listado')">
                    Consultar
                </button>
                <button type="button" class="btn btn-secondary" ><a href="../vista/menu.jsp" class="text-decoration-none" style="color: white">Volver al inicio</a></button>
            </div>
        </div>

        <br>

        <div id="Registrar" class="bd-example d-none">
            <div class="container">
                <div class="modal-content">     
                    <div  class="bd-example">
                        <h1>Registrar Proveedores</h1>
                        <br>
                        <div align="center">
                            <img src="../imagenes/agregar-usuario.png" alt="..."/>
                        </div>
                        <br>
                        <form action="/Effective/RegistrarProveedores" method="post" onsubmit=" return ValidacionProveedores()" class="cold-12">
                            <div class="form-row"> 
                                <div class="form-group col-md-6">
                                    <h5>Nit</h5>
                                    <input id="txt_identificacion_proveedor_Reg" type="text" class="form-control" name="identificacion_proveedor" placeholder="Digite la identificación"/>
                                </div>
                                <br> 
                                <br> 
                                <div class="form-group col-md-6">
                                    <h5>Nombre</h5>
                                    <input id="txt_nombre_proveedor_Reg" type="text" class="form-control" name="nombre_proveedor" placeholder="Digite el nombre"/>
                                </div>
                                <br> 
                                <div class="form-group col-md-6">
                                    <h5>Correo</h5>
                                    <input id="txt_correo_proveedor_Reg" type="email" class="form-control" name="correo_proveedor" placeholder="Digite el correo"/>
                                </div>
                                <br>     
                                <div class="form-group col-md-6">
                                    <h5>Dirección</h5>
                                    <input id="txt_direccion_proveedor_Reg" type="text" class="form-control" name="direccion_proveedor" placeholder="Digite la dirección"/>
                                </div>
                                <br>
                                <div class="form-group col-md-6">
                                    <h5>Primer telefono</h5>
                                    <input id="txt_telefono_1_proveedor_Reg" type="text" class="form-control" name="telefono_1_proveedor" placeholder="Digite el primer telefono"/>
                                </div>
                                <br>
                                <div class="form-group col-md-6">
                                    <h5>Segundo telefono</h5>
                                    <input id="txt_telefono_2_proveedor_Reg" type="text" class="form-control" name="telefono_2_proveedor" placeholder="Si tiene segundo número digitelo"/>
                                </div>
                                <br>
                            </div>                                                
                            <div id="ControlsitoRegistro" class="bd-example d-none">
                                <input id="txt_Banderota_Reg" type="text" class="form-control" name="BanderaReg"/>
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
                        <h1>Actualización Proveedores</h1>
                        <br>
                        <div align="center">
                            <img src="../imagenes/agregar-usuario.png" alt="..."/>
                        </div>
                        <br>
                        <form action="/Effective/ActualizarProveedores" method="post" onsubmit=" return ValidacionProveedoresAct()" class="cold-12">
                            <div class="form-row"> 
                                <div class="form-group col-md-6">
                                    <h5>Nit</h5>
                                    <input id="txt_identificacion_proveedor_Act" type="text" class="form-control" name="identificacion_proveedor" readonly placeholder="Digite la identificación" value="<%=request.getParameter("Identificacion")%>" required/>
                                </div>
                                <br> 
                                <br> 
                                <div class="form-group col-md-6">
                                    <h5>Nombre</h5>
                                    <input id="txt_nombre_proveedor_Act" type="text" class="form-control" name="nombre_proveedor" placeholder="Digite el nombre" value="<%=request.getParameter("Nombres")%>" required/>
                                </div>
                                <br> 
                                <div class="form-group col-md-6">
                                    <h5>Correo</h5>
                                    <input id="txt_correo_proveedor_Act" type="text" class="form-control" name="correo_proveedor" placeholder="Digite el correo" value="<%=request.getParameter("Correo")%>" required/>
                                </div>
                                <br>     
                                <div class="form-group col-md-6">
                                    <h5>Dirección</h5>
                                    <input id="txt_direccion_proveedor_Act" type="text" class="form-control" name="direccion_proveedor" placeholder="Digite la dirección" value="<%=request.getParameter("Direccion")%>" required/>
                                </div>
                                <br>
                                <div class="form-group col-md-6">
                                    <h5>Primer telefono</h5>
                                    <input id="txt_telefono_1_proveedor_Act" type="text" class="form-control" name="telefono_1_proveedor" placeholder="Digite el primer telefono" value="<%=request.getParameter("Telefonouno")%>" required/>
                                </div>
                                <br>
                                <div class="form-group col-md-6">
                                    <h5>Segundo telefono</h5>
                                    <input id="txt_telefono_2_proveedor_Act" type="text" class="form-control" name="telefono_2_proveedor" placeholder="Si tiene segundo número digitelo" value="<%=request.getParameter("Telefonodos")%>"/>
                                </div>
                                <br>
                            </div>                                                
                            <div id="ControlsitoRegistro" class="bd-example d-none">
                                <input id="txt_Banderota_Reg" type="text" class="form-control" name="BanderaReg"/>
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
            <form action="/Effective/ConsultarProveedor" method="post">
                <div class="row justify-content-md-center">
                    <div id="Listado" class="bd-example">
                        <br>
                        <div>
                            <h1>Listado Proveedores</h1>
                            <br>
                            <div id="ControlRegistro" class="input-group">
                                <input id="txt_Id_Consultado" type="text" class="placeholder form-control d-none" name="IdConsultado" value="<%=buscando%>" autofocus placeholder="   Buscar por Identificacion del proveedor"/>
                                <button type="button" class="btn btn-info d-none" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"onClick="realizarBusqueda()">Buscar</button>
                            </div>

                            <div id="Tabla_Proveedores"></div>
                            <%
                                PROVEEDORES_DAO proveedor_dao = new PROVEEDORES_DAO();
                                ArrayList<PROVEEDORES> proveedorList = new ArrayList<PROVEEDORES>();
                                proveedorList = proveedor_dao.ConsultarListadoProveedor(buscando, buscando);
                                out.println("<table id='tablita' class='display table table-primary table-striped table-hover table-bordered border-danger'><thead><tr><th>Nit</th><th>Nombre</th><th>Correo</th><th>Direccion</th><th>Telefono1</th><th>Telefono2</th><th>Actualizar</th><th>Eliminar</th></tr></thead>");

                                for (PROVEEDORES C : proveedorList) {

                                    out.println("<tr>");
                                    out.println("<td>" + C.getIdentificacion_proveedor() + "</td>");
                                    out.println("<td>" + C.getNombre_proveedor() + "</td>");
                                    out.println("<td>" + C.getCorreo_proveedor() + "</td>");
                                    out.println("<td>" + C.getDireccion_proveedor() + "</td>");
                                    out.println("<td>" + C.getTelefono_1_proveedor() + "</td>");
                                    out.println("<td>" + C.getTelefono_2_proveedor() + "</td>");

                                    out.println("<td>" + "<input type = 'submit' class='placeholder btn btn-primary form-control btn btn-login' value='Actualizar   'name='Actualizar'onclick='SetIdConsulta(" + C.getIdentificacion_proveedor() + ")'/>" + "</td>");
                                    out.println("<td>" + "<input type = 'submit' class='placeholder btn btn-danger form-control btn btn-login' value=''name='Eliminar'onclick='return confirmar (SetIdConsulta(" + C.getIdentificacion_proveedor() + "))'/>" + "</td>");
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

