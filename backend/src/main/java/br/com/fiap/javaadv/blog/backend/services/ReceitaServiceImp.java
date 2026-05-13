package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.MedicamentoRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.ReceitaRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional( propagation = Propagation.REQUIRED)
public class ReceitaServiceImp implements ReceitaService{
    private final ReceitaRepository receitaRepository;
    private final MedicamentoRepository medicamentoRepository;

    @Override
    public Receita create(Receita receita) {

        Set<Medicamento> medicamentos = receita.getMedicamentos()
                .stream()
                .map(med -> medicamentoRepository.findById(med.getId()).orElse(null))
                .collect(Collectors.toSet());

        receita.setMedicamentos(medicamentos);

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
    public boolean existsById(UUID id){return this.receitaRepository.existsById(id);}
}
