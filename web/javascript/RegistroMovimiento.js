function RegistroMovimiento() {
    
    var resultado = 'Correcto';
    var numero_de_documento = $ ('#txt_numero_de_documento_Reg').val();
    var fecha = $ ('#txt_fecha_Reg').val();
    var subtotal = $ ('#txt_subtotal_Reg').val();
    var total_iva = $ ('#txt_total_iva_Reg').val();
    var descuento = $ ('#txt_descuento_Reg').val();
    var total_factura = $ ('#txt_total_factura_Reg').val();
    var FORMAS_DE_PAGO_id_forma_de_pago = $ ('#txt_FORMAS_DE_PAGO_id_forma_de_pago_reg').val();
    var TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable = $ ('#txt_TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable_reg').val();
    var USUARIOS_id_usuario = $ ('#txt_USUARIOS_id_usuario_reg').val();
    
    console.log(' --Información a validar-- ');
    console.log('numero_de_documento: ' + numero_de_documento + ".");
    console.log('fecha: ' + fecha + ".");
    console.log('subtotal: ' + subtotal + ".");
    console.log('total_iva: ' + total_iva + ".");
    console.log('descuento: ' + descuento + ".");
    console.log('total_factura: ' + total_factura + ".");
    console.log('FORMAS_DE_PAGO_id_forma_de_pago: ' + FORMAS_DE_PAGO_id_forma_de_pago + ".");
    console.log('TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable: ' + TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable + ".");
    console.log('USUARIOS_id_usuario: ' + USUARIOS_id_usuario + ".");
    
    if (numero_de_documento == '') {
        alert('El numero de documento no puede estar vacio');
        resultado = 'Error';
    } else if (fecha == '') {
        alert('la fecha no puede estar vacia');
        resultado = 'Error';
    } else if (subtotal == '') {
        alert('el subtotal no puede estar vacio');
        resultado = 'Error';
    } else if (total_iva == '') {
        alert('el total iva no puede estar vacio');
        resultado = 'Error';
    } else if (descuento == '') {
        alert('el descuento no puede estar vacio');
        resultado = 'Error';
    } else if (total_factura == '') {
        alert('el total de factura no puede estar vacio');
        resultado = 'Error';
    }  else if (FORMAS_DE_PAGO_id_forma_de_pago == '') {
        alert('la forma de pago no puede estar vacia');
        resultado = 'Error';
    } else if (TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable == '') {
        alert('el tipo de documento contable no puede estar vacio');
        resultado = 'Error';
    } else if (USUARIOS_id_usuario == '') {
        alert('el usuario no puede estar vacio');
        resultado = 'Error';
    
    }else if (isNaN(numero_de_documento)) {
        alert('el numero de documento debe ser numérico');
        resultado = 'Error';

    }else if (isNaN(FORMAS_DE_PAGO_id_forma_de_pago)) {
        alert('la forma de pago debe ser numérica');
        resultado = 'Error';

    }else if (isNaN(TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable)) {
        alert('El tipo de docuemto contable debe ser numérico');
        resultado = 'Error';

    }else if (isNaN(USUARIOS_id_usuario)) {
        alert('El usuario debe ser numérico');
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
    window.location.href = '/Effective/vista/RegistroMovimiento.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function(){
console.log("Texto" + $('#txt_Id_Consultado').val());
});


function setIdFormasdePago(x){
    
    $('#txt_FORMAS_DE_PAGO_id_forma_de_pago_reg').val(x);
    console.log("valor : " + x);   
}
function setIdTipoDocContable(x){
    
    $('#txt_TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable_reg').val(x);
    console.log("valor : " + x);   
}
function setIdUsuario(x){
    
    $('#txt_USUARIOS_id_usuario_reg').val(x);
    console.log("valor : " + x);   
}

function RegistroMovimientoAct() {
    var resultado = 'Correcto';
    var numero_de_documento = $ ('#txt_numero_de_documento_Act').val();
    var fecha = $ ('#txt_fecha_Act').val();
    var subtotal = $ ('#txt_subtotal_Act').val();
    var total_iva = $ ('#txt_total_iva_Act').val();
    var descuento = $ ('#txt_descuento_Act').val();
    var total_factura = $ ('#txt_total_factura_Act').val();
    var FORMAS_DE_PAGO_id_forma_de_pago = $ ('#txt_FORMAS_DE_PAGO_id_forma_de_pago_Act').val();
    var TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable = $ ('#txt_TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable_Act').val();
    var USUARIOS_id_usuario = $ ('#txt_USUARIOS_id_usuario_Act').val();
    
    console.log(' --Información a validar-- ');
    console.log('numero_de_documento: ' + numero_de_documento + ".");
    console.log('fecha: ' + fecha + ".");
    console.log('subtotal: ' + subtotal + ".");
    console.log('total_iva: ' + total_iva + ".");
    console.log('descuento: ' + descuento + ".");
    console.log('total_factura: ' + total_factura + ".");
    console.log('FORMAS_DE_PAGO_id_forma_de_pago: ' + FORMAS_DE_PAGO_id_forma_de_pago + ".");
    console.log('TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable: ' + TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable + ".");
    console.log('USUARIOS_id_usuario: ' + USUARIOS_id_usuario + ".");
    
    if (numero_de_documento == '') {
        alert('El numero de documento no puede estar vacio');
        resultado = 'Error';
    } else if (fecha == '') {
        alert('la fecha no puede estar vacia');
        resultado = 'Error';
    } else if (subtotal == '') {
        alert('el subtotal no puede estar vacio');
        resultado = 'Error';
    } else if (total_iva == '') {
        alert('el total iva no puede estar vacio');
        resultado = 'Error';
    } else if (descuento == '') {
        alert('el descuento no puede estar vacio');
        resultado = 'Error';
    } else if (total_factura == '') {
        alert('el total de factura no puede estar vacio');
        resultado = 'Error';
    }  else if (FORMAS_DE_PAGO_id_forma_de_pago == '') {
        alert('la forma de pago no puede estar vacia');
        resultado = 'Error';
    } else if (TIPO_DE_DOCUMENTO_CONTABLE_id_tipo_de_documento_contable == '') {
        alert('el tipo de documento contable no puede estar vacio');
        resultado = 'Error';
    } else if (USUARIOS_id_usuario == '') {
        alert('el usuario no puede estar vacio');
        resultado = 'Error';
    
    }else if (isNaN(numero_de_documento)) {
        alert('el numero de documento debe ser numérico');
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
    window.location.href = '/Effective/vista/gestionarMovimientos?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});


