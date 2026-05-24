package com.cipa.nomina;

import com.cipa.nomina.model.EmpleadoPorHoras;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmpleadoPorHorasTest {

    @Test
    void debeCalcularHorasNormales() {

        EmpleadoPorHoras empleado =
                new EmpleadoPorHoras(
                        "2",
                        "Neider",
                        1,
                        40,
                        10000
                );

        double salario = empleado.calcularSalarioBruto();

        assertEquals(400000, salario);
    }

    @Test
    void debeCalcularHorasExtras() {

        EmpleadoPorHoras empleado =
                new EmpleadoPorHoras(
                        "2",
                        "Neider",
                        1,
                        50,
                        10000
                );

        double salario = empleado.calcularSalarioBruto();

        assertEquals(550000, salario);
    }

    @Test
    void noDebeAccederFondoAhorro() {

        EmpleadoPorHoras empleado =
                new EmpleadoPorHoras(
                        "2",
                        "Neider",
                        1,
                        40,
                        10000
                );

        assertFalse(empleado.puedeAccederFondoAhorro());
    }
}