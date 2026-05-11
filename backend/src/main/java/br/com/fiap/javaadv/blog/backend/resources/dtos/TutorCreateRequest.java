package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TutorCreateRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter Date dataNascimento;

    private @Getter @Setter String telefone;

    private @Getter @Setter SexoEnum sexo;

    private @Getter @Setter String email;

    private @Getter @Setter String senha;

    public static Tutor toEntity(final TutorCreateRequest dto) {

        return Tutor.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .telefone(dto.getTelefone())
                .sexo(dto.getSexo())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
    }

    public static TutorCreateRequest toDto(final Tutor tutor){
        return TutorCreateRequest.builder()
                .nome(tutor.getNome())
                .dataNascimento(tutor.getDataNascimento())
                .telefone(tutor.getTelefone())
                .sexo(tutor.getSexo())
                .email(tutor.getEmail())
                .senha(tutor.getSenha())
                .build();
    }
}

