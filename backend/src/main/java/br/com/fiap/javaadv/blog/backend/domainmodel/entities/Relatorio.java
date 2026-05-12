package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="relatorio_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @Size(max = 500, message = "A observacao deve ter no maximo 500 caracteres")
    @Column(name="OBS_rel", length = 500)
    private @Getter @Setter String observacao;


    //Relacionamentos
    //historico(n:1)
    @ManyToOne
    @JoinColumn(name = "ID_hist_(PK)")
    private @Getter @Setter Historico historico;

    //medico(N:1)
    @ManyToOne
    @JoinColumn(name = "ID_med_(PK)")
    private @Getter @Setter Medico medico;

    //clinica (n:n)
    @ManyToMany( mappedBy = "relatorios", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Clinica> clinicas;
}
