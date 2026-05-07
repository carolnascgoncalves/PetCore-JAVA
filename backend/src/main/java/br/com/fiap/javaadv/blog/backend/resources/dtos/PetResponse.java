package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter SexoEnum sexo;

    private @Getter @Setter String raca;

    private @Getter @Setter Date dataNascimento;

    //adicionar especie??

    private @Getter @Setter String pelagem;

    private @Getter @Setter String porte;

    private @Getter @Setter boolean status;

    public static PetResponse toDTO(final Pet pet){
        return PetResponse.builder()
                .id(pet.getId())
                .nome(pet.getNome())
                .sexo(pet.getSexo())
                .raca(pet.getRaca())
                .dataNascimento(pet.getDataNascimento())
                .pelagem(pet.getPelagem())
                .porte(pet.getPorte())
                .status(pet.isStatus())
                .build();
    }
}
