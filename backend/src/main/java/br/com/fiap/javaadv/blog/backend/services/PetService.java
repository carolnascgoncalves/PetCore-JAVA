package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PetService {
    Pet create(Pet pet);

    Optional<Pet> updateStatus(UUID id, StatusEnum status);

    Optional<Pet> update(UUID id, Pet pet);

    Page<Pet> fetchAll(Pageable pageable);

    Page<Pet> fetchMenuAll(Pageable pageable);

    Optional<Pet> fetchById(UUID id);

    boolean existsById(UUID id);

    void delete(Pet pet);

    void delete(UUID id);
}
