package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ExameRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.MedicoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class ExameServiceImp implements ExameService{
    private final ExameRepository exameRepository;

    @Override
    public Exame create(Exame exame){
        return this.exameRepository.save(exame);
    }

    @Override
    public Optional<Exame> update(UUID id, Exame exame){
        if(this.exameRepository.existsById(id)){
            exame.setId(id);
            this.exameRepository.save(exame);

            return Optional.of(exame);
        }
        return Optional.empty();
    }

    @Override
    public void delete(UUID id){
        exameRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Exame> fetchById(UUID id){
        return this.exameRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.exameRepository.existsById(id);
    }

    public Page<Exame> fetchAll(Pageable pageable){
        return this.exameRepository.findAll(pageable);
    }

}
