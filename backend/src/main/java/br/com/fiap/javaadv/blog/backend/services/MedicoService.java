package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MedicoService {
    Medico create(Medico medico);

    Optional<Medico> update(UUID id, Medico tutor);

    void delete(Medico medico);

    void delete(UUID id);

    Page<Medico> fetchAll(Pageable pageable);

    Optional<Medico> fetchById(UUID id);

    boolean existsById(UUID id);

    Optional<Medico> login(String email, String senha);

}
