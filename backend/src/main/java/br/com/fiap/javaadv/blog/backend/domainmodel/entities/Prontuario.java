package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
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
    @JoinColumn(name = "ID_med_(PK)")
    private @Getter @Setter Medico medico;

    //exame (1:n)
    @OneToMany(mappedBy = "prontuario", fetch = FetchType.LAZY)
    @Builder.Default
    private @Getter @Setter Set<Exame> exames = new HashSet<>();

    //receita (1:n)
    @OneToMany(mappedBy = "prontuario", fetch = FetchType.LAZY)
    @Builder.Default
    private @Getter @Setter Set<Receita> receitas = new HashSet<>();

    //historico (n:1)
    @ManyToOne
    @JoinColumn(name = "ID_hist_(PK)")
    private @Getter @Setter Historico historico;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Prontuario that = (Prontuario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
