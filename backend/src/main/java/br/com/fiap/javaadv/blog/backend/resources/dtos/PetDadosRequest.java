package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDadosRequest {
    private @Getter @Setter StatusEnum statusEnum;

    public static Pet toEntity(final PetDadosRequest dto) {
        return Pet.builder()
                .status(dto.getStatusEnum())
                .build();
    }

    public static PetDadosRequest toDto(final Pet pet){
        return PetDadosRequest.builder()
                .statusEnum(pet.getStatus())
                .build();
    }
}
