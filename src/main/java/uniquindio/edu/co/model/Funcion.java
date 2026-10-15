package uniquindio.edu.co.model;
import java.util.ArrayList;

public class Funcion {

    private int unicoCode;
    private String fechaPresentacion;
    private int horaInicio;
    private int precioBoleta;
    private Pelicula pelicula;   // referencia a otra clase
    private Sala sala;
    private ArrayList<Boleta> listBoletas;

        public Funcion(int unicoCode, String fechaPresentacion, int horaInicio, int precioBoleta, Pelicula pelicula, Sala sala){

        this.unicoCode = unicoCode;
        this.fechaPresentacion = fechaPresentacion;
        this.horaInicio = horaInicio;
        this.precioBoleta = precioBoleta;
        this.pelicula = pelicula;
        this.sala = sala;
        this.listBoletas = new ArrayList<>();

    }

    public int getMinutos(){
        int horas = horaInicio / 100;
        int minutos = horaInicio % 100;
        return horas * 60 +minutos;
    }

    public int getFinMinutos(){
        return getMinutos() + Integer.parseInt(pelicula.getDuracionMin());
    }

    public int getUnicoCode() {
        return unicoCode;
    }

    public void setUnicoCode(int unicoCode) {
        this.unicoCode = unicoCode;
    }

    public String getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(String fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getPrecioBoleta() {
        return precioBoleta;
    }

    public void setPrecioBoleta(int precioBoleta) {
        this.precioBoleta = precioBoleta;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public ArrayList<Boleta> getListBoletas() {
        return listBoletas;
    }

    public void setListBoletas(ArrayList<Boleta> listBoletas) {
        this.listBoletas = listBoletas;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "unicoCode=" + unicoCode +
                ", fechaPresentacion='" + fechaPresentacion + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", precioBoleta=" + precioBoleta +
                '}';
    }
}
