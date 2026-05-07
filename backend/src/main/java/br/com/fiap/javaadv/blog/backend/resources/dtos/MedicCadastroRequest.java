package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicCadastroRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter String dosagem;

    private @Getter @Setter String instrucao;

    public static MedicCadastroRequest toDTO(final Medicamento medicamento){
        return MedicCadastroRequest.builder()
                .nome(medicamento.getNome())
                .dosagem(medicamento.getDosagem())
                .instrucao(medicamento.getInstrucao())
                .build();
    }

}
