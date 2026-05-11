package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;

import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private @Getter @Setter Set<UUID> idRelatorios;

    private @Getter @Setter Set<UUID> idProntuarios;

    private @Getter @Setter Set<UUID> idExames;


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

                .idRelatorios(
                        medico.getRelatorios()
                                .stream()
                                .map(Relatorio::getId)
                                .collect(Collectors.toSet())
                )
                .idProntuarios(
                        medico.getProntuarios()
                                .stream()
                                .map(Prontuario::getId)
                                .collect(Collectors.toSet())
                )
                .idExames(
                        medico.getExames()
                                .stream()
                                .map(Exame::getId)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
