package uniquindio.edu.co.model;

import java.util.List;

public class Producto {

private int codigo, disponible;
private String nombre, descripcion;
private double precio;

public Producto(int codigo, int disponible, String nombre, String descripcion, double precio){

    this.codigo = codigo;
    this.disponible = disponible;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;

}
}
