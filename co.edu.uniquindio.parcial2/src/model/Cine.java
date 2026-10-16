package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    /**
     * Constructor clase Cine
     * @param nombre
     * @param nit
     * @param direccion
     */

    public Cine(String nombre, int nit, String direccion){
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listSalas = new ArrayList<>();
        this.listPeliculas = new ArrayList<>();
        this.listFunciones = new ArrayList<>();
        this.listClientes = new ArrayList<>();
        this.listOperarios = new ArrayList<>();
        this.listVentas = new ArrayList<>();
        this.consecutivoBoleta = 1;
    }

    // Validacion de auxiliares

    /** La fecha debe tener formato yyyy-MM-dd y ser una fecha real (así se pueden comparar con equals) */
    private boolean fechaValida(String fecha){
        boolean valida = true;
        try{
            LocalDate.parse(fecha);
        }catch(DateTimeParseException | NullPointerException e){
            valida = false;
        }
        return valida;
    }

    private boolean textoValido(String texto){
        return texto != null && !texto.trim().isEmpty();
    }

    private boolean horaValida(int horaInicio){
        return horaInicio >= 0 && horaInicio <= 2359 && horaInicio % 100 < 60;
    }

    //CRUD para peliculas

    /** 1. Registrar película (el código no puede existir) */
    public boolean registrarPelicula(int peliCode, String titulo, int duracionMin, String clasifEdad, Genero generoPeli){
        boolean registrada = false;
        if(textoValido(titulo) && textoValido(clasifEdad) && generoPeli != null
                && duracionMin > 0 && buscarPelicula(peliCode) == null){
            Pelicula nueva = new Pelicula(peliCode, titulo, duracionMin, clasifEdad, generoPeli);
            listPeliculas.add(nueva);
            registrada = true;
        }
        return registrada;
    }

    /**
     * Metodo que sirve para poder buscar peliculas por codigo
     * @param peliCode
     * @return
     */

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

    /** Una película solo puede cambiar de duración si no tiene funciones (cambiaría la hora de fin) */
    public boolean actualizarPelicula(int peliCode, String titulo, int duracionMin, String clasifEdad, Genero generoPeli){
        boolean actualizada = false;
        Pelicula pelicula = buscarPelicula(peliCode);
        if(pelicula != null && textoValido(titulo) && textoValido(clasifEdad) && generoPeli != null && duracionMin > 0
                && (duracionMin == pelicula.getDuracionMin() || !peliculaTieneFunciones(peliCode))){
            pelicula.setTitulo(titulo);
            pelicula.setDuracionMin(duracionMin);
            pelicula.setClasifEdad(clasifEdad);
            pelicula.setGeneroPeli(generoPeli);
            actualizada = true;
        }
        return actualizada;
    }

    /** No se puede eliminar una película que tiene funciones programadas */
    public boolean eliminarPelicula(int peliCode){
        boolean eliminada = false;
        Pelicula pelicula = buscarPelicula(peliCode);
        if(pelicula != null && !peliculaTieneFunciones(peliCode)){
            listPeliculas.remove(pelicula);
            eliminada = true;
        }
        return eliminada;
    }

    /**
     * Metodo para verificar si una pelicula tiene funciones
     * @param peliCode
     * @return
     */

    public boolean peliculaTieneFunciones(int peliCode){
        boolean tiene = false;
        for(int i = 0; i < listFunciones.size(); i++){
            if(listFunciones.get(i).getThePelicula().getPeliCode() == peliCode){
                tiene = true;
                break;
            }
        }
        return tiene;
    }

    //CRUD de cliente

    /** 2. Registrar cliente (el documento no puede existir, ni como cliente ni como operario) */
    public boolean registrarCliente(String nombre, int documento, String correo, long numTelefono, int codeAfiliacion, double descuento){
        boolean registrado = false;
        if(textoValido(nombre) && textoValido(correo) && descuento >= 0 && descuento <= 1
                && buscarPersona(documento) == null){
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

    /**
     * Metodo para buscar un cliente por el numero de su documento
     * @param documento
     * @return
     */

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



    public List<Cliente> consultarClientes(){
        return new ArrayList<>(listClientes);
    }

    /** El documento identifica al cliente, por eso no se modifica */
    public boolean actualizarCliente(int documento, String nombreCompleto, long numTelefono, String correo, int codeAfiliacion, double descuento){
        boolean actualizado = false;
        Cliente cliente = buscarCliente(documento);
        if(cliente != null && textoValido(nombreCompleto) && textoValido(correo) && descuento >= 0 && descuento <= 1){
            cliente.setNombreCompleto(nombreCompleto);
            cliente.setNumTelefono(numTelefono);
            cliente.setCorreo(correo);
            cliente.setCodeAfiliacion(codeAfiliacion);
            cliente.setDescuento(descuento);
            actualizado = true;
        }
        return actualizado;
    }

    /** No se puede eliminar un cliente que tiene ventas */
    public boolean eliminarCliente(int documento){
        boolean eliminado = false;
        Cliente cliente = buscarCliente(documento);
        if(cliente != null && !clienteTieneVentas(documento)){
            listClientes.remove(cliente);
            eliminado = true;
        }
        return eliminado;
    }

    /**
     * Metodo para validar si un cliete tiene compras
     * @param documento
     * @return
     */

    public boolean clienteTieneVentas(int documento){
        boolean tiene = false;
        for(int i = 0; i < listVentas.size(); i++){
            if(listVentas.get(i).getTheCliente().getDocumento() == documento){
                tiene = true;
                break;
            }
        }
        return tiene;
    }

    /**
     * CRUD de el operario
     * @param documento
     * @param nombreCompleto
     * @param numTelefono
     * @param correo
     * @param codEmpleado
     * @param fechaContratacion
     * @param salario
     * @return
     */

    /**
     * Metodo para registar un operario con susu atributos
     * @param documento
     * @param nombreCompleto
     * @param numTelefono
     * @param correo
     * @param codEmpleado
     * @param fechaContratacion
     * @param salario
     * @return
     */

    public boolean registrarOperario(int documento, String nombreCompleto, long numTelefono, String correo, int codEmpleado, String fechaContratacion, double salario){
        boolean registrado = false;
        if(textoValido(nombreCompleto) && textoValido(correo) && fechaValida(fechaContratacion) && salario > 0
                && buscarOperario(codEmpleado) == null && buscarPersona(documento) == null){
            Operario nuevo = new Operario(documento, nombreCompleto, numTelefono, correo, codEmpleado, fechaContratacion, salario);
            listOperarios.add(nuevo);
            registrado = true;
        }
        return registrado;
    }

    /**
     * Metodo para buscar un operario con el codigo de empleado
     * @param codEmpleado
     * @return
     */

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

    public List<Operario> consultarOperarios(){
        return new ArrayList<>(listOperarios);
    }

    /** El código de empleado y el documento identifican al operario, por eso no se modifican */
    public boolean actualizarOperario(int codEmpleado, String nombreCompleto, long numTelefono, String correo, String fechaContratacion, double salario){
        boolean actualizado = false;
        Operario operario = buscarOperario(codEmpleado);
        if(operario != null && textoValido(nombreCompleto) && textoValido(correo) && fechaValida(fechaContratacion) && salario > 0){
            operario.setNombreCompleto(nombreCompleto);
            operario.setNumTelefono(numTelefono);
            operario.setCorreo(correo);
            operario.setFechaContratacion(fechaContratacion);
            operario.setSalario(salario);
            actualizado = true;
        }
        return actualizado;
    }

    /** No se puede eliminar un operario que registró ventas */
    public boolean eliminarOperario(int codEmpleado){
        boolean eliminado = false;
        Operario operario = buscarOperario(codEmpleado);
        if(operario != null && !operarioTieneVentas(codEmpleado)){
            listOperarios.remove(operario);
            eliminado = true;
        }
        return eliminado;
    }

    /**
     * Metodo para verificar si un operario tene ventas
     * @param codEmpleado
     * @return
     */

    public boolean operarioTieneVentas(int codEmpleado){
        boolean tiene = false;
        for(int i = 0; i < listVentas.size(); i++){
            if(listVentas.get(i).getTheOperario().getCodEmpleado() == codEmpleado){
                tiene = true;
                break;
            }
        }
        return tiene;
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

    //CRUD de la sala

    /**registrar una sala
     * Metodo para
     * @param codigoSala
     * @param nombre
     * @param capMax
     * @param tipo
     * @param filas
     * @param columnas
     * @return
     */

    public boolean registrarSala(int codigoSala, String nombre, int capMax, TipoSala tipo, int filas, int columnas){
        boolean registrada = false;
        if(textoValido(nombre) && tipo != null && filas > 0 && columnas > 0
                && capMax > 0 && capMax <= filas * columnas && buscarSala(codigoSala) == null){
            Sala nueva = new Sala(codigoSala, nombre, capMax, tipo, filas, columnas);
            listSalas.add(nueva);
            registrada = true;
        }
        return registrada;
    }

    /**
     * metodo para buscar una sala con el codigo de la sala
     * @param codigoSala
     * @return
     */

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

    public List<Sala> consultarSalas(){
        return new ArrayList<>(listSalas);
    }

    /**
     * Nombre y tipo se pueden cambiar siempre (el tipo solo afecta ventas futuras, las boletas
     * ya vendidas conservan su valor). La capacidad, las filas y las columnas solo cambian
     * si la sala no tiene boletas vendidas.
     */
    public boolean actualizarSala(int codigoSala, String nombre, int capMax, TipoSala tipo, int filas, int columnas){
        boolean actualizada = false;
        Sala sala = buscarSala(codigoSala);
        if(sala != null && textoValido(nombre) && tipo != null && filas > 0 && columnas > 0
                && capMax > 0 && capMax <= filas * columnas){
            boolean cambiaDistribucion = capMax != sala.getCapMax() || filas != sala.getFilas() || columnas != sala.getColumnas();
            if(!cambiaDistribucion || !salaTieneBoletasVendidas(codigoSala)){
                sala.setNombre(nombre);
                sala.setTipo(tipo);
                sala.setCapMax(capMax);
                sala.setFilas(filas);
                sala.setColumnas(columnas);
                actualizada = true;
            }
        }
        return actualizada;
    }

    /** No se puede eliminar una sala que tiene funciones programadas */
    public boolean eliminarSala(int codigoSala){
        boolean eliminada = false;
        Sala sala = buscarSala(codigoSala);
        if(sala != null && !salaTieneFunciones(codigoSala)){
            listSalas.remove(sala);
            eliminada = true;
        }
        return eliminada;
    }

    /**
     * metodo para verificar si una sala tiene funciones
     * @param codigoSala
     * @return
     */

    public boolean salaTieneFunciones(int codigoSala){
        boolean tiene = false;
        for(int i = 0; i < listFunciones.size(); i++){
            if(listFunciones.get(i).getTheSala().getCodigoSala() == codigoSala){
                tiene = true;
                break;
            }
        }
        return tiene;
    }

    /**
     * metodo para verificar si una sala tiene boletas vendidas
     * @param codigoSala
     * @return
     */

    public boolean salaTieneBoletasVendidas(int codigoSala){
        boolean tiene = false;
        for(int i = 0; i < listFunciones.size(); i++){
            Funcion funcion = listFunciones.get(i);
            if(funcion.getTheSala().getCodigoSala() == codigoSala && funcion.getBoletasVendidas() > 0){
                tiene = true;
                break;
            }
        }
        return tiene;
    }

    //CRUD de funciones

    /** 3. Programar función (verificando que no haya conflicto de horarios en la sala) */
    public boolean programarFuncion(int unicoCode, String fechaPresentacion, int horaInicio, double precioBoleta, int codigoPelicula, int codigoSala){
        boolean programada = false;
        Pelicula pelicula = buscarPelicula(codigoPelicula);
        Sala sala = buscarSala(codigoSala);

        if(pelicula != null && sala != null && fechaValida(fechaPresentacion) && horaValida(horaInicio)
                && precioBoleta > 0 && buscarFuncion(unicoCode) == null){
            Funcion nueva = new Funcion(unicoCode, fechaPresentacion, horaInicio, precioBoleta, pelicula, sala);
            if(!conflictoHorario(nueva)){
                listFunciones.add(nueva);
                programada = true;
            }
        }
        return programada;
    }

    /**
     * Verifica si la función entra en conflicto con alguna ya programada.
     * Se ignora la función con el mismo código (sirve al actualizar una función existente).
     */
    public boolean conflictoHorario(Funcion nueva){
        boolean conflicto = false;
        for(int i = 0; i < listFunciones.size(); i++){
            Funcion existente = listFunciones.get(i);
            if(existente.getUnicoCodigo() != nueva.getUnicoCodigo() && existente.hayConflicto(nueva)){
                conflicto = true;
                break;
            }
        }
        return conflicto;
    }

    /**
     * Metodo para buscar una funcion por codigo
     * @param unicoCode
     * @return
     */

    public Funcion buscarFuncion(int unicoCode){
        Funcion encontrada = null;
        for(int i = 0; i < listFunciones.size(); i++){
            Funcion funcion = listFunciones.get(i);
            if(funcion.getUnicoCodigo() == unicoCode){
                encontrada = funcion;
                break;
            }
        }
        return encontrada;
    }

    public List<Funcion> consultarFunciones(){
        return new ArrayList<>(listFunciones);
    }

    /** Todas las funciones (horarios y salas) en las que se proyecta una película */
    public List<Funcion> consultarFuncionesPorPelicula(int peliCode){
        List<Funcion> resultado = new ArrayList<>();
        for(int i = 0; i < listFunciones.size(); i++){
            if(listFunciones.get(i).getThePelicula().getPeliCode() == peliCode){
                resultado.add(listFunciones.get(i));
            }
        }
        return resultado;
    }

    /**
     * Actualiza fecha, hora y precio base. La película y la sala no cambian (para eso se elimina y se
     * programa de nuevo). Si ya hay boletas vendidas solo se permite cambiar el precio (aplica a
     * ventas futuras). El nuevo horario no puede chocar con otra función de la sala.
     */
    public boolean actualizarFuncion(int unicoCode, String fechaPresentacion, int horaInicio, double precioBoleta){
        boolean actualizada = false;
        Funcion funcion = buscarFuncion(unicoCode);
        if(funcion != null && fechaValida(fechaPresentacion) && horaValida(horaInicio) && precioBoleta > 0){
            boolean cambiaHorario = !fechaPresentacion.equals(funcion.getFechaPresentacion()) || horaInicio != funcion.getHoraInicio();
            if(!cambiaHorario || funcion.getBoletasVendidas() == 0){
                Funcion candidata = new Funcion(unicoCode, fechaPresentacion, horaInicio, precioBoleta, funcion.getThePelicula(), funcion.getTheSala());
                if(!conflictoHorario(candidata)){
                    funcion.setFechaPresentacion(fechaPresentacion);
                    funcion.setHoraInicio(horaInicio);
                    funcion.setPrecioBoleta(precioBoleta);
                    actualizada = true;
                }
            }
        }
        return actualizada;
    }

    /** No se puede eliminar una función que ya tiene boletas vendidas (primero se anulan las ventas) */
    public boolean eliminarFuncion(int unicoCode){
        boolean eliminada = false;
        Funcion funcion = buscarFuncion(unicoCode);
        if(funcion != null && funcion.getBoletasVendidas() == 0){
            listFunciones.remove(funcion);
            eliminada = true;
        }
        return eliminada;
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

    //ventas

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

        if(operario != null && cliente != null && funcion != null && fechaValida(fechaRealizada)
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
                && funcion.hayCapacidad(asientos.length);

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

    /** Agrega una boleta a una venta existente (buscándola por su código) */
    public boolean agregarBoletaAVenta(int codigoVenta, int fila, int numAsiento){
        boolean agregada = false;
        Venta venta = buscarVenta(codigoVenta);
        if(venta != null){
            agregada = agregarBoletaAVenta(venta, fila, numAsiento);
        }
        return agregada;
    }

    /** Agrega una boleta a la venta (si el asiento es válido y está libre en la función de la venta) */
    private boolean agregarBoletaAVenta(Venta venta, int fila, int numAsiento){
        boolean agregada = false;
        Funcion funcion = venta.getTheFuncion();

        if(funcion.asientoValido(fila, numAsiento)
                && !funcion.asientoOcupado(fila, numAsiento)
                && funcion.hayCapacidad(1)){

            double valor = funcion.calcularValorBoleta(venta.getTheCliente());
            Boleta boleta = new Boleta(consecutivoBoleta++, fila, numAsiento, valor);
            venta.agregarBoleta(boleta);
            funcion.agregarBoleta(boleta);   // así el asiento queda ocupado para esa función
            agregada = true;
        }
        return agregada;
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

    public List<Venta> consultarVentas(){
        return new ArrayList<>(listVentas);
    }

    /** Anula una venta: elimina la venta y libera los asientos de sus boletas */
    public boolean anularVenta(int codigoVenta){
        boolean anulada = false;
        Venta venta = buscarVenta(codigoVenta);
        if(venta != null){
            for(int i = 0; i < venta.getListBoletas().size(); i++){
                venta.getTheFuncion().quitarBoleta(venta.getListBoletas().get(i));
            }
            listVentas.remove(venta);
            anulada = true;
        }
        return anulada;
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



    public String getNombre()
    { return nombre; }

    public void setNombre(String nombre)
    { this.nombre = nombre; }

    public int getNit() { return nit; }

    public void setNit(int nit)
    { this.nit = nit; }

    public String getDireccion()
    { return direccion; }

    public void setDireccion(String direccion)
    { this.direccion = direccion; }

    public List<Sala> getListSalas()
    { return listSalas; }

    public void setListSalas(List<Sala> listSalas)
    { this.listSalas = listSalas; }

    public List<Pelicula> getListPeliculas()
    { return listPeliculas; }

    public void setListPeliculas(List<Pelicula> listPeliculas)
    { this.listPeliculas = listPeliculas; }


    public List<Funcion> getListFunciones()
    { return listFunciones; }

    public void setListFunciones(List<Funcion> listFunciones)
    { this.listFunciones = listFunciones; }

    public List<Cliente> getListClientes()
    { return listClientes; }

    public void setListClientes(List<Cliente> listClientes)
    { this.listClientes = listClientes; }

    public List<Operario> getListOperarios()
    { return listOperarios; }

    public void setListOperarios(List<Operario> listOperarios)
    { this.listOperarios = listOperarios; }

    public List<Venta> getListVentas()
    { return listVentas; }
    public void setListVentas(List<Venta> listVentas)
    { this.listVentas = listVentas; }

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