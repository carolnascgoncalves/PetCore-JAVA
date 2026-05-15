package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicamentoDadosRequest {
    private @Getter @Setter String dosagem;

    private @Getter @Setter String instrucao;

    public static Medicamento toEntity(final MedicamentoDadosRequest dto) {
        return Medicamento.builder()
                .dosagem(dto.getDosagem())
                .instrucao(dto.getInstrucao())
                .build();
    }

    public static MedicamentoDadosRequest toDto(final Medicamento medicamento){
        return MedicamentoDadosRequest.builder()
                .dosagem(medicamento.getDosagem())
                .instrucao(medicamento.getInstrucao())
                .build();
    }
}
