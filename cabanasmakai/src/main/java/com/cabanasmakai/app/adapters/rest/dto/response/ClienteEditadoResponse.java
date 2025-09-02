package com.cabanasmakai.app.adapters.rest.dto.response;

import lombok.Data;

@Data
public class ClienteEditadoResponse {
    private Long id;
    private String nome;
    private String cidade;
    private String telefone;
    private String email;
    private String cpf;
}
