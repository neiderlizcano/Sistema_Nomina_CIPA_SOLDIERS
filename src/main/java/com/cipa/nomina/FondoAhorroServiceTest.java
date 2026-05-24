package com.cipa.nomina;

import com.cipa.nomina.model.EmpleadoAsalariado;
import com.cipa.nomina.model.EmpleadoPorHoras;
import com.cipa.nomina.service.FondoAhorroService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FondoAhorroServiceTest {

    @Test
    void debeAplicarFondoAhorroEmpleadoValido() {

        EmpleadoAsalariado empleado =
                new EmpleadoAsalariado(
                        "1",
                        "Antonio",
                        5,
                        3000000
                );

        FondoAhorroService service = new FondoAhorroService();

        double fondo = service.calcularFondoAhorro(empleado);

        assertEquals(300000, fondo);
    }

    @Test
    void noDebeAplicarFondoEmpleadoPorHoras() {

        EmpleadoPorHoras empleado =
                new EmpleadoPorHoras(
                        "2",
                        "Neider",
                        1,
                        40,
                        10000
                );

        FondoAhorroService service = new FondoAhorroService();

        double fondo = service.calcularFondoAhorro(empleado);

        assertEquals(0, fondo);
    }
}