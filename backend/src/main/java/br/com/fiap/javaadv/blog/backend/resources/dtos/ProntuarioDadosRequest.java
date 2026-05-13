package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProntuarioDadosRequest {
    private @Getter @Setter String descricao;

    public static Prontuario toEntity(final ProntuarioDadosRequest dto) {
        return Prontuario.builder()
                .descricao(dto.getDescricao())
                .build();
    }

    public static ProntuarioDadosRequest toDto(final Prontuario prontuario){
        return ProntuarioDadosRequest.builder()
                .descricao(prontuario.getDescricao())
                .build();
    }
}
