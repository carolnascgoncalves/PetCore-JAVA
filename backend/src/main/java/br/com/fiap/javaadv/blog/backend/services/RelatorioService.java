package br.com.fiap.javaadv.blog.backend.services;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RelatorioService {
    Relatorio create(Relatorio relatorio);

    Optional<Relatorio> update(UUID id, Relatorio relatorio);

    Page<Relatorio> fetchAll(Pageable pageable);

    Optional<Relatorio> fetchById(UUID id);

    void delete(UUID id);

    boolean existsById(UUID id);
}
