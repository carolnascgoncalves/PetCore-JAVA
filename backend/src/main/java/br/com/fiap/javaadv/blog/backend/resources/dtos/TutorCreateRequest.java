package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Profile;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/// Criação do tutor no petCore
public class TutorCreateRequest {
    @NotBlank( message="O nome é obrigatório.")
    @Size(min=2, max=100, message="Tamanho do nome errôneo. Deve estar entre 2 e 100 caracteres.")
    private @Getter @Setter String nome;

    @NotNull(message = "A data de nascimento é obrigatória")
    private @Getter @Setter Date dataNascimento;

    @NotBlank( message="O telefone é obrigatório.")
    @Size(max = 11, message="Tamanho do telefone errôneo. Deve conter 11 caracteres")
    private @Getter @Setter String telefone;

    @NotBlank( message="O sexo é obrigatório.")
    @Size(max=1, message="Tamanho do sexo errôneo. Deve conter 1 caractere")
    private @Getter @Setter char sexo;

    @Email
    @NotBlank( message="O email é obrigatório.")
    @Size(min=2, max=100, message="Tamanho do email errôneo. Deve estar entre 2 e 100 caracteres.")
    private @Getter @Setter String email;

    @NotBlank( message="A senha é obrigatório.")
    @Size(min=8, max=30, message="Tamanho da senha errôneo. Deve estar entre 8 e 30 caracteres.")
    private @Getter @Setter String senha;


    public static TutorCreateRequest toDTO(final Tutor tutor){
        return null;
    }

    public static Tutor toEntity(){
        return null;
    }

    /*
    public static ProfileDTO fromEntity( final Profile profile ) {
        return ProfileDTO.builder()
                .id(profile.getId())
                .bio(profile.getBio())
                .imagePath(profile.getImagePath())
                .userId(profile.getUser() != null ? profile.getUser().getId() : null)
                .build();
    }

    public static Profile fromDTO( final ProfileDTO dto ){
        return Profile.builder()
                .id(dto.getId())
                .bio(dto.getBio())
                .imagePath( dto.getImagePath())
                //.user()
                .build();
    }
    */

}

