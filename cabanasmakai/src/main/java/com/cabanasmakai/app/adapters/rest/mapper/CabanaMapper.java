package com.cabanasmakai.app.adapters.rest.mapper;

import com.cabanasmakai.app.adapters.rest.dto.request.CabanaRequest;
import com.cabanasmakai.app.adapters.rest.dto.response.CabanaResponse;
import com.cabanasmakai.app.adapters.rest.dto.response.ReservaResponse;
import com.cabanasmakai.app.domain.Cabanas;
import com.cabanasmakai.app.domain.Reserva;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class CabanaMapper {

    public Cabanas toEntity(CabanaRequest cabanaRequest) {
        if(cabanaRequest == null) return null;
        Cabanas cabanas = new Cabanas();
        cabanas.setNumeroCabana(cabanaRequest.getNumeroCabana());
        cabanas.setStatusCabana(cabanaRequest.getStatusCabana());
        return cabanas;
    }

    public CabanaResponse toDto(Cabanas cabanas) {
        if(cabanas == null) return null;
        CabanaResponse c = new CabanaResponse();
        c.setId(cabanas.getId());
        c.setNumeroCabana(cabanas.getNumeroCabana());
        c.setStatusCabana(cabanas.getStatusCabana());
        if(cabanas.getReservas() != null) {
            List<ReservaResponse> reservasResponse = cabanas.getReservas().stream()
                    .map(this::toReservaResponse)
                    .toList();
            c.setReservas(reservasResponse);
        }
        return c;
    }

    private ReservaResponse toReservaResponse(Reserva reserva) {
        ReservaResponse reservaResponse = new ReservaResponse();
        reservaResponse.setId(reserva.getId());
        reservaResponse.setClienteId(reserva.getCliente().getId());
        reservaResponse.setDataEntrada(reserva.getDataEntrada());
        reservaResponse.setDataSaida(reserva.getDataSaida());
        reservaResponse.setStatus(reserva.getStatus().name());  // Convertendo o enum para String
        return reservaResponse;
    }
}
