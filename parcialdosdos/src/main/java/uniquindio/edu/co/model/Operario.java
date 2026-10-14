package uniquindio.edu.co.model;

public class Operario extends Persona{

    private int codEmpleado;
    private String fechaContratacion;
    private double salario;


    /**
     * Metodo constructor de la clase Persona
     *
     * @param documento      de la persona
     *                       de al persona
     * @param nombreCompleto de la persona
     * @param numTelefono
     * @param correo         de la persona
     */
    public Operario(int documento, String nombreCompleto, int numTelefono, String correo, int codEmpleado, String fechaContratacion, double salario) {
        super(documento, nombreCompleto, numTelefono, correo);

        this.codEmpleado = codEmpleado;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
    }

    public int getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(int codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
