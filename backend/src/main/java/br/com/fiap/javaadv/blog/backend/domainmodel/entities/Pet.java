package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name="pet_petcore")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(max=100, message="O nome deve ter no máximo 100 caracteres")
    @Column(name="NOME_pet", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotBlank(message= "A especie é obrigatoria")
    @Size(max=100, message="A especie deve ter no máximo 150 caracteres")
    @Column(name="ESPECIE_pet", length = 150, nullable = false)
    private @Getter @Setter String especie;

    @NotBlank(message= "A raca é obrigatoria")
    @Size(max=150, message="A raca deve ter no máximo 150 caracteres")
    @Column(name="RACA_pet", length = 150, nullable = false)
    private @Getter @Setter String raca;

    @NotNull(message = "A data é obrigatória")
    @Column(name="DATA_NASC_pet", nullable = false)
    private @Getter @Setter Date dataNascimento;

    @NotBlank(message= "A pelagem é obrigatoria")
    @Size(max=150, message="A pelagem deve ter no máximo 150 caracteres")
    @Column(name="PELAGEM_pet", length = 150, nullable = false)
    private @Getter @Setter String pelagem;

    @NotBlank(message= "O porte é obrigatorio")
    @Size(max=25, message="O porte deve ter no máximo 25 caracteres")
    @Column(name="PORTE_pet", length = 25, nullable = false)
    private @Getter @Setter String porte;

    @NotNull(message = "O sexo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name="SEXO_pet", nullable = false)
    private @Getter @Setter SexoEnum sexo;

    @Column(name="STATUS_pet")
    private @Getter @Setter StatusEnum status;

    @Column(name="URL_IMG_pet")
    private @Getter @Setter String urlImg;

    //Relacionamentos
    //tutores(n:n)
    @JsonIgnore
    @ManyToMany( mappedBy = "pets", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Tutor> tutores;

    //historico(1:1)
    @OneToOne
    @JoinColumn(name = "ID_hist_(PK)")
    private @Getter @Setter Historico historico;



    public int calcularIdade() {
        LocalDate nascimento = dataNascimento.toLocalDate();
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

}
