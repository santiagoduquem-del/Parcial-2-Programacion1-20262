package uniquindio.edu.co.model;

public class Cliente extends Persona{

    private int codeAfiliacion;
    private double descuento;

    /**
     * Metodo constructor de la clase Persona
     *
     * @param documento      de la persona
     *                       de al persona
     * @param nombreCompleto de la persona
     * @param numTelefono
     * @param correo         de la persona
     */
    public Cliente(int documento, String nombreCompleto, int numTelefono, String correo, int codeAfiliacion) {
        super(documento, nombreCompleto, numTelefono, correo);

        this.codeAfiliacion = codeAfiliacion;
        this.descuento = 0;

    }

    public int getCodeAfiliacion() {
        return codeAfiliacion;
    }

    public void setCodeAfiliacion(int codeAfiliacion) {
        this.codeAfiliacion = codeAfiliacion;
    }



    @Override
    public String toString() {
        return "Cliente{" +
                "codeAfiliacion=" + codeAfiliacion +
                '}';
    }
}
