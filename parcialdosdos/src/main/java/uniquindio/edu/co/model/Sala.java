package uniquindio.edu.co.model;


public class Sala {

    private int codigoSala;
    private String nombre;
    private int capMax;
    private TipoSala tipo;
    private int filas;
    private int columnas;


    public Sala(int codigoSala, String nombre, int capMax, TipoSala tipo, int filas, int columnas){
        this.codigoSala = codigoSala;
        this.nombre = nombre;
        this.capMax = capMax;
        this.tipo = tipo;
        this.filas = filas;
        this.columnas = columnas;
    }

    public int getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(int codigoSala) {
        this.codigoSala = codigoSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapMax() {
        return capMax;
    }

    public void setCapMax(int capMax) {
        this.capMax = capMax;
    }

    public TipoSala getTipo() {
        return tipo;
    }

    public void setTipo(TipoSala tipo) {
        this.tipo = tipo;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "codigoSala=" + codigoSala +
                ", nombre='" + nombre + '\'' +
                ", capMax=" + capMax +
                ", tipo=" + tipo +
                ", filas=" + filas +
                ", columnas=" + columnas +
                '}';
    }
}
