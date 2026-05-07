package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import lombok.*;

import java.sql.Date;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceitaMenuResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter Date validade;

    //fazer um método que pelo ID, retorne o nome do medico
    private @Getter @Setter UUID idMedico;

    private @Getter @Setter String nomeMedico;

    public static ReceitaMenuResponse toDTO(final Receita receita){
        return ReceitaMenuResponse.builder()
                .id(receita.getId())
                .nome(receita.getNome())
                .validade(receita.getValidade())
                .build();
    }

}
