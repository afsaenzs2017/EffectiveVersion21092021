function ValidacionRoles() {

    var resultado = 'Correcto';
    var tipo_usuario = $('#txt_tipo_usuario_Reg').val();
    console.log(" -- Información a validar -- ");
    console.log("tipo_usuario: ." + tipo_usuario + ".");

    if (tipo_usuario == '') {

        alert("El rol no puede ser nulo");
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
    window.location.href = '/Effective/vista/registrarroles.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});


