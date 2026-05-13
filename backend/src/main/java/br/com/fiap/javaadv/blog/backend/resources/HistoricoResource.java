package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import br.com.fiap.javaadv.blog.backend.resources.dtos.HistoricoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.HistoricoResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ReceitaRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ReceitaResponse;
import br.com.fiap.javaadv.blog.backend.services.HistoricoService;
import br.com.fiap.javaadv.blog.backend.services.ReceitaService;
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
@RequestMapping("/api/historico")
@RequiredArgsConstructor
public class HistoricoResource {
    private final HistoricoService historicoService;

    @PostMapping
    public ResponseEntity<HistoricoRequest> create(@Valid @RequestBody HistoricoRequest historicoRequest ){
        Historico historico = HistoricoRequest.toEntity(historicoRequest);
        Historico savedHistorico = this.historicoService.create(historico);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedHistorico.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(HistoricoRequest.toDto(savedHistorico));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<HistoricoResponse>> fetchAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(
                this.historicoService.fetchAll(pageable)
                        .stream()
                        .map(HistoricoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoResponse> fetchById( @PathVariable UUID id ){
        return this.historicoService.fetchById(id)
                .map(historico -> ResponseEntity.ok(HistoricoResponse.toDto(historico)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.historicoService.existsById(id)) {
            this.historicoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
