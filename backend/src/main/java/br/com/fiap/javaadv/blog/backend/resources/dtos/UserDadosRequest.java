package br.com.fiap.javaadv.blog.backend.resources.dtos;

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
}
