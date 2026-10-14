package uniquindio.edu.co.model;

public class Factura {

    private String fechaPago, metodoPago, estadoTrans;
    private double valorPago;

    public Factura(String fechaPago, String metodoPago, String estadoTrans, double valorPago){

        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.estadoTrans = estadoTrans;
        this.valorPago = valorPago;
    }
}
