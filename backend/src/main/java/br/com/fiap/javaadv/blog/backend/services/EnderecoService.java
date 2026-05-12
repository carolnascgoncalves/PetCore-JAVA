package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Endereco;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoService {
    Endereco create(Endereco endereco);

    Optional<Endereco> update(UUID id, Endereco endereco);

    void delete(UUID id);

    Page<Endereco> fetchAll(Pageable pageable);

    Optional<Endereco> fetchById(UUID id);

    boolean existsById(UUID id);
}
