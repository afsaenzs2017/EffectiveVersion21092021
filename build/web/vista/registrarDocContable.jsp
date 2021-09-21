<%-- 
    Document   : registrarDocContable
    Created on : 31/08/2021, 02:53:04 PM
    Author     : AndresSalazar
--%>

<%@page import="modelo.TIPO_DE_DOCUMENTO_CONTABLE"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controlador.TIPO_DE_DOCUMENTO_CONTABLE_DAO"%>
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
        <script src="../javascript/ValidacionDocContable.js"></script>
        <script src="../javascript/visualizaOculta.js"></script>
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../css/estilos.css" rel="stylesheet" type="text/css"/>       
        <title>Registrar tipo doc contable</title>
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

        <div class="form-row">
            <div class="form-group col-md-6">
                <div id="Registrar" class="bd-example">
                    <div class="modal-content">
                        <div class="container">
                            <div class="row justify-content-md-center">
                                <h1>Registro tipo doc usuario</h1>
                                <div align="center">
                                    <img src="../imagenes/archivo (1).png" alt="..."/>                                    <br>
                                </div>
                                <br>
                                <form action="/Effective/RegistroDocContable" method="post" onsubmit=" return ValidacionDocContable()" class="col-12">
                                    <br>
                                    <div class="form-row"> 
                                        <div class="col-md-12">
                                            <h5>Descripción</h5>
                                            <input id="txt_descripcion_doc_contable_Reg" type="text" class="form-control" name="descripcion_doc" placeholder="Ingrese la descripcion"/>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <br>                                           
                                            <h5>Naturaleza documento</h5>
                                            <input id="txt_naturaleza_doc_contable_Reg" type="text" class="form-control" name="naturaleza_doc" placeholder="Ingrese la naturaleza del documento contable"/>  
                                        </div>
                                    </div>  
                                    <br>
                                    <div class="row justify-content-md-center">
                                        <div class="btn-group">
                                            <input type="submit" class="btn btn-success form-control btn btn-login" value="Registrar"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div id="ControlsitoRegistro" class="bd-example d-none">
                                        <input id="txt_Banderota_Reg" type="text" class="form-control" name="BanderaReg"/>
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
                        <form action="/Effective/consultarDocContable" method="post">
                            <div class="ajuste">
                                <div id="ControlRegistro" class="input-group">
                                    <input id="txt_Id_Consultado" type="text" class="placeholder form-control" name="IdConsultado" value="<%=buscando%>" autofocus placeholder="   Buscar por Id del documento"/>
                                    <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"onClick="realizarBusqueda()">Buscar</button>
                                </div>
                                <div class="col-sm-6">
                                    <br>
                                    <br>
                                    <div class="card-body ajustar">
                                        <div class="d-flex col-sm-12 ml-auto">
                                            <h1>Listado de documentos contables</h1>                       
                                        </div>
                                        <br>
                                        <%
                                            TIPO_DE_DOCUMENTO_CONTABLE_DAO DocContable_dao = new TIPO_DE_DOCUMENTO_CONTABLE_DAO();
                                            ArrayList<TIPO_DE_DOCUMENTO_CONTABLE> DocContableList = new ArrayList<TIPO_DE_DOCUMENTO_CONTABLE>();
                                            DocContableList = DocContable_dao.ConsultarListadoTipoDocumento(buscando, buscando);
                                            out.println("<table class='table table-primary table-striped table-hover table-bordered border-danger'><tr><td>Id</td><td>Descripcion</td><td>Naturaleza</td><td colspan='2'><div align='center'>Acciones</div></td>");

                                            for (TIPO_DE_DOCUMENTO_CONTABLE U : DocContableList) {

                                                out.println("<tr>");
                                                out.println("<td>" + U.getId_tipo_de_documento_contable() + "</td>");
                                                out.println("<td>" + U.getDescripcion() + "</td>");
                                                out.println("<td>" + U.getNaturaleza() + "</td>");
                                                out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-primary form-control btn btn-login' value='Actualizar   'name='Actualizar'onclick='SetIdConsulta(" + U.getId_tipo_de_documento_contable() + ")'/>" + "</td>");
                                                out.println("<td>" + "<input type = 'submit' class=' placeholder btn btn-danger form-control btn btn-login ' value='' name='Eliminar' onclick='return confirmar (SetIdConsulta(" + U.getId_tipo_de_documento_contable() + "))'/>" + "</td>");
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
                                <h1>Modificar tipo de usuario</h1>
                                <div align="center">
                                    <img src="../imagenes/barra-de-estado.png" alt="..."/>
                                </div>
                                <br>
                                <form action="/Effective/modificarDocContable" method="post" class="col-12">
                                    <br>
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <h5>Id</h5>
                                            <input type="text" class="form-control" name="doc_contable_id" readonly="" placeholder="Ingrese el tipo de usuario" value="<%=request.getParameter("id")%>"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <h5>Descripción</h5>
                                            <input type="text" class="form-control" name="descripcion_doc" placeholder="Ingrese el tipo de usuario" value="<%=request.getParameter("descripcion")%>" required=""/>
                                        </div>
                                        <br>
                                        <div class="col-md-6">
                                            <h5>Naturaleza</h5>
                                            <input type="text" class="form-control" name="naturaleza_doc" placeholder="Ingrese el tipo de usuario" value="<%=request.getParameter("naturaleza")%>" required=""/>     
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row justify-content-md-center">
                                        <div class="btn-group">            
                                            <button class="btn btn-success me-md-2 botones" onclick="return confirmarAct()" type="submit">Actualizar</button> 
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
