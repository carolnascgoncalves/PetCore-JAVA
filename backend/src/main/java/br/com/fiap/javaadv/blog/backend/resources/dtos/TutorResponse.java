package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;

import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TutorResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter Date dataNascimento;

    private @Getter @Setter String telefone;

    private @Getter @Setter String email;

    private @Getter @Setter SexoEnum sexo;

    private @Getter @Setter String urlImg;

    public static TutorResponse toDto(final Tutor tutor) {

        return TutorResponse.builder()
                .id(tutor.getId())
                .nome(tutor.getNome())
                .dataNascimento(tutor.getDataNascimento())
                .telefone(tutor.getTelefone())
                .email(tutor.getEmail())
                .sexo(tutor.getSexo())
                .urlImg(tutor.getUrlImg())
                .build();
    }
}
