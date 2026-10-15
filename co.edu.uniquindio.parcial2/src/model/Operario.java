package model;

public class Operario extends Persona {

    private int codEmpleado;
    private String fechaContratacion;
    private double salario;

    /**
     * Constructor de Operario
     * @param documento documento de identidad
     * @param nombreCompleto nombre completo
     * @param numTelefono teléfono
     * @param correo correo electrónico
     * @param codEmpleado código de empleado
     * @param fechaContratacion fecha de contratación
     * @param salario salario básico
     */
    public Operario(int documento, String nombreCompleto, long numTelefono, String correo, int codEmpleado, String fechaContratacion, double salario) {
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

    @Override
    public String toString() {
        return "Operario{" +
                super.toString() +
                ", codEmpleado=" + codEmpleado +
                ", fechaContratacion='" + fechaContratacion + '\'' +
                ", salario=" + salario +
                '}';
    }
}