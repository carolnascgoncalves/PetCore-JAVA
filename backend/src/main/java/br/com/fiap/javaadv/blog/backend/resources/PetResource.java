package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.PetService;
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
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetResource {
    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetRequest> create(@Valid @RequestBody PetRequest petRequest ){
        Pet pet = petRequest.toEntity(petRequest);
        Pet savedPet = this.petService.create(pet);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPet.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(petRequest.toDto(savedPet));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PetRequest> update(@PathVariable UUID id, @Valid @RequestBody PetRequest dadosDto){
        return this.petService.update(id, PetRequest.toEntity(dadosDto))
                .map(pet ->
                        ResponseEntity.ok(PetRequest.toDto(pet)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PetRequest> updateStatus(@PathVariable UUID id, @Valid @RequestBody PetRequest dadosDto){
        return this.petService.update(id, PetRequest.toEntity(dadosDto))
                .map(pet ->
                        ResponseEntity.ok(PetRequest.toDto(pet)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.petService.existsById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<PetResponse>> fetchAll(Pageable pageable){
        return ResponseEntity.ok(
                this.petService.fetchAll(pageable)
                        .stream()
                        .map(PetResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping
    public ResponseEntity<List<PetMenuResponse>> fetchMenuAll(Pageable pageable){
        return ResponseEntity.ok(
                this.petService.fetchAll(pageable)
                        .stream()
                        .map(PetMenuResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> fetchById( @PathVariable UUID id ){
        return this.petService.fetchById(id)
                .map(pet -> ResponseEntity.ok(PetResponse.toDto(pet)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
