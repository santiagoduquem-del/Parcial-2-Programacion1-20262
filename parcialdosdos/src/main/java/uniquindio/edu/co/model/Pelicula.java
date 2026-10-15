package uniquindio.edu.co.model;

public class Pelicula {

    private int peliCode;
    private String titulo;
    private String duracionMin;
    private String clasifEdad;
    private Genero generoPeli;

    public Pelicula(int peliCode, String titulo, String duracionMin, String clasifEdad, Genero generoPeli){
        this.peliCode = peliCode;
        this.titulo = titulo;
        this.duracionMin = duracionMin;
        this.clasifEdad = clasifEdad;
        this.generoPeli = generoPeli;

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

    public String getClasifEdad() {
        return clasifEdad;
    }

    public Genero getGeneroPeli() {
        return generoPeli;
    }

    public void setGeneroPeli(Genero generoPeli) {
        this.generoPeli = generoPeli;
    }

    public void setClasifEdad(String clasifEdad) {
        this.clasifEdad = clasifEdad;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "peliCode=" + peliCode +
                ", titulo='" + titulo + '\'' +
                ", duracionMin='" + duracionMin + '\'' +
                ", genero='" + generoPeli + '\'' +
                ", clasifEdad='" + clasifEdad + '\'' +
                '}';
    }
}
