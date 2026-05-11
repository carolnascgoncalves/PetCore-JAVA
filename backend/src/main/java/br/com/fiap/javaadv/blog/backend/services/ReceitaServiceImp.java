package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ReceitaRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
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
public class ReceitaServiceImp implements ReceitaService{
    private final ReceitaRepository receitaRepository;

    @Override
    public Receita create(Receita receita) {
        return this.receitaRepository.save(receita);
    }

    @Override
    @Transactional( propagation = Propagation.NEVER)
    public Optional<Receita> fetchById(UUID id) {
        return this.receitaRepository.findById(id);
    }

    @Override
    @Transactional( propagation = Propagation.NEVER)
    public Page<Receita> fetchAll(Pageable pageable) {
        return this.receitaRepository.findAll(pageable);
    }

    @Override
    public void delete(UUID id) {
        this.receitaRepository.deleteById(id);
    }

    @Override
    public void delete(Receita receita) {
        this.receitaRepository.delete(receita);
    }

    @Override
    public boolean existsById(UUID id){return this.receitaRepository.existsById(id);}
}
