package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetMenuResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter Integer idade;

    private @Getter @Setter SexoEnum sexo;

    public static PetMenuResponse toDTO(final Pet pet){
        return PetMenuResponse.builder()
                .id(pet.getId())
                .nome(pet.getNome())
                .idade(Pet.calcularIdade(pet.getDataNascimento()))
                .sexo(pet.getSexo())
                .build();
    }
}
