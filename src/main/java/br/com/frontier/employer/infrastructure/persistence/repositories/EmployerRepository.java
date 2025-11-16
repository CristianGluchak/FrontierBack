package br.com.frontier.employer.infrastructure.persistence.repositories;

import br.com.frontier.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
public interface EmployerRepository extends JpaRepository<EmployerJpaEntity, UUID> {
}
