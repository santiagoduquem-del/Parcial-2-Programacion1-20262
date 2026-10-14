package uniquindio.edu.co.model;
import java.util.ArrayList;

public class Pelicula {

    private int peliCode;
    private String titulo;
    private String duracionMin;
    private String genero;
    private String clasifEdad;

    public Pelicula(int peliCode, String titulo, String duracionMin, String genero, String clasifEdad){
        this.peliCode = peliCode;
        this.titulo = titulo;
        this.duracionMin = duracionMin;
        this.genero = genero;
        this.clasifEdad = clasifEdad;

    }

    public int getPeliCode() {
        return peliCode;
    }

    public void setPeliCode(int peliCode) {
        this.peliCode = peliCode;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(String duracionMin) {
        this.duracionMin = duracionMin;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasifEdad() {
        return clasifEdad;
    }

    public void setClasifEdad(String clasifEdad) {
        this.clasifEdad = clasifEdad;
    }
}
