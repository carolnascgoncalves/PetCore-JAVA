package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.TutorService;
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
@RequestMapping("/api/tutor")
@RequiredArgsConstructor
public class TutorResource {
    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorCreateRequest> create( @Valid @RequestBody TutorCreateRequest tutorRequest ){
        Tutor tutor = tutorRequest.toEntity(tutorRequest);
        Tutor savedTutor = this.tutorService.create(tutor);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTutor.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(tutorRequest.toDto(savedTutor));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDadosRequest> update(@PathVariable UUID id, @Valid @RequestBody UserDadosRequest dadosDto){
        return this.tutorService.update(id, UserDadosRequest.toEntity(dadosDto))
                .map(tutor ->
                        ResponseEntity.ok(UserDadosRequest.toDto(tutor)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.tutorService.existsById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<TutorResponse>> fetchAll(Pageable pageable){
        return ResponseEntity.ok(
                this.tutorService.fetchAll(pageable)
                        .stream()
                        .map(TutorResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorResponse> fetchById( @PathVariable UUID id ){
        return this.tutorService.fetchById(id)
                .map(tutor -> ResponseEntity.ok(TutorResponse.toDto(tutor)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/login")
    public Optional<Tutor> login(String email, String senha){
        Optional<Tutor> tutor = tutorService.login(email, senha);
        if(tutor.isPresent() && tutor.get().getSenha().equals(senha)){return tutor;}

        return Optional.empty();
    }
}
