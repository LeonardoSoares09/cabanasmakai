package com.cabanasmakai.app.adapters.rest.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaResponse {
    private Long id;
    private Long clienteId;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String status;
}
