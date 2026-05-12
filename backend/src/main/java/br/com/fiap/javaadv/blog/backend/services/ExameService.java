package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Exame;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ExameService {
    Exame create(Exame exame);

    Optional<Exame> update(UUID id, Exame exame);

    void delete(UUID id);

    Page<Exame> fetchAll(Pageable pageable);

    Optional<Exame> fetchById(UUID id);

    boolean existsById(UUID id);
}
