package co.edu.uniquindio.parcial2;

import model.*;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Menú interactivo de CinePlus con JOptionPane.
 * Esta clase solo pide datos y muestra resultados; las reglas del negocio viven en el paquete model.
 */
public class Main {

    private static final String TITULO = "CinePlus";
    private static final int TOPE = 999999999;   // máximo para códigos y documentos
    private static final boolean CARGAR_DATOS_DE_PRUEBA = false;   // true = inicia con datos de ejemplo, sin preguntar

    private static Cine cine;

    /** Se lanza cuando el usuario cierra o cancela un cuadro de diálogo en medio de una operación */
    private static class OperacionCancelada extends RuntimeException {
        private static final long serialVersionUID = 1L;

        OperacionCancelada() {
            super("Operación cancelada.");
        }

        OperacionCancelada(String mensaje) {
            super(mensaje);
        }
    }

    //INICIO

    public static void main(String[] args) {
        cine = new Cine("CinePlus", 900123456, "Armenia, Quindío");

        if (CARGAR_DATOS_DE_PRUEBA) {
            cargarDatosDePrueba();
        }
        menuPrincipal();
        mostrar("Gracias por usar " + cine.getNombre() + ". ¡Hasta pronto!");
    }

    private static void cargarDatosDePrueba() {
        String manana = LocalDate.now().plusDays(1).toString();

        cine.registrarSala(1, "Sala 1", 60, TipoSala.TRADICIONAL, 6, 10);
        cine.registrarSala(2, "Sala VIP", 30, TipoSala.VIP, 5, 6);
        cine.registrarSala(3, "Sala 3D", 40, TipoSala.SALA_3D, 5, 8);

        cine.registrarPelicula(1, "Aventura Estelar", 120, "12+", Genero.ACCION);
        cine.registrarPelicula(2, "Risas en Armenia", 95, "Todo público", Genero.COMEDIA);
        cine.registrarPelicula(3, "La Casa del Lago", 105, "18+", Genero.TERROR);
        cine.registrarPelicula(4, "Mundo Animado", 90, "Todo público", Genero.ANIMACION);

        cine.registrarOperario(1001, "Carlos Ramírez", 3101234567L, "carlos@cineplus.com", 1, "2024-03-15", 1800000);
        cine.registrarCliente("Ana Gómez", 2001, "ana@correo.com", 3209876543L, 1, 0.10);
        cine.registrarCliente("Luis Pérez", 2002, "luis@correo.com", 3001112233L, 2);

        cine.programarFuncion(1, manana, 1400, 15000, 1, 1);
        cine.programarFuncion(2, manana, 1700, 15000, 2, 1);
        cine.programarFuncion(3, manana, 2000, 15000, 3, 2);
        cine.programarFuncion(4, manana, 1500, 15000, 4, 3);
    }

    //MENUS

    private static void menuPrincipal() {
        String[] opciones = {"Películas", "Salas", "Funciones", "Clientes", "Operarios", "Ventas", "Salir"};
        boolean salir = false;
        while (!salir) {
            int op = elegirIndice("Menú principal - " + cine.getNombre() + "\nSeleccione una opción:", opciones);
            switch (op) {
                case 0: menuPeliculas(); break;
                case 1: menuSalas(); break;
                case 2: menuFunciones(); break;
                case 3: menuClientes(); break;
                case 4: menuOperarios(); break;
                case 5: menuVentas(); break;
                default: salir = true;
            }
        }
    }

    private static void menuPeliculas() {
        String[] opciones = {"Registrar película", "Buscar película", "Listar películas disponibles",
                "Actualizar película", "Eliminar película", "Volver"};
        boolean volver = false;
        while (!volver) {
            int op = elegirIndice("PELÍCULAS\nSeleccione una opción:", opciones);
            try {
                switch (op) {
                    case 0: registrarPelicula(); break;
                    case 1: buscarPelicula(); break;
                    case 2: listarPeliculas(); break;
                    case 3: actualizarPelicula(); break;
                    case 4: eliminarPelicula(); break;
                    default: volver = true;
                }
            } catch (OperacionCancelada e) {
                mostrar(e.getMessage());
            }
        }
    }

    private static void menuSalas() {
        String[] opciones = {"Registrar sala", "Buscar sala", "Listar salas",
                "Actualizar sala", "Eliminar sala", "Volver"};
        boolean volver = false;
        while (!volver) {
            int op = elegirIndice("SALAS\nSeleccione una opción:", opciones);
            try {
                switch (op) {
                    case 0: registrarSala(); break;
                    case 1: buscarSala(); break;
                    case 2: listarSalas(); break;
                    case 3: actualizarSala(); break;
                    case 4: eliminarSala(); break;
                    default: volver = true;
                }
            } catch (OperacionCancelada e) {
                mostrar(e.getMessage());
            }
        }
    }

    private static void menuFunciones() {
        String[] opciones = {"Programar función", "Buscar función", "Listar funciones",
                "Funciones de una película", "Ver mapa de asientos", "Calcular porcentaje de ocupación",
                "Actualizar función", "Eliminar función", "Volver"};
        boolean volver = false;
        while (!volver) {
            int op = elegirIndice("FUNCIONES\nSeleccione una opción:", opciones);
            try {
                switch (op) {
                    case 0: programarFuncion(); break;
                    case 1: buscarFuncion(); break;
                    case 2: listarFunciones(); break;
                    case 3: funcionesDeUnaPelicula(); break;
                    case 4: verMapaAsientos(); break;
                    case 5: calcularOcupacion(); break;
                    case 6: actualizarFuncion(); break;
                    case 7: eliminarFuncion(); break;
                    default: volver = true;
                }
            } catch (OperacionCancelada e) {
                mostrar(e.getMessage());
            }
        }
    }

    private static void menuClientes() {
        String[] opciones = {"Registrar cliente", "Buscar cliente", "Listar clientes",
                "Actualizar cliente", "Eliminar cliente", "Volver"};
        boolean volver = false;
        while (!volver) {
            int op = elegirIndice("CLIENTES\nSeleccione una opción:", opciones);
            try {
                switch (op) {
                    case 0: registrarCliente(); break;
                    case 1: buscarCliente(); break;
                    case 2: listarClientes(); break;
                    case 3: actualizarCliente(); break;
                    case 4: eliminarCliente(); break;
                    default: volver = true;
                }
            } catch (OperacionCancelada e) {
                mostrar(e.getMessage());
            }
        }
    }

    private static void menuOperarios() {
        String[] opciones = {"Registrar operario", "Buscar operario", "Listar operarios",
                "Actualizar operario", "Eliminar operario", "Volver"};
        boolean volver = false;
        while (!volver) {
            int op = elegirIndice("OPERARIOS\nSeleccione una opción:", opciones);
            try {
                switch (op) {
                    case 0: registrarOperario(); break;
                    case 1: buscarOperario(); break;
                    case 2: listarOperarios(); break;
                    case 3: actualizarOperario(); break;
                    case 4: eliminarOperario(); break;
                    default: volver = true;
                }
            } catch (OperacionCancelada e) {
                mostrar(e.getMessage());
            }
        }
    }

    private static void menuVentas() {
        String[] opciones = {"Realizar venta de boletas", "Buscar venta", "Calcular valor total de una venta",
                "Generar factura", "Agregar boleta a una venta", "Anular venta", "Listar ventas", "Volver"};
        boolean volver = false;
        while (!volver) {
            int op = elegirIndice("VENTAS\nSeleccione una opción:", opciones);
            try {
                switch (op) {
                    case 0: realizarVenta(); break;
                    case 1: buscarVenta(); break;
                    case 2: calcularValorTotalVenta(); break;
                    case 3: generarFactura(); break;
                    case 4: agregarBoletaAVenta(); break;
                    case 5: anularVenta(); break;
                    case 6: listarVentas(); break;
                    default: volver = true;
                }
            } catch (OperacionCancelada e) {
                mostrar(e.getMessage());
            }
        }
    }

    //PELICULAS

    private static void registrarPelicula() {
        int codigo = leerEntero("Código de la película:", 1, TOPE);
        if (cine.buscarPelicula(codigo) != null) {
            mostrar("Ya existe una película con el código " + codigo + ".");
            return;
        }
        String titulo = leerTexto("Título:");
        int duracion = leerEntero("Duración en minutos:", 1, 600);
        String clasificacion = elegirOpcion("Clasificación por edad:", clasificaciones(), "Todo público");
        Genero genero = elegirOpcion("Género:", Genero.values(), Genero.ACCION);

        if (cine.registrarPelicula(codigo, titulo, duracion, clasificacion, genero)) {
            mostrar("Película registrada correctamente.");
        } else {
            mostrar("No se pudo registrar la película.");
        }
    }

    private static void buscarPelicula() {
        int codigo = leerEntero("Código de la película a buscar:", 1, TOPE);
        Pelicula pelicula = cine.buscarPelicula(codigo);
        if (pelicula == null) {
            mostrar("No existe una película con el código " + codigo + ".");
        } else {
            mostrarTexto("Película encontrada", describir(pelicula));
        }
    }

    private static void listarPeliculas() {
        List<Pelicula> lista = cine.consultarPeliculasDisponibles();
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            lineas.add(describir(lista.get(i)));
        }
        mostrarLista("Películas disponibles", lineas, "No hay películas registradas.");
    }

    private static void actualizarPelicula() {
        int codigo = leerEntero("Código de la película a actualizar:", 1, TOPE);
        Pelicula pelicula = cine.buscarPelicula(codigo);
        if (pelicula == null) {
            mostrar("No existe una película con el código " + codigo + ".");
            return;
        }
        String titulo = leerTexto("Título:", pelicula.getTitulo());
        int duracion = pelicula.getDuracionMin();
        if (cine.peliculaTieneFunciones(codigo)) {
            mostrar("La duración no se puede modificar porque la película tiene funciones programadas.");
        } else {
            duracion = leerEntero("Duración en minutos:", 1, 600, String.valueOf(duracion));
        }
        String clasificacion = elegirOpcion("Clasificación por edad:", clasificaciones(), pelicula.getClasifEdad());
        Genero genero = elegirOpcion("Género:", Genero.values(), pelicula.getGeneroPeli());

        if (cine.actualizarPelicula(codigo, titulo, duracion, clasificacion, genero)) {
            mostrar("Película actualizada correctamente.");
        } else {
            mostrar("No se pudo actualizar la película.");
        }
    }

    private static void eliminarPelicula() {
        int codigo = leerEntero("Código de la película a eliminar:", 1, TOPE);
        Pelicula pelicula = cine.buscarPelicula(codigo);
        if (pelicula == null) {
            mostrar("No existe una película con el código " + codigo + ".");
        } else if (cine.peliculaTieneFunciones(codigo)) {
            mostrar("No se puede eliminar: la película tiene funciones programadas.\nElimine primero sus funciones.");
        } else if (confirmar("¿Eliminar la película?\n" + describir(pelicula))) {
            cine.eliminarPelicula(codigo);
            mostrar("Película eliminada.");
        }
    }

    //SALAS

    private static void registrarSala() {
        int codigo = leerEntero("Código de la sala:", 1, TOPE);
        if (cine.buscarSala(codigo) != null) {
            mostrar("Ya existe una sala con el código " + codigo + ".");
            return;
        }
        String nombre = leerTexto("Nombre de la sala:");
        TipoSala tipo = elegirOpcion("Tipo de sala:", TipoSala.values(), TipoSala.TRADICIONAL);
        int filas = leerEntero("Número de filas:", 1, 50);
        int columnas = leerEntero("Número de columnas (asientos por fila):", 1, 50);
        int maxima = filas * columnas;
        int capacidad = leerEntero("Capacidad máxima de espectadores (hasta " + maxima + "):", 1, maxima, String.valueOf(maxima));

        if (cine.registrarSala(codigo, nombre, capacidad, tipo, filas, columnas)) {
            mostrar("Sala registrada correctamente.");
        } else {
            mostrar("No se pudo registrar la sala.");
        }
    }

    private static void buscarSala() {
        int codigo = leerEntero("Código de la sala a buscar:", 1, TOPE);
        Sala sala = cine.buscarSala(codigo);
        if (sala == null) {
            mostrar("No existe una sala con el código " + codigo + ".");
        } else {
            mostrarTexto("Sala encontrada", describir(sala));
        }
    }

    private static void listarSalas() {
        List<Sala> lista = cine.consultarSalas();
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            lineas.add(describir(lista.get(i)));
        }
        mostrarLista("Salas del complejo", lineas, "No hay salas registradas.");
    }

    private static void actualizarSala() {
        int codigo = leerEntero("Código de la sala a actualizar:", 1, TOPE);
        Sala sala = cine.buscarSala(codigo);
        if (sala == null) {
            mostrar("No existe una sala con el código " + codigo + ".");
            return;
        }
        String nombre = leerTexto("Nombre de la sala:", sala.getNombre());
        TipoSala tipo = elegirOpcion("Tipo de sala:", TipoSala.values(), sala.getTipo());
        int filas = sala.getFilas();
        int columnas = sala.getColumnas();
        int capacidad = sala.getCapMax();

        if (cine.salaTieneBoletasVendidas(codigo)) {
            mostrar("La sala tiene boletas vendidas: solo se modifican el nombre y el tipo.\n"
                    + "Las filas, columnas y capacidad no se pueden cambiar.");
        } else {
            filas = leerEntero("Número de filas:", 1, 50, String.valueOf(filas));
            columnas = leerEntero("Número de columnas:", 1, 50, String.valueOf(columnas));
            int maxima = filas * columnas;
            capacidad = leerEntero("Capacidad máxima (hasta " + maxima + "):", 1, maxima,
                    String.valueOf(Math.min(capacidad, maxima)));
        }

        if (cine.actualizarSala(codigo, nombre, capacidad, tipo, filas, columnas)) {
            mostrar("Sala actualizada correctamente.");
        } else {
            mostrar("No se pudo actualizar la sala.");
        }
    }

    private static void eliminarSala() {
        int codigo = leerEntero("Código de la sala a eliminar:", 1, TOPE);
        Sala sala = cine.buscarSala(codigo);
        if (sala == null) {
            mostrar("No existe una sala con el código " + codigo + ".");
        } else if (cine.salaTieneFunciones(codigo)) {
            mostrar("No se puede eliminar: la sala tiene funciones programadas.\nElimine primero sus funciones.");
        } else if (confirmar("¿Eliminar la sala?\n" + describir(sala))) {
            cine.eliminarSala(codigo);
            mostrar("Sala eliminada.");
        }
    }

    //FUNCIONES

    private static void programarFuncion() {
        int codigo = leerEntero("Código de la función:", 1, TOPE);
        if (cine.buscarFuncion(codigo) != null) {
            mostrar("Ya existe una función con el código " + codigo + ".");
            return;
        }
        Pelicula pelicula = elegirPelicula();
        Sala sala = elegirSala();
        String fecha = leerFecha("Fecha de presentación (yyyy-MM-dd):", LocalDate.now().plusDays(1).toString());
        int hora = leerHora("Hora de inicio (HHmm, ej: 1430 = 2:30 p.m.):", "1400");
        double precio = leerDouble("Precio base de la boleta:", 1, 1000000, "15000");

        if (cine.programarFuncion(codigo, fecha, hora, precio, pelicula.getPeliCode(), sala.getCodigoSala())) {
            Funcion funcion = cine.buscarFuncion(codigo);
            mostrar("Función programada correctamente.\nTermina a las " + formatoHora(funcion.getHoraFin()) + ".");
        } else {
            mostrar("No se pudo programar la función: en la " + sala.getNombre()
                    + " ya hay otra función que se cruza con ese horario.\n"
                    + "(Se tiene en cuenta la duración de la película: " + pelicula.getDuracionMin() + " min.)");
        }
    }

    private static void buscarFuncion() {
        int codigo = leerEntero("Código de la función a buscar:", 1, TOPE);
        Funcion funcion = cine.buscarFuncion(codigo);
        if (funcion == null) {
            mostrar("No existe una función con el código " + codigo + ".");
        } else {
            mostrarTexto("Función encontrada", describir(funcion));
        }
    }

    private static void listarFunciones() {
        List<Funcion> lista = cine.consultarFunciones();
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            lineas.add(describir(lista.get(i)));
        }
        mostrarLista("Funciones programadas", lineas, "No hay funciones programadas.");
    }

    private static void funcionesDeUnaPelicula() {
        Pelicula pelicula = elegirPelicula();
        List<Funcion> lista = cine.consultarFuncionesPorPelicula(pelicula.getPeliCode());
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            lineas.add(describir(lista.get(i)));
        }
        mostrarLista("Funciones de " + pelicula.getTitulo(), lineas, "La película no tiene funciones programadas.");
    }

    private static void verMapaAsientos() {
        Funcion funcion = elegirFuncion();
        mostrarTexto("Asientos - " + resumenFuncion(funcion), funcion.mapaAsientos()
                + "\n[X] ocupado   [ ] libre");
    }

    private static void calcularOcupacion() {
        Funcion funcion = elegirFuncion();
        double ocupacion = cine.calcularPorcentajeOcupacion(funcion.getUnicoCodigo());
        mostrar(resumenFuncion(funcion) + "\n\nBoletas vendidas: " + funcion.getBoletasVendidas()
                + " de " + funcion.getTheSala().getCapMax()
                + "\nPorcentaje de ocupación: " + String.format("%.2f", ocupacion) + " %");
    }

    private static void actualizarFuncion() {
        Funcion funcion = elegirFuncion();
        String fecha = funcion.getFechaPresentacion();
        int hora = funcion.getHoraInicio();

        if (funcion.getBoletasVendidas() > 0) {
            mostrar("La función ya tiene boletas vendidas: solo se puede cambiar el precio base.\n"
                    + "El nuevo precio aplica para las ventas futuras.");
        } else {
            fecha = leerFecha("Fecha de presentación (yyyy-MM-dd):", fecha);
            hora = leerHora("Hora de inicio (HHmm):", String.valueOf(hora));
        }
        double precio = leerDouble("Precio base de la boleta:", 1, 1000000, formatoNumero(funcion.getPrecioBoleta()));

        if (cine.actualizarFuncion(funcion.getUnicoCodigo(), fecha, hora, precio)) {
            mostrar("Función actualizada correctamente.");
        } else {
            mostrar("No se pudo actualizar: el nuevo horario se cruza con otra función de la misma sala.");
        }
    }

    private static void eliminarFuncion() {
        Funcion funcion = elegirFuncion();
        if (funcion.getBoletasVendidas() > 0) {
            mostrar("No se puede eliminar: la función tiene " + funcion.getBoletasVendidas()
                    + " boletas vendidas.\nAnule primero las ventas correspondientes.");
        } else if (confirmar("¿Eliminar la función?\n" + describir(funcion))) {
            cine.eliminarFuncion(funcion.getUnicoCodigo());
            mostrar("Función eliminada.");
        }
    }

    //CLIENTES

    private static void registrarCliente() {
        int documento = leerEntero("Documento de identidad:", 1, TOPE);
        if (cine.buscarPersona(documento) != null) {
            mostrar("Ya existe una persona (cliente u operario) con el documento " + documento + ".");
            return;
        }
        String nombre = leerTexto("Nombre completo:");
        String correo = leerCorreo("Correo electrónico:", "");
        long telefono = leerLong("Número de teléfono:", 1000000L, 9999999999L, "");
        int afiliacion = leerEntero("Código de afiliación:", 1, TOPE);
        double descuento = leerPorcentaje("Porcentaje de descuento (0 a 100):", "0");

        if (cine.registrarCliente(nombre, documento, correo, telefono, afiliacion, descuento)) {
            mostrar("Cliente registrado correctamente.");
        } else {
            mostrar("No se pudo registrar el cliente.");
        }
    }

    private static void buscarCliente() {
        int documento = leerEntero("Documento del cliente a buscar:", 1, TOPE);
        Cliente cliente = cine.buscarCliente(documento);
        if (cliente == null) {
            mostrar("No existe un cliente con el documento " + documento + ".");
        } else {
            mostrarTexto("Cliente encontrado", describir(cliente));
        }
    }

    private static void listarClientes() {
        List<Cliente> lista = cine.consultarClientes();
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            lineas.add(describir(lista.get(i)));
        }
        mostrarLista("Clientes", lineas, "No hay clientes registrados.");
    }

    private static void actualizarCliente() {
        int documento = leerEntero("Documento del cliente a actualizar:", 1, TOPE);
        Cliente cliente = cine.buscarCliente(documento);
        if (cliente == null) {
            mostrar("No existe un cliente con el documento " + documento + ".");
            return;
        }
        String nombre = leerTexto("Nombre completo:", cliente.getNombreCompleto());
        String correo = leerCorreo("Correo electrónico:", cliente.getCorreo());
        long telefono = leerLong("Número de teléfono:", 1000000L, 9999999999L, String.valueOf(cliente.getNumTelefono()));
        int afiliacion = leerEntero("Código de afiliación:", 1, TOPE, String.valueOf(cliente.getCodeAfiliacion()));
        double descuento = leerPorcentaje("Porcentaje de descuento (0 a 100):", porcentajeTexto(cliente.getDescuento()));

        if (cine.actualizarCliente(documento, nombre, telefono, correo, afiliacion, descuento)) {
            mostrar("Cliente actualizado correctamente.");
        } else {
            mostrar("No se pudo actualizar el cliente.");
        }
    }

    private static void eliminarCliente() {
        int documento = leerEntero("Documento del cliente a eliminar:", 1, TOPE);
        Cliente cliente = cine.buscarCliente(documento);
        if (cliente == null) {
            mostrar("No existe un cliente con el documento " + documento + ".");
        } else if (cine.clienteTieneVentas(documento)) {
            mostrar("No se puede eliminar: el cliente tiene ventas registradas.");
        } else if (confirmar("¿Eliminar al cliente?\n" + describir(cliente))) {
            cine.eliminarCliente(documento);
            mostrar("Cliente eliminado.");
        }
    }

    //OPERARIOS

    private static void registrarOperario() {
        int codEmpleado = leerEntero("Código de empleado:", 1, TOPE);
        if (cine.buscarOperario(codEmpleado) != null) {
            mostrar("Ya existe un operario con el código de empleado " + codEmpleado + ".");
            return;
        }
        int documento = leerEntero("Documento de identidad:", 1, TOPE);
        if (cine.buscarPersona(documento) != null) {
            mostrar("Ya existe una persona (cliente u operario) con el documento " + documento + ".");
            return;
        }
        String nombre = leerTexto("Nombre completo:");
        String correo = leerCorreo("Correo electrónico:", "");
        long telefono = leerLong("Número de teléfono:", 1000000L, 9999999999L, "");
        String fecha = leerFecha("Fecha de contratación (yyyy-MM-dd):", LocalDate.now().toString());
        double salario = leerDouble("Salario básico:", 1, 100000000, "1800000");

        if (cine.registrarOperario(documento, nombre, telefono, correo, codEmpleado, fecha, salario)) {
            mostrar("Operario registrado correctamente.");
        } else {
            mostrar("No se pudo registrar el operario.");
        }
    }

    private static void buscarOperario() {
        int codEmpleado = leerEntero("Código de empleado a buscar:", 1, TOPE);
        Operario operario = cine.buscarOperario(codEmpleado);
        if (operario == null) {
            mostrar("No existe un operario con el código " + codEmpleado + ".");
        } else {
            mostrarTexto("Operario encontrado", describir(operario));
        }
    }

    private static void listarOperarios() {
        List<Operario> lista = cine.consultarOperarios();
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            lineas.add(describir(lista.get(i)));
        }
        mostrarLista("Operarios", lineas, "No hay operarios registrados.");
    }

    private static void actualizarOperario() {
        int codEmpleado = leerEntero("Código de empleado a actualizar:", 1, TOPE);
        Operario operario = cine.buscarOperario(codEmpleado);
        if (operario == null) {
            mostrar("No existe un operario con el código " + codEmpleado + ".");
            return;
        }
        String nombre = leerTexto("Nombre completo:", operario.getNombreCompleto());
        String correo = leerCorreo("Correo electrónico:", operario.getCorreo());
        long telefono = leerLong("Número de teléfono:", 1000000L, 9999999999L, String.valueOf(operario.getNumTelefono()));
        String fecha = leerFecha("Fecha de contratación (yyyy-MM-dd):", operario.getFechaContratacion());
        double salario = leerDouble("Salario básico:", 1, 100000000, formatoNumero(operario.getSalario()));

        if (cine.actualizarOperario(codEmpleado, nombre, telefono, correo, fecha, salario)) {
            mostrar("Operario actualizado correctamente.");
        } else {
            mostrar("No se pudo actualizar el operario.");
        }
    }

    private static void eliminarOperario() {
        int codEmpleado = leerEntero("Código de empleado a eliminar:", 1, TOPE);
        Operario operario = cine.buscarOperario(codEmpleado);
        if (operario == null) {
            mostrar("No existe un operario con el código " + codEmpleado + ".");
        } else if (cine.operarioTieneVentas(codEmpleado)) {
            mostrar("No se puede eliminar: el operario tiene ventas registradas.");
        } else if (confirmar("¿Eliminar al operario?\n" + describir(operario))) {
            cine.eliminarOperario(codEmpleado);
            mostrar("Operario eliminado.");
        }
    }

    //VENTAS

    private static void realizarVenta() {
        int codigoVenta = leerEntero("Código de la venta:", 1, TOPE);
        if (cine.buscarVenta(codigoVenta) != null) {
            mostrar("Ya existe una venta con el código " + codigoVenta + ".");
            return;
        }
        int codEmpleado = leerEntero("Código de empleado del operario que registra la venta:", 1, TOPE);
        if (cine.buscarOperario(codEmpleado) == null) {
            mostrar("No existe un operario con el código " + codEmpleado + ".");
            return;
        }
        int documento = leerEntero("Documento del cliente:", 1, TOPE);
        Cliente cliente = cine.buscarCliente(documento);
        if (cliente == null) {
            mostrar("No existe un cliente con el documento " + documento + ". Regístrelo primero.");
            return;
        }
        Funcion funcion = elegirFuncion();
        int disponibles = funcion.getTheSala().getCapMax() - funcion.getBoletasVendidas();
        if (disponibles <= 0) {
            mostrar("La función está agotada.");
            return;
        }

        mostrarTexto("Asientos - " + resumenFuncion(funcion), funcion.mapaAsientos() + "\n[X] ocupado   [ ] libre");
        int cantidad = leerEntero("¿Cuántas boletas desea comprar? (máximo " + disponibles + ")", 1, disponibles);
        int[][] asientos = pedirAsientos(funcion, cantidad);
        String fecha = leerFecha("Fecha de la venta (yyyy-MM-dd):", LocalDate.now().toString());

        double valorBoleta = funcion.calcularValorBoleta(cliente);
        String resumen = "Película: " + funcion.getThePelicula().getTitulo()
                + "\nSala: " + funcion.getTheSala().getNombre() + " (" + funcion.getTheSala().getTipo() + ")"
                + "\nBoletas: " + cantidad
                + "\nValor por boleta: " + dinero(valorBoleta)
                + "\nTotal a pagar: " + dinero(valorBoleta * cantidad)
                + "\n\n¿Confirmar la venta?";
        if (!confirmar(resumen)) {
            throw new OperacionCancelada();
        }

        if (cine.realizarVenta(codigoVenta, fecha, codEmpleado, documento, funcion.getUnicoCodigo(), asientos)) {
            mostrarTexto("Venta realizada", textoFactura(codigoVenta));
        } else {
            mostrar("No se pudo realizar la venta.");
        }
    }

    /** Pide fila y asiento de cada boleta, validando que existan, estén libres y no se repitan */
    private static int[][] pedirAsientos(Funcion funcion, int cantidad) {
        int filas = funcion.getTheSala().getFilas();
        int columnas = funcion.getTheSala().getColumnas();
        int[][] asientos = new int[cantidad][2];

        for (int i = 0; i < cantidad; i++) {
            boolean valido = false;
            while (!valido) {
                String etiqueta = "Boleta " + (i + 1) + " de " + cantidad + "\n";
                int fila = leerEntero(etiqueta + "Fila (1 a " + filas + "):", 1, filas);
                int numero = leerEntero(etiqueta + "Número de asiento (1 a " + columnas + "):", 1, columnas);

                if (funcion.asientoOcupado(fila, numero)) {
                    mostrar("El asiento F" + fila + "-" + numero + " ya está ocupado. Elija otro.");
                } else if (asientoRepetido(asientos, i, fila, numero)) {
                    mostrar("Ya eligió el asiento F" + fila + "-" + numero + " en esta venta. Elija otro.");
                } else {
                    asientos[i][0] = fila;
                    asientos[i][1] = numero;
                    valido = true;
                }
            }
        }
        return asientos;
    }

    private static boolean asientoRepetido(int[][] asientos, int hasta, int fila, int numero) {
        boolean repetido = false;
        for (int j = 0; j < hasta; j++) {
            if (asientos[j][0] == fila && asientos[j][1] == numero) {
                repetido = true;
                break;
            }
        }
        return repetido;
    }

    private static void buscarVenta() {
        int codigo = leerEntero("Código de la venta a buscar:", 1, TOPE);
        Venta venta = cine.buscarVenta(codigo);
        if (venta == null) {
            mostrar("No existe una venta con el código " + codigo + ".");
        } else {
            mostrarTexto("Venta encontrada", describir(venta));
        }
    }

    private static void calcularValorTotalVenta() {
        int codigo = leerEntero("Código de la venta:", 1, TOPE);
        double total = cine.calcularValorTotalVenta(codigo);
        if (total < 0) {
            mostrar("No existe una venta con el código " + codigo + ".");
        } else {
            Venta venta = cine.buscarVenta(codigo);
            mostrar("Venta " + codigo + " (" + venta.getListBoletas().size() + " boletas)\n"
                    + "Valor total: " + dinero(total));
        }
    }

    private static void generarFactura() {
        int codigo = leerEntero("Código de la venta:", 1, TOPE);
        if (cine.buscarVenta(codigo) == null) {
            mostrar("No existe una venta con el código " + codigo + ".");
        } else {
            mostrarTexto("Factura", textoFactura(codigo));
        }
    }

    private static void agregarBoletaAVenta() {
        int codigo = leerEntero("Código de la venta:", 1, TOPE);
        Venta venta = cine.buscarVenta(codigo);
        if (venta == null) {
            mostrar("No existe una venta con el código " + codigo + ".");
            return;
        }
        Funcion funcion = venta.getTheFuncion();
        if (!funcion.hayCapacidad(1)) {
            mostrar("La función está agotada.");
            return;
        }
        mostrarTexto("Asientos - " + resumenFuncion(funcion), funcion.mapaAsientos() + "\n[X] ocupado   [ ] libre");
        int fila = leerEntero("Fila (1 a " + funcion.getTheSala().getFilas() + "):", 1, funcion.getTheSala().getFilas());
        int numero = leerEntero("Número de asiento (1 a " + funcion.getTheSala().getColumnas() + "):", 1, funcion.getTheSala().getColumnas());

        if (cine.agregarBoletaAVenta(codigo, fila, numero)) {
            Boleta boleta = venta.getListBoletas().get(venta.getListBoletas().size() - 1);
            mostrar("Boleta agregada: F" + fila + "-" + numero + " por " + dinero(boleta.getValor())
                    + "\nNuevo total de la venta: " + dinero(venta.calcularValorTotal()));
        } else {
            mostrar("No se pudo agregar la boleta: el asiento F" + fila + "-" + numero + " ya está ocupado.");
        }
    }

    private static void anularVenta() {
        int codigo = leerEntero("Código de la venta a anular:", 1, TOPE);
        Venta venta = cine.buscarVenta(codigo);
        if (venta == null) {
            mostrar("No existe una venta con el código " + codigo + ".");
        } else if (confirmar("¿Anular la venta? Los asientos quedarán libres.\n\n" + describir(venta))) {
            cine.anularVenta(codigo);
            mostrar("Venta anulada. Los asientos fueron liberados.");
        }
    }

    private static void listarVentas() {
        List<Venta> lista = cine.consultarVentas();
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            lineas.add(describir(lista.get(i)) + "\n");
        }
        mostrarLista("Ventas realizadas", lineas, "No hay ventas registradas.");
    }

    //TEXTOS PARA MOSTRAR

    private static String describir(Pelicula p) {
        return "[" + p.getPeliCode() + "] " + p.getTitulo() + " | " + p.getDuracionMin() + " min | "
                + p.getGeneroPeli() + " | " + p.getClasifEdad();
    }

    private static String describir(Sala s) {
        return "[" + s.getCodigoSala() + "] " + s.getNombre() + " | " + s.getTipo()
                + " (recargo " + porcentajeTexto(s.calcularRecargo()) + " %) | capacidad " + s.getCapMax()
                + " | " + s.getFilas() + " filas x " + s.getColumnas() + " columnas";
    }

    private static String describir(Funcion f) {
        return "[" + f.getUnicoCodigo() + "] " + f.getThePelicula().getTitulo() + " | " + f.getFechaPresentacion()
                + " " + formatoHora(f.getHoraInicio()) + " - " + formatoHora(f.getHoraFin())
                + " | " + f.getTheSala().getNombre() + " | base " + dinero(f.getPrecioBoleta())
                + " | vendidas " + f.getBoletasVendidas() + "/" + f.getTheSala().getCapMax();
    }

    private static String describir(Cliente c) {
        return "Doc " + c.getDocumento() + " | " + c.getNombreCompleto() + " | tel " + c.getNumTelefono()
                + " | " + c.getCorreo() + " | afiliación " + c.getCodeAfiliacion()
                + " | descuento " + porcentajeTexto(c.getDescuento()) + " %";
    }

    private static String describir(Operario o) {
        return "Cód " + o.getCodEmpleado() + " | Doc " + o.getDocumento() + " | " + o.getNombreCompleto()
                + " | tel " + o.getNumTelefono() + " | " + o.getCorreo()
                + " | contratado " + o.getFechaContratacion() + " | salario " + dinero(o.getSalario());
    }

    private static String describir(Venta v) {
        StringBuilder texto = new StringBuilder();
        texto.append("Venta ").append(v.getCodigoVenta()).append(" | ").append(v.getFechaRealizada()).append("\n");
        texto.append("Cliente: ").append(v.getTheCliente().getNombreCompleto())
                .append(" (doc ").append(v.getTheCliente().getDocumento()).append(")\n");
        texto.append("Operario: ").append(v.getTheOperario().getNombreCompleto()).append("\n");
        texto.append("Función: ").append(resumenFuncion(v.getTheFuncion())).append("\n");
        texto.append("Boletas:\n");
        for (int i = 0; i < v.getListBoletas().size(); i++) {
            Boleta b = v.getListBoletas().get(i);
            texto.append("   #").append(b.getCodigoBoleta()).append(" | fila ").append(b.getFila())
                    .append(", asiento ").append(b.getNumAsiento()).append(" | ").append(dinero(b.getValor())).append("\n");
        }
        texto.append("Total: ").append(dinero(v.calcularValorTotal()));
        return texto.toString();
    }

    private static String resumenFuncion(Funcion f) {
        return "[" + f.getUnicoCodigo() + "] " + f.getThePelicula().getTitulo() + " - " + f.getFechaPresentacion()
                + " " + formatoHora(f.getHoraInicio()) + " - " + f.getTheSala().getNombre();
    }

    private static String textoFactura(int codigoVenta) {
        Factura f = cine.generarFactura(codigoVenta);
        return "FACTURA DE VENTA - " + cine.getNombre() + "\n"
                + "NIT: " + cine.getNit() + " | " + cine.getDireccion() + "\n"
                + "------------------------------------------\n"
                + "Venta N.º: " + f.codigoVenta() + "\n"
                + "Fecha: " + f.fechaRealizada() + "\n"
                + "Cliente: " + f.nombreCliente() + "\n"
                + "Atendido por: " + f.nombreOperario() + "\n"
                + "Película: " + f.tituloPelicula() + "\n"
                + "Cantidad de boletas: " + f.cantidadBoletas() + "\n"
                + "------------------------------------------\n"
                + "VALOR TOTAL: " + dinero(f.valorTotal());
    }

    private static String dinero(double valor) {
        return String.format("$%,.2f", valor);
    }

    private static String formatoHora(int hhmm) {
        return String.format("%02d:%02d", hhmm / 100, hhmm % 100);
    }

    /** 15000.0 -> "15000" (para mostrar como valor inicial en los cuadros de texto) */
    private static String formatoNumero(double valor) {
        String texto = String.valueOf(valor);
        if (texto.endsWith(".0")) {
            texto = texto.substring(0, texto.length() - 2);
        }
        return texto;
    }

    /** 0.10 -> "10", 0.075 -> "7.5" */
    private static String porcentajeTexto(double fraccion) {
        return formatoNumero(Math.round(fraccion * 10000) / 100.0);
    }

    private static String[] clasificaciones() {
        return new String[]{"Todo público", "7+", "12+", "15+", "18+"};
    }

    // SELLECION, no se hizo con opcion de número como en el parcial pasado, se obtó por usar desplegables

    private static Pelicula elegirPelicula() {
        List<Pelicula> lista = cine.consultarPeliculasDisponibles();
        if (lista.isEmpty()) {
            throw new OperacionCancelada("No hay películas registradas. Registre una primero.");
        }
        String[] textos = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            textos[i] = lista.get(i).getPeliCode() + " - " + lista.get(i).getTitulo();
        }
        int indice = elegirIndice("Seleccione la película:", textos);
        if (indice < 0) {
            throw new OperacionCancelada();
        }
        return lista.get(indice);
    }

    private static Sala elegirSala() {
        List<Sala> lista = cine.consultarSalas();
        if (lista.isEmpty()) {
            throw new OperacionCancelada("No hay salas registradas. Registre una primero.");
        }
        String[] textos = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            textos[i] = lista.get(i).getCodigoSala() + " - " + lista.get(i).getNombre() + " (" + lista.get(i).getTipo() + ")";
        }
        int indice = elegirIndice("Seleccione la sala:", textos);
        if (indice < 0) {
            throw new OperacionCancelada();
        }
        return lista.get(indice);
    }

    private static Funcion elegirFuncion() {
        List<Funcion> lista = cine.consultarFunciones();
        if (lista.isEmpty()) {
            throw new OperacionCancelada("No hay funciones programadas. Programe una primero.");
        }
        String[] textos = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            textos[i] = resumenFuncion(lista.get(i));
        }
        int indice = elegirIndice("Seleccione la función:", textos);
        if (indice < 0) {
            throw new OperacionCancelada();
        }
        return lista.get(indice);
    }

    // datos joption pane

    /** Lista desplegable. Retorna el índice elegido o -1 si el usuario cancela */
    private static int elegirIndice(String mensaje, String[] opciones) {
        Object elegida = JOptionPane.showInputDialog(null, mensaje, TITULO,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        int indice = -1;
        for (int i = 0; elegida != null && i < opciones.length; i++) {
            if (opciones[i].equals(elegida)) {
                indice = i;
                break;
            }
        }
        return indice;
    }

    /** Lista desplegable de cualquier tipo de dato (enums, textos). Cancela la operación si el usuario cierra */
    private static <T> T elegirOpcion(String mensaje, T[] valores, T inicial) {
        Object elegida = JOptionPane.showInputDialog(null, mensaje, TITULO,
                JOptionPane.QUESTION_MESSAGE, null, valores, inicial);
        if (elegida == null) {
            throw new OperacionCancelada();
        }
        @SuppressWarnings("unchecked")
        T resultado = (T) elegida;
        return resultado;
    }

    private static String pedir(String mensaje, String inicial) {
        Object respuesta = JOptionPane.showInputDialog(null, mensaje, TITULO,
                JOptionPane.QUESTION_MESSAGE, null, null, inicial);
        if (respuesta == null) {
            throw new OperacionCancelada();
        }
        return respuesta.toString().trim();
    }

    private static String leerTexto(String mensaje) {
        return leerTexto(mensaje, "");
    }

    private static String leerTexto(String mensaje, String inicial) {
        String texto = "";
        while (texto.isEmpty()) {
            texto = pedir(mensaje, inicial);
            if (texto.isEmpty()) {
                mostrar("Este campo no puede estar vacío.");
            }
        }
        return texto;
    }

    private static int leerEntero(String mensaje, int min, int max) {
        return leerEntero(mensaje, min, max, "");
    }

    private static int leerEntero(String mensaje, int min, int max, String inicial) {
        Integer valor = null;
        while (valor == null) {
            String texto = pedir(mensaje, inicial);
            try {
                int numero = Integer.parseInt(texto);
                if (numero < min || numero > max) {
                    mostrar("Ingrese un número entre " + min + " y " + max + ".");
                } else {
                    valor = numero;
                }
            } catch (NumberFormatException e) {
                mostrar("Ingrese un número entero válido.");
            }
        }
        return valor;
    }

    private static long leerLong(String mensaje, long min, long max, String inicial) {
        Long valor = null;
        while (valor == null) {
            String texto = pedir(mensaje, inicial);
            try {
                long numero = Long.parseLong(texto);
                if (numero < min || numero > max) {
                    mostrar("Ingrese un número entre " + min + " y " + max + ".");
                } else {
                    valor = numero;
                }
            } catch (NumberFormatException e) {
                mostrar("Ingrese solo dígitos, sin espacios ni signos.");
            }
        }
        return valor;
    }

    private static double leerDouble(String mensaje, double min, double max, String inicial) {
        Double valor = null;
        while (valor == null) {
            String texto = pedir(mensaje, inicial).replace(',', '.');
            try {
                double numero = Double.parseDouble(texto);
                if (numero < min || numero > max) {
                    mostrar("Ingrese un valor entre " + formatoNumero(min) + " y " + formatoNumero(max) + ".");
                } else {
                    valor = numero;
                }
            } catch (NumberFormatException e) {
                mostrar("Ingrese un número válido (sin puntos de miles).");
            }
        }
        return valor;
    }

    /** Pide un porcentaje de 0 a 100 y lo retorna como fracción (10 -> 0.10) */
    private static double leerPorcentaje(String mensaje, String inicial) {
        return leerDouble(mensaje, 0, 100, inicial) / 100;
    }

    private static String leerFecha(String mensaje, String inicial) {
        String fecha = null;
        while (fecha == null) {
            String texto = pedir(mensaje, inicial);
            try {
                fecha = LocalDate.parse(texto).toString();
            } catch (DateTimeParseException e) {
                mostrar("Fecha no válida. Use el formato yyyy-MM-dd, por ejemplo 2026-10-25.");
            }
        }
        return fecha;
    }

    /** Hora en formato HHmm (1430 = 2:30 p.m.) */
    private static int leerHora(String mensaje, String inicial) {
        Integer hora = null;
        while (hora == null) {
            String texto = pedir(mensaje, inicial);
            try {
                int numero = Integer.parseInt(texto);
                if (numero < 0 || numero > 2359 || numero % 100 > 59) {
                    mostrar("Hora no válida. Use el formato HHmm entre 0000 y 2359, por ejemplo 1430.");
                } else {
                    hora = numero;
                }
            } catch (NumberFormatException e) {
                mostrar("Hora no válida. Use solo números, por ejemplo 1430.");
            }
        }
        return hora;
    }

    private static String leerCorreo(String mensaje, String inicial) {
        String correo = null;
        while (correo == null) {
            String texto = leerTexto(mensaje, inicial);
            if (texto.contains("@") && texto.indexOf('@') > 0 && texto.indexOf('@') < texto.length() - 1 && !texto.contains(" ")) {
                correo = texto;
            } else {
                mostrar("Correo no válido. Ejemplo: nombre@correo.com");
            }
        }
        return correo;
    }

    //salida

    private static void mostrar(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, TITULO, JOptionPane.INFORMATION_MESSAGE);
    }

    private static boolean confirmar(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, TITULO,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }

    /** Muestra un texto de varias líneas en una letra de ancho fijo (sirve para el mapa de asientos) */
    private static void mostrarTexto(String titulo, String texto) {
        String[] lineas = texto.split("\n");
        int ancho = 30;
        for (int i = 0; i < lineas.length; i++) {
            ancho = Math.max(ancho, lineas[i].length());
        }
        JTextArea area = new JTextArea(texto);
        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        area.setRows(Math.min(lineas.length + 1, 20));
        area.setColumns(Math.min(ancho + 2, 110));
        area.setCaretPosition(0);
        JOptionPane.showMessageDialog(null, new JScrollPane(area), titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarLista(String titulo, List<String> lineas, String mensajeVacio) {
        if (lineas.isEmpty()) {
            mostrar(mensajeVacio);
        } else {
            StringBuilder texto = new StringBuilder();
            for (int i = 0; i < lineas.size(); i++) {
                texto.append(lineas.get(i)).append("\n");
            }
            texto.append("\nTotal: ").append(lineas.size());
            mostrarTexto(titulo, texto.toString());
        }
    }
}