package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.coyote.Request;

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
                .historico(Historico.builder().id(dto.getIdMedico()).build())
                .medico(Medico.builder().id(dto.getIdMedico()).build())
                .build();
    }

    public static RelatorioRequest toDto(final Relatorio relatorio){
        return RelatorioRequest.builder()
                .idHist(relatorio.getHistorico().getId())
                .idMedico(relatorio.getMedico().getId())
                .observacao(relatorio.getObservacao())
                .build();
    }
}
