function RegistroUsuarios() {

    var resultado = 'Correcto';

    var nombres_usuario = document.getElementById("txt_nombres_usuario_reg").value;
    var apellidos_usuario = document.getElementById("txt_apellidos_usuario_reg").value;
    var identificacion_usuario = document.getElementById("txt_identificacion_usuario_reg").value;
    var clave_usuario = document.getElementById("txt_clave_reg").value;
    var telefono_usuario = document.getElementById("txt_telefono_usuario_reg").value;
    var direccion_usuario = document.getElementById("txt_direccion_usuario_reg").value;
    var correo_usuario = document.getElementById("txt_correo_usuario_reg").value;
    var TIPO_USUARIO_id_tipo_usuario = document.getElementById("txt_TIPO_USUARIO_id_tipo_usuario_reg").value;
    var ESTADO_USUARIO_id_estado_usuario = document.getElementById("txt_ESTADO_USUARIO_id_estado_usuario_reg").value;
    var GENERO_USUARIO_id_genero_usuario = document.getElementById("txt_GENERO_USUARIO_id_genero_usuario_reg").value;
    var TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario = document.getElementById("txt_TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario_reg").value;



    console.log('Información a validar');
    console.log('nombres_usuario: ' + nombres_usuario + ".");
    console.log('apellidos_usuario: ' + apellidos_usuario) + ".";
    console.log('identificacion_usuario: ' + identificacion_usuario + ".");
    console.log('clave_usuario: ' + clave_usuario + ".");
    console.log('telefono_usuario: ' + telefono_usuario + ".");
    console.log('direccion_usuario: ' + direccion_usuario + ".");
    console.log('correo_usuario: ' + correo_usuario + ".");
    console.log('TIPO_USUARIO_id_tipo_usuario: ' + TIPO_USUARIO_id_tipo_usuario + ".");
    console.log('ESTADO_USUARIO_id_estado_usuario: ' + ESTADO_USUARIO_id_estado_usuario + ".");
    console.log('GENERO_USUARIO_id_genero_usuario: ' + GENERO_USUARIO_id_genero_usuario + ".");
    console.log('TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario: ' + TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario + ".");


    if (nombres_usuario == '') {
        alert('El nombre del usuario no puede estar vacio');
        resultado = 'Error';
    } else if (apellidos_usuario == '') {
        alert('El apellido no puede estar vacio');
        resultado = 'Error';
    } else if (identificacion_usuario == '') {
        alert('La identificacion no puede estar vacia');
        resultado = 'Error';

    } else if (clave_usuario == '') {
        alert('La clave no puede estar vacia');
        resultado = 'Error';

    } else if (telefono_usuario == '') {
        alert('El número telefónico no puede estar vacio');
        resultado = 'Error';
    } else if (direccion_usuario == '') {
        alert('La dirección no puede estar vacia');
        resultado = 'Error';
    } else if (correo_usuario == '') {
        alert('El correo no puede estar vacio');
        resultado = 'Error';
    } else if (TIPO_USUARIO_id_tipo_usuario == '') {
        alert('El tipo de usuario no puede estar vacio');
        resultado = 'Error';
    } else if (ESTADO_USUARIO_id_estado_usuario == '') {
        alert('El estado de usuario no puede estar vacio');
        resultado = 'Error';
    } else if (GENERO_USUARIO_id_genero_usuario == '') {
        alert('El genero de usuario no puede estar vacio');
        resultado = 'Error';
    } else if (TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario == '') {
        alert('El tipo documento no puede estar vacio');
        resultado = 'Error';

    } else if (isNaN(identificacion_usuario)) {
        alert('El número de identificacion debe ser numérico');
        resultado = 'Error';

    } else if (isNaN(telefono_usuario)) {
        alert('El número telefónico debe ser numérico');
        resultado = 'Error';

    } else {
        resultado = 'Correcto';
    }
    $('#txt_bandera_reg').val(resultado);
    console.log('Resultado:' + $('#txt_bandera_reg').val());
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
    window.location.href = '/Effective/vista/registroUsuarios.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});



function setIdTipoUsuario(x) {

    $('#txt_TIPO_USUARIO_id_tipo_usuario_reg').val(x);
    console.log("valor : " + x);
}

function setIdTipoDocumento(x) {

    $('#txt_TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario_reg').val(x);
    console.log("valor : " + x);


}

function setIdEstadoUsuario(x) {

    $('#txt_ESTADO_USUARIO_id_estado_usuario_reg').val(x);
    console.log("valor : " + x);

}

function setIdGeneroUsuario(x) {

    $('#txt_GENERO_USUARIO_id_genero_usuario_reg').val(x);
    console.log("valor : " + x);


}

function RegistroUsuariosAct() {

    var resultado = 'Correcto';

    var nombres_usuario = document.getElementById("txt_nombres_usuario_Act").value;
    var apellidos_usuario = document.getElementById("txt_apellidos_usuario_Act").value;
    var identificacion_usuario = document.getElementById("txt_identificacion_usuario_Act").value;
    var clave_usuario = document.getElementById("txt_clave_Act").value;
    var telefono_usuario = document.getElementById("txt_telefono_usuario_Act").value;
    var direccion_usuario = document.getElementById("txt_direccion_usuario_Act").value;
    var correo_usuario = document.getElementById("txt_correo_usuario_Act").value;
    var TIPO_USUARIO_id_tipo_usuario = document.getElementById("txt_TIPO_USUARIO_id_tipo_usuario_Act").value;
    var ESTADO_USUARIO_id_estado_usuario = document.getElementById("txt_ESTADO_USUARIO_id_estado_usuario_Act").value;
    var GENERO_USUARIO_id_genero_usuario = document.getElementById("txt_GENERO_USUARIO_id_genero_usuario_Act").value;
    var TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario = document.getElementById("txt_TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario_Act").value;



    console.log('Información a validar');
    console.log('nombres_usuario: ' + nombres_usuario + ".");
    console.log('apellidos_usuario: ' + apellidos_usuario) + ".";
    console.log('identificacion_usuario: ' + identificacion_usuario + ".");
    console.log('clave_usuario: ' + clave_usuario + ".");
    console.log('telefono_usuario: ' + telefono_usuario + ".");
    console.log('direccion_usuario: ' + direccion_usuario + ".");
    console.log('correo_usuario: ' + correo_usuario + ".");
    console.log('TIPO_USUARIO_id_tipo_usuario: ' + TIPO_USUARIO_id_tipo_usuario + ".");
    console.log('ESTADO_USUARIO_id_estado_usuario: ' + ESTADO_USUARIO_id_estado_usuario + ".");
    console.log('GENERO_USUARIO_id_genero_usuario: ' + GENERO_USUARIO_id_genero_usuario + ".");
    console.log('TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario: ' + TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario + ".");


    if (nombres_usuario == '') {
        alert('El nombre del usuario no puede estar vacio');
        resultado = 'Error';
    } else if (apellidos_usuario == '') {
        alert('El apellido no puede estar vacio');
        resultado = 'Error';
    } else if (identificacion_usuario == '') {
        alert('La identificacion no puede estar vacia');
        resultado = 'Error';

    } else if (clave_usuario == '') {
        alert('La clave no puede estar vacia');
        resultado = 'Error';

    } else if (telefono_usuario == '') {
        alert('El número telefónico no puede estar vacio');
        resultado = 'Error';
    } else if (direccion_usuario == '') {
        alert('La dirección no puede estar vacia');
        resultado = 'Error';
    } else if (correo_usuario == '') {
        alert('El correo no puede estar vacio');
        resultado = 'Error';
    } else if (TIPO_USUARIO_id_tipo_usuario == '') {
        alert('El tipo de usuario no puede estar vacio');
        resultado = 'Error';
    } else if (ESTADO_USUARIO_id_estado_usuario == '') {
        alert('El estado de usuario no puede estar vacio');
        resultado = 'Error';
    } else if (GENERO_USUARIO_id_genero_usuario == '') {
        alert('El genero de usuario no puede estar vacio');
        resultado = 'Error';
    } else if (TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario == '') {
        alert('El tipo documento no puede estar vacio');
        resultado = 'Error';

    } else if (isNaN(identificacion_usuario)) {
        alert('El número de identificacion debe ser numérico');
        resultado = 'Error';

    } else if (isNaN(telefono_usuario)) {
        alert('El número telefónico debe ser numérico');
        resultado = 'Error';

    } else {
        resultado = 'Correcto';
    }
    $('#txt_bandera_reg').val(resultado);
    console.log('Resultado:' + $('#txt_bandera_reg').val());
    if (resultado == 'Correcto') {
        return true;
    } else {
        return false;
    }
}


function validarCorreo(correo) {
    var expReg = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;
    var esValido = expReg.test(correo);
    if (esValido == true) {


    } else {

        alert("El correo de usuario es incorrecto");

    }

}












