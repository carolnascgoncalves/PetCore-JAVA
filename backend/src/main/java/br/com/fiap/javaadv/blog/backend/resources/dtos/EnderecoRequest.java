package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Clinica;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoRequest {
    private @Getter @Setter String cep;

    private @Getter @Setter String complemento;

    public static Endereco toEntity(final EnderecoRequest dto) {

        return Endereco.builder()
                .cep(dto.getCep())
                .complemento(dto.getComplemento())
                .build();
    }

    public static EnderecoRequest toDto(final Endereco endereco){
        return EnderecoRequest.builder()
                .cep(endereco.getCep())
                .complemento(endereco.getComplemento())
                .build();
    }
}
