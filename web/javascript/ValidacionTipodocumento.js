function ValidacionTipodocumento() {

    var resultado = 'Correcto';
    var tipo_documento = $('#txt_tipo_doc_identificacion_usuario_Reg').val();
    console.log(" -- Información a validar -- ");
    console.log("tipo_documento: ." + tipo_documento + ".");

    if (tipo_documento == '') {

        alert("El tipo de documento no puede ser nulo");
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
    window.location.href = '/Effective/vista/registrartipodocumento.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});


