function ValidacionProveedores() {

    var resultado = 'Correcto';
    var identificacion_proveedor = $('#txt_identificacion_proveedor_Reg').val();
    var nombre_proveedor = $('#txt_nombre_proveedor_Reg').val();
    var correo_proveedor = $('#txt_correo_proveedor_Reg').val();
    var direccion_proveedor = $('#txt_direccion_proveedor_Reg').val();
    var telefono_1_proveedor = $('#txt_telefono_1_proveedor_Reg').val();
    var telefono_2_proveedor = $('#txt_telefono_2_proveedor_Reg').val();
    
    
    console.log(" -- Información a validar -- ");
    console.log("identificacion_proveedor : . " + identificacion_proveedor + ".");
    console.log("nombre_proveedor : . " + nombre_proveedor + ".");
    console.log("correo_proveedor : . " + correo_proveedor + ".");
    console.log("direccion_proveedor : . " + direccion_proveedor + ".");
    console.log("telefono_1_proveedor : . " + telefono_1_proveedor + ".");
    console.log("telefono_2_proveedor : . " + telefono_2_proveedor + ".");

    if (identificacion_proveedor == '') {

        alert("La identificación del proveedor no puede ser nula");
        resultado = 'Error';

    } else if (nombre_proveedor == '') {

        alert("El nombre del proveedor no puede ser nulo");
        resultado = 'Error';

    } else if (correo_proveedor == '') {

        alert("El correo del proveedor no puede ser nulo");
        resultado = 'Error';

    } else if (direccion_proveedor == '') {

        alert("La direccion del proveedor no puede ser nula");
        resultado = 'Error';

    } else if (telefono_1_proveedor == '') {

        alert("El primer telefono del proveedor no puede ser nulo");
        resultado = 'Error';

    } else if (telefono_2_proveedor == '') {

        alert("El segundo telefono del proveedor no puede ser nulo");
        resultado = 'Error';

    } else if (isNaN(identificacion_proveedor)) {

        alert("La identificacion del proveedor debe ser numerica");
        resultado = 'Error';

    } else if (isNaN(telefono_1_proveedor)) {

        alert("El telefono debe ser numerico");
        resultado = 'Error';

    } else if (isNaN(telefono_2_proveedor)) {

        alert("El telefono debe ser numerico");
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
    window.location.href = '/Effective/vista/registrarProveedores.jsp?buscando=' + textoBuscado.trim() + '&';
}

$('#txt_Id_Consultado').change(function () {
    console.log("Texto" + $('#txt_Id_Consultado').val());
});















