
function RegistrarProductos() {

    var resultado = 'Correcto';

    var nombre_producto = $('#txt_nombre_producto_Reg').val();
    var identificacion_producto = $('#txt_identificacion_producto_Reg').val();
    var precio_producto = $('#txt_precio_producto_Reg').val();
    var descripcion_producto = $('#txt_descripcion_producto_Reg').val();
    var cantidad_producto = $('#txt_cantidad_producto_Reg').val();
    var iva_producto = $('#txt_iva_producto_Reg').val();
    var UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto = $('#txt_UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto_Reg').val();
    var PROVEEDORES_id_proveedor = $('#txt_PROVEEDORES_id_proveedor_Reg').val();
    var CATEGORIA_PRODUCTO_id_categoria_producto = $('#txt_CATEGORIA_PRODUCTO_id_categoria_producto_Reg').val();
    var ESTADO_PRODUCTO_id_estado_producto = $('#txt_ESTADO_PRODUCTO_id_estado_producto_Reg').val();
    var MARCA_PRODUCTO_id_marca_producto = $('#txt_MARCA_PRODUCTO_id_marca_producto_Reg').val();
    var imagen_producto = $('#txt_imagen_producto_Reg').val();
    

    console.log('Información a validar');
    console.log('nombre_producto: ' + nombre_producto + ".");
    console.log('identificacion_producto: ' + identificacion_producto + ".");
    console.log('precio_producto: ' + precio_producto) + ".";
    console.log('descripcion_producto: ' + descripcion_producto + ".");
    console.log('cantidad_producto: ' + cantidad_producto + ".");
    console.log('iva_producto: ' + iva_producto + ".");
    console.log('UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto: ' + UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto + ".");
    console.log('PROVEEDORES_id_proveedor: ' + PROVEEDORES_id_proveedor + ".");
    console.log('CATEGORIA_PRODUCTO_id_categoria_producto: ' + CATEGORIA_PRODUCTO_id_categoria_producto + ".");
    console.log('ESTADO_PRODUCTO_id_estado_producto: ' + ESTADO_PRODUCTO_id_estado_producto + ".");
    console.log('MARCA_PRODUCTO_id_marca_producto: ' + MARCA_PRODUCTO_id_marca_producto + ".");
    console.log('imagen_producto: ' + imagen_producto + ".");



    if (nombre_producto == '') {
        alert('El nombre del producto no puede estar vacio');
        resultado = 'Error';
    } else if (identificacion_producto == '') {
        alert('La identificacion del producto no puede estar vacia');
        resultado = 'Error';
    } else if (precio_producto == '') {
        alert('El precio del producto no puede estar vacio');
        resultado = 'Error';
    } else if (descripcion_producto == '') {
        alert('La descripcion no puede estar vacia');
        resultado = 'Error';
    } else if (cantidad_producto == '') {
        alert('La cantidad del producto  no puede estar vacio');
        resultado = 'Error';
    } else if (iva_producto == '') {
        alert('El iva del producto no puede estar vacia');
        resultado = 'Error';   
    } else if (UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto == '') {
        alert('La unidad de medida no puede estar vacio');
        resultado = 'Error';
    } else if (PROVEEDORES_id_proveedor == '') {
        alert('El proveedor del producto no puede estar vacio');
        resultado = 'Error';
    } else if (CATEGORIA_PRODUCTO_id_categoria_producto == '') {
        alert('La categoria del producto no puede estar vacio');
        resultado = 'Error';
    } else if (ESTADO_PRODUCTO_id_estado_producto == '') {
        alert('El estado  del producto no puede estar vacio');
        resultado = 'Error';
    } else if (MARCA_PRODUCTO_id_marca_producto == '') {
        alert('La marca del producto  no puede estar vacio');
        resultado = 'Error';

    } else if (isNaN(precio_producto)) {
        alert('El `precio debe ser numérico');
        resultado = 'Error';

    } else if (isNaN(iva_producto)) {
        alert('El iva del  debe ser numérico');
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
    window.location.href = '/Effective/vista/registrarproductos.jsp?buscando=' + textoBuscado.trim() + '&';
}



function setIdUnidadMedida(x) {

    $('#txt_UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto_Reg').val(x);
    console.log("valor : " + x);
}
function setIdProveedor(x) {

    $('#txt_PROVEEDORES_id_proveedor_Reg').val(x);
    console.log("valor : " + x);
}
function setIdCategoria(x) {

    $('#txt_CATEGORIA_PRODUCTO_id_categoria_producto_reg').val(x);
    console.log("valor : " + x);
}

function setIdEstadoP(x) {

    $('#txt_ESTADO_PRODUCTO_id_estado_producto_reg').val(x);
    console.log("valor : " + x);
}
function setIdMarca(x) {

    $('#txt_MARCA_PRODUCTO_id_marca_producto_reg').val(x);
    console.log("valor : " + x);
}


function ActProductos() {

    var resultado = 'Correcto';

    var nombre_producto = $('#txt_nombre_producto_Act').val();
    var identificacion_producto = $('#txt_identificacion_producto_Act').val();
    var precio_producto = $('#txt_precio_producto_Act').val();
    var descripcion_producto = $('#txt_descripcion_producto_Act').val();
    var cantidad_producto = $('#txt_cantidad_producto_Act').val();
    var iva_producto = $('#txt_iva_producto_Act').val();
    var UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto = $('#txt_UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto_Act').val();
    var PROVEEDORES_id_proveedor = $('#txt_PROVEEDORES_id_proveedor_Act').val();
    var CATEGORIA_PRODUCTO_id_categoria_producto = $('#txt_CATEGORIA_PRODUCTO_id_categoria_producto_Act').val();
    var ESTADO_PRODUCTO_id_estado_producto = $('#txt_ESTADO_PRODUCTO_id_estado_producto_Act').val();
    var MARCA_PRODUCTO_id_marca_producto = $('#txt_MARCA_PRODUCTO_id_marca_producto_Act').val();
    var imagen_producto = $('#txt_imagen_producto_Act').val();

    console.log('Información a validar');
    console.log('nombre_producto: ' + nombre_producto + ".");
    console.log('identificacion_producto: ' + identificacion_producto + ".");
    console.log('precio_producto: ' + precio_producto) + ".";
    console.log('descripcion_producto: ' + descripcion_producto + ".");
    console.log('cantidad_producto: ' + cantidad_producto + ".");
    console.log('iva_producto: ' + iva_producto + ".");
    console.log('UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto: ' + UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto + ".");
    console.log('PROVEEDORES_id_proveedor: ' + PROVEEDORES_id_proveedor + ".");
    console.log('CATEGORIA_PRODUCTO_id_categoria_producto: ' + CATEGORIA_PRODUCTO_id_categoria_producto + ".");
    console.log('ESTADO_PRODUCTO_id_estado_producto: ' + ESTADO_PRODUCTO_id_estado_producto + ".");
    console.log('MARCA_PRODUCTO_id_marca_producto: ' + MARCA_PRODUCTO_id_marca_producto + ".");
    console.log('imagen_producto: ' + imagen_producto + ".");



    if (nombre_producto == '') {
        alert('El nombre del producto no puede estar vacio');
        resultado = 'Error';
    } else if (identificacion_producto == '') {
        alert('La identificacion del producto no puede estar vacia');
        resultado = 'Error';
    } else if (precio_producto == '') {
        alert('El precio del producto no puede estar vacio');
        resultado = 'Error';
    } else if (descripcion_producto == '') {
        alert('La descripcion no puede estar vacia');
        resultado = 'Error';
    } else if (cantidad_producto == '') {
        alert('La cantidad del producto  no puede estar vacio');
        resultado = 'Error';
    } else if (iva_producto == '') {
        alert('El iva del producto no puede estar vacia');
        resultado = 'Error';
    } else if (imagen_producto == '') {
        alert('Seleccione producto');
        resultado = 'Error';    
    } else if (UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto == '') {
        alert('La unidad de medida no puede estar vacio');
        resultado = 'Error';
    } else if (PROVEEDORES_id_proveedor == '') {
        alert('El proveedor del producto no puede estar vacio');
        resultado = 'Error';
    } else if (CATEGORIA_PRODUCTO_id_categoria_producto == '') {
        alert('La categoria del producto no puede estar vacio');
        resultado = 'Error';
    } else if (ESTADO_PRODUCTO_id_estado_producto == '') {
        alert('El estado  del producto no puede estar vacio');
        resultado = 'Error';
    } else if (MARCA_PRODUCTO_id_marca_producto == '') {
        alert('La marca del producto  no puede estar vacio');
        resultado = 'Error';

    } else if (isNaN(precio_producto)) {
        alert('El `precio debe ser numérico');
        resultado = 'Error';

    } else if (isNaN(iva_producto)) {
        alert('El iva del  debe ser numérico');
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
    window.location.href = '/Effective/vista/registrarproductos.jsp?buscando=' + textoBuscado.trim() + '&';
}



function setIdUnidadMedida(x) {

    $('#txt_UNIDAD_DE_MEDIDA_PRODUCTO_id_unidad_de_medida_producto_Reg').val(x);
    console.log("valor : " + x);
}
function setIdProveedor(x) {

    $('#txt_PROVEEDORES_id_proveedor_Reg').val(x);
    console.log("valor : " + x);
}
function setIdCategoria(x) {

    $('#txt_CATEGORIA_PRODUCTO_id_categoria_producto_reg').val(x);
    console.log("valor : " + x);
}

function setIdEstadoP(x) {

    $('#txt_ESTADO_PRODUCTO_id_estado_producto_reg').val(x);
    console.log("valor : " + x);
}
function setIdMarca(x) {

    $('#txt_MARCA_PRODUCTO_id_marca_producto_reg').val(x);
    console.log("valor : " + x);
}





