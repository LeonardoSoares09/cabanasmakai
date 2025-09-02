package com.cabanasmakai.app.adapters.rest.dto.request;

import com.cabanasmakai.app.domain.enums.StatusCabana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CabanaRequest {

    @NotBlank(message = "Número é obrigatório")
    @NotNull
    private String numeroCabana;
    @NotNull
    private StatusCabana statusCabana;
}
