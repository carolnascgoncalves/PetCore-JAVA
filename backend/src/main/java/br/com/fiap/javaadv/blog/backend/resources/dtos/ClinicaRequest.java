package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Clinica;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Endereco;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicaRequest {
    private @Getter @Setter String cnpj;

    private @Getter @Setter String nome;

    private @Getter @Setter UUID idEndereco;

    public static Clinica toEntity(final ClinicaRequest dto) {

        return Clinica.builder()
                .cnpj(dto.getCnpj())
                .nome(dto.getNome())
                .endereco(Endereco.builder().id(dto.idEndereco).build())
                .build();
    }
}
