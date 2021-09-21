<%-- 
    Document   : registrarUnidadMedida
    Created on : 17/02/2021, 04:24:30 PM
    Author     : AndresSaenz
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.UNIDAD_DE_MEDIDA_PRODUCTO"%>
<%@page import="controlador.UNIDAD_DE_MEDIDA_PRODUCTO_DAO"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../javascript/jquery-1.11.0.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="../javascript/visualizaOculta.js" type="text/javascript"></script>
        <script src="../javascript/RegistroUnidadMedida.js" type="text/javascript"></script>
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>


        <title>Registrar Unidad de Medida</title>
    </head>

    <body body onload="visualizaOculta('<%=request.getParameter("Vista")%>');">


        <% String buscando = request.getParameter("buscando");
            if (buscando == null) {
                buscando = "";
            }
        %>

        <div class="row justify-content-md-center">
            <div class="btn-group">
                <button type="button" class="btn btn-secondary" ><a href="../vista/menu.jsp" class="text-decoration-none" style="color: white">Volver al inicio</a></button>
            </div>
        </div>

        <div class="form-row">
            <div class=" form-group col-sm-6">
                <div id="Registrar" class="bd-example">
                    <div class="modal-content">
                        <div class="container">
                            <div class="row justify-content-md-center">
                                <h1>Registrar Unidad de Medida</h1>
                                <div align="center">
                                    <img src="../imagenes/orden.png" alt=".."/>
                                </div>
                                <br>
                                <form action="/Effective/RegistrarUnidadDeMedida" method="post" onsubmit=" return ValidacionUnidadMedida()" class="col-12">
                                    <div class="form-group">
                                        <h5> Descripcion Unidad de Medida</h5>
                                        <input id="txt_unidad_medida_Reg" type="text" class="form-control" name="descripcion_unidad_de_medida_producto" placeholder="Ingrese la unidad de medida"/>
                                    </div>
                                    <br>
                                    <div class="row justify-content-md-center">
                                        <div class="btn-group">
                                            <input type="submit" class="btn btn-success form-control btn btn-login" value="Registrar"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div id="controlRegistro" class="bd-example d-none">
                                        <input id="txt_bandera_reg" type="text" class="form-control" name="BanderaReg"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="form-group col-md-6">
                <div id="Listado" class="bd-example">
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <div class="container">
                        <form action="/Effective/consultarUnidadDeMedida" method="post">
                            <div class="ajuste">
                                <div id="ControlRegistro" class="input-group">
                                    <input id="txt_Id_Consultado" type="text" class=" placeholder form-control" name="IdConsultado" value="<%=buscando%>" autofocus placeholder="   Buscar por Id de la unidad de medida"/>
                                    <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"onClick="realizarBusqueda()">Buscar</button>
                                </div>
                                <div class="col-sm-6">
                                    <br>
                                    <br>
                                    <div class="card-body ajustar">
                                        <div class="d-flex col-sm-12 ml-auto">
                                            <h1>Listado Unidad de medida</h1>                       
                                        </div>
                                        <br>
                                        <%
                                            UNIDAD_DE_MEDIDA_PRODUCTO_DAO unidad_medida_dao = new UNIDAD_DE_MEDIDA_PRODUCTO_DAO();
                                            ArrayList<UNIDAD_DE_MEDIDA_PRODUCTO> unidadMedidaList = new ArrayList<UNIDAD_DE_MEDIDA_PRODUCTO>();
                                            unidadMedidaList = unidad_medida_dao.ConsultarListarUnidad_de_medida(buscando, buscando);
                                            out.println("<table class='table table-primary table-striped table-hover table-bordered border-danger'><tr><td>Id</td><td>Descripcion</td><td colspan='2'><div align='center'>Acciones</div></td>");

                                            for (UNIDAD_DE_MEDIDA_PRODUCTO U : unidadMedidaList) {

                                                out.println("<tr>");
                                                out.println("<td>" + U.getId_unidad_de_medida_producto() + "</td>");
                                                out.println("<td>" + U.getDescripcion_unidad_de_medida_producto() + "</td>");
                                                out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-primary form-control btn btn-login' value='Actualizar   'name='Actualizar'onclick='SetIdConsulta(" + U.getId_unidad_de_medida_producto() + ")'/>" + "</td>");
                                                out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-danger form-control btn btn-login ' value='' name='Eliminar' onclick='return confirmar (SetIdConsulta(" + U.getId_unidad_de_medida_producto() + "))'/>" + "</td>");
                                                out.println("</tr>");
                                                out.println("</tr>");

                                            }

                                            out.println("</table>");

                                        %>
                                    </div>
                                </div>
                            </div>
                        </form>        
                    </div> 
                </div>
            </div>
        </div>

        <div class="ajutin">
            <div class="form-group col-md-6">
                <div id="Actualizar" class="bd-example d-none">
                    <div class="modal-content">
                        <div class="container">
                            <div class="row justify-content-md-center">
                                <h1>Modificar Unidad de medida</h1>
                                <div align="center">
                                    <img src="../imagenes/orden.png" alt="..."/>
                                </div>
                                <br>
                                <form action="/Effective/modificarUnidadMedida" method="post" class="col-12">
                                    <br>
                                    <div class="form-group">
                                        <h5>Id</h5>
                                        <input type="text" class="form-control" name="unidad_id" readonly="" placeholder="Ingrese el id" value="<%=request.getParameter("Id")%>"/>
                                    </div>
                                    <br>
                                    <div class="form-group">
                                        <h5>Undidad de medida Producto</h5>
                                        <input type="text" class="form-control" name="unidad_medida" placeholder="Ingrese la unidad e medida" value="<%=request.getParameter("descripcion")%>" required=""/>
                                    </div>
                                    <br>
                                    <div class="row justify-content-md-center">
                                        <div class="btn-group">            
                                            <button class="btn btn-success me-md-2 botones" type="submit">Actualizar</button> 
                                        </div>
                                    </div>

                                    <div id="ControlsitoRegistro" class="bd-example d-none">
                                        <input id="txt_Banderota_Reg" type="text" class="form-control" name="BanderaReg"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
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
                margin-left: 70px;
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

            .card-body{

                position: relative;
                right: 25px;
            }

            .ajuste{

                position: relative;
                right: 110px;

            }

            .ajutin{

                position: relative;
                right: -500px;  
                margin-top: -90px; 
            }

            .botones{

                width: 95px;
                height: 50px;
                margin: 10px 10px 10px 10px;


            }

            .placeholder{

                font-family: "Font Awesome 5 Free"; 
                font-weight: 900;

            }


        </style>
    </body>
</html>