package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetImgRequest {
    private @Getter @Setter String urlImg;

    public static Pet toEntity(final PetImgRequest dto) {
        return Pet.builder()
                .urlImg(dto.getUrlImg())
                .build();
    }

    public static PetImgRequest toDto(final Pet pet){
        return PetImgRequest.builder()
                .urlImg(pet.getUrlImg())
                .build();
    }
}
