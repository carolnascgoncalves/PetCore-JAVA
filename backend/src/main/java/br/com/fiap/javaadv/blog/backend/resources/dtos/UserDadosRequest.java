package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
//ALTERAR DADOS
public class UserDadosRequest {
    private @Getter @Setter String email;

    private @Getter @Setter String telefone;

    private @Getter @Setter String senha;

    private @Getter @Setter String urlImg;

    public static UserDadosRequest toDto(final Tutor tutor){
        return UserDadosRequest.builder()
                .email(tutor.getEmail())
                .telefone(tutor.getTelefone())
                .senha(tutor.getSenha())
                .build();
    }

    public static Tutor toEntity(final UserDadosRequest dto){
        return Tutor.builder()
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .senha(dto.getSenha())
                .build();
    }
}
