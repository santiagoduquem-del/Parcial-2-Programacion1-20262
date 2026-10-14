package uniquindio.edu.co.model;

public class Cliente {

    private int identificacion, telefono;
    private String nombre, correo, direccion;

    public Cliente(String nombre, String correo, String direccion, int identificacion, int telefono){
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.telefono = telefono;
    }
}
