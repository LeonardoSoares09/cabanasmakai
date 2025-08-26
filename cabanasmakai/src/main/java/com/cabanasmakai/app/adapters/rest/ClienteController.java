package com.cabanasmakai.app.adapters.rest;

import com.cabanasmakai.app.adapters.rest.dto.ClienteDTO;
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
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientes = clienteService.listarClientes()
                .stream()
                .map(clienteMapper::toDto)
                .toList();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id).
                map(cliente -> ResponseEntity.ok(clienteMapper.toDto(cliente))).
                orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> editarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente.setId(id);
        Cliente atualizado = clienteService.editarCliente(cliente);
        return ResponseEntity.ok(clienteMapper.toDto(atualizado));
    }
}
