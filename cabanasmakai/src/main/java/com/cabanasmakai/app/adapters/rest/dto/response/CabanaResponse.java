package com.cabanasmakai.app.adapters.rest.dto.response;

import com.cabanasmakai.app.domain.Reserva;
import com.cabanasmakai.app.domain.enums.StatusCabana;
import lombok.Data;

import java.util.List;

@Data
public class CabanaResponse {
    private Long id;
    private String numeroCabana;
    private StatusCabana statusCabana;
    private List<ReservaResponse> reservas;
}
