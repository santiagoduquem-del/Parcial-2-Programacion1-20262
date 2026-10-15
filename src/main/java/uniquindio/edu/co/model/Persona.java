package uniquindio.edu.co.model;

public class Persona {

    private int documento;
    private String nombreCompleto;
    private int numTelefono;
    private  String correo;

    /**
     * Metodo constructor de la clase Persona
     * @param nombreCompleto de la persona
     * @param correo de la persona
     * @param documento de la persona
     *                  de al persona
     */
    public Persona(int documento, String nombreCompleto, int numTelefono, String correo){
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

    public int getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(int numTelefono) {
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
