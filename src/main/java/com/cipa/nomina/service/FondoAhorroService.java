package com.cipa.nomina.service;

import com.cipa.nomina.exception.NominaException;
import com.cipa.nomina.model.Empleado;

/**
 * Servicio encargado de calcular el fondo de ahorro.
 *
 * Solo aplica para empleados que puedan acceder al fondo,
 * tengan más de 1 año en la empresa y acepten este beneficio.
 */
public class FondoAhorroService {

    private static final double PORCENTAJE_FONDO_AHORRO = 0.02;

    public double calcularFondoAhorro(Empleado empleado, boolean aceptaFondoAhorro) {
        validarEmpleado(empleado);

        if (!aceptaFondoAhorro) {
            return 0;
        }

        if (!empleado.puedeAccederFondoAhorro()) {
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