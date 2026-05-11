package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.ProntuarioService;
import br.com.fiap.javaadv.blog.backend.services.TutorService;
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
@RequestMapping("/api/prontuario")
@RequiredArgsConstructor
public class ProntuarioResource {
    private final ProntuarioService prontuarioService;

    @PostMapping
    public ResponseEntity<ProntuarioRequest> create(@Valid @RequestBody ProntuarioRequest prontuarioRequest ){
        Prontuario prontuario = prontuarioRequest.toEntity(prontuarioRequest);
        Prontuario savedProntuario = this.prontuarioService.create(prontuario);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProntuario.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(prontuarioRequest.toDto(savedProntuario));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProntuarioRequest> update(@PathVariable UUID id, @Valid @RequestBody ProntuarioRequest dadosDto){
        return this.prontuarioService.update(id, ProntuarioRequest.toEntity(dadosDto))
                .map(prontuario ->
                        ResponseEntity.ok(ProntuarioRequest.toDto(prontuario)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.prontuarioService.existsById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProntuarioResponse>> fetchAll(Pageable pageable){
        return ResponseEntity.ok(
                this.prontuarioService.fetchAll(pageable)
                        .stream()
                        .map(ProntuarioResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioResponse> fetchById( @PathVariable UUID id ){
        return this.prontuarioService.fetchById(id)
                .map(prontuario -> ResponseEntity.ok(ProntuarioResponse.toDto(prontuario)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
