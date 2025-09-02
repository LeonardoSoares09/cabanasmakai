package com.cabanasmakai.app.adapters.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

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

    @NotNull(message = "Data de entrada é obrigatória")
    private LocalDate dataEntrada;

    @NotNull(message = "Data de saída é obrigatória")
    private LocalDate dataSaida;

    @AssertTrue(message = "dataSaida deve ser depois de dataEntrada")
    public boolean isPeriodoValido(){
        if(dataEntrada == null || dataSaida == null){
            return true;
        }
        return !dataSaida.isBefore(dataEntrada);
    }
}
