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


    public static Medico toEntity(final MedicoCreateRequest dto) {

        return Medico.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .sexo(dto.getSexo())
                .senha(dto.getSenha())
                .especialidade(dto.getEspecialidade())
                .build();
    }

    public static MedicoCreateRequest toDto(final Medico medico){
        return MedicoCreateRequest.builder()
                .nome(medico.getNome())
                .dataNascimento(medico.getDataNascimento())
                .telefone(medico.getTelefone())
                .email(medico.getEmail())
                .sexo(medico.getSexo())
                .senha(medico.getSenha())
                .especialidade(medico.getEspecialidade())
                .build();
    }
}
