package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

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

    public static Receita toEntity(final ReceitaRequest dto) {

        return Receita.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .validade(dto.getValidade())
                .build();
    }
}
