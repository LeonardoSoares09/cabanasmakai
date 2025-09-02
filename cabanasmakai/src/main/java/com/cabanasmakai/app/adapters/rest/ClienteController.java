package com.cabanasmakai.app.adapters.rest;

import com.cabanasmakai.app.adapters.rest.dto.request.ClienteRequest;
import com.cabanasmakai.app.adapters.rest.dto.response.ClienteEditadoResponse;
import com.cabanasmakai.app.adapters.rest.mapper.ClienteMapper;
import com.cabanasmakai.app.application.ClienteService;
import com.cabanasmakai.app.domain.Cliente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<ClienteEditadoResponse> criar(@RequestBody @Valid ClienteRequest clienteRequest) {
        Cliente cliente = clienteService.criarCliente(clienteMapper.toEntity(clienteRequest));

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(cliente.getId()).
                toUri();

        return ResponseEntity.created(location).body(clienteMapper.toDto(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteEditadoResponse>> listarClientes() {
        List<ClienteEditadoResponse> clientes = clienteService.listarClientes()
                .stream()
                .map(clienteMapper::toDto)
                .toList();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEditadoResponse> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteEditadoResponse> editarCliente(@PathVariable Long id, @RequestBody ClienteRequest clienteDTO) {

        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente.setId(id);
        cliente = clienteService.editaCliente(cliente);
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }
}
