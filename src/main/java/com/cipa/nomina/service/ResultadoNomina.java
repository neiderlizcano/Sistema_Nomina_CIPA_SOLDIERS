package com.cipa.nomina.service;

/**
 * Esta clase es el resultado final del cálculo de nómina de un empleado.
 *
 * Esta clase permite mostrar la información de forma ordenada,
 * separando salario bruto, beneficios, deducciones, fondo de ahorro
 * y salario neto.
 */
public class ResultadoNomina {

    private final String idEmpleado;
    private final String nombreEmpleado;
    private final double salarioBruto;
    private final double beneficios;
    private final double deducciones;
    private final double fondoAhorro;
    private final double salarioNeto;

    public ResultadoNomina(String idEmpleado, String nombreEmpleado,
                           double salarioBruto, double beneficios,
                           double deducciones, double fondoAhorro,
                           double salarioNeto) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.salarioBruto = salarioBruto;
        this.beneficios = beneficios;
        this.deducciones = deducciones;
        this.fondoAhorro = fondoAhorro;
        this.salarioNeto = salarioNeto;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public double getBeneficios() {
        return beneficios;
    }

    public double getDeducciones() {
        return deducciones;
    }

    public double getFondoAhorro() {
        return fondoAhorro;
    }

    public double getSalarioNeto() {
        return salarioNeto;
    }

    @Override
    public String toString() {
        return "ResultadoNomina{" +
                "idEmpleado='" + idEmpleado + '\'' +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", salarioBruto=" + salarioBruto +
                ", beneficios=" + beneficios +
                ", deducciones=" + deducciones +
                ", fondoAhorro=" + fondoAhorro +
                ", salarioNeto=" + salarioNeto +
                '}';
    }
}