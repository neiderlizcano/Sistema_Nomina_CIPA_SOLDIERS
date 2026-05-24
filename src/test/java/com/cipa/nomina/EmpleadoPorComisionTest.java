package com.cipa.nomina;

import com.cipa.nomina.model.EmpleadoPorComision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpleadoPorComisionTest {

    @Test
    void debeCalcularSalarioPorComision() {

        EmpleadoPorComision empleado =
                new EmpleadoPorComision(
                        "3",
                        "Carlos",
                        4,
                        2000000,
                        5000000,
                        0.10
                );

        double salario = empleado.calcularSalarioBruto();

        assertEquals(2500000, salario);
    }
}