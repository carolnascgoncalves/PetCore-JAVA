package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name="exame_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(max=100, message="O nome deve ter no máximo 100 caracteres")
    @Column(name="NOME_ex", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotNull(message = "A data é obrigatória")
    @Column(name="DATA_ex", nullable = false)
    private @Getter @Setter Date data;

    @NotBlank(message= "O tipo do exame é obrigatorio")
    @Size(max=100, message="O tipo do exame deve ter no máximo 100 caracteres")
    @Column(name="TP_ex", length = 100, nullable = false)
    private @Getter @Setter String tipo;


    //Relacionamentos
    //medico(n:1)
    @ManyToOne
    @JoinColumn(name = "ID_med_(PK)")
    private @Getter @Setter Medico medico;

    //prontuario(n:1)
    @ManyToOne
    @JoinColumn(name = "ID_pront_(PK)")
    private @Getter @Setter Prontuario prontuario;


}
