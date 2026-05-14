package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.ReceitaService;
import br.com.fiap.javaadv.blog.backend.services.RelatorioService;
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
@RequestMapping("/api/receita")
@RequiredArgsConstructor
public class ReceitaResource {
    private final ReceitaService receitaService;

    @PostMapping
    public ResponseEntity<ReceitaRequest> create(@Valid @RequestBody ReceitaRequest receitaRequest ){
        Receita receita = ReceitaRequest.toEntity(receitaRequest);
        Receita savedReceita = this.receitaService.create(receita);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedReceita.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(ReceitaRequest.toDto(savedReceita));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ReceitaResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10)Pageable pageable){
        return ResponseEntity.ok(
                this.receitaService.fetchAll(pageable)
                        .stream()
                        .map(ReceitaResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaResponse> fetchById( @PathVariable UUID id ){
        return this.receitaService.fetchById(id)
                .map(receita -> ResponseEntity.ok(ReceitaResponse.toDto(receita)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){

        if(this.receitaService.existsById(id)){
            this.receitaService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
