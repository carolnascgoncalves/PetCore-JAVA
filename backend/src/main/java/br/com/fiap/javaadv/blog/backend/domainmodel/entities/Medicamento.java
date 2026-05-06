package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="endereco_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotBlank(message = "O nome é obrigatorio")
    @Size(max = 100, message = "O nome deve ter no maximo 100 caracteres")
    @Column(name = "NOME_medic", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotBlank(message = "A dosagem é obrigatoria")
    @Size(max = 15, message = "A dosagem deve ter no maximo 15 caracteres")
    @Column(name = "DOSAGEM_medic", length = 15, nullable = false)
    private @Getter @Setter String dosagem;

    @NotBlank(message = "A instrucao é obrigatorio")
    @Size(max = 200, message = "A instrucao deve ter no maximo 200 caracteres")
    @Column(name = "INSTRC_medic", length = 200, nullable = false)
    private @Getter @Setter String instrucao;


    //Relacionamentos
    //receita (n:n)
    @ManyToMany(mappedBy = "medicamentos")
    private @Getter @Setter Set<Receita> receitas;

}
