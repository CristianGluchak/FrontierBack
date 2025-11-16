package br.com.frontier.rh_simplificado.user.infrastructure.persistence.repositories;

import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.user.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
public interface UserRepository extends JpaRepository<UserJpaEntity, UUID>,
    JpaSpecificationExecutor<UserJpaEntity> {

    UserJpaEntity findByEmailAndPassword(String name, String password);
}
