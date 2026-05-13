package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
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
                .urlImg(tutor.getUrlImg())
                .build();
    }

    public static Tutor toEntity(final UserDadosRequest dto){
        return Tutor.builder()
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .senha(dto.getSenha())
                .urlImg(dto.getUrlImg())
                .build();
    }

    public static UserDadosRequest toDtoMed(final Medico medico){
        return UserDadosRequest.builder()
                .email(medico.getEmail())
                .telefone(medico.getTelefone())
                .senha(medico.getSenha())
                .urlImg(medico.getUrlImg())
                .build();
    }

    public static Medico toEntityMed(final UserDadosRequest dto){
        return Medico.builder()
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .senha(dto.getSenha())
                .urlImg(dto.getUrlImg())
                .build();
    }
}
