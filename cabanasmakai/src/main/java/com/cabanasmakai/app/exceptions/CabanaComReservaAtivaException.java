package com.cabanasmakai.app.exceptions;

public class CabanaComReservaAtivaException extends RuntimeException {
    public CabanaComReservaAtivaException(Long id) {
        super("Cabana com reserva ativa " + id);
    }
}
