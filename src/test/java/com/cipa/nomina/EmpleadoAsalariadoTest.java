package com.cipa.nomina;

import com.cipa.nomina.model.EmpleadoAsalariado;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class EmpleadoAsalariadoTest {

    @Test
    void debeCalcularSalarioBrutoCorrectamente() {

        EmpleadoAsalariado empleado =
                new EmpleadoAsalariado(
                        "1",
                        "Antonio",
                        3,
                        3000000
                );

        double salario = empleado.calcularSalarioBruto();

        assertEquals(3000000, salario);
    }

    @Test
    void debeCalcularBeneficiosCorrectamente() {

        EmpleadoAsalariado empleado =
                new EmpleadoAsalariado(
                        "1",
                        "Antonio",
                        3,
                        3000000
                );

        double beneficios = empleado.calcularBeneficios();

        assertEquals(1000000, beneficios);
    }

}