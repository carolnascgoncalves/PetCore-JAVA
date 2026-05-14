package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Clinica;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Endereco;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.ClinicaService;
import br.com.fiap.javaadv.blog.backend.services.EnderecoService;
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
@RequestMapping("/api/clinica")
@RequiredArgsConstructor
public class ClinicaResource {
    private final ClinicaService clinicaService;

    @PostMapping
    public ResponseEntity<ClinicaRequest> create(@Valid @RequestBody ClinicaRequest enderecoRequest ){
        Clinica clinica = enderecoRequest.toEntity(enderecoRequest);
        Clinica savedClinica = this.clinicaService.create(clinica);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedClinica.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(enderecoRequest.toDto(savedClinica));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClinicaDadosRequest> update(@PathVariable UUID id, @Valid @RequestBody ClinicaDadosRequest dadosDto){
        return this.clinicaService.update(id, ClinicaDadosRequest.toEntity(dadosDto))
                .map(end ->
                        ResponseEntity.ok(ClinicaDadosRequest.toDto(end)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.clinicaService.existsById(id)) {
            this.clinicaService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClinicaResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10)Pageable pageable){
        return ResponseEntity.ok(
                this.clinicaService.fetchAll(pageable)
                        .stream()
                        .map(ClinicaResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaResponse> fetchById(@PathVariable UUID id ){
        return this.clinicaService.fetchById(id)
                .map(clinica -> ResponseEntity.ok(ClinicaResponse.toDto(clinica)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
