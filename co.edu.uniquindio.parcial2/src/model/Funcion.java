package model;

import java.util.ArrayList;
import java.util.List;

public class Funcion {

    private int unicoCode;
    private String fechaPresentacion;
    private int horaInicio;          // formato HHmm, ej: 1430 = 2:30 p.m.
    private int precioBoleta;        // precio base

    // Relaciones
    private Pelicula thePelicula;
    private Sala theSala;
    private List<Boleta> listBoletas;

    public Funcion(int unicoCode, String fechaPresentacion, int horaInicio, int precioBoleta, Pelicula thePelicula, Sala theSala){
        this.unicoCode = unicoCode;
        this.fechaPresentacion = fechaPresentacion;
        this.horaInicio = horaInicio;
        this.precioBoleta = precioBoleta;
        this.thePelicula = thePelicula;
        this.theSala = theSala;
        this.listBoletas = new ArrayList<>();
    }

    /** Hora de inicio convertida a minutos desde las 00:00 */
    public int getMinutos(){
        int horas = horaInicio / 100;
        int minutos = horaInicio % 100;
        return horas * 60 + minutos;
    }

    /** Hora de fin (inicio + duración de la película) en minutos desde las 00:00 */
    public int getFinMinutos(){
        return getMinutos() + thePelicula.getDuracionMin();
    }

    /** Indica si el asiento (fila, número) existe en la sala de esta función */
    public boolean asientoValido(int fila, int numAsiento){
        return fila >= 1 && fila <= theSala.getFilas() && numAsiento >= 1 && numAsiento <= theSala.getColumnas();
    }

    /** Indica si el asiento ya fue vendido para ESTA función */
    public boolean asientoOcupado(int fila, int numAsiento){
        boolean ocupado = false;
        for(int i = 0; i < listBoletas.size(); i++){
            Boleta boleta = listBoletas.get(i);
            if(boleta.getFila() == fila && boleta.getNumAsiento() == numAsiento){
                ocupado = true;
                break;
            }
        }
        return ocupado;
    }

    /** Ocupación = (boletas vendidas / capacidad máxima de la sala) x 100 */
    public double calcularOcupacion(){
        return (double) listBoletas.size() / theSala.getCapMax() * 100;
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

    public Pelicula getThePelicula() {
        return thePelicula;
    }

    public void setThePelicula(Pelicula thePelicula) {
        this.thePelicula = thePelicula;
    }

    public Sala getTheSala() {
        return theSala;
    }

    public void setTheSala(Sala theSala) {
        this.theSala = theSala;
    }

    public List<Boleta> getListBoletas() {
        return listBoletas;
    }

    public void setListBoletas(List<Boleta> listBoletas) {
        this.listBoletas = listBoletas;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "unicoCode=" + unicoCode +
                ", fechaPresentacion='" + fechaPresentacion + '\'' +
                ", horaInicio=" + horaInicio +
                ", precioBoleta=" + precioBoleta +
                ", thePelicula=" + thePelicula.getTitulo() +
                ", theSala=" + theSala.getNombre() +
                ", boletasVendidas=" + listBoletas.size() +
                '}';
    }
}