package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProntuarioRequest {

    private @Getter @Setter Date data;

    private @Getter @Setter String descricao;

    private @Getter @Setter UUID idMedico;

    private @Getter @Setter UUID idHistorico;

    public static Prontuario toEntity(final ProntuarioRequest dto) {

        return Prontuario.builder()
                .data(dto.getData())
                .descricao(dto.getDescricao())
                .build();
    }
}