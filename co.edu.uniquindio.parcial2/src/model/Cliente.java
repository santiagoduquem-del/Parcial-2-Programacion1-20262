package model;

public class Cliente extends Persona {

    private int codeAfiliacion;
    private double descuento;   // porcentaje expresado como fracción: 0.10 = 10 %

    /**
     * Constructor de Cliente
     * @param documento documento de identidad
     * @param nombreCompleto nombre completo
     * @param numTelefono teléfono
     * @param correo correo electrónico
     * @param codeAfiliacion código de afiliación
     * @param descuento porcentaje de descuento (entre 0 y 1)
     */
    public Cliente(int documento, String nombreCompleto, long numTelefono, String correo, int codeAfiliacion, double descuento) {
        super(documento, nombreCompleto, numTelefono, correo);
        this.codeAfiliacion = codeAfiliacion;
        this.descuento = descuento;
    }

    /** Cliente sin descuento */
    public Cliente(int documento, String nombreCompleto, long numTelefono, String correo, int codeAfiliacion) {
        this(documento, nombreCompleto, numTelefono, correo, codeAfiliacion, 0);
    }

    public int getCodeAfiliacion() {
        return codeAfiliacion;
    }

    public void setCodeAfiliacion(int codeAfiliacion) {
        this.codeAfiliacion = codeAfiliacion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                super.toString() +
                ", codeAfiliacion=" + codeAfiliacion +
                ", descuento=" + descuento +
                '}';
    }
}