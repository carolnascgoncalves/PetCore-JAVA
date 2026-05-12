package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MedicamentoService {
    Medicamento create(Medicamento medicamento);

    Optional<Medicamento> update(UUID id, Medicamento medic);

    void delete(UUID id);

    Page<Medicamento> fetchAll(Pageable pageable);

    Optional<Medicamento> fetchById(UUID id);

    boolean existsById(UUID id);
}
