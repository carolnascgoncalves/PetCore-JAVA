package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import lombok.*;

import java.sql.Date;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExameMenuResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter Date data;

    //fazer um método que pelo ID, retorne o nome do medico
    private @Getter @Setter UUID idMedico;

    private @Getter @Setter String nomeMedico;

    public static ExameMenuResponse toDTO(final Exame exame){
        return ExameMenuResponse.builder()
                .id(exame.getId())
                .nome(exame.getNome())
                .data(exame.getData())
                .build();
    }

}
