package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricoRequest {
    private @Getter @Setter Date data;

    private @Getter @Setter StatusEnum status = StatusEnum.ATIVO;

    private @Getter @Setter Set<UUID> idProntuarios;

    private @Getter @Setter Set<UUID> idRelatorios;

    private @Getter @Setter UUID idPet;


    public static HistoricoRequest toDto(final Historico historico) {
        return HistoricoRequest.builder()
                .data(historico.getData())
                .status(historico.getStatus())

                .idProntuarios(
                        historico.getProntuarios()
                                .stream()
                                .map(prontuario -> prontuario.getId())
                                .collect(Collectors.toSet())
                )
                .idRelatorios(
                        historico.getRelatorios()
                                .stream()
                                .map(relatorio -> relatorio.getId())
                                .collect(Collectors.toSet())
                )
                .idPet(historico.getPet().getId())
                .build();
    }
}
