package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ProntuarioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
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
public class ProntuarioServiceImp implements ProntuarioService{
    private final ProntuarioRepository prontuarioRepository;

    @Override
    public Prontuario create(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    @Override
    public Optional<Prontuario> update(UUID id, Prontuario patch) {
        return prontuarioRepository.findById(id)
                .map(existing -> {

                    if (patch.getDescricao() != null)
                        existing.setDescricao(patch.getDescricao());

                    return prontuarioRepository.save(existing);
                });
    }

    @Override
    public Page<Prontuario> fetchAll(Pageable pageable) {
        return prontuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Prontuario> fetchById(UUID id) {
        return prontuarioRepository.findById(id);
    }

    @Override
    public boolean existsById(UUID id){
        return prontuarioRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        prontuarioRepository.deleteById(id);
    }


}
