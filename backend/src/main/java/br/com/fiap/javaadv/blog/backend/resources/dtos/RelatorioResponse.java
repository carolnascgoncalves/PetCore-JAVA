package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String observacao;

    private @Getter @Setter UUID idHist;

    private @Getter @Setter UUID idMedico;

    public static RelatorioResponse toDto(final Relatorio relatorio) {

        return RelatorioResponse.builder()
                .id(relatorio.getId())
                .observacao(relatorio.getObservacao())
                .idHist(relatorio.getHistorico() != null ? relatorio.getHistorico().getId() : null)
                .idMedico(relatorio.getMedico() != null ? relatorio.getMedico().getId() : null)
                .build();
    }
}
