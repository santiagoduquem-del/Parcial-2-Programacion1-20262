package uniquindio.edu.co.model;

import java.util.ArrayList;

public class Venta {

    private int codigoVenta;
    private String fechaRealizada;
    private String operario;
    private ArrayList<Boleta> boletas;

    public Venta(int codigoVenta, String fechaRealizada, String operario){
        this.codigoVenta = codigoVenta;
        this.fechaRealizada = fechaRealizada;
        this.operario = operario;
        this.boletas = new ArrayList<>();
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

    public String getOperario() {
        return operario;
    }

    public void setOperario(String operario) {
        this.operario = operario;
    }

    public ArrayList<Boleta> getBoletas() {
        return boletas;
    }

    public void setBoletas(ArrayList<Boleta> boletas) {
        this.boletas = boletas;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "codigoVenta=" + codigoVenta +
                ", fechaRealizada='" + fechaRealizada + '\'' +
                ", operario='" + operario + '\'' +
                ", boletas=" + boletas +
                '}';
    }
}
