package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import jdk.jshell.Snippet;
import lombok.*;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter Date dataNasc;

    private @Getter @Setter SexoEnum sexo;

    private @Getter @Setter String urlImg;

    private @Getter @Setter StatusEnum status;

    private @Getter @Setter String porte;

    private @Getter @Setter String especie;

    private @Getter @Setter String raca;

    private @Getter @Setter String pelagem;

    private @Getter @Setter UUID idHistorico;

    public static Pet toEntity(final PetRequest dto){
        return Pet.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNasc())
                .sexo(dto.getSexo())
                .urlImg(dto.getUrlImg())
                .status(dto.getStatus())
                .porte(dto.getPorte())
                .especie(dto.getEspecie())
                .raca(dto.getRaca())
                .pelagem(dto.getPelagem())
                .historico(dto.getIdHistorico() != null ? Historico.builder().id(dto.getIdHistorico()).build() : null)
                .build();
    }

    public static PetRequest toDto(final Pet pet) {

        return PetRequest.builder()
                .nome(pet.getNome())
                .dataNasc(pet.getDataNascimento())
                .sexo(pet.getSexo())
                .urlImg(pet.getUrlImg())
                .status(pet.getStatus())
                .porte(pet.getPorte())
                .especie(pet.getEspecie())
                .raca(pet.getRaca())
                .pelagem(pet.getPelagem())
                .idHistorico(pet.getHistorico().getId())
                .build();
    }

}
