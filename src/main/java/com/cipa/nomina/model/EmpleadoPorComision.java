package com.cipa.nomina.model;

/**
 * Esta clase representa a un empleado que recibe salario base
 * más una comisión sobre sus ventas.
 *
 * Si las ventas superan $20.000.000, recibe un bono adicional
 * del 3% sobre las ventas.
 *
 * Por ser empleado permanente, recibe bono de alimentación.
 */

public class EmpleadoPorComision extends Empleado {

    private static final double BONO_ALIMENTACION = 1_000_000;
    private static final double LIMITE_VENTAS_BONO = 20_000_000;
    private static final double PORCENTAJE_BONO_VENTAS = 0.03;

    private final double salarioBase;
    private final double ventas;
    private final double porcentajeComision;

    public EmpleadoPorComision(String id, String nombre, int aniosEnEmpresa,
                               double salarioBase, double ventas, double porcentajeComision) {
        super(id, nombre, aniosEnEmpresa);

        validarValorNoNegativo(salarioBase, "El salario base no puede ser negativo.");
        validarValorNoNegativo(ventas, "Las ventas no pueden ser menores a $0.");
        validarValorNoNegativo(porcentajeComision, "El porcentaje de comisión no puede ser negativo.");

        this.salarioBase = salarioBase;
        this.ventas = ventas;
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioBase + calcularComision();
    }

    @Override
    public double calcularBeneficios() {
        double beneficios = BONO_ALIMENTACION;

        if (ventas > LIMITE_VENTAS_BONO) {
            beneficios += ventas * PORCENTAJE_BONO_VENTAS;
        }

        return beneficios;
    }

    private double calcularComision() {
        return ventas * porcentajeComision;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getVentas() {
        return ventas;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }
}