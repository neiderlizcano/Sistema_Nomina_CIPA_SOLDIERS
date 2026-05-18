package com.cipa.nomina.exception;

/**
 * Excepción para representar errores propios
 * del sistema de nómina.
 */
public class NominaException extends RuntimeException {

    public NominaException(String mensaje) {
        super(mensaje);
    }
}