package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProntuarioResponse {

    private @Getter @Setter UUID id;

    private @Getter @Setter Date data;

    private @Getter @Setter String descricao;

    private @Getter @Setter UUID idMedico;

    private @Getter @Setter String nomeMedico;

    private @Getter @Setter Set<UUID> idExames;

    private @Getter @Setter Set<UUID> idReceitas;

    private @Getter @Setter UUID idHistorico;

    public static ProntuarioResponse toDto(final Prontuario prontuario) {

        return ProntuarioResponse.builder()
                .id(prontuario.getId())
                .data(prontuario.getData())
                .descricao(prontuario.getDescricao())

                .idMedico(prontuario.getMedico() != null ? prontuario.getMedico().getId() : null)
                .idHistorico(prontuario.getHistorico() != null ? prontuario.getHistorico().getId() : null)
                .nomeMedico(prontuario.getMedico() != null ? prontuario.getMedico().getNome() : null)
                .idExames(
                        prontuario.getExames()
                                .stream()
                                .map(Exame::getId)
                                .collect(Collectors.toSet())
                )
                .idReceitas(
                        prontuario.getReceitas()
                                .stream()
                                .map(Receita::getId)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}