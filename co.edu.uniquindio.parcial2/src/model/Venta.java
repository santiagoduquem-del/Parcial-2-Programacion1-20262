package model;

import java.util.ArrayList;
import java.util.List;

public class Venta {

    private int codigoVenta;
    private String fechaRealizada;

    // Relaciones
    private Operario theOperario;   // quien registra la venta
    private Cliente theCliente;     // a quien pertenece la venta
    private Funcion theFuncion;     // una venta corresponde a una sola función
    private List<Boleta> listBoletas;

    public Venta(int codigoVenta, String fechaRealizada, Operario theOperario, Cliente theCliente, Funcion theFuncion){
        this.codigoVenta = codigoVenta;
        this.fechaRealizada = fechaRealizada;
        this.theOperario = theOperario;
        this.theCliente = theCliente;
        this.theFuncion = theFuncion;
        this.listBoletas = new ArrayList<>();
    }

    public void agregarBoleta(Boleta boleta){
        listBoletas.add(boleta);
    }

    /** El valor total de la venta es la suma de los valores de sus boletas */
    public double calcularValorTotal(){
        double total = 0;
        for(int i = 0; i < listBoletas.size(); i++){
            total += listBoletas.get(i).getValor();
        }
        return total;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public String getFechaRealizada() {
        return fechaRealizada;
    }

    public void setFechaRealizada(String fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public Operario getTheOperario() {
        return theOperario;
    }

    public void setTheOperario(Operario theOperario) {
        this.theOperario = theOperario;
    }

    public Cliente getTheCliente() {
        return theCliente;
    }

    public void setTheCliente(Cliente theCliente) {
        this.theCliente = theCliente;
    }

    public Funcion getTheFuncion() {
        return theFuncion;
    }

    public void setTheFuncion(Funcion theFuncion) {
        this.theFuncion = theFuncion;
    }

    public List<Boleta> getListBoletas() {
        return listBoletas;
    }

    public void setListBoletas(List<Boleta> listBoletas) {
        this.listBoletas = listBoletas;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "codigoVenta=" + codigoVenta +
                ", fechaRealizada='" + fechaRealizada + '\'' +
                ", theOperario=" + theOperario.getNombreCompleto() +
                ", theCliente=" + theCliente.getNombreCompleto() +
                ", theFuncion=" + theFuncion.getUnicoCode() +
                ", listBoletas=" + listBoletas +
                '}';
    }
}