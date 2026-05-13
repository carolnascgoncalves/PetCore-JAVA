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
    private @Getter @Setter String nome;

    private @Getter @Setter Date validade;

    private @Getter @Setter UUID idMedico;

    private @Getter @Setter Set<UUID> idMedicamentos;

    public static Receita toEntity(final ReceitaRequest dto){

        return Receita.builder()
                .nome(dto.getNome())
                .validade(dto.getValidade())

                .medico(Medico.builder().id(dto.getIdMedico()).build())
                .medicamentos(
                        dto.getIdMedicamentos() != null ? dto.getIdMedicamentos()
                                .stream()
                                .map(id -> Medicamento.builder().id(id).build())
                                .collect(Collectors.toSet()) : Set.of()
                )

                .build();
    }
    public static ReceitaRequest toDto(final Receita receita){
        return ReceitaRequest.builder()
                .nome(receita.getNome())
                .validade(receita.getValidade())
                .idMedico(receita.getMedico().getId())
                .idMedicamentos(
                        receita.getMedicamentos()
                                .stream()
                                .map(Medicamento::getId)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
