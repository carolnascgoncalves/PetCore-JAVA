package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.ExameService;
import br.com.fiap.javaadv.blog.backend.services.MedicamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<ExameDadosRequest> update(@PathVariable UUID id, @Valid @RequestBody ExameDadosRequest dadosDto){
        return this.exameService.update(id, ExameDadosRequest.toEntity(dadosDto))
                .map(exame ->
                        ResponseEntity.ok(ExameDadosRequest.toDto(exame)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.exameService.existsById(id)) {
            this.exameService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ExameResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10)Pageable pageable){
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
