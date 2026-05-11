package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ClinicaRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.MedicoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Clinica;
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
public class ClinicaServiceImp implements ClinicaService{
    private final ClinicaRepository clinicaRepository;

    @Override
    public Clinica create(Clinica clinica){
        return this.clinicaRepository.save(clinica);
    }

    @Override
    public Optional<Clinica> update(UUID id, Clinica clinica){
        if(this.clinicaRepository.existsById(id)){
            clinica.setId(id);
            this.clinicaRepository.save(clinica);

            return Optional.of(clinica);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Clinica clinica){
        clinicaRepository.delete(clinica);
    }

    @Override
    public void delete(UUID id){
        clinicaRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Clinica> fetchById(UUID id){
        return this.clinicaRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.clinicaRepository.existsById(id);
    }

    public Page<Clinica> fetchAll(Pageable pageable){
        return this.clinicaRepository.findAll(pageable);
    }

}
