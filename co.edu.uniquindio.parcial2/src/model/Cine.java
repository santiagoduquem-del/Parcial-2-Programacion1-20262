package model;

import java.util.ArrayList;
import java.util.List;

public class Cine {

    private String nombre;
    private int nit;
    private String direccion;

    // Relaciones
    private List<Sala> listSalas;
    private List<Pelicula> listPeliculas;
    private List<Funcion> listFunciones;
    private List<Cliente> listClientes;
    private List<Operario> listOperarios;
    private List<Venta> listVentas;

    private int consecutivoBoleta;   // genera códigos únicos de boleta

    public Cine(String nombre, int nit, String direccion){
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listSalas = new ArrayList<>();      // antes faltaba -> NullPointerException
        this.listPeliculas = new ArrayList<>();
        this.listFunciones = new ArrayList<>();
        this.listClientes = new ArrayList<>();
        this.listOperarios = new ArrayList<>();
        this.listVentas = new ArrayList<>();
        this.consecutivoBoleta = 1;
    }

    //PELICULAS

    /** 1. Registrar película (el código no puede existir) */
    public boolean registrarPelicula(int peliCode, String titulo, int duracionMin, String clasifEdad, Genero generoPeli){
        boolean registrada = false;
        if(duracionMin > 0 && buscarPelicula(peliCode) == null){
            Pelicula nueva = new Pelicula(peliCode, titulo, duracionMin, clasifEdad, generoPeli);
            listPeliculas.add(nueva);
            registrada = true;
        }
        return registrada;
    }

    public Pelicula buscarPelicula(int peliCode){
        Pelicula encontrada = null;
        for(int i = 0; i < listPeliculas.size(); i++){
            Pelicula pelicula = listPeliculas.get(i);
            if(pelicula.getPeliCode() == peliCode){
                encontrada = pelicula;
                break;
            }
        }
        return encontrada;
    }

    /** Consultar películas disponibles en el catálogo */
    public List<Pelicula> consultarPeliculasDisponibles(){
        return new ArrayList<>(listPeliculas);
    }

    // ------------------------------------------------------------------
    // PERSONAS (clientes y operarios)
    // ------------------------------------------------------------------

    /** 2. Registrar cliente (el documento no puede existir, ni como cliente ni como operario) */
    public boolean registrarCliente(String nombre, int documento, String correo, long numTelefono, int codeAfiliacion, double descuento){
        boolean registrado = false;
        if(descuento >= 0 && descuento <= 1 && buscarPersona(documento) == null){
            Cliente nuevo = new Cliente(documento, nombre, numTelefono, correo, codeAfiliacion, descuento);
            listClientes.add(nuevo);
            registrado = true;
        }
        return registrado;
    }

    /** Registrar cliente sin descuento */
    public boolean registrarCliente(String nombre, int documento, String correo, long numTelefono, int codeAfiliacion){
        return registrarCliente(nombre, documento, correo, numTelefono, codeAfiliacion, 0);
    }

    public Cliente buscarCliente(int documento){
        Cliente encontrado = null;
        for(int i = 0; i < listClientes.size(); i++){
            Cliente cliente = listClientes.get(i);
            if(cliente.getDocumento() == documento){
                encontrado = cliente;
                break;
            }
        }
        return encontrado;
    }

    public boolean registrarOperario(int documento, String nombreCompleto, long numTelefono, String correo, int codEmpleado, String fechaContratacion, double salario){
        boolean registrado = false;
        if(buscarOperario(codEmpleado) == null && buscarPersona(documento) == null){
            Operario nuevo = new Operario(documento, nombreCompleto, numTelefono, correo, codEmpleado, fechaContratacion, salario);
            listOperarios.add(nuevo);
            registrado = true;
        }
        return registrado;
    }

    public Operario buscarOperario(int codEmpleado){
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

    /** Busca por documento en clientes y operarios (el documento es único para toda persona) */
    public Persona buscarPersona(int documento){
        Persona encontrada = buscarCliente(documento);
        if(encontrada == null){
            for(int i = 0; i < listOperarios.size(); i++){
                if(listOperarios.get(i).getDocumento() == documento){
                    encontrada = listOperarios.get(i);
                    break;
                }
            }
        }
        return encontrada;
    }

    // ------------------------------------------------------------------
    // SALAS
    // ------------------------------------------------------------------

    public boolean registrarSala(int codigoSala, String nombre, int capMax, TipoSala tipo, int filas, int columnas){
        boolean registrada = false;
        if(filas > 0 && columnas > 0 && capMax > 0 && capMax <= filas * columnas && buscarSala(codigoSala) == null){
            Sala nueva = new Sala(codigoSala, nombre, capMax, tipo, filas, columnas);
            listSalas.add(nueva);
            registrada = true;
        }
        return registrada;
    }

    public Sala buscarSala(int codigoSala){
        Sala encontrada = null;
        for(int i = 0; i < listSalas.size(); i++){
            Sala sala = listSalas.get(i);
            if(sala.getCodigoSala() == codigoSala){
                encontrada = sala;
                break;
            }
        }
        return encontrada;
    }

    // ------------------------------------------------------------------
    // FUNCIONES
    // ------------------------------------------------------------------

    /** 3. Programar función (verificando que no haya conflicto de horarios en la sala) */
    public boolean programarFuncion(int unicoCode, String fechaPresentacion, int horaInicio, int precioBoleta, Pelicula pelicula, Sala sala){
        boolean programada = false;
        boolean datosValidos = pelicula != null && sala != null && precioBoleta > 0
                && horaInicio >= 0 && horaInicio <= 2359 && horaInicio % 100 < 60
                && buscarPelicula(pelicula.getPeliCode()) != null
                && buscarSala(sala.getCodigoSala()) != null;

        if(datosValidos && buscarFuncion(unicoCode) == null){
            Funcion nueva = new Funcion(unicoCode, fechaPresentacion, horaInicio, precioBoleta, pelicula, sala);
            if(!conflictoHorario(nueva)){   // antes estaba invertido: programaba solo si HABÍA conflicto
                listFunciones.add(nueva);
                programada = true;
            }
        }
        return programada;
    }

    /**
     * Verifica conflicto de horarios: misma sala, misma fecha y rangos
     * [inicio, fin) que se superponen (fin = inicio + duración de la película).
     */
    public boolean conflictoHorario(Funcion nueva){
        boolean conflicto = false;
        for(int i = 0; i < listFunciones.size(); i++){
            Funcion func = listFunciones.get(i);
            if(func.getTheSala().getCodigoSala() == nueva.getTheSala().getCodigoSala()
                    && func.getFechaPresentacion().equals(nueva.getFechaPresentacion())
                    && nueva.getMinutos() < func.getFinMinutos()
                    && func.getMinutos() < nueva.getFinMinutos()){
                conflicto = true;
                break;
            }
        }
        return conflicto;
    }

    public Funcion buscarFuncion(int unicoCode){
        Funcion encontrada = null;
        for(int i = 0; i < listFunciones.size(); i++){
            Funcion funcion = listFunciones.get(i);
            if(funcion.getUnicoCode() == unicoCode){
                encontrada = funcion;
                break;
            }
        }
        return encontrada;
    }

    /** Verifica si un asiento ya está vendido para una función. Retorna true si está ocupado. */
    public boolean verificarAsientoOcupado(Funcion funcion, int fila, int numAsiento){
        return funcion.asientoOcupado(fila, numAsiento);
    }

    /** 6. Porcentaje de ocupación de una función (retorna -1 si la función no existe) */
    public double calcularPorcentajeOcupacion(int unicoCode){
        double ocupacion = -1;
        Funcion funcion = buscarFuncion(unicoCode);
        if(funcion != null){
            ocupacion = funcion.calcularOcupacion();
        }
        return ocupacion;
    }

    // ------------------------------------------------------------------
    // VENTAS
    // ------------------------------------------------------------------

    /**
     * 4. Realizar venta de boletas para un cliente en una función.
     * @param asientos arreglo de asientos, cada uno como {fila, numAsiento}. Ej: {{1,1},{1,2}}
     * La venta es atómica: si algún asiento no es válido/está ocupado, no se vende nada.
     */
    public boolean realizarVenta(int codigoVenta, String fechaRealizada, int codEmpleado, int documentoCliente, int codigoFuncion, int[][] asientos){
        boolean realizada = false;
        Operario operario = buscarOperario(codEmpleado);
        Cliente cliente = buscarCliente(documentoCliente);
        Funcion funcion = buscarFuncion(codigoFuncion);

        if(operario != null && cliente != null && funcion != null
                && buscarVenta(codigoVenta) == null
                && asientosDisponibles(funcion, asientos)){

            Venta venta = new Venta(codigoVenta, fechaRealizada, operario, cliente, funcion);
            for(int i = 0; i < asientos.length; i++){
                agregarBoletaAVenta(venta, asientos[i][0], asientos[i][1]);
            }
            listVentas.add(venta);
            realizada = true;
        }
        return realizada;
    }

    /** Valida que todos los asientos pedidos existan, estén libres, no se repitan y haya capacidad */
    private boolean asientosDisponibles(Funcion funcion, int[][] asientos){
        boolean disponibles = asientos != null && asientos.length > 0
                && funcion.getListBoletas().size() + asientos.length <= funcion.getTheSala().getCapMax();

        for(int i = 0; disponibles && i < asientos.length; i++){
            if(asientos[i] == null || asientos[i].length != 2
                    || !funcion.asientoValido(asientos[i][0], asientos[i][1])
                    || funcion.asientoOcupado(asientos[i][0], asientos[i][1])){
                disponibles = false;
            }
            // asiento repetido dentro de la misma solicitud
            for(int j = 0; disponibles && j < i; j++){
                if(asientos[j][0] == asientos[i][0] && asientos[j][1] == asientos[i][1]){
                    disponibles = false;
                }
            }
        }
        return disponibles;
    }

    /** Agrega una boleta a la venta (si el asiento es válido y está libre en la función de la venta) */
    public boolean agregarBoletaAVenta(Venta venta, int fila, int numAsiento){
        boolean agregada = false;
        Funcion funcion = venta.getTheFuncion();

        if(funcion.asientoValido(fila, numAsiento)
                && !funcion.asientoOcupado(fila, numAsiento)
                && funcion.getListBoletas().size() < funcion.getTheSala().getCapMax()){

            double valor = calcularValorBoleta(funcion, venta.getTheCliente());
            Boleta boleta = new Boleta(consecutivoBoleta++, fila, numAsiento, valor);
            venta.agregarBoleta(boleta);
            funcion.getListBoletas().add(boleta);   // así el asiento queda ocupado para esa función
            agregada = true;
        }
        return agregada;
    }

    /** Valor de la boleta = precio base x (1 + recargo) x (1 - descuento) */
    public double calcularValorBoleta(Funcion funcion, Cliente cliente){
        double recargo = funcion.getTheSala().getTipo().getRecargo();
        return funcion.getPrecioBoleta() * (1 + recargo) * (1 - cliente.getDescuento());
    }

    public Venta buscarVenta(int codigoVenta){
        Venta encontrada = null;
        for(int i = 0; i < listVentas.size(); i++){
            Venta venta = listVentas.get(i);
            if(venta.getCodigoVenta() == codigoVenta){
                encontrada = venta;
                break;
            }
        }
        return encontrada;
    }

    /** 5. Valor total de una venta buscándola por su código (retorna -1 si no existe) */
    public double calcularValorTotalVenta(int codigoVenta){
        double total = -1;
        Venta venta = buscarVenta(codigoVenta);
        if(venta != null){
            total = venta.calcularValorTotal();
        }
        return total;
    }

    /** Genera la factura (record) de una venta a partir de su código. Retorna null si la venta no existe. */
    public Factura generarFactura(int codigoVenta){
        Factura factura = null;
        Venta venta = buscarVenta(codigoVenta);
        if(venta != null){
            factura = new Factura(
                    venta.getCodigoVenta(),
                    venta.getFechaRealizada(),
                    venta.getTheCliente().getNombreCompleto(),
                    venta.getTheOperario().getNombreCompleto(),
                    venta.getTheFuncion().getThePelicula().getTitulo(),
                    venta.getListBoletas().size(),
                    venta.calcularValorTotal());
        }
        return factura;
    }

    // ------------------------------------------------------------------
    // GETTERS
    // ------------------------------------------------------------------

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getNit() { return nit; }
    public void setNit(int nit) { this.nit = nit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<Sala> getListSalas() { return listSalas; }
    public void setListSalas(List<Sala> listSalas) { this.listSalas = listSalas; }

    public List<Pelicula> getListPeliculas() { return listPeliculas; }
    public void setListPeliculas(List<Pelicula> listPeliculas) { this.listPeliculas = listPeliculas; }

    public List<Funcion> getListFunciones() { return listFunciones; }
    public void setListFunciones(List<Funcion> listFunciones) { this.listFunciones = listFunciones; }

    public List<Cliente> getListClientes() { return listClientes; }
    public void setListClientes(List<Cliente> listClientes) { this.listClientes = listClientes; }

    public List<Operario> getListOperarios() { return listOperarios; }
    public void setListOperarios(List<Operario> listOperarios) { this.listOperarios = listOperarios; }

    public List<Venta> getListVentas() { return listVentas; }
    public void setListVentas(List<Venta> listVentas) { this.listVentas = listVentas; }

    @Override
    public String toString() {
        return "Cine{" +
                "nombre='" + nombre + '\'' +
                ", nit=" + nit +
                ", direccion='" + direccion + '\'' +
                ", salas=" + listSalas.size() +
                ", peliculas=" + listPeliculas.size() +
                ", funciones=" + listFunciones.size() +
                ", clientes=" + listClientes.size() +
                ", operarios=" + listOperarios.size() +
                ", ventas=" + listVentas.size() +
                '}';
    }
}