package uniquindio.edu.co.model;

public class Funcion {

    private int unicoCode;
    private String fechaPresentacion;
    private String horaInicio;
    private int precioBoleta;
    private Pelicula pelicula;   // referencia a otra clase
    private Sala sala;

    public Funcion(int unicoCode, String fechaPresentacion, String horaInicio, int precioBoleta, Pelicula pelicula, Sala sala){

        this.unicoCode = unicoCode;
        this.fechaPresentacion = fechaPresentacion;
        this.horaInicio = horaInicio;
        this.precioBoleta = precioBoleta;
        this.pelicula = pelicula;
        this.sala = sala;

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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
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
