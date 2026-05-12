package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface HistoricoService {
    Historico create(Historico historico);

    void delete(UUID id);

    Page<Historico> fetchAll(Pageable pageable);

    Optional<Historico> fetchById(UUID id);

    boolean existsById(UUID id);
}
