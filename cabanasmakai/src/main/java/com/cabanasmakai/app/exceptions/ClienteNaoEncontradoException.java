package com.cabanasmakai.app.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {
        public ClienteNaoEncontradoException(Long id) {
            super("Cliente não encontrado com id " + id);
        }
}
