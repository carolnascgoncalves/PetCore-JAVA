package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.TutorRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional( propagation = Propagation.REQUIRED)
public class TutorServiceImp implements TutorService{
    private final TutorRepository tutorRepository;

    @Override
    public Tutor create(Tutor tutor){
        return this.tutorRepository.save(tutor);
    }

    @Override
    public Optional<Tutor> update(UUID id, Tutor patch) {
        return tutorRepository.findById(id)
                .map(existing -> {

                    if (patch.getNome() != null)
                        existing.setNome(patch.getNome());

                    if (patch.getDataNascimento() != null)
                        existing.setDataNascimento(patch.getDataNascimento());

                    if (patch.getTelefone() != null)
                        existing.setTelefone(patch.getTelefone());

                    if (patch.getEmail() != null)
                        existing.setEmail(patch.getEmail());

                    if (patch.getSexo() != null)
                        existing.setSexo(patch.getSexo());

                    if (patch.getSenha() != null)
                        existing.setSenha(patch.getSenha());

                    if (patch.getUrlImg() != null)
                        existing.setUrlImg(patch.getUrlImg());

                    return tutorRepository.save(existing);
                });
    }
    @Override
    public void delete(UUID id){
        tutorRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Tutor> fetchById(UUID id){
        return this.tutorRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.tutorRepository.existsById(id);
    }

    public Page<Tutor> fetchAll(Pageable pageable){
        return this.tutorRepository.findAll(pageable);
    }

    @Override
    public Optional<Tutor> fetchByEmail(String email, String senha){
        Optional<Tutor> tutor = tutorRepository.findByEmail(email);
        if(tutor.isPresent() && tutor.get().getSenha().equals(senha)){return tutor;}

        return Optional.empty();
    }
}
