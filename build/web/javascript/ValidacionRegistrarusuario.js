function ValidacionRegistrarusuario() {

    var resultado = 'Correcto';
    var nombres_usuario = $('#txt_nombres_usuario_Reg').val();
    var apellidos_usuario = $('#txt_apellidos_usuar_reg').val();
    var identificacion_usuario = $('#txt_identificacion_usuario_reg').val();
    var clave = $('#txt_clave_reg').val();
    var telefono_usuario = $('#txt_telefono_usuario_reg').val();
    var direccion_usuario = $('#txt_direccion_usuario_reg').val();
    var correo_usuario = $('#txt_correo_usuario_reg').val();
    var ESTADO_USUARIO_id_estado_usuario = $('#txt_ESTADO_USUARIO_id_estado_usuario_reg').val();
    var TIPO_USUARIO_id_tipo_usuario = $('#txt_TIPO_USUARIO_id_tipo_usuario_reg').val();
    var GENERO_USUARIO_id_genero_usuario = $('#txt_GENERO_USUARIO_id_genero_usuario_reg').val();
    var TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario = $('#txt_TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario_reg').val();
    

    console.log('Información a validar');
    console.log('nombres_usuario: ' + nombres_usuario) + ".";
    console.log('apellidos_usuario: ' + apellidos_usuario + ".");
    console.log('identificacion_usuario: ' + identificacion_usuario + ".");
    console.log('clave: ' + clave + ".");
    console.log('telefono_usuario: ' + telefono_usuario + ".");
    console.log('direccion_usuario: ' + direccion_usuario + ".");
    console.log('correo_usuario: ' + correo_usuario + ".");
    console.log('ESTADO_USUARIO_id_estado_usuario: ' + ESTADO_USUARIO_id_estado_usuario + ".");
    console.log('TIPO_USUARIO_id_tipo_usuario: ' + TIPO_USUARIO_id_tipo_usuario + ".");
    console.log('GENERO_USUARIO_id_genero_usuario: ' + GENERO_USUARIO_id_genero_usuario + ".");
    console.log('TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario: ' + TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario + ".");

    if (nombres_usuario == '') {
        alert('El número de identificación no puede estar vacio');
        resultado = 'Error';
    } else if (apellidos_usuario == '') {
        alert('El nombre no puede estar vacio');
        resultado = 'Error';
    } else if (identificacion_usuario == '') {
        alert('La contraseña no puede estar vacia');
        resultado = 'Error';
    } else if (clave == '') {
        alert('El número telefónico no puede estar vacio');
        resultado = 'Error';
    } else if (telefono_usuario == '') {
        alert('La dirección no puede estar vacia');
        resultado = 'Error';
    } else if (direccion_usuario == '') {
        alert('El correo no puede estar vacio');
        resultado = 'Error';
    } else if (barrio == '') {
        alert('El barrio no puede estar vacio');
        resultado = 'Error';
    } else if (correo_usuario == '') {
        alert('El estado de usuario no puede estar vacio');
        resultado = 'Error';
    } else if (ESTADO_USUARIO_id_estado_usuario == '') {
        alert('El tipo de usuario no puede estar vacio');
        resultado = 'Error';
    } else if (TIPO_USUARIO_id_tipo_usuario == '') {
        alert('El tipo documento no puede estar vacio');
        resultado = 'Error';
    } else if (GENERO_USUARIO_id_genero_usuario == '') {
        alert('El tipo género no puede estar vacio');
        resultado = 'Error';
    } else if (TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario == '') {
        alert('La ciudad no puede estar vacia');
        resultado = 'Error';


    } else if (isNaN(ESTADO_USUARIO_id_estado_usuario)) {
        alert('El número telefónico debe ser numérico');
        resultado = 'Error';
    } else if (isNaN(TIPO_USUARIO_id_tipo_usuario)) {
        alert('El estado usuario debe ser numérico');
        resultado = 'Error';
    } else if (isNaN(GENERO_USUARIO_id_genero_usuario)) {
        alert('El tipo usuario debe ser numérico');
        resultado = 'Error';
    } else if (isNaN(TIPO_DOC_IDENTIFICACION_USUARIO_id_doc_identificacion_usuario)) {
        alert('El tipo documento debe ser numérico');
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

