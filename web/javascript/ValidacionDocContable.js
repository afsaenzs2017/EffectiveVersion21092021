function ValidacionDocContable() {

    var resultado = 'Correcto';
    var codigo_doc = $('#txt_codigo_doc_contable_Reg').val();
    var descripcion_doc = $('#txt_descripcion_doc_contable_Reg').val();
    var naturaleza_doc = $('#txt_naturaleza_doc_contable_Reg').val();


    console.log(" -- Información a validar -- ");
    console.log("codigo_doc: ." + codigo_doc + ".");
    console.log("descripcion_doc: ." + descripcion_doc + ".");
    console.log("naturaleza_doc: ." + naturaleza_doc + ".");

    if (codigo_doc == '') {
        alert('El codigo del documento contable no puede estar vacio');
        resultado = 'Error';
    } else if (descripcion_doc == '') {
        alert('La descripcion del documento contable no puede estar vacia');
        resultado = 'Error';
    } else if (naturaleza_doc == '') {
        alert('La naturaleza del documento contable no puede estar vacia');
        resultado = 'Error';
    }else {
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
    window.location.href = '/Effective/vista/registrarDocContable.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});

