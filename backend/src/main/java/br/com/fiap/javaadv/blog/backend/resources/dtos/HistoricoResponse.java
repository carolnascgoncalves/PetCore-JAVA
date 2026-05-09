package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricoResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter List<ProntuarioResponse> prontuarios;

    public static HistoricoResponse toDto(final Historico historico) {

        return HistoricoResponse.builder()
                .id(historico.getId())

                .prontuarios(
                        historico.getProntuarios()
                                .stream()
                                .map(ProntuarioResponse::toDto)
                                .toList()
                )

                .build();
    }
}