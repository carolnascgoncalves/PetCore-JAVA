package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExameRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter Date data;

    private @Getter @Setter String tipo;

    private @Getter @Setter UUID idMedico;

    private @Getter @Setter UUID idProntuario;

    public static Exame toEntity(final ExameRequest dto) {
        return Exame.builder()
                .nome(dto.getNome())
                .data(dto.getData())
                .tipo(dto.getTipo())
                .medico(Medico.builder().id(dto.getIdMedico()).build())
                .prontuario(Prontuario.builder().id(dto.getIdProntuario()).build())
                .build();
    }

    public static ExameRequest toDto(final Exame exame){
        return ExameRequest.builder()
                .nome(exame.getNome())
                .data(exame.getData())
                .tipo(exame.getTipo())
                .idMedico(exame.getMedico().getId())
                .idProntuario(exame.getProntuario().getId())
                .build();

    }
}
