package br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories;

import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.shared.enums.AtivoInativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
public interface EmployeeRepository extends JpaRepository<EmployeeJpaEntity, UUID>,
    JpaSpecificationExecutor<EmployeeJpaEntity> {

    List<EmployeeJpaEntity> findByEmployerIdAndStatus(UUID employerId, AtivoInativo status);
}
