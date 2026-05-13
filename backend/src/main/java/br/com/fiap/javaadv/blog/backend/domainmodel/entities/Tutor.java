package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="tutor_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(min = 2, max=100, message="O nome deve ter entre 2 à 100 caracteres")
    @Column(name="NOME_tut", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotNull(message = "A data é obrigatória")
    @Column(name="DATA_NASC_tut", nullable = false)
    private @Getter @Setter Date dataNascimento;

    @NotBlank(message= "O telefone é obrigatorio")
    @Size(max=11, message="O telefone deve ter no máximo 11 caracteres")
    @Column(name="TEL_tut", length = 11, nullable = false)
    private @Getter @Setter String telefone;

    @NotBlank( message = "O email é obrigatorio")
    @Email(message="O email deve ser válido")
    @Size(min = 2, max = 100, message="O email deve ter entre 2 à 100 caracteres")
    @Column(name="EMAIL_tut", length = 100, nullable = false)
    private @Getter @Setter String email;

    @NotNull(message = "O sexo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name="SEXO_pet", nullable = false)
    private @Getter @Setter SexoEnum sexo;

    @NotBlank(message="A senha é obrigatoria")
    @Size( min=8, max = 30, message="O senha deve ter entre 2 à 30 caracteres.")
    @Column( name = "SENHA_tut", length = 30, nullable = false)
    private @Getter @Setter String senha;

    @Column(name="URL_IMG_tut")
    private @Getter @Setter String urlImg;

    // Relacionamentos
    @ManyToMany
    @JoinTable(
            name="tut_pet_petcore",
            joinColumns = @JoinColumn(name="ID_tut_(FK)"),
            inverseJoinColumns = @JoinColumn(name="ID_pet_(FK)")
    )
    @Builder.Default
    private @Getter @Setter Set<Pet> pets = new HashSet<>();
}
