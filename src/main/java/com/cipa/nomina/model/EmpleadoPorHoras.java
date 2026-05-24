package com.cipa.nomina.model;

/**
 * Representa a un empleado que trabaja por horas.
 *
 * Según el enunciado, este tipo de empleado recibe un pago según las horas trabajadas.
 * Si trabaja más de 40 horas, las horas adicionales se pagan a 1.5 veces
 * la tarifa normal y no recibe bonos.
 *
 * Además, si tiene más de 1 año en la empresa, puede acceder al fondo de ahorro
 * siempre que acepte ese beneficio.
 */
public class EmpleadoPorHoras extends Empleado {

    private static final int LIMITE_HORAS_NORMALES = 40;
    private static final double MULTIPLICADOR_HORA_EXTRA = 1.5;

    private final double horasTrabajadas;
    private final double tarifaPorHora;

    public EmpleadoPorHoras(String id, String nombre, int aniosEnEmpresa,
                            double horasTrabajadas, double tarifaPorHora) {
        super(id, nombre, aniosEnEmpresa);

        validarValorNoNegativo(horasTrabajadas, "Las horas trabajadas no pueden ser negativas.");
        validarValorMayorQueCero(tarifaPorHora, "La tarifa por hora debe ser mayor que cero.");

        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    @Override
    public double calcularSalarioBruto() {
        if (horasTrabajadas <= LIMITE_HORAS_NORMALES) {
            return horasTrabajadas * tarifaPorHora;
        }

        double horasNormales = LIMITE_HORAS_NORMALES;
        double horasExtras = horasTrabajadas - LIMITE_HORAS_NORMALES;

        double pagoHorasNormales = horasNormales * tarifaPorHora;
        double pagoHorasExtras = horasExtras * tarifaPorHora * MULTIPLICADOR_HORA_EXTRA;

        return pagoHorasNormales + pagoHorasExtras;
    }

    @Override
    public double calcularBeneficios() {
        return 0;
    }

    @Override
    public boolean puedeAccederFondoAhorro() {
        return getAniosEnEmpresa() > 1;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }
}