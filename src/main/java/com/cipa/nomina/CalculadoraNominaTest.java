package com.cipa.nomina;

import com.cipa.nomina.model.EmpleadoAsalariado;
import com.cipa.nomina.service.CalculadoraNomina;
import com.cipa.nomina.service.DeduccionService;
import com.cipa.nomina.service.FondoAhorroService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculadoraNominaTest {

    @Test
    void debeGenerarResumenNomina() {

        EmpleadoAsalariado empleado =
                new EmpleadoAsalariado(
                        "1",
                        "Antonio",
                        5,
                        3000000
                );

        CalculadoraNomina calculadora =
                new CalculadoraNomina(
                        new DeduccionService(),
                        new FondoAhorroService()
                );

        String resumen = calculadora.generarResumenNomina(empleado);

        assertTrue(resumen.contains("Antonio"));
    }
}