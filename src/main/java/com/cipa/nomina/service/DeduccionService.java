package com.cipa.nomina.service;

import com.cipa.nomina.exception.NominaException;
import com.cipa.nomina.model.Empleado;

/**
 * Esta clase contiene el servicio encargado de calcular las deducciones obligatorias.
 *
 * Se aplica para la seguridad social y pensión equivalente al 4% del salario bruto.
 * El porcentaje de ARL se deja configurable porque el enunciado menciona ARL,
 * pero no especifica un porcentaje exacto.
 */
public class DeduccionService {

    private static final double PORCENTAJE_SEGURIDAD_SOCIAL_Y_PENSION = 0.04;

    private final double porcentajeArl;

    public DeduccionService() {
        this(0);
    }

    public DeduccionService(double porcentajeArl) {
        if (porcentajeArl < 0) {
            throw new NominaException("El porcentaje de ARL no puede ser negativo.");
        }

        this.porcentajeArl = porcentajeArl;
    }

    public double calcularDeducciones(Empleado empleado) {
        validarEmpleado(empleado);
        return calcularSeguridadSocialYPension(empleado) + calcularArl(empleado);
    }

    public double calcularSeguridadSocialYPension(Empleado empleado) {
        validarEmpleado(empleado);
        return empleado.calcularSalarioBruto() * PORCENTAJE_SEGURIDAD_SOCIAL_Y_PENSION;
    }

    public double calcularArl(Empleado empleado) {
        validarEmpleado(empleado);
        return empleado.calcularSalarioBruto() * porcentajeArl;
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new NominaException("El empleado es obligatorio para calcular deducciones.");
        }
    }
}