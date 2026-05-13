package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.MedicamentoService;
import br.com.fiap.javaadv.blog.backend.services.ProntuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/medicamentos")
@RequiredArgsConstructor
public class MedicamentoResource {
    private final MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<MedicamentoRequest> create(@Valid @RequestBody MedicamentoRequest medicRequest ){
        Medicamento medicamento = medicRequest.toEntity(medicRequest);
        Medicamento savedMedic = this.medicamentoService.create(medicamento);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMedic.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(medicRequest.toDto(savedMedic));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MedicamentoRequest> update(@PathVariable UUID id, @Valid @RequestBody MedicamentoRequest dadosDto){
        return this.medicamentoService.update(id, MedicamentoRequest.toEntity(dadosDto))
                .map(medicamento ->
                        ResponseEntity.ok(MedicamentoRequest.toDto(medicamento)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.medicamentoService.existsById(id)) {
            this.medicamentoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MedicamentoResponse>> fetchAll(@PageableDefault(page = 0, size = 10)Pageable pageable){
        return ResponseEntity.ok(
                this.medicamentoService.fetchAll(pageable)
                        .stream()
                        .map(MedicamentoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> fetchById( @PathVariable UUID id ){
        return this.medicamentoService.fetchById(id)
                .map(medicamento -> ResponseEntity.ok(MedicamentoResponse.toDto(medicamento)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
