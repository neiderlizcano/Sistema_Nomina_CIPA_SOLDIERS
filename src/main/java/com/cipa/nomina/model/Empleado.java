package com.cipa.nomina.model;

import com.cipa.nomina.exception.NominaException;

/**
 * Clase base para todos los empleados.
 *
 * Esta clase aplica abstracción porque define comportamientos comunes,
 * pero deja que cada tipo de empleado implemente su propio cálculo
 * de salario bruto y beneficios.
 */
public abstract class Empleado {

    private final String id;
    private final String nombre;
    private final int aniosEnEmpresa;

    private static final double PORCENTAJE_SEGURIDAD_SOCIAL_Y_PENSION = 0.04;

    public Empleado(String id, String nombre, int aniosEnEmpresa) {
        validarTextoObligatorio(id, "El id del empleado es obligatorio.");
        validarTextoObligatorio(nombre, "El nombre del empleado es obligatorio.");

        if (aniosEnEmpresa < 0) {
            throw new NominaException("Los años en la empresa no pueden ser negativos.");
        }

        this.id = id;
        this.nombre = nombre;
        this.aniosEnEmpresa = aniosEnEmpresa;
    }

    public abstract double calcularSalarioBruto();

    public abstract double calcularBeneficios();

    public double calcularDeducciones() {
        return calcularSalarioBruto() * PORCENTAJE_SEGURIDAD_SOCIAL_Y_PENSION;
    }

    public double calcularSalarioNeto() {
        double salarioNeto = calcularSalarioBruto() + calcularBeneficios() - calcularDeducciones();

        if (salarioNeto < 0) {
            throw new NominaException("El salario neto no puede ser negativo.");
        }

        return salarioNeto;
    }

    protected void validarValorNoNegativo(double valor, String mensaje) {
        if (valor < 0) {
            throw new NominaException(mensaje);
        }
    }

    private void validarTextoObligatorio(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new NominaException(mensaje);
        }
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAniosEnEmpresa() {
        return aniosEnEmpresa;
    }
}