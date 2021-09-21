<%-- 
   Document   : menu
   Created on : 13/10/2020, 09:10:02 PM
   Author     : AndresSaenz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% if (session.getAttribute("username") == null) {
        out.print("<script>alert('No ha iniciado sesión')</script>");
        String urls = "../index.html";
        out.print("<script>window.location.href='" + urls + "';</script>");
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Effective Record</title>
        <link rel="icon"  type="image/png" href="../imagenes/LYDM.png">
    </head>
    <body>


        <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top" style="padding:0px;">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top" style="padding:0px;">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">
                        <img src="../imagenes/LYDM.png" alt="..." width="140" height="100" class="d-inline-block align-text-top">

                    </a>
                </div>
            </nav>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>



            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link"  href="menu.jsp">Inicio <span class="sr-only">(current)</span></a>
                    </li>
                    <%
                        int tipousu = Integer.parseInt(session.getAttribute("tipousu").toString());
                        if (tipousu == 1) {
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Registros
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                            <a class="dropdown-item" href="../vista/registrarroles.jsp">Registrar tipo usuario</a>
                            <a class="dropdown-item" href="../vista/registrarestadousuario.jsp">Registrar estado usuario</a>
                            <a class="dropdown-item" href="../vista/registrartipodocumento.jsp">Registrar tipo documento usuario</a>
                            <a class="dropdown-item" href="../vista/registrargenerousuario.jsp">Registrar genero usuario</a>
                            <a class="dropdown-item" href="../vista/registrarCategoria.jsp">Registro categoria</a>
                            <a class="dropdown-item" href="../vista/registrarEstadoProducto.jsp">Registro Estado producto</a>
                            <a class="dropdown-item" href="../vista/registrarMarca.jsp">Registro Marca</a>
                            <a class="dropdown-item" href="../vista/registrarUnidadMedida.jsp">Registro Unidad medida</a>
                            <a class="dropdown-item" href="../vista/registrarFormasPago.jsp">Registro Formas de pago</a>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Gestión de usuarios
                        </a>

                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="../vista/registroUsuarios.jsp">Gestionar Usuarios</a> 
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Gestión de proveedores
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="../vista/registrarProveedores.jsp">Gestionar Proveedores</a> 
                        </div>

                    </li>

                    <%
                        }
                    %>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Gestión de productos
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="../vista/registrarproductos.jsp">Gestionar Productos</a> 
                        </div>

                    </li>



                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Gestion de clientes
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown"> 
                            <a class="dropdown-item" href="../vista/gestionarClientes.jsp">Gestion clientes</a> 
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Facturacion
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="../vista/gestionarMovimientos.jsp">Facturacion</a>
                            <a class="dropdown-item" href="../vista/Inventario.jsp">Inventario</a>
                        </div>
                    </li>


                </ul>


                <div class="dropdown circulo">
                    <button   style="border-radius: 100%; padding: 0; margin-right: 20px;"  class="btn btn-dark dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i style="font-size: 50px" class="fas fa-user-circle"></i></button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="margin-left: -47px;">
                        <a class="dropdown-item" href="#">
                            <center>
                                <img src="../imagenes/empleados_1.png" alt="60" width="60"/>
                            </center>
                        </a>
                        <!--- Imprimir variables de sesión username e identy en pantalla --->
                        <a class="dropdown-item" href="#">Nombre: </a><center><% out.println(session.getAttribute("username")); %></center>
                        <a class="dropdown-item" href="#">Identificacion: </a><center><% out.println(session.getAttribute("identy"));%></center>
                        <br>
                        <center>
                            <a class="btn btn-outline-danger" href="../index.html" onclick='return confirmar()'>Salir</a>
                        </center>
                    </div>
                </div>

            </div>
        </nav>


        <script>
            function confirmar(procodigo)
            {
//Confirmar cierre de sesión retornará verdadero o falso    
                pregunta = confirm("¿Desea cerrar sesión?");
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
                background-size: 100%;
                font-family: 'Acme', sans-serif;
                font-size: 20px;

            }

            h1{

                color: whitesmoke;

            }

            p,hr{

                color: whitesmoke;

            }



            .btn-danger{
                font-family: "Font Awesome 5 Free"; 
                font-weight: 900;
                border-radius: 11%;
                width: 207%;
                padding-bottom: 31%;

            }
            .circulo{
                margin-right: 48px;
            }




        </style>






        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
