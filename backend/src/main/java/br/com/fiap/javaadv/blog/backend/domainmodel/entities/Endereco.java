package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="endereco_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotBlank(message = "O cep é obrigatorio")
    @Size(min = 8, max = 8, message = "O cep deve ter 8 caracteres")
    @Column(name = "CEP_end", length = 8, nullable = false)
    private @Getter @Setter String cep;

    @Column(name = "COMPL_end", length = 200)
    @Size(min = 2,max = 200, message = "O complemento deve ter entre 2 à 200 caracteres")
    private @Getter @Setter String complemento;


    //Relacionamentos
    @OneToOne( mappedBy = "endereco")
    private @Getter @Setter Clinica clinica;
}
