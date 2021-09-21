function ValidacionCategoria() {

    var resultado = 'Correcto';
    var nombre_categoria_producto = $('#txt_nombre_categoria_Reg').val();
    console.log(" -- Información a validar -- ");
    console.log("nombre_categoria_producto: ." + nombre_categoria_producto + ".");

    if (nombre_categoria_producto == '') {

        alert("La categoria no puede ser nulo");
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
    window.location.href = '/Effective/vista/registrarCategoria.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});


