package com.cabanasmakai.app.adapters.rest.dto.response;

import java.time.LocalDate;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cidade,
        String telefone,
        String email,
        String cpf,
        LocalDate dataEntrada,
        LocalDate dataSaida
) {}