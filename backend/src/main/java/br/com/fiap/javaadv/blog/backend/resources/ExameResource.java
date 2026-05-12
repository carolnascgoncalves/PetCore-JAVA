package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ExameRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ExameResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.MedicamentoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.MedicamentoResponse;
import br.com.fiap.javaadv.blog.backend.services.ExameService;
import br.com.fiap.javaadv.blog.backend.services.MedicamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exame")
@RequiredArgsConstructor
public class ExameResource {
    private final ExameService exameService;

    @PostMapping
    public ResponseEntity<ExameRequest> create(@Valid @RequestBody ExameRequest exameRequest ){
        Exame exame = exameRequest.toEntity(exameRequest);
        Exame savedExame = this.exameService.create(exame);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedExame.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(exameRequest.toDto(savedExame));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExameRequest> update(@PathVariable UUID id, @Valid @RequestBody ExameRequest dadosDto){
        return this.exameService.update(id, ExameRequest.toEntity(dadosDto))
                .map(medicamento ->
                        ResponseEntity.ok(ExameRequest.toDto(medicamento)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.exameService.existsById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<ExameResponse>> fetchAll(Pageable pageable){
        return ResponseEntity.ok(
                this.exameService.fetchAll(pageable)
                        .stream()
                        .map(ExameResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameResponse> fetchById( @PathVariable UUID id ){
        return this.exameService.fetchById(id)
                .map(exame -> ResponseEntity.ok(ExameResponse.toDto(exame)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
