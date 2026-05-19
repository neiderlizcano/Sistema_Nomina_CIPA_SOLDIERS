package com.cipa.nomina;

import com.cipa.nomina.model.Empleado;
import com.cipa.nomina.model.EmpleadoAsalariado;
import com.cipa.nomina.model.EmpleadoPorComision;
import com.cipa.nomina.model.EmpleadoPorHoras;
import com.cipa.nomina.model.EmpleadoTemporal;
import com.cipa.nomina.service.CalculadoraNomina;
import com.cipa.nomina.service.ResultadoNomina;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("        SISTEMA DE NOMINA EMPRESA SOLDIERS       ");
        System.out.println();
        CalculadoraNomina calculadoraNomina = new CalculadoraNomina();

        List<Empleado> empleados = List.of(
                new EmpleadoAsalariado(
                        "EMP-001",
                        "Carlos Perez",
                        6,
                        3_000_000
                ),
                new EmpleadoPorHoras(
                        "EMP-002",
                        "Ana Gomez",
                        2,
                        45,
                        20_000
                ),
                new EmpleadoPorComision(
                        "EMP-003",
                        "Luis Martinez",
                        4,
                        2_000_000,
                        25_000_000,
                        0.05
                ),
                new EmpleadoTemporal(
                        "EMP-004",
                        "Maria Rodriguez",
                        0,
                        1_800_000,
                        LocalDate.of(2026, 1, 1),
                        LocalDate.of(2026, 6, 30)
                )
        );

        for (Empleado empleado : empleados) {
            boolean aceptaFondoAhorro = empleado instanceof EmpleadoPorHoras;

            ResultadoNomina resultado = calculadoraNomina.calcular(
                    empleado,
                    aceptaFondoAhorro
            );

            imprimirResultado(resultado);
        }
    }

    private static void imprimirResultado(ResultadoNomina resultado) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println("Empleado: " + resultado.getNombreEmpleado());
        System.out.println("ID: " + resultado.getIdEmpleado());
        System.out.println("Salario bruto: " + formatoMoneda.format(resultado.getSalarioBruto()));
        System.out.println("Beneficios: " + formatoMoneda.format(resultado.getBeneficios()));
        System.out.println("Deducciones: " + formatoMoneda.format(resultado.getDeducciones()));
        System.out.println("Fondo de ahorro: " + formatoMoneda.format(resultado.getFondoAhorro()));
        System.out.println("Salario neto: " + formatoMoneda.format(resultado.getSalarioNeto()));
        System.out.println("-----------------------------------");
    }
}