package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;

import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicoResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter Date dataNascimento;

    private @Getter @Setter String telefone;

    private @Getter @Setter String email;

    private @Getter @Setter SexoEnum sexo;

    private @Getter @Setter String especialidade;

    private @Getter @Setter String urlImg;

    public static MedicoResponse toDto(final Medico medico) {

        return MedicoResponse.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .dataNascimento(medico.getDataNascimento())
                .telefone(medico.getTelefone())
                .email(medico.getEmail())
                .sexo(medico.getSexo())
                .especialidade(medico.getEspecialidade())
                .urlImg(medico.getUrlImg())
                .build();
    }
}
