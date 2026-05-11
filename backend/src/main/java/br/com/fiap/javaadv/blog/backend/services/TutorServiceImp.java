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
    public Optional<Tutor> update(UUID id, Tutor tutor){
        if(this.tutorRepository.existsById(id)){
            tutor.setId(id);
            this.tutorRepository.save(tutor);

            return Optional.of(tutor);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Tutor tutor){
        tutorRepository.delete(tutor);
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
    public Optional<Tutor> login(String email, String senha){
        Optional<Tutor> tutor = tutorRepository.findByEmail(email);
        if(tutor.isPresent() && tutor.get().getSenha().equals(senha)){return tutor;}

        return Optional.empty();
    }
}
