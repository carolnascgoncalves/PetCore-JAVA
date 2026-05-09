package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicamentoResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter String dosagem;

    private @Getter @Setter String instrucao;

    public static MedicamentoResponse toDto(final Medicamento medicamento) {

        return MedicamentoResponse.builder()
                .id(medicamento.getId())
                .nome(medicamento.getNome())
                .dosagem(medicamento.getDosagem())
                .instrucao(medicamento.getInstrucao())
                .build();
    }
}
