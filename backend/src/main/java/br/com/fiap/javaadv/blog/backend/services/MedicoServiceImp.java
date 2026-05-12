package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.MedicoRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.TutorRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
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
public class MedicoServiceImp implements MedicoService{
    private final MedicoRepository medicoRepository;

    @Override
    public Medico create(Medico medico){
        return this.medicoRepository.save(medico);
    }

    @Override
    public Optional<Medico> update(UUID id, Medico medico){
        if(this.medicoRepository.existsById(id)){
            medico.setId(id);
            this.medicoRepository.save(medico);

            return Optional.of(medico);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Medico medico){
        medicoRepository.delete(medico);
    }

    @Override
    public void delete(UUID id){
        medicoRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Medico> fetchByEmail(UUID id){
        return this.medicoRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.medicoRepository.existsById(id);
    }

    public Page<Medico> fetchAll(Pageable pageable){
        return this.medicoRepository.findAll(pageable);
    }

    @Override
    public Optional<Medico> fetchByEmail(String email, String senha){
        Optional<Medico> medico = medicoRepository.findByEmail(email);
        if(medico.isPresent() && medico.get().getSenha().equals(senha)){return medico;}

        return Optional.empty();
    }
}
