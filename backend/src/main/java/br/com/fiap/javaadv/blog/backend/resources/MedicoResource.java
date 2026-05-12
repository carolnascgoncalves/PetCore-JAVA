package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
                .map(tutor ->
                        ResponseEntity.ok(UserDadosRequest.toDtoMed(tutor)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.medicoService.existsById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<MedicoResponse>> fetchAll(Pageable pageable){
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

    @GetMapping("/login")
    public Optional<Medico> fetchById(String email, String senha){
        Optional<Medico> medico = medicoService.fetchByEmail(email, senha);
        if(medico.isPresent() && medico.get().getSenha().equals(senha)){return medico;}

        return Optional.empty();
    }
}
