<%@page import="modelo.TIPO_DE_DOCUMENTO_CONTABLE"%>
<%@page import="controlador.TIPO_DE_DOCUMENTO_CONTABLE_DAO"%>
<%@page import="modelo.FORMAS_DE_PAGO"%>
<%@page import="controlador.FORMAS_DE_PAGO_DAO"%>
<%@page import="modelo.CLIENTES"%>
<%@page import="controlador.CLIENTES_DAO"%>
<%@page import="modelo.MOVIMIENTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.PRODUCTOS"%>
<%@page import="modelo.DETALLE_MOVIMIENTO"%>
<%@page import="controlador.DETALLE_MOVIMIENTO_DAO"%>
<%@page import="controlador.PRODUCTOS_DAO"%>
<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

<script src="../javascript/jquery-1.11.0.min.js" type="text/javascript"></script>
<script>
    //$(document).ready(function () {
    //   $("#SelClientepost").change(function () {
    //      var value = $(this).val();
    //      $("#Clientepost").val(value);
    //   });
    // });
</script>
<%
    ArrayList<DETALLE_MOVIMIENTO> lista;
    lista = null;
    double total = 0, subtotal = 0, iva = 0;
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="icon"  type="image/png" href="../imagenes/LYDM.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Venta</title>
    </head>       
    <div class="contenedor">        
        <div class="row">
            <div class="col-sm-8 mx-auto">
                <h2 align="center">
                    <p class="text-light bg-dark">Registrar Venta</p>
                </h2>    
                <form action="../../Effective/GrabarFactura" method="Post">
                    <table class='table table-striped' align="center">
                        <tr>
                            <th scope="col"><label class="form-control"><strong>Fecha</strong></label></th>
                            <th scope="col" colspan="3"><input class="form-control" type="date" id="txtfecha" name="txtfecha" class="form-control" required style='width: 150px'></th>                     
                        <script>
                            var now = new Date();
                            var day = ("0" + now.getDate()).slice(-2);
                            var month = ("0" + (now.getMonth() + 1)).slice(-2);
                            var today = now.getFullYear() + "-" + (month) + "-" + (day);
                            $("#txtfecha").val(today);
                        </script>
                        <th scope="col"><label class="form-control"><strong>Documento</strong></label></th>
                        <th scope="col" colspan="3"><input class="form-control" type="text" id="txtdocumento" name="txtdocumento" class="form-control" required style='width: 150px'></th>  
                        </tr>
                        <tr>
                            <th scope="col"><label class="form-control"><strong>Cliente</strong></label></th>
                            <th scope="col" colspan="4">
                                <%
                                    CLIENTES_DAO clientes_dao = new CLIENTES_DAO();
                                    ArrayList<CLIENTES> micliente = clientes_dao.ConsultarListadoClientes("", "", "");
                                    out.println("<select class='form-control' id='Clientepost' name='Clientepost' required size = '1' required style='width: 150px'>");
                                    for (CLIENTES C : micliente) {
                                        String completo = C.getNombreCliente() + " " + C.getApellidoCliente();
                                        out.println("<option value='" + C.getIdCliente() + "'>" + completo + "</option>");
                                    }
                                    out.println("</select>");
                                %>                    
                            </th>

                        </tr>
                        <tr>

                        </tr>
                        <tr>
                            <th scope="col"><label class="form-control"><strong>Tipo Documento Contable</strong></label></th>
                            <th scope="col" colspan="3">
                                <%
                                    TIPO_DE_DOCUMENTO_CONTABLE_DAO tipo_de_documento_contable_dao = new TIPO_DE_DOCUMENTO_CONTABLE_DAO();
                                    ArrayList<TIPO_DE_DOCUMENTO_CONTABLE> midocumento = tipo_de_documento_contable_dao.ConsultarListadoTipoDocumento("", "");
                                    out.println("<select class='form-control' id='Tipodpost' name='Tipodpost' required size = '1' required style='width: 150px'>");
                                    for (TIPO_DE_DOCUMENTO_CONTABLE T : midocumento) {
                                        out.println("<option value='" + T.getId_tipo_de_documento_contable() + "'>" + T.getDescripcion() + "</option>");

                                    }
                                    out.println("</select>");
                                %>
                            </th>
                            <th scope="col"><label class="form-control"><strong>Forma Pago</strong></label></th>
                            <th scope="col" colspan="3">
                                <%
                                    FORMAS_DE_PAGO_DAO forma_de_pago_dao = new FORMAS_DE_PAGO_DAO();
                                    ArrayList<FORMAS_DE_PAGO> mipago = forma_de_pago_dao.ConsultarListadoFormasdePago("", "");
                                    out.println("<select class='form-control' id='Formapost' name='Formapost' required size = '1' required style='width: 150px'>");
                                    for (FORMAS_DE_PAGO F : mipago) {
                                        out.println("<option value='" + F.getId_forma_de_pago() + "'>" + F.getTipo_de_pago() + "</option>");
                                    }
                                    out.println("</select>");
                                %>
                            </th>
                        </tr>
                        <tr style="background-color: skyblue; color:black; font-weight:bold">
                            <td align="center">Nombre</td>
                            <td align="center">Precio</td>
                            <td align="center">Cantidad</td>
                            <td align="center">Subtotal</td>
                            <td align="center">Iva</td>
                            <td align="center">Total</td>
                            <td align="center">Acción</td>
                        </tr>

                        <%
                            total = 0;
                            subtotal = 0;
                            iva = 0;
                            lista = (ArrayList<DETALLE_MOVIMIENTO>) session.getAttribute("carrito");
                            if (lista != null) {
                                for (DETALLE_MOVIMIENTO d : lista) {
                        %>           
                        <tr>
                            <td><%=d.getNombre_Producto()%></td><!-- comment -->
                            <td align="right"><%=d.getPrecio_productos()%></td>
                            <td align="right"><%=d.getCantidad()%></td>
                            <td align="right"><%=Math.round(d.getSubtotal_productos() * 100.0) / 100.0%></td>
                            <td align="right"><%=Math.round(d.getIva_productos() * 100.0) / 100.0%></td>
                            <td align="right"><%=Math.round(d.getTotal_productos() * 100.0) / 100.0%></td>
                            <% subtotal = subtotal + d.getSubtotal_productos(); %>
                            <% iva = iva + d.getIva_productos(); %>
                            <% total = total + d.getTotal_productos();%>
                            <td>
                                <a href="../../Effective/RemoverElementoCarrito?txteli=<%=d.getPRODUCTOS_id_producto()%>" a class="btn btn-danger sm">Eliminar</a>
                            </td>
                        </tr>

                        <%

                                }

                            }
                        %>
                        <style>
                            .valores
                            {
                                font-size: 17px;
                                color: black;
                                background: skyblue;
                            }
                        </style>
                        <tr>
                            <th scope="col" colspan="3"></th>
                            <th scope="col"><div align="right" class="valores"><%=String.format("%.2f", subtotal)%></div><input type="hidden" id="txtsubtotal" name="txtsubtotal" value="<%=subtotal%>"/></th>
                            <th scope="col"><div align="right" class="valores"><%=String.format("%.2f", iva)%></div><input type="hidden" id="txtiva" name="txtiva" value="<%=iva%>"/></th>
                            <th scope="col"><div align="right" class="valores"><%=String.format("%.2f", total)%></div><input type="hidden" id="txttotal" name="txttotal" value="<%=total%>"/></th>
                                <% session.setAttribute("FTotal", total); %>
                        </tr>
                        <tr>
                            <th scope="col" colspan="7">
                                <% if (lista != null) {
                                %>       
                                <div align="center"><input type="submit" class='btn btn-primary btn-sm' name="RegistroDetalleMovimiento" value="Registrar"></div>                   
                                    <%
                                    } else {
                                    %>
                                <div align="center"><input disabled='true' type="submit" class='btn btn-primary btn-sm' name="RegistroDetalleMovimiento" value="Registrar"></div>
                                    <%
                                        }
                                    %>
                            </th>
                        </tr>
                        <tr>
                            <th scope="col" colspan="7"><div align="center"><a href="gestionarMovimientos.jsp" class='btn btn-warning btn-sm' type='submit'><strong>Seguir Comprando</strong></a> <% if (lista != null) { %> <a href="../../Effective/BorrarCarrito" class='btn btn-danger btn-sm' type='submit'><strong>Cancelar compra</strong></a></div> <% }%></th>
                        </tr>

                    </table>
                </form>
            </div>
        </div>
    </div>
</html>


<style>

    body{
        background-image: url(../imagenes/paper.jpg);
        background-repeat: no-repeat;
        background-size: 130%;
        font-family: 'Acme', sans-serif;
        font-size: 20px;

    }

</style>
