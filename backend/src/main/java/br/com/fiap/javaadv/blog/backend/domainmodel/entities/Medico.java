package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="medico_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Medico {
    @Id
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(min = 2, max=100, message="O nome deve ter entre 2 à 100 caracteres")
    @Column(name="NOME_med", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotNull(message = "A data é obrigatória")
    @Column(name="DATA_NASC_med", nullable = false)
    private @Getter @Setter Date dataNascimento;

    @NotBlank(message= "O telefone é obrigatorio")
    @Size(min = 11, max=11, message="O telefone deve ter 11 caracteres")
    @Column(name="TEL_med", length = 11, nullable = false)
    private @Getter @Setter String telefone;

    @NotBlank( message = "O email é obrigatorio")
    @Email(message="O email deve ser válido")
    @Size(min = 2, max = 100, message="O email deve ter entre 2 à 100 caracteres")
    @Column(name="EMAIL_med", length = 100, nullable = false)
    private @Getter @Setter String email;

    @NotNull(message = "O sexo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name="SEXO_pet", nullable = false)
    private @Getter @Setter SexoEnum sexo;

    @NotBlank(message="A senha é obrigatoria")
    @Size( min=8, max=30, message="O senha deve ter entre 8 à 30 caracteres.")
    @Column( name = "SENHA_med", length = 30, nullable = false)
    private @Getter @Setter String senha;

    @NotBlank(message= "A especialidade é obrigatoria")
    @Size(max=200, message="A especialidade deve ter no máximo 200 caracteres")
    @Column(name="ESPEC_med", length = 200, nullable = false)
    private @Getter @Setter String especialidade;

    @Column(name="URL_IMG_med")
    private @Getter @Setter String urlImg;

    //Relacionamentos
    //receita (1:n)
    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Relatorio> relatorios;

    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Prontuario> prontuarios;

    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Exame> exames;

    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Receita> receitas;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(id, medico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @PrePersist
    public void gerarId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
