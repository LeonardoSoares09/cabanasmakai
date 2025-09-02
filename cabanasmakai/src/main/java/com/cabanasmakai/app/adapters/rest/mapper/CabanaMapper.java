package com.cabanasmakai.app.adapters.rest.mapper;

import com.cabanasmakai.app.adapters.rest.dto.request.CabanaRequest;
import com.cabanasmakai.app.adapters.rest.dto.response.CabanaResponse;
import com.cabanasmakai.app.domain.Cabanas;
import org.springframework.stereotype.Component;

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
        return c;
    }
}
