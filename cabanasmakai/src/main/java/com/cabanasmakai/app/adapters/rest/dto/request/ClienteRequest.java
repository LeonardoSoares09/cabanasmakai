package com.cabanasmakai.app.adapters.rest.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotBlank(message = "Nome é obrigatório")
    @NotNull
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank(message = "Cidade é obrigatória")
    @NotNull
    private String cidade;

    @NotBlank(message = "Telefone é obrigatório")
    @NotNull
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXXX-XXXX")
    @Size(min = 10, max = 15, message = "Telefone deve ter entre 10 e 15 caracteres")
    private String telefone;

    @Email(message = "E-mail inválido")
    @NotNull
    private String email;

    @NotBlank(message = "CPF é obrigatório")
    @NotNull
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    private String cpf;
}
