package com.cipa.nomina.model;

import com.cipa.nomina.exception.NominaException;

import java.time.LocalDate;

/**
 * Esta clase representa a un empleado temporal.
 *
 * Este tipo de empleado recibe un salario fijo mensual,
 * tiene un contrato por tiempo definido y no recibe bonos
 * ni beneficios adicionales.
 */

public class EmpleadoTemporal extends Empleado {

    private final double salarioMensual;
    private final LocalDate fechaInicioContrato;
    private final LocalDate fechaFinContrato;

    public EmpleadoTemporal(String id, String nombre, int aniosEnEmpresa,
                            double salarioMensual,
                            LocalDate fechaInicioContrato,
                            LocalDate fechaFinContrato) {
        super(id, nombre, aniosEnEmpresa);

        validarValorNoNegativo(salarioMensual, "El salario mensual no puede ser negativo.");
        validarFechasContrato(fechaInicioContrato, fechaFinContrato);

        this.salarioMensual = salarioMensual;
        this.fechaInicioContrato = fechaInicioContrato;
        this.fechaFinContrato = fechaFinContrato;
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioMensual;
    }

    @Override
    public double calcularBeneficios() {
        return 0;
    }

    private void validarFechasContrato(LocalDate fechaInicioContrato, LocalDate fechaFinContrato) {
        if (fechaInicioContrato == null) {
            throw new NominaException("La fecha de inicio del contrato es obligatoria.");
        }

        if (fechaFinContrato == null) {
            throw new NominaException("La fecha de fin del contrato es obligatoria.");
        }

        if (fechaFinContrato.isBefore(fechaInicioContrato)) {
            throw new NominaException("La fecha de fin del contrato no puede ser anterior a la fecha de inicio.");
        }
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public LocalDate getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public LocalDate getFechaFinContrato() {
        return fechaFinContrato;
    }
}