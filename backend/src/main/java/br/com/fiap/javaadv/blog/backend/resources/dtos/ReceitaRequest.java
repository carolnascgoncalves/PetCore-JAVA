package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceitaRequest {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter Date validade;

    private @Getter @Setter UUID idMedico;

    private @Getter @Setter UUID idTutor;

    private @Getter @Setter UUID idProntuario;

    private @Getter @Setter Set<UUID> idMedicamentos;

    public static Receita toEntity(final ReceitaRequest dto){

        return Receita.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .validade(dto.getValidade())

                .medico(Medico.builder().id(dto.getIdMedico()).build())
                .prontuario(Prontuario.builder().id(dto.getIdProntuario()).build())
                .medicamentos(
                        dto.getIdMedicamentos()
                                .stream()
                                .map(id -> Medicamento.builder().id(id).build())
                                .collect(Collectors.toSet())
                )

                .build();
    }
    public static ReceitaRequest toDto(final Receita receita){
        return ReceitaRequest.builder()
                .id(receita.getId())
                .nome(receita.getNome())
                .validade(receita.getValidade())
                .idMedico(receita.getMedico().getId())
                .idProntuario(receita.getProntuario().getId())
                .idMedicamentos(
                        receita.getMedicamentos()
                                .stream()
                                .map(Medicamento::getId)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
