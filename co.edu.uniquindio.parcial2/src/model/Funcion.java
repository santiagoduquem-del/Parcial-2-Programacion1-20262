package model;

import java.util.ArrayList;
import java.util.List;

public class Funcion {

    private int unicoCode;
    private String fechaPresentacion;   // formato yyyy-MM-dd
    private int horaInicio;             // formato HHmm, ej: 1430 = 2:30 p.m.
    private double precioBoleta;        // precio base

    // Relaciones
    private Pelicula thePelicula;
    private Sala theSala;
    private List<Boleta> listBoletas;

    public Funcion(int unicoCode, String fechaPresentacion, int horaInicio, double precioBoleta, Pelicula thePelicula, Sala theSala){
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

    /** Hora de fin en formato HHmm (si pasa de medianoche se muestra la hora del día siguiente) */
    public int getHoraFin(){
        int fin = getFinMinutos();
        return (fin / 60 % 24) * 100 + fin % 60;
    }

    /**
     * Hay conflicto de horarios con otra función si es la misma sala, la misma fecha
     * y los rangos [inicio, fin) se superponen. El fin depende de la duración de la película.
     */
    public boolean hayConflicto(Funcion otra){
        return theSala.getCodigoSala() == otra.getTheSala().getCodigoSala()
                && fechaPresentacion.equals(otra.getFechaPresentacion())
                && getMinutos() < otra.getFinMinutos()
                && otra.getMinutos() < getFinMinutos();
    }

    /** Indica si el asiento (fila, número) existe en la sala de esta función */
    public boolean asientoValido(int fila, int numAsiento){
        return theSala.asientoValido(fila, numAsiento);
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

    /** Valor de la boleta = precio base x (1 + recargo de la sala) x (1 - descuento del cliente) */
    public double calcularValorBoleta(Cliente cliente){
        double valorConRecargo = precioBoleta * (1 + theSala.calcularRecargo());
        return cliente.aplicarDescuento(valorConRecargo);
    }

    /** Ocupación = (boletas vendidas / capacidad máxima de la sala) x 100 */
    public double calcularOcupacion(){
        return (double) listBoletas.size() / theSala.getCapMax() * 100;
    }

    public int getBoletasVendidas(){
        return listBoletas.size();
    }

    /** Indica si todavía hay capacidad para vender la cantidad de boletas pedida */
    public boolean hayCapacidad(int cantidad){
        return listBoletas.size() + cantidad <= theSala.getCapMax();
    }

    /** Registra la boleta vendida (el asiento queda ocupado para esta función) */
    public void agregarBoleta(Boleta boleta){
        listBoletas.add(boleta);
    }

    /** Libera el asiento de una boleta (se usa al anular una venta) */
    public boolean quitarBoleta(Boleta boleta){
        return listBoletas.remove(boleta);
    }

    /** Mapa de asientos de la sala: [X] ocupado, [ ] libre */
    public String mapaAsientos(){
        StringBuilder mapa = new StringBuilder();
        mapa.append("          ---- PANTALLA ----\n\n");
        mapa.append("     ");
        for(int c = 1; c <= theSala.getColumnas(); c++){
            mapa.append(String.format("%3d ", c));
        }
        mapa.append("\n");
        for(int f = 1; f <= theSala.getFilas(); f++){
            mapa.append(String.format("F%-2d  ", f));
            for(int c = 1; c <= theSala.getColumnas(); c++){
                if(asientoOcupado(f, c)){
                    mapa.append("[X] ");
                }else{
                    mapa.append("[ ] ");
                }
            }
            mapa.append("\n");
        }
        return mapa.toString();
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

    public double getPrecioBoleta() {
        return precioBoleta;
    }

    public void setPrecioBoleta(double precioBoleta) {
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
                ", horaFin=" + getHoraFin() +
                ", precioBoleta=" + precioBoleta +
                ", thePelicula=" + thePelicula.getTitulo() +
                ", theSala=" + theSala.getNombre() +
                ", boletasVendidas=" + listBoletas.size() +
                '}';
    }
}