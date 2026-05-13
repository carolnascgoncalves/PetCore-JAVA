package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
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

    private @Getter @Setter Set<ExameResponse> exames;

    private @Getter @Setter Set<ReceitaResponse> receitas;

    private @Getter @Setter UUID idHistorico;

    public static ProntuarioResponse toDto(final Prontuario prontuario) {

        return ProntuarioResponse.builder()
                .id(prontuario.getId())
                .data(prontuario.getData())
                .descricao(prontuario.getDescricao())

                .idMedico(prontuario.getMedico() != null ? prontuario.getMedico().getId() : null)
                .idHistorico(prontuario.getHistorico() != null ? prontuario.getHistorico().getId() : null)
                .nomeMedico(
                        prontuario.getMedico() != null ? prontuario.getMedico().getNome() : null
                )
                .exames(
                        prontuario.getExames()
                                .stream()
                                .map(ExameResponse::toDto)
                                .collect(Collectors.toSet())
                )
                .receitas(
                        prontuario.getReceitas()
                                .stream()
                                .map(ReceitaResponse::toDto)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}