package com.cabanasmakai.app.exceptions;

public class ErroInternoException extends RuntimeException {
    public ErroInternoException(String mensagem) {
        super(mensagem);
    }
}

