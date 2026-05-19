package com.cipa.nomina.service;

import com.cipa.nomina.exception.NominaException;
import com.cipa.nomina.model.Empleado;

/**
 * En esta clase esta el servicio principal encargado de calcular la nómina completa.
 *
 * Esta clase coordina el cálculo de salario bruto, beneficios,
 * deducciones, fondo de ahorro y salario neto.
 */
public class CalculadoraNomina {

    private final DeduccionService deduccionService;
    private final FondoAhorroService fondoAhorroService;

    public CalculadoraNomina() {
        this(new DeduccionService(), new FondoAhorroService());
    }

    public CalculadoraNomina(DeduccionService deduccionService, FondoAhorroService fondoAhorroService) {
        if (deduccionService == null) {
            throw new NominaException("El servicio de deducciones es obligatorio.");
        }

        if (fondoAhorroService == null) {
            throw new NominaException("El servicio de fondo de ahorro es obligatorio.");
        }

        this.deduccionService = deduccionService;
        this.fondoAhorroService = fondoAhorroService;
    }

    public ResultadoNomina calcular(Empleado empleado) {
        return calcular(empleado, false);
    }

    public ResultadoNomina calcular(Empleado empleado, boolean aceptaFondoAhorro) {
        validarEmpleado(empleado);

        double salarioBruto = empleado.calcularSalarioBruto();
        double beneficios = empleado.calcularBeneficios();
        double deducciones = deduccionService.calcularDeducciones(empleado);
        double fondoAhorro = fondoAhorroService.calcularFondoAhorro(empleado, aceptaFondoAhorro);
        double salarioNeto = salarioBruto + beneficios - deducciones;

        if (salarioNeto < 0) {
            throw new NominaException("El salario neto no puede ser negativo.");
        }

        return new ResultadoNomina(
                empleado.getId(),
                empleado.getNombre(),
                salarioBruto,
                beneficios,
                deducciones,
                fondoAhorro,
                salarioNeto
        );
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new NominaException("El empleado es obligatorio para calcular la nómina.");
        }
    }
}