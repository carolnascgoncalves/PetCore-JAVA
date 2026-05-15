package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetResponse {
    private @Getter @Setter UUID id;

    private @Getter @Setter String nome;

    private @Getter @Setter int idade;

    private @Getter @Setter Date dataNasc;

    private @Getter @Setter SexoEnum sexo;

    private @Getter @Setter String urlImg;

    private @Getter @Setter StatusEnum status;

    private @Getter @Setter String porte;

    private @Getter @Setter String especie;

    private @Getter @Setter String raca;

    private @Getter @Setter String pelagem;

    private @Getter @Setter UUID idHistorico;

    public static PetResponse toDto(final Pet pet) {

        return PetResponse.builder()
                .id(pet.getId())
                .nome(pet.getNome())
                .idade(pet.calcularIdade())
                .dataNasc(pet.getDataNascimento())
                .sexo(pet.getSexo())
                .urlImg(pet.getUrlImg())
                .status(pet.getStatus())
                .porte(pet.getPorte())
                .especie(pet.getEspecie())
                .raca(pet.getRaca())
                .pelagem(pet.getPelagem())
                .idHistorico(pet.getHistorico() != null ? pet.getHistorico().getId() : null)
                .build();
    }

}
