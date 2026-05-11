package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RelatorioRepository extends JpaRepository<Relatorio, UUID> {
}
