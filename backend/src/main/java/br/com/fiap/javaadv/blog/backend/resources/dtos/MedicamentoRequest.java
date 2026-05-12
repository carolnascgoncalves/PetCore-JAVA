package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
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

    public static MedicamentoRequest toDto(final Medicamento medicamento){
        return MedicamentoRequest.builder()
                .nome(medicamento.getNome())
                .dosagem(medicamento.getDosagem())
                .instrucao(medicamento.getInstrucao())
                .build();
    }

}
