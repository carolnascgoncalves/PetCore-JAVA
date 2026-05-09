package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioRequest {
    private @Getter @Setter String observacao;

    private @Getter @Setter UUID idHist;

    private @Getter @Setter UUID idMedico;


    public static Relatorio toEntity(final RelatorioRequest dto) {

        return Relatorio.builder()
                .observacao(dto.getObservacao())
                .build();
    }
}
