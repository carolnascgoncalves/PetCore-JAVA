package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceitaCadastroRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter Date validade;

    private @Getter @Setter UUID idMedico;

    private @Getter @Setter Set<UUID> idMedicamentos;

    public static ReceitaCadastroRequest toDTO(final Receita receita){
        return ReceitaCadastroRequest.builder()
                .nome(receita.getNome())
                .validade(receita.getValidade())
                .build();
    }

}
