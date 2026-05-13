package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.PetRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.TutorRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.Set;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataTutPet(TutorRepository tutorRepository, PetRepository petRepository){
        return args -> {
            Tutor tutor1 = Tutor.builder()
                    .nome("Sandra Nascimento")
                    .dataNascimento(Date.valueOf("14-11-1968"))
                    .telefone("11992746375")
                    .email("sandraReg@gmail.com")
                    .sexo(SexoEnum.F)
                    .senha("523453466256")
                    .build();

            Tutor tutor2 = Tutor.builder()
                    .nome("Noelle Almeida")
                    .dataNascimento(Date.valueOf("18-05-1988"))
                    .telefone("11992933212")
                    .email("Nolal@gmail.com")
                    .sexo(SexoEnum.F)
                    .senha("2445356ghd5")
                    .build();

            Tutor tutor3 = Tutor.builder()
                    .nome("Lucia Lurdes")
                    .dataNascimento(Date.valueOf("23-04-1993"))
                    .telefone("11993857635")
                    .email("lulu.lurdes@gmail.com")
                    .sexo(SexoEnum.F)
                    .senha("233453564fghj")
                    .build();

            Tutor tutor4 = Tutor.builder()
                    .nome("Mario Barros")
                    .dataNascimento(Date.valueOf("27-08-2008"))
                    .telefone("11993627104")
                    .email("mariooo324@gmail.com")
                    .sexo(SexoEnum.M)
                    .senha("maa933452847")
                    .build();

            Tutor tutor5 = Tutor.builder()
                    .nome("Luis Alfredo")
                    .dataNascimento(Date.valueOf("14-11-1990"))
                    .telefone("11903725364")
                    .email("lui98437@gmail.com")
                    .sexo(SexoEnum.M)
                    .senha("92384792745")
                    .build();

            /*
            tutorRepository.save(tutor1);
            tutorRepository.save(tutor2);

            Pet pet1 = Pet.builder()
                    .nome("")
                    .especie("")
                    .raca("")
                    .dataNascimento(Date.valueOf(""))
                    .pelagem("")
                    .porte("")
                    .sexo(SexoEnum.M)
                    .status(StatusEnum.ATIVO)
                    .urlImg("")
                    .tutores(Set.of(tutor1,tutor2))
                    .build();

            petRepository.save(pet);
            manu.setPets(Set.of(pet));
            carol.setPets(Set.of(pet));
            tutorRepository.save(manu);
            tutorRepository.save(carol);

             */
        };
    }
}
