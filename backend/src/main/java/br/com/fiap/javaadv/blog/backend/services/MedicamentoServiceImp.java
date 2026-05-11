package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.MedicamentoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
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
public class MedicamentoServiceImp implements MedicamentoService{
    private final MedicamentoRepository medicamentoRepository;

    @Override
    public Medicamento create(Medicamento medicamento){
        return this.medicamentoRepository.save(medicamento);
    }

    @Override
    public Optional<Medicamento> update(UUID id, Medicamento medicamento){
        if(this.medicamentoRepository.existsById(id)){
            medicamento.setId(id);
            this.medicamentoRepository.save(medicamento);

            return Optional.of(medicamento);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Medicamento medicamento){
        medicamentoRepository.delete(medicamento);
    }

    @Override
    public void delete(UUID id){
        medicamentoRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Medicamento> fetchById(UUID id){
        return this.medicamentoRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.medicamentoRepository.existsById(id);
    }

    public Page<Medicamento> fetchAll(Pageable pageable){
        return this.medicamentoRepository.findAll(pageable);
    }

}
