package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Endereco;
import jakarta.persistence.Column;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String cep;

    private @Getter @Setter String complemento;

    private @Getter @Setter UUID idClinica;

    public static EnderecoResponse toDto(final Endereco endereco) {

        return EnderecoResponse.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .complemento(endereco.getComplemento())
                .idClinica(endereco.getClinica() != null ? endereco.getClinica().getId() : null)
                .build();
    }
}
