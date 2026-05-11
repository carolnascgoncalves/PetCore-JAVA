package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceitaResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;
    private @Getter @Setter Date validade;
    private @Getter @Setter UUID idMedico;
    private @Getter @Setter String nomeMedico;
    private @Getter @Setter String urlImgMed;
    private @Getter @Setter Set<UUID> idMedicamentos;

    public static ReceitaResponse toDto(final Receita receita) {

        return ReceitaResponse.builder()
                .id(receita.getId())
                .nome(receita.getNome())
                .validade(receita.getValidade())

                .idMedico(receita.getMedico() != null ? receita.getMedico().getId() : null)
                .nomeMedico(receita.getMedico() != null ? receita.getMedico().getNome() : null)
                .urlImgMed(receita.getMedico() != null ? receita.getMedico().getUrlImg() : null)
                .idMedicamentos(
                        receita.getMedicamentos()
                                .stream()
                                .map(Medicamento::getId)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
