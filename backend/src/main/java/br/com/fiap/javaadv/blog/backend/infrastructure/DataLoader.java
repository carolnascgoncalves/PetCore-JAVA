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
    CommandLineRunner initData(TutorRepository tutorRepository, PetRepository petRepository){
        return args -> {
            Tutor manu = Tutor.builder()
                    .nome("Emanuelly ventura")
                    .dataNascimento(Date.valueOf("2007-02-26"))
                    .telefone("11995142123")
                    .email("manuVentura@gmail.com")
                    .sexo(SexoEnum.F)
                    .senha("senha123")
                    .build();

            Tutor carol = Tutor.builder()
                    .nome("Carolina Nascimento")
                    .dataNascimento(Date.valueOf("2006-11-20"))
                    .telefone("11945414013")
                    .email("carolrsc@gmail.com")
                    .sexo(SexoEnum.F)
                    .senha("senha1234")
                    .build();

            Pet pet = Pet.builder()
                    .nome("Jully")
                    .especie("Cachorro")
                    .raca("Vira lata")
                    .dataNascimento(Date.valueOf("2015-12-12"))
                    .pelagem("Média")
                    .porte("Grande")
                    .sexo(SexoEnum.F)
                    .status(StatusEnum.ATIVO)
                    .tutores(Set.of(manu,carol))
                    .build();

            petRepository.save(pet);


            manu.setPets(Set.of(pet));
            carol.setPets(Set.of(pet));

            tutorRepository.save(manu);
            tutorRepository.save(carol);

            /*
            User admin = User.builder()
                    .email("admin@gmailcom")
                    .name("TIO FULADO")
                    .password("123456789DEIZ")
                    .build();

            User zemane = User.builder()
                    .email("zemane@gmailcom")
                    .name("ZE MANE")
                    .password("123456789DEIZDINOVO")
                    .build();

            userRepository.save(admin);
            userRepository.save(zemane);

            Collection<Profile> profiles = new LinkedList<>();
            profiles.add( Profile.builder()
                    .bio("Some Bio")
                    .imagePath("some image path")
                    .user( admin )
                    .build());

            profiles.add( Profile.builder()
                    .bio("Some Bio2")
                    .imagePath("some image path2")
                    .user( zemane)
                    .build());

            profileRepository.saveAll(profiles);
             */



        /*
        @Bean
        CommandLineRunner initDataBIGGGGER(ProfileRepository profileRepository, UserRepository userRepository) {
            return args -> {

                int TOTAL = 10_000;
                List<User> users = new ArrayList<>(TOTAL);

                for (int i = 1; i <= TOTAL; i++) {
                    users.add(User.builder()
                            .email("user" + i + "@gmail.com")
                            .name("User " + i)
                            .password("password" + i)
                            .build());
                }

                userRepository.saveAll(users);

                List<Profile> profiles = new ArrayList<>(TOTAL);

                for (int i = 0; i < TOTAL; i++) {
                    profiles.add(Profile.builder()
                            .bio("Bio of user " + (i + 1))
                            .imagePath("/images/user" + (i + 1) + ".png")
                            .user(users.get(i))
                            .build());
                }

                profileRepository.saveAll(profiles);
            };
            */
        };
    }
}
