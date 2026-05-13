package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="clinica_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O cnpj é obrigatorio")
    @Size(min= 14, max = 14, message = "O cnpj deve ter 14 caracteres")
    @Column(name = "CNPJ_cli", length = 14, nullable = false)
    private String cnpj;

    @NotBlank(message = "O nome é obrigatorio")
    @Size(max = 100, message = "O nome deve ter entre 2 à 100 caracteres")
    @Column(name = "NOME_cli", length = 100, nullable = false)
    private String nome;


    //Relacionamentos
    //relatorio (n:n)
    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "cli_rel_petcore",
            joinColumns = @JoinColumn(name="ID_cli_(FK)"),
            inverseJoinColumns = @JoinColumn(name="ID_rel_(FK)")
    )
    private Set<Relatorio> relatorios;

    //endereco(1:1)
    @OneToOne
    @JoinColumn( name = "ID_end_(FK)")
    private Endereco endereco;
}
