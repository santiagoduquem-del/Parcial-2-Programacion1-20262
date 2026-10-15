package model;

public class Persona {

    private int documento;
    private String nombreCompleto;
    private long numTelefono;   // long: un celular colombiano (10 dígitos) no cabe en un int
    private String correo;

    /**
     * Método constructor de la clase Persona
     * @param documento documento de identidad de la persona (único)
     * @param nombreCompleto nombre completo de la persona
     * @param numTelefono número de teléfono de la persona
     * @param correo correo electrónico de la persona
     */
    public Persona(int documento, String nombreCompleto, long numTelefono, String correo){
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.numTelefono = numTelefono;
        this.correo = correo;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public long getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(long numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "documento=" + documento +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", numTelefono=" + numTelefono +
                ", correo='" + correo + '\'' +
                '}';
    }
}