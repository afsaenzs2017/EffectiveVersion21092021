function RegistrarClientes(){
    
    var resultado = 'Correcto';
   
    var nombreCliente = $('#txt_nombreCliente_reg').val();
    var apellidoCliente = $('#txt_apellidoCliente_reg').val();
    var identificacionCliente = $('#txt_identificacionCliente_reg').val();
    var correoCliente = $('#correoCliente').val();
    var telefonoCliente = $('#txt_telefonoCliente_reg').val();
    var direccionCliente = $('#txt_direccionCliente_reg').val();
    var idUsuario = $('#txt_id_usuario_reg').val();
    
    console.log('Información a validar');
    console.log('nombreCliente: ' + nombreCliente + ".");
    console.log('apellidoCliente: ' + apellidoCliente) + ".";
    console.log('identificacionCliente: ' + identificacionCliente + ".");
    console.log('telefonoCliente: ' + telefonoCliente + ".");
    console.log('direccionCliente: ' + direccionCliente + ".");
    console.log('correoCliente: ' + correoCliente + ".");
    console.log('idUsuario: ' + idUsuario + ".");
    
     if (nombreCliente == '') {
        alert('El nombre del cliente no puede estar vacio');
        resultado = 'Error';
    } else if (apellidoCliente == '') {
        alert('El apellido del cliente no puede estar vacio');
        resultado = 'Error';
    } else if (identificacionCliente == '') {
        alert('La identificacion del cliente no puede estar vacia');
        resultado = 'Error';

    } else if (correoCliente == '') {
        alert('El correo del cliente no puede estar vacio');
        resultado = 'Error';

    } else if (telefonoCliente == '') {
        alert('El número telefónico del cliente no puede estar vacio');
        resultado = 'Error';
    } else if (direccionCliente == '') {
        alert('La dirección del cliente no puede estar vacia');
        resultado = 'Error'; 
    } else if (idUsuario == '') {
        alert('Debe seleccionar el usuario que lo registro');
        resultado = 'Error';

    } else if (isNaN(identificacionCliente)) {
        alert('El número de identificacion del clinete debe ser numérico');
        resultado = 'Error';

    } else if (isNaN(telefonoCliente)) {
        alert('El número telefónico del cliente debe ser numérico');
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
    window.location.href = '/Effective/vista/gestionarClientes.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});



function setIdUsuario(x) {

    $('#txt_id_usuario_reg').val(x);
    console.log("valor : " + x);
}

