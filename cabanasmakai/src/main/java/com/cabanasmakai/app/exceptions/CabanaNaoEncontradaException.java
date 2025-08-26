package com.cabanasmakai.app.exceptions;

public class CabanaNaoEncontradaException extends RuntimeException {
    public CabanaNaoEncontradaException(Long id) {
        super("Cabana não encontrado com id " + id);
    }
}
