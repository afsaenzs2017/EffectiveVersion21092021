function ValidacionMarca() {

    var resultado = 'Correcto';
    var nombre_marca_producto = $('#txt_Marca_Reg').val();
   
    console.log(" -- Información a validar -- ");
    console.log("nombre_marca_producto: ." + nombre_marca_producto + ".");

    if (nombre_marca_producto == '') {
        alert("La categoria no puede ser nulo");
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
    window.location.href = '/Effective/vista/registrarMarca.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});

