function ValidacionEstadoProduc() {

    var resultado = 'Correcto';
    var descripcion_estado_producto = $('#txt_EstadoProducto_Reg').val();
    console.log(" -- Información a validar -- ");
    console.log("txt_descripcion_estado_producto_reg: ." + descripcion_estado_producto + ".");

    if (descripcion_estado_producto == '') {

        alert("La descricion estado producto no puede ser nulo");
        resultado = 'Error';

    } else {

        resultado = 'Correcto';
    }
    $('#txt_bandera_reg').val(resultado);
    console.log("Resultado: " + $('#txt_bandera_reg').val());

    if (resultado == 'Correcto') {

        return true;

    } else {

        return false;
    }

}
function SetIdConsulta(x) {
    $('#txt_Id_Consultado').val(x);
    console.log("valor : " + x);
}

function realizarBusqueda() {
    var textoBuscado = $('#txt_Id_Consultado').val();
    console.log("Texto: " + $('#txt_Id_Consultado').val());
    window.location.href = '/Effective/vista/registrarEstadoProducto.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});

