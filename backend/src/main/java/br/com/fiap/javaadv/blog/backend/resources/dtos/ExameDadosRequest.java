package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExameDadosRequest {
    private @Getter @Setter String nome;
    private @Getter @Setter Date data;
    private @Getter @Setter String tipo;

    public static ExameDadosRequest toDto(final Exame exame){
        return ExameDadosRequest.builder()
                .nome(exame.getNome())
                .data(exame.getData())
                .tipo(exame.getTipo())
                .build();
    }

    public static Exame toEntity(final ExameDadosRequest dto){
        return Exame.builder()
                .nome(dto.getNome())
                .data(dto.getData())
                .tipo(dto.getTipo())
                .build();
    }
}
