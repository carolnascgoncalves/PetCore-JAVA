package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioDadosRequest {
    private @Getter @Setter String observacao;

    public static Relatorio toEntity(final RelatorioDadosRequest dto) {
        return Relatorio.builder()
                .observacao(dto.getObservacao())
                .build();
    }

    public static RelatorioDadosRequest toDto(final Relatorio relatorio){
        return RelatorioDadosRequest.builder()
                .observacao(relatorio.getObservacao())
                .build();
    }
}
