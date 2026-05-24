package com.cipa.nomina;

import com.cipa.nomina.exception.NominaException;
import com.cipa.nomina.model.Empleado;
import com.cipa.nomina.model.EmpleadoAsalariado;
import com.cipa.nomina.model.EmpleadoPorComision;
import com.cipa.nomina.model.EmpleadoPorHoras;
import com.cipa.nomina.model.EmpleadoTemporal;
import com.cipa.nomina.service.CalculadoraNomina;
import com.cipa.nomina.service.ResultadoNomina;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CalculadoraNomina calculadoraNomina = new CalculadoraNomina();
    private static final NumberFormat formatoMoneda =
            NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    public static void main(String[] args) {
        boolean continuar = true;

        System.out.println();
        System.out.println("        SISTEMA DE NOMINA CIPA SOLDIERS       ");
        System.out.println();

        while (continuar) {
            mostrarMenu();

            int opcion = leerEntero("Seleccione una opcion: ");

            try {
                switch (opcion) {
                    case 1 -> registrarEmpleadoAsalariado();
                    case 2 -> registrarEmpleadoPorHoras();
                    case 3 -> registrarEmpleadoPorComision();
                    case 4 -> registrarEmpleadoTemporal();
                    case 5 -> {
                        continuar = false;
                        System.out.println("Sistema finalizado correctamente.");
                    }
                    default -> System.out.println("Opcion no valida. Intente nuevamente.");
                }
            } catch (NominaException | DateTimeParseException e) {
                System.out.println("Error: " + e.getMessage());
            }

            if (continuar) {
                System.out.println();
                System.out.println("Presione ENTER para continuar...");
                scanner.nextLine();
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println(" MENU PRINCIPAL");
        System.out.println("1. Calcular nomina de empleado asalariado");
        System.out.println("2. Calcular nomina de empleado por horas");
        System.out.println("3. Calcular nomina de empleado por comision");
        System.out.println("4. Calcular nomina de empleado temporal");
        System.out.println("5. Salir");
        System.out.println("");
    }

    private static void registrarEmpleadoAsalariado() {
        System.out.println();
        System.out.println(" EMPLEADO ASALARIADO ");

        String id = leerTexto("Ingrese ID del empleado: ");
        String nombre = leerTexto("Ingrese nombre del empleado: ");
        int aniosEnEmpresa = leerEntero("Ingrese años en la empresa: ");
        double salarioMensual = leerDouble("Ingrese salario mensual: ");

        Empleado empleado = new EmpleadoAsalariado(
                id,
                nombre,
                aniosEnEmpresa,
                salarioMensual
        );

        ResultadoNomina resultado = calculadoraNomina.calcular(empleado);
        imprimirResultado(resultado);
    }

    private static void registrarEmpleadoPorHoras() {
        System.out.println();
        System.out.println("EMPLEADO POR HORAS ");

        String id = leerTexto("Ingrese ID del empleado: ");
        String nombre = leerTexto("Ingrese nombre del empleado: ");
        int aniosEnEmpresa = leerEntero("Ingrese años en la empresa: ");
        double horasTrabajadas = leerDouble("Ingrese horas trabajadas: ");
        double tarifaPorHora = leerDouble("Ingrese tarifa por hora: ");
        boolean aceptaFondoAhorro = leerBooleano("¿Acepta fondo de ahorro? (s/n): ");

        Empleado empleado = new EmpleadoPorHoras(
                id,
                nombre,
                aniosEnEmpresa,
                horasTrabajadas,
                tarifaPorHora
        );

        ResultadoNomina resultado = calculadoraNomina.calcular(empleado, aceptaFondoAhorro);
        imprimirResultado(resultado);
    }

    private static void registrarEmpleadoPorComision() {
        System.out.println();
        System.out.println("EMPLEADO POR COMISION ");

        String id = leerTexto("Ingrese ID del empleado: ");
        String nombre = leerTexto("Ingrese nombre del empleado: ");
        int aniosEnEmpresa = leerEntero("Ingrese años en la empresa: ");
        double salarioBase = leerDouble("Ingrese salario base: ");
        double ventas = leerDouble("Ingrese total de ventas: ");
        double porcentajeComision = leerDouble("Ingrese porcentaje de comision. Ejemplo: 5 para 5%: ");

        Empleado empleado = new EmpleadoPorComision(
                id,
                nombre,
                aniosEnEmpresa,
                salarioBase,
                ventas,
                porcentajeComision / 100
        );

        ResultadoNomina resultado = calculadoraNomina.calcular(empleado);
        imprimirResultado(resultado);
    }

    private static void registrarEmpleadoTemporal() {
        System.out.println();
        System.out.println("EMPLEADO TEMPORAL");

        String id = leerTexto("Ingrese ID del empleado: ");
        String nombre = leerTexto("Ingrese nombre del empleado: ");
        int aniosEnEmpresa = leerEntero("Ingrese años en la empresa: ");
        double salarioMensual = leerDouble("Ingrese salario mensual: ");

        LocalDate fechaInicio = leerFecha("Ingrese fecha de inicio del contrato (YYYY-MM-DD): ");
        LocalDate fechaFin = leerFecha("Ingrese fecha de fin del contrato (YYYY-MM-DD): ");

        Empleado empleado = new EmpleadoTemporal(
                id,
                nombre,
                aniosEnEmpresa,
                salarioMensual,
                fechaInicio,
                fechaFin
        );

        ResultadoNomina resultado = calculadoraNomina.calcular(empleado);
        imprimirResultado(resultado);
    }

    private static void imprimirResultado(ResultadoNomina resultado) {
        System.out.println();
        System.out.println("--------------- RESULTADO NOMINA -------------");
        System.out.println("ID empleado: " + resultado.getIdEmpleado());
        System.out.println("Nombre: " + resultado.getNombreEmpleado());
        System.out.println("Salario bruto: " + formatoMoneda.format(resultado.getSalarioBruto()));
        System.out.println("Beneficios: " + formatoMoneda.format(resultado.getBeneficios()));
        System.out.println("Deducciones: " + formatoMoneda.format(resultado.getDeducciones()));
        System.out.println("Fondo de ahorro: " + formatoMoneda.format(resultado.getFondoAhorro()));
        System.out.println("Salario neto: " + formatoMoneda.format(resultado.getSalarioNeto()));
        System.out.println("----------------------------------------------");
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero valido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String valor = scanner.nextLine().replace(",", ".");
                return Double.parseDouble(valor);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }

    private static LocalDate leerFecha(String mensaje) {
        System.out.print(mensaje);
        return LocalDate.parse(scanner.nextLine());
    }

    private static boolean leerBooleano(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String respuesta = scanner.nextLine().trim().toLowerCase();

            if (respuesta.equals("s") || respuesta.equals("si")) {
                return true;
            }

            if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;
            }

            System.out.println("Respuesta no valida. Escriba s/n.");
        }
    }
}