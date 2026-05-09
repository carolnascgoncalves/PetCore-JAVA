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
@Table(name="endereco_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(max=100, message="O nome deve ter no máximo 100 caracteres")
    @Column(name="NOME_rec", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotNull(message = "A data é obrigatória")
    @Column(name="VAL_rec", nullable = false)
    private @Getter @Setter Date validade;


    //Relacionamentos
    //medico (n:1)
    @ManyToOne
    @JoinColumn(name = "ID_med")
    private @Getter @Setter Medico medico;

    //prontuario (n:1)
    @ManyToOne
    @JoinColumn(name = "ID_pront")
    private @Getter @Setter Prontuario prontuario;

    //medicamento (n:n)
    @ManyToMany
    @JoinTable(
            name="rec_medic_petcore",
            joinColumns = @JoinColumn(name = "ID_rec_fk"),
            inverseJoinColumns = @JoinColumn(name="ID_medic_fk")
    )
    private @Getter @Setter Set<Medicamento> medicamentos;
}
