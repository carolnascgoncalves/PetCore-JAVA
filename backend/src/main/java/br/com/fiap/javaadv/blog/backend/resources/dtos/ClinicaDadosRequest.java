package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Clinica;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Endereco;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicaDadosRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter UUID idEndereco;

    public static ClinicaDadosRequest toDto(final Clinica clinica){
        return ClinicaDadosRequest.builder()
                .nome(clinica.getNome())
                .idEndereco(clinica.getEndereco().getId())
                .build();
    }

    public static Clinica toEntity(final ClinicaDadosRequest dto){
        return Clinica.builder()
                .nome(dto.getNome())
                .endereco(Endereco.builder().id(dto.getIdEndereco()).build())
                .build();
    }
}
