package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;


public class Tienda {

private List<Producto> listTiendaProducto;
private List<Pedido> listTiendaPedido;
private List<Cliente> listTiendaCliente;

public Tienda(){
    this.listTiendaProducto = new ArrayList<>();
    this.listTiendaPedido = new ArrayList<>();
    this.listTiendaCliente = new ArrayList<>();
}


}
