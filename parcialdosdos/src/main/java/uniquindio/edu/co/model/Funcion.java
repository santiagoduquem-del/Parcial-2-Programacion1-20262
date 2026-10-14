package uniquindio.edu.co.model;

public class Funcion {

    private int unicoCode;
    private String fechaPresentacion;
    private String horaInicio;
    private int precioBoleta;

    public Funcion(int unicoCode, String fechaPresentacion, String horaInicio, int precioBoleta){

        this.unicoCode = unicoCode;
        this.fechaPresentacion = fechaPresentacion;
        this.horaInicio = horaInicio;
        this.precioBoleta = precioBoleta;

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
