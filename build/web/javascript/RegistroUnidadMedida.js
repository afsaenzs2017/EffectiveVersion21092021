

function ValidacionUnidadMedida () {

    var resultado = 'Correcto';
    var descripcion_unidad_de_medida_producto = $('#txt_unidad_medida_Reg').val();
    
    console.log(" -- Información a validar -- ");
    console.log("descripcion_unidad_de_medida_producto: ." + descripcion_unidad_de_medida_producto + ".");

    if (descripcion_unidad_de_medida_producto == '') {

        alert("La unidad no puede estar vacia");
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
    window.location.href = '/Effective/vista/registrarUnidadMedida.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});
