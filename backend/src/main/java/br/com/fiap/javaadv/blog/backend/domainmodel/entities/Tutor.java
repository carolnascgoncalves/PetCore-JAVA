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
    @Size(max=100, message="O nome deve ter no máximo 100 caracteres")
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
    @Size(max = 100, message="O email deve ter no máximo 100 caracteres")
    @Column(name="EMAIL_tut", length = 100, nullable = false)
    private @Getter @Setter String email;

    @NotBlank(message= "O sexo é obrigatorio")
    @Size( max=1, message="O sexo deve ter no maximo 1 caractere")
    @Column(name="SEXO_tut", length = 1, nullable = false)
    private @Getter @Setter SexoEnum sexo;

    @NotBlank(message="A senha é obrigatoria")
    @Size( min=8, message="O senha deve ter ao menos 8 caracteres.")
    @Column( name = "SENHA_tut", length = 30, nullable = false)
    private @Getter @Setter String senha;


    // Relacionamentos
    @ManyToMany
    @JoinTable(
            name="tut_pet_petcore",
            joinColumns = @JoinColumn(name="ID_pet_fk"),
            inverseJoinColumns = @JoinColumn(name="ID_tut_fk")
    )
    private @Getter @Setter Set<Pet> pets = new HashSet<>();
}
