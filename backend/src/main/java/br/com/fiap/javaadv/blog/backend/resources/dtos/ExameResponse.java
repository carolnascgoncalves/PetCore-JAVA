package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExameResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;
    private @Getter @Setter Date data;
    private @Getter @Setter String tipo;
    private @Getter @Setter UUID idMedico;
    private @Getter @Setter String nomeMedico;
    private @Getter @Setter String urlImgMed;

    public static ExameResponse toDto(final Exame exame) {

        return ExameResponse.builder()
                .id(exame.getId())
                .nome(exame.getNome())
                .data(exame.getData())
                .tipo(exame.getTipo())

                .idMedico(exame.getMedico() != null ? exame.getMedico().getId() : null)
                .nomeMedico(exame.getMedico() != null ? exame.getMedico().getNome() : null)
                .urlImgMed(exame.getMedico() != null ? exame.getMedico().getUrlImg() : null)
                .build();
    }
}
