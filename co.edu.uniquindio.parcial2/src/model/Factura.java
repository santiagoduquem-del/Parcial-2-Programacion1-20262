package model;

/**
 * Comprobante de una venta. Es un record porque es un resumen de datos
 * que no debe modificarse una vez emitido (inmutable).
 */
public record Factura(int codigoVenta,
                      String fechaRealizada,
                      String nombreCliente,
                      String nombreOperario,
                      String tituloPelicula,
                      int cantidadBoletas,
                      double valorTotal) {
}