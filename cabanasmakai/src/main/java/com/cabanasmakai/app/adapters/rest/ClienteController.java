package com.cabanasmakai.app.adapters.rest;

import com.cabanasmakai.app.adapters.rest.dto.ClienteDTO;
import com.cabanasmakai.app.adapters.rest.mapper.ClienteMapper;
import com.cabanasmakai.app.application.ClienteService;
import com.cabanasmakai.app.domain.Cliente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody @Valid ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente salvo = clienteService.criarCliente(cliente);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(salvo.getId()).
                toUri();

        return ResponseEntity.created(location).body(clienteMapper.toDto(salvo));
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id).
                map(cliente -> ResponseEntity.ok(clienteMapper.toDto(cliente))).
                orElse(ResponseEntity.notFound().build());
    }
}
