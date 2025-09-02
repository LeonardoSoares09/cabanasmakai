package com.cabanasmakai.app.adapters.rest;

import com.cabanasmakai.app.adapters.rest.dto.request.CabanaRequest;
import com.cabanasmakai.app.adapters.rest.dto.response.CabanaResponse;
import com.cabanasmakai.app.adapters.rest.mapper.CabanaMapper;
import com.cabanasmakai.app.application.CabanaService;
import com.cabanasmakai.app.domain.Cabanas;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cabanas")
@RequiredArgsConstructor
public class CabanaController {

    private final CabanaService cabanaService;
    private final CabanaMapper cabanaMapper;

    @PostMapping
    public ResponseEntity<CabanaResponse> criarCabana(@RequestBody @Valid CabanaRequest dto){
        Cabanas cabana = cabanaService.adicionarCabana(cabanaMapper.toEntity(dto));
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(cabana.getId()).
                toUri();
        return ResponseEntity.created(location).body(cabanaMapper.toDto(cabana));
    }

    @GetMapping
    public ResponseEntity<List<CabanaResponse>> listarCabanas(){
        List<CabanaResponse> cabanas = cabanaService.listarCabanas()
                .stream()
                .map(cabanaMapper::toDto)
                .toList();
        return ResponseEntity.ok(cabanas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabanaResponse> buscarCabanaPorId(@PathVariable Long id){
        Cabanas cabanas = cabanaService.listarCabanaId(id);
        return ResponseEntity.ok(cabanaMapper.toDto(cabanas));
    }
}
