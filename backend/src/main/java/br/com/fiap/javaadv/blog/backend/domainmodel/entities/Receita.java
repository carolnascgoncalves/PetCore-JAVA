package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="receita_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(min = 2, max=100, message="O nome deve ter entre 2 à 100 caracteres")
    @Column(name="NOME_rec", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotNull(message = "A data é obrigatória")
    @Column(name="VAL_rec", nullable = false)
    private @Getter @Setter Date validade;


    //Relacionamentos
    //medico (n:1)
    @ManyToOne
    @JoinColumn(name = "ID_med_(PK)")
    private @Getter @Setter Medico medico;

    //prontuario (n:1)
    @ManyToOne
    @JoinColumn(name = "ID_pront_(PK)")
    private @Getter @Setter Prontuario prontuario;

    //medicamento (n:n)
    @ManyToMany
    @JoinTable(
            name="rec_medic_petcore",
            joinColumns = @JoinColumn(name = "ID_rec_(FK)"),
            inverseJoinColumns = @JoinColumn(name="ID_medic_(FK)")
    )
    private @Getter @Setter Set<Medicamento> medicamentos;
}
