package uniquindio.edu.co.model;

import java.util.List;

public class Pedido {
    private int numPedido, cantSolicitada;
    private String fechaCreacion, estado;
    private double precio, subTotal;

    private Cliente cliente;
    private List<Producto> listProductoPedido;
    private Factura factura;

    public Pedido(int numPedido, int cantSolicitada, String fechaCreacion,
                  String estado, double precio, double subTotal, Cliente cliente,
                  List<Producto> listProductoPedido, Factura factura){

        this.numPedido = numPedido;
        this.cantSolicitada = cantSolicitada;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.precio = precio;
        this.subTotal = subTotal;
        this.cliente = cliente;
        this.listProductoPedido = listProductoPedido;
        this.factura = factura;
    }
}
