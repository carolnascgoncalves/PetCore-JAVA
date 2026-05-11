package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Clinica;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ClinicaService {
    Clinica create(Clinica clinica);

    Optional<Clinica> update(UUID id, Clinica clinica);

    void delete(Clinica clinica);

    void delete(UUID id);

    Page<Clinica> fetchAll(Pageable pageable);

    boolean existsById(UUID id);

    Optional<Clinica> fetchById(UUID id);

}
