package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.RelatorioService;
import br.com.fiap.javaadv.blog.backend.services.TutorService;
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
@RequestMapping("/api/relatorio")
@RequiredArgsConstructor
public class RelatorioResource {
    private final RelatorioService relatorioService;

    @PostMapping//http://localhost:8080/api/relatorio
    public ResponseEntity<RelatorioRequest> create(@Valid @RequestBody RelatorioRequest relatorioRequest ){
        Relatorio relatorio = relatorioRequest.toEntity(relatorioRequest);
        Relatorio savedRelatorio = this.relatorioService.create(relatorio);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRelatorio.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(relatorioRequest.toDto(savedRelatorio));
    }

    @PatchMapping("/{id}")//http://localhost:8080/api/relatorio/{id}
    public ResponseEntity<RelatorioDadosRequest> update(@PathVariable UUID id, @Valid @RequestBody RelatorioDadosRequest dadosDto){
        return this.relatorioService.update(id, RelatorioDadosRequest.toEntity(dadosDto))
                .map(relatorio ->
                        ResponseEntity.ok(RelatorioDadosRequest.toDto(relatorio)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }


    @GetMapping("/listar") //http://localhost:8080/api/relatorio/listar
    public ResponseEntity<List<RelatorioResponse>> fetchAll(@PageableDefault(page = 0, size = 10)Pageable pageable){
        return ResponseEntity.ok(
                this.relatorioService.fetchAll(pageable)
                        .stream()
                        .map(RelatorioResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}") //http://localhost:8080/api/relatorio/{id}
    public ResponseEntity<RelatorioResponse> fetchById( @PathVariable UUID id ){
        return this.relatorioService.fetchById(id)
                .map(relatorio -> ResponseEntity.ok(RelatorioResponse.toDto(relatorio)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.relatorioService.existsById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();

    }
}
