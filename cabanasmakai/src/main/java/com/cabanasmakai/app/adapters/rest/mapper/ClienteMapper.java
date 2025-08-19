package com.cabanasmakai.app.adapters.rest.mapper;

import com.cabanasmakai.app.adapters.rest.dto.ClienteDTO;
import com.cabanasmakai.app.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteDTO clienteDTO);
    ClienteDTO toDto(Cliente cliente);
}
