package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;
import java.util.UUID;

public interface TutorService {
    Tutor create(Tutor tutor);

    Optional<Tutor> update(UUID id, Tutor tutor);

    void delete(UUID id);

    Page<Tutor> fetchAll(Pageable pageable);

    Optional<Tutor> fetchById(UUID id);

    boolean existsById(UUID id);

    Optional<Tutor> fetchByEmail(String email, String senha);
}
