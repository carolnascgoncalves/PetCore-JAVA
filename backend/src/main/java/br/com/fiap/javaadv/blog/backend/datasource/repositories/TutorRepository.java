package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Order;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Optional;

import java.util.List;
import java.util.UUID;

public interface TutorRepository  extends JpaRepository<Tutor, UUID>{
    Optional<Tutor> findByEmail(String email);
}
