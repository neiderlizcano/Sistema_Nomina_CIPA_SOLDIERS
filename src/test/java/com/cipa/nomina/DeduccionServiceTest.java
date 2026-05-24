package com.cipa.nomina;

import com.cipa.nomina.model.EmpleadoAsalariado;
import com.cipa.nomina.service.DeduccionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeduccionServiceTest {

    @Test
    void debeCalcularDeduccionesCorrectamente() {

        EmpleadoAsalariado empleado =
                new EmpleadoAsalariado(
                        "1",
                        "Antonio",
                        2,
                        2000000
                );

        DeduccionService service = new DeduccionService();

        double deducciones = service.calcularDeducciones(empleado);

        assertEquals(80000, deducciones);
    }
}