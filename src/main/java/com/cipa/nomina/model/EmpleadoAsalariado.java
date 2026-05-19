package com.cipa.nomina.model;

/**
 * En esta clase, este tipo de empleado recibe un salario fijo mensual.
 * Si lleva más de 5 años en la empresa, recibe un bono del 10%.
 * Además, al ser un empleado permanente, recibe bono de alimentación.
 */

public class EmpleadoAsalariado extends Empleado {

    private static final double PORCENTAJE_BONO_ANTIGUEDAD = 0.10;
    private static final double BONO_ALIMENTACION = 1_000_000;

    private final double salarioMensual;

    public EmpleadoAsalariado(String id, String nombre, int aniosEnEmpresa, double salarioMensual) {
        super(id, nombre, aniosEnEmpresa);
        validarValorNoNegativo(salarioMensual, "El salario mensual no puede ser negativo.");
        this.salarioMensual = salarioMensual;
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioMensual;
    }

    @Override
    public double calcularBeneficios() {
        double beneficios = BONO_ALIMENTACION;

        if (getAniosEnEmpresa() > 5) {
            beneficios += salarioMensual * PORCENTAJE_BONO_ANTIGUEDAD;
        }

        return beneficios;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }
}