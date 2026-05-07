package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProntuarioCadastroRequest {
    private @Getter @Setter Date data;

    private @Getter @Setter String descricao;

    private @Getter @Setter UUID idMedico;

    private @Getter @Setter Set<UUID> idExames;

    private @Getter @Setter Set<UUID> idReceitas;

    public static ProntuarioCadastroRequest toDTO(final Prontuario prontuario){
        return ProntuarioCadastroRequest.builder()
                .data(prontuario.getData())
                .descricao(prontuario.getDescricao())
                //.achar pelo ID ? sei la
                .build();
    }

}
