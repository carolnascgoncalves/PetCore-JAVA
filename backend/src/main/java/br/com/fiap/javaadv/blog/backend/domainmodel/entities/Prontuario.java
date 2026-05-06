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
@Table(name="prontuario_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotNull(message = "A data é obrigatória")
    @Column(name="DATA_pront", nullable = false)
    private @Getter @Setter Date data;

    @NotBlank(message= "A descricao é obrigatoria")
    @Size(max=500, message="O descricao deve ter no máximo 400 caracteres")
    @Column(name="DESC_pront", length = 500, nullable = false)
    private @Getter @Setter String descricao;


    //Relacionamentos
    //medico (n:1)
    @ManyToOne
    @JoinColumn(name = "ID_med")
    private Medico medico;

    //exame (1:n)
    @OneToMany(mappedBy = "exame", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter @Setter Set<Exame> exames;

    //receita (1:n)
    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter @Setter Set<Receita> receitas;

    //historico (n:1)
    @ManyToOne
    @JoinColumn(name = "ID_hist")
    private Historico historico;


    //FOREIGN KEYS
    //fk pet (N:1)
    @ManyToOne
    @JoinColumn(name = "ID_pet")
    private Pet pet;

    //fk tutor (N:1)
    @ManyToOne
    @JoinColumn(name = "ID_tutor")
    private Tutor tutor;
}
