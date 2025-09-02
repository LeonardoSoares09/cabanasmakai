package com.cabanasmakai.app.adapters.rest.mapper;

import com.cabanasmakai.app.adapters.rest.dto.request.ClienteRequest;
import com.cabanasmakai.app.adapters.rest.dto.response.ClienteEditadoResponse;
import com.cabanasmakai.app.domain.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequest dto) {
        if (dto == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCidade(dto.getCidade());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        return cliente;
    }

    public ClienteEditadoResponse toDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteEditadoResponse c = new ClienteEditadoResponse();
        c.setId(cliente.getId());
        c.setNome(cliente.getNome());
        c.setCidade(cliente.getCidade());
        c.setTelefone(cliente.getTelefone());
        c.setEmail(cliente.getEmail());
        c.setCpf(cliente.getCpf());
        return c;
    }
}
