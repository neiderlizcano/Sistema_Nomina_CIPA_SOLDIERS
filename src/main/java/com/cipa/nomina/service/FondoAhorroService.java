package com.cipa.nomina.service;

import com.cipa.nomina.exception.NominaException;
import com.cipa.nomina.model.Empleado;
import com.cipa.nomina.model.EmpleadoPorHoras;

/**
 * Servicio encargado de calcular el fondo de ahorro.
 *
 * En este caso es solo aplicable para empleados por horas con más de 1 año en la empresa
 * y únicamente si el empleado acepta acceder al fondo.
 */
public class FondoAhorroService {

    private static final double PORCENTAJE_FONDO_AHORRO = 0.02;

    public double calcularFondoAhorro(Empleado empleado, boolean aceptaFondoAhorro) {
        validarEmpleado(empleado);

        if (!aceptaFondoAhorro) {
            return 0;
        }

        if (!(empleado instanceof EmpleadoPorHoras)) {
            return 0;
        }

        if (empleado.getAniosEnEmpresa() <= 1) {
            return 0;
        }

        return empleado.calcularSalarioBruto() * PORCENTAJE_FONDO_AHORRO;
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new NominaException("El empleado es obligatorio para calcular el fondo de ahorro.");
        }
    }
}