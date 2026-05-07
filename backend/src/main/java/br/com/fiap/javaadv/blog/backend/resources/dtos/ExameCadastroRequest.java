package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExameCadastroRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter Date data;

    private @Getter @Setter String tipo;

    public static ExameCadastroRequest toDTO(final Exame exame){
        return ExameCadastroRequest.builder()
                .nome(exame.getNome())
                .data(exame.getData())
                .tipo(exame.getTipo())
                .build();
    }
}
