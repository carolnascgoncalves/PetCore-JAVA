package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.RelatorioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
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
public class RelatorioServiceImp implements RelatorioService{
    private final RelatorioRepository relatorioRepository;

    @Override
    public Relatorio create(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    @Override
    public Optional<Relatorio> update(UUID id, Relatorio relatorio) {
        return relatorioRepository.findById(id)
                .map(existing -> {

                    if (relatorio.getObservacao() != null)
                        existing.setObservacao(relatorio.getObservacao());

                    if (relatorio.getHistorico() != null)
                        existing.setHistorico(relatorio.getHistorico());

                    if (relatorio.getMedico() != null)
                        existing.setMedico(relatorio.getMedico());

                    return relatorioRepository.save(existing);
                });
    }

    @Transactional( propagation = Propagation.NEVER)
    @Override
    public Page<Relatorio> fetchAll(Pageable pageable) {
        return this.relatorioRepository.findAll(pageable);
    }

    @Transactional( propagation = Propagation.NEVER)
    @Override
    public Optional<Relatorio> fetchById(UUID id) {
        return this.relatorioRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        this.relatorioRepository.deleteById(id);
    }

    public boolean existsById(UUID id){
        return this.relatorioRepository.existsById(id);
    }
}
