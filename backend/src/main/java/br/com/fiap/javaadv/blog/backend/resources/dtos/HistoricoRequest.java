package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.*;
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

    @Builder.Default
    private @Getter @Setter StatusEnum status = StatusEnum.ATIVO;

    private @Getter @Setter Set<UUID> idProntuarios;

    private @Getter @Setter Set<UUID> idRelatorios;

    private @Getter @Setter UUID idPet;

    public static Historico toEntity(final HistoricoRequest dto) {
        return Historico.builder()
                .data(dto.getData())
                .status(dto.getStatus())
                .pet(Pet.builder().id(dto.getIdPet()).build())

                .prontuarios(dto.getIdProntuarios()
                        .stream()
                        .map(id -> Prontuario.builder()
                                .id(id)
                                .build())
                        .collect(Collectors.toSet()))

                .relatorios(dto.getIdRelatorios()
                        .stream()
                        .map(id -> Relatorio.builder()
                                .id(id)
                                .build())
                        .collect(Collectors.toSet())
                )
                .build();
    }

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
