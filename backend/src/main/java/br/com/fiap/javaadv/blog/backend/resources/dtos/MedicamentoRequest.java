package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicamentoRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter String dosagem;

    private @Getter @Setter String instrucao;

    public static Medicamento toEntity(final MedicamentoRequest dto) {

        return Medicamento.builder()
                .nome(dto.getNome())
                .dosagem(dto.getDosagem())
                .instrucao(dto.getInstrucao())
                .build();
    }

}
