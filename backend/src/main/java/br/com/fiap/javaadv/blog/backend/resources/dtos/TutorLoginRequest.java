package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/// Login do tutor no petCore
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TutorLoginRequest {
    private @Getter @Setter String email;

    private @Getter @Setter String senha;

    public static TutorLoginRequest toDTO(final Tutor tutor){
        return TutorLoginRequest.builder()
                .email(tutor.getEmail())
                .senha(tutor.getSenha())
                .build();
    }

    public static Tutor toEntity(final TutorLoginRequest dto){
        return Tutor.builder()
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
    }
}
