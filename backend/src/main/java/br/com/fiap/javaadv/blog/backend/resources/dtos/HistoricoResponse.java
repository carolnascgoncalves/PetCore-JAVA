package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricoResponse {
    private @Getter @Setter UUID id;

    @Builder.Default
    private @Getter @Setter StatusEnum status;

    private @Getter @Setter Set<UUID> idProntuarios;

    private @Getter @Setter UUID idPet;

    public static HistoricoResponse toDto(final Historico historico) {
        return HistoricoResponse.builder()
                .id(historico.getId())
                .status(historico.getStatus())

                .idProntuarios(
                        historico.getProntuarios()
                                .stream()
                                .map(prontuario -> prontuario.getId())
                                .collect(Collectors.toSet())
                )
                .idPet(historico.getPet() != null ? historico.getPet().getId() : null)
                .build();
    }
}