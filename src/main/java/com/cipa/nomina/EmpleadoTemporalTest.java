package com.cipa.nomina;

import com.cipa.nomina.model.EmpleadoTemporal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpleadoTemporalTest {

    @Test
    void debeCalcularSalarioTemporal() {

        EmpleadoTemporal empleado =
                new EmpleadoTemporal(
                        "4",
                        "Maria",
                        1,
                        1500000,
                        6
                );

        double salario = empleado.calcularSalarioBruto();

        assertEquals(1500000, salario);
    }
}