package uniquindio.edu.co.model;
import java.util.ArrayList;
import java.util.List;

public class Cine {

    private String nombre;
    private int nit;
    private String direccion;
    private ArrayList<Sala>listSalas;
    private ArrayList<Pelicula>listPeliculas;
    private ArrayList<Funcion>listFunciones;
    private ArrayList<Cliente>listClientes;
    private ArrayList<Operario>listOperarios;
    private ArrayList<Venta>listVentas;

    public Cine(String nombre, int nit, String direccion){
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listPeliculas = new ArrayList<>();
        this.listFunciones = new ArrayList<>();
        this.listClientes = new ArrayList<>();
        this.listOperarios = new ArrayList<>();
        this.listVentas = new ArrayList<>();
    }

    public boolean registrarCliente(String nombre, int documento, String correo, int numTelefono, int codeAfiliacion){
        boolean existe = false;
        Persona cliente = buscarCliente(documento);
        if(cliente == null){
            Cliente estado = new Cliente(documento, nombre, numTelefono, correo, codeAfiliacion);
            listClientes.add(estado);
            existe = true;
        }
        return existe;
    }

    public Persona buscarCliente(int documento){
       Persona encontrado = null;
       for(int i = 0; i < listClientes.size(); i++ ){
           Persona cliente = listClientes.get(i);
           if(cliente.getDocumento() == documento){
               encontrado = cliente;
               break;
           }
       }
        return encontrado;
    }

    public boolean registrarOperario(int documento, String nombreCompleto, int numTelefono, String correo, int codEmpleado, String fechaContratacion, double salario){
        boolean existe = false;
        Persona operario = buscarOperario(codEmpleado);
        if(operario == null){
            Operario estado = new Operario(documento, nombreCompleto, numTelefono, correo, codEmpleado, fechaContratacion, salario);
            listOperarios.add(estado);
            existe = true;
        }
        return existe;
    }

    public Persona buscarOperario(int codEmpleado){
        Operario encontrado = null;
        for(int i = 0; i < listOperarios.size(); i++){
            Operario operario = listOperarios.get(i);
            if(operario.getCodEmpleado() == codEmpleado){
                encontrado = operario;
                break;
            }

        }
        return encontrado;
    }


    public boolean registrarSala(int codigoSala, String nombre, int capMax, TipoSala tipo, int filas, int columnas){
        boolean existe = false;
        Sala sala = buscarSala(codigoSala);
        if(sala == null){
            Sala estado = new Sala(codigoSala, nombre, capMax, tipo, filas, columnas);
            listSalas.add(estado);
            existe = true;
        }
        return existe;
    }

    public Sala buscarSala(int codigoSala){
        Sala encontrada = null;
        for(int i = 0; i < listSalas.size(); i++){
            Sala sala =listSalas.get(i);
            if(sala.getCodigoSala() == codigoSala){
                encontrada = sala;
                break;
            }
        }
        return encontrada;
    }

    public boolean registrarPelicula(int peliCode, String titulo, String duracionMin, String clasifEdad, Genero generoPeli){
        boolean existe = false;
        Pelicula pelicula = buscarPelicula(peliCode);
        if(pelicula == null){
            Pelicula estado = new Pelicula(peliCode, titulo, duracionMin, clasifEdad, generoPeli);
            listPeliculas.add(estado);
            existe = true;
        }
        return existe;
    }

    public Pelicula buscarPelicula(int peliCode){
        Pelicula encontrada = null;
        for(int i = 0; i < listPeliculas.size(); i++ ){
            Pelicula pelicula = listPeliculas.get(i);
            if(pelicula.getPeliCode() == peliCode){
                encontrada = pelicula;
                break;
            }
        }
        return encontrada;
    }

    public boolean programarFuncion(int unicoCode, String fechaPresentacion, int horaInicio, int precioBoleta, Pelicula pelicula, Sala sala){
        boolean programada = false;
        if(buscarFuncion(unicoCode) == null){
        Funcion nueva = new Funcion(unicoCode, fechaPresentacion, horaInicio, precioBoleta, pelicula, sala);
        if(conflictoHorario(nueva)){
            listFunciones.add(nueva);
            programada = true;
        }
        }
        return programada;
    }

    public boolean conflictoHorario(Funcion nueva){
        boolean conflicto = false;
        for(int i = 0; i < listFunciones.size(); i++){
            Funcion func = listFunciones.get(i);
            if(func.getSala() ==  nueva.getSala() &&  func.getFechaPresentacion().equals(nueva.getFechaPresentacion()) && nueva.getMinutos() < func.getFinMinutos()
                    && func.getMinutos() < nueva.getFinMinutos()){
                conflicto = true;
                break;
            }

        }
        return conflicto;
    }

    public Funcion buscarFuncion(int unicoCode){
        Funcion encontrada = null;
        for(int i = 0; i < listFunciones.size(); i++ ){
            Funcion funcion = listFunciones.get(i);
            if(funcion.getUnicoCode() == unicoCode){
                encontrada = funcion;
                break;
            }
        }
        return encontrada;
    }

}
