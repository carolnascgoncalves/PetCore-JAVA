package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetMenuResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter int idade;

    private @Getter @Setter SexoEnum sexo;

    private @Getter @Setter String urlImg;

    public static PetMenuResponse toDto(final Pet pet) {

        return PetMenuResponse.builder()
                .id(pet.getId())
                .nome(pet.getNome())
                .idade(pet.calcularIdade())
                .sexo(pet.getSexo())
                .urlImg(pet.getUrlImg())
                .build();
    }
}
