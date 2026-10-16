package model;

public class Pelicula {

    private int peliCode;
    private String titulo;
    private int duracionMin;   // duración en minutos (int para poder calcular la hora de fin)
    private String clasifEdad;
    private Genero generoPeli;

    /**
     * Constructor clase pelicula
     * @param peliCode
     * @param titulo
     * @param duracionMin
     * @param clasifEdad
     * @param generoPeli
     */

    public Pelicula(int peliCode, String titulo, int duracionMin, String clasifEdad, Genero generoPeli){
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

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public String getClasifEdad() {
        return clasifEdad;
    }

    public void setClasifEdad(String clasifEdad) {
        this.clasifEdad = clasifEdad;
    }

    public Genero getGeneroPeli() {
        return generoPeli;
    }

    public void setGeneroPeli(Genero generoPeli) {
        this.generoPeli = generoPeli;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "peliCode=" + peliCode +
                ", titulo='" + titulo + '\'' +
                ", duracionMin=" + duracionMin +
                ", genero=" + generoPeli +
                ", clasifEdad='" + clasifEdad + '\'' +
                '}';
    }
}