package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medico")
@RequiredArgsConstructor
public class MedicoResource {
    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoCreateRequest> create(@Valid @RequestBody MedicoCreateRequest medicoRequest ){
        Medico medico = medicoRequest.toEntity(medicoRequest);
        Medico savedTutor = this.medicoService.create(medico);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTutor.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(medicoRequest.toDto(savedTutor));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDadosRequest> update(@PathVariable UUID id, @Valid @RequestBody UserDadosRequest dadosDto){
        return this.medicoService.update(id, UserDadosRequest.toEntityMed(dadosDto))
                .map(med ->
                        ResponseEntity.ok(UserDadosRequest.toDtoMed(med)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.medicoService.existsById(id)) {
            this.medicoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MedicoResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10)Pageable pageable){
        return ResponseEntity.ok(
                this.medicoService.fetchAll(pageable)
                        .stream()
                        .map(MedicoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> fetchById( @PathVariable UUID id ){
        return this.medicoService.fetchByEmail(id)
                .map(medico -> ResponseEntity.ok(MedicoResponse.toDto(medico)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/login")
    public ResponseEntity<MedicoResponse> fetchByEmail(@RequestBody UserLoginRequest loginDto) {
        return medicoService.fetchByEmail(loginDto.getEmail(), loginDto.getSenha())
                .map(med -> ResponseEntity.ok(MedicoResponse.toDto(med)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
