package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricoCadastroRequest {
    private @Getter @Setter Date data;

    private @Getter @Setter String status;

    private @Getter @Setter UUID idPet;

    private @Getter @Setter Set<UUID> idProntuarios;

    public static HistoricoCadastroRequest toDTO(final Historico historico){
        return HistoricoCadastroRequest.builder()
                .data(historico.getData())
                .status(historico.getStatus())
                .build();
    }
}
