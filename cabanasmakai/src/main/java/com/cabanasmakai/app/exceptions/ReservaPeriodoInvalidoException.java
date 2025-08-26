package com.cabanasmakai.app.exceptions;

import java.time.LocalDate;

public class ReservaPeriodoInvalidoException extends RuntimeException {
    public ReservaPeriodoInvalidoException(LocalDate dataEntrada, LocalDate dataSaida) {
        super("Periodo invalido" + dataEntrada + ", " + dataSaida);
    }
}
