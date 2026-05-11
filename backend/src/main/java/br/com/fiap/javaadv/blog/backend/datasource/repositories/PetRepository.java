package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository  extends JpaRepository<Pet, UUID> {
}
