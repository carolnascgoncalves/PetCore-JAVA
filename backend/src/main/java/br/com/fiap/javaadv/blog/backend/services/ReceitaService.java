package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ReceitaService {
    Receita create(Receita receita);

    Optional<Receita> fetchById(UUID id);

    Page<Receita> fetchAll(Pageable pageable);

    void delete(UUID id);

    void delete(Receita receita);

    boolean existsById(UUID id);
}
