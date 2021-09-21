function ValidacionEstadousuario() {

    var resultado = 'Correcto';
    var estado_usuario = $('#txt_descripcion_estado_usuario_Reg').val();
    console.log(" -- Información a validar -- ");
    console.log("estado_usuario: ." + estado_usuario + ".");

    if (estado_usuario == '') {

        alert("El estado usuario no puede ser nulo");
        resultado = 'Error';

    } else {

        resultado = 'Correcto';
    }
    $('#txt_Banderota_Reg').val(resultado);
    console.log("Resultado: " + $('#txt_Banderota_Reg').val());

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
    window.location.href = '/Effective/vista/registrarestadousuario.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});





