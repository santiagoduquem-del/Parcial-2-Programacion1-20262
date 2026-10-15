package uniquindio.edu.co.model;

public class Boleta {

    private int codigoBoleta;
    private int fila;
    private int numAsiento;
    private double valor;

    public Boleta(int codigoBoleta, int fila, int numAsiento, double valor){
        this.codigoBoleta = codigoBoleta;
        this.fila = fila;
        this.numAsiento = numAsiento;
        this.valor = valor;
    }

    public int getCodigoBoleta() {
        return codigoBoleta;
    }

    public void setCodigoBoleta(int codigoBoleta) {
        this.codigoBoleta = codigoBoleta;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(int numAsiento) {
        this.numAsiento = numAsiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Boleta{" +
                "codigoBoleta=" + codigoBoleta +
                ", fila=" + fila +
                ", numAsiento=" + numAsiento +
                ", valor=" + valor +
                '}';
    }
}
