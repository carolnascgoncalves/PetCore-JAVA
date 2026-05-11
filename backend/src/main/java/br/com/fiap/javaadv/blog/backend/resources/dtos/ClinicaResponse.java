package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Clinica;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicaResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String cnpj;

    private @Getter @Setter String nome;

    private @Getter @Setter UUID idEndereco;

    public static ClinicaResponse toDto(final Clinica clinica) {

        return ClinicaResponse.builder()
                .id(clinica.getId())
                .cnpj(clinica.getCnpj())
                .nome(clinica.getNome())

                .idEndereco(clinica.getEndereco() != null ? clinica.getEndereco().getId() : null)
                .build();
    }
}
