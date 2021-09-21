function RegistroDetalleMovimiento() {

    var resultado = 'Correcto';
    var cantidad = $('#txt_cantidad_Reg').val();
    var precio_productos = $('#txt_precio_productos_Reg').val();
    var iva_productos = $('#txt_iva_productos_Reg').val();
    var total_productos = $('#txt_total_productos_Reg').val();
    var PRODUCTOS_id_producto = $('#txt_id_producto_Reg').val();
    var MOVIMIENTO_id_movimiento = $('#txt_movimiento_Reg').val();

    console.log(' --Información a validar-- ');
    console.log('cantidad: ' + cantidad + ".");
    console.log('precio_productos: ' + precio_productos + ".");
    console.log('iva_productos: ' + iva_productos + ".");
    console.log('total_productos: ' + total_productos + ".");
    console.log('PRODUCTOS_id_producto: ' + PRODUCTOS_id_producto + ".");
    console.log('MOVIMIENTO_id_movimiento: ' + MOVIMIENTO_id_movimiento + ".");

    if (MOVIMIENTO_id_movimiento == '') {
        alert('El movimiento no puede estar vacio');
        resultado = 'Error';

    } else if (PRODUCTOS_id_producto == '') {
        alert('El producto no puede estar vacio');
        resultado = 'Error';
    } else if (cantidad == '') {
        alert('La cantidad no puede estar vacia');
        resultado = 'Error';
    } else if (precio_productos == '') {
        alert('El precio del producto no puede estar vacio');
        resultado = 'Error';
    } else if (iva_productos == '') {
        alert('El iva no puede estar vacio');
        resultado = 'Error';
    } else if (total_productos == '') {
        alert('El total de productos no puede estar vacio');
        resultado = 'Error';

    } else if (isNaN(PRODUCTOS_id_producto)) {
        alert('El producto debe ser numérico');
        resultado = 'Error';

    } else if (isNaN(MOVIMIENTO_id_movimiento)) {
        alert('El movimiento debe ser numérico');
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


