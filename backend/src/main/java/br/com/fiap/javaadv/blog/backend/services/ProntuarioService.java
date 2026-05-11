package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProntuarioService {
    Prontuario create(Prontuario prontuario);

    Optional<Prontuario> update(UUID id, Prontuario prontuario);

    Page<Prontuario> fetchAll(Pageable pageable);

    Optional<Prontuario> fetchById(UUID id);

    boolean existsById(UUID id);

    void delete(UUID id);

}
