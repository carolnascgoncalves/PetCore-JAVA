package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.*;
import lombok.*;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProntuarioRequest {

    private @Getter @Setter Date data;

    private @Getter @Setter String descricao;

    private @Getter @Setter UUID idMedico;

    private @Getter @Setter UUID idHistorico;

    @Builder.Default
    private @Getter @Setter Set<UUID> idExames = new HashSet<>(); //pode ou não ser nulo

    @Builder.Default
    private @Getter @Setter Set<UUID> idReceitas = new HashSet<>(); //pode ou não ser nulo


    public static Prontuario toEntity(final ProntuarioRequest dto) {
        return Prontuario.builder()
                .data(dto.getData())
                .descricao(dto.getDescricao())
                .medico(Medico.builder().id(dto.getIdMedico()).build())
                .historico(Historico.builder().id(dto.getIdHistorico()).build())

                .receitas(dto.getIdReceitas()
                        .stream()
                        .map(id -> Receita.builder()
                                                .id(id)
                                                .build())
                        .collect(Collectors.toSet()))

                .exames(dto.getIdExames()
                        .stream()
                        .map(id -> Exame.builder()
                                                .id(id)
                                                .build())
                        .collect(Collectors.toSet())
                )
                .build();
    }

    public static ProntuarioRequest toDto(final Prontuario prontuario){
        return ProntuarioRequest.builder()
                .data(prontuario.getData())
                .descricao(prontuario.getDescricao())
                .idMedico(prontuario.getMedico().getId())
                .idHistorico(prontuario.getHistorico().getId())

                .idReceitas(prontuario.getReceitas()
                                .stream()
                                .map(Receita::getId)
                                .collect(Collectors.toSet()))

                .idExames(prontuario.getExames()
                                .stream()
                                .map(Exame::getId)
                                .collect(Collectors.toSet()))
                .build();
    }
}