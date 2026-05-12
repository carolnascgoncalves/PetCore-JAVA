package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.HistoricoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
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
public class HistoricoServiceImp implements HistoricoService{
    private final HistoricoRepository historicoRepository;

    @Override
    public Historico create(Historico historico){
        return this.historicoRepository.save(historico);
    }

    @Override
    public void delete(UUID id){
        historicoRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Historico> fetchById(UUID id){
        return this.historicoRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.historicoRepository.existsById(id);
    }

    public Page<Historico> fetchAll(Pageable pageable){
        return this.historicoRepository.findAll(pageable);
    }

}
