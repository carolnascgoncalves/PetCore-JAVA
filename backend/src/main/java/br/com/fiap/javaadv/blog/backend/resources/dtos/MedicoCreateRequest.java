package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicoCreateRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter Date dataNascimento;

    private @Getter @Setter String telefone;

    private @Getter @Setter String email;

    private @Getter @Setter SexoEnum sexo;

    private @Getter @Setter String senha;

    private @Getter @Setter String especialidade;

    private @Getter @Setter String urlImg;

    public static Medico toEntity(final MedicoCreateRequest dto) {

        return Medico.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .sexo(dto.getSexo())
                .senha(dto.getSenha())
                .especialidade(dto.getEspecialidade())
                .urlImg(dto.getUrlImg())
                .build();
    }
}
