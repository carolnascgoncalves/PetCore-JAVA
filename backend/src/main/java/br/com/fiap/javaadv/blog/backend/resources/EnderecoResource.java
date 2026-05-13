package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Endereco;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import br.com.fiap.javaadv.blog.backend.resources.dtos.EnderecoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.EnderecoResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ExameRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ExameResponse;
import br.com.fiap.javaadv.blog.backend.services.EnderecoService;
import br.com.fiap.javaadv.blog.backend.services.ExameService;
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
@RequestMapping("/api/endereco")
@RequiredArgsConstructor
public class EnderecoResource {
    private final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoRequest> create(@Valid @RequestBody EnderecoRequest enderecoRequest ){
        Endereco endereco = enderecoRequest.toEntity(enderecoRequest);
        Endereco savedEndereco = this.enderecoService.create(endereco);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEndereco.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(enderecoRequest.toDto(savedEndereco));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EnderecoRequest> update(@PathVariable UUID id, @Valid @RequestBody EnderecoRequest dadosDto){
        return this.enderecoService.update(id, EnderecoRequest.toEntity(dadosDto))
                .map(end ->
                        ResponseEntity.ok(EnderecoRequest.toDto(end)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.enderecoService.existsById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<EnderecoRequest>> fetchAll(@PageableDefault(page = 0, size = 10)Pageable pageable){
        return ResponseEntity.ok(
                this.enderecoService.fetchAll(pageable)
                        .stream()
                        .map(EnderecoRequest::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> fetchById(@PathVariable UUID id ){
        return this.enderecoService.fetchById(id)
                .map(exame -> ResponseEntity.ok(EnderecoResponse.toDto(exame)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
