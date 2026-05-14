package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.*;

@Entity
@Table(name="historico_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotNull(message = "A data é obrigatória")
    @Column(name="DATA_hist",nullable = false)
    private @Getter @Setter Date data;

    @Column(name="STATUS_hist", length = 15)
    private @Getter @Setter StatusEnum status;


    //Relacionamentos
    //pet (1:1)
    @OneToOne(mappedBy = "historico")
    private @Getter @Setter Pet pet;

    //relatorio(1:n)
    @OneToMany(mappedBy = "historico", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Relatorio> relatorios;

    //prontuario(1:n)
    @OneToMany(mappedBy = "historico", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Prontuario> prontuarios;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Historico historico = (Historico) o;
        return Objects.equals(id, historico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
