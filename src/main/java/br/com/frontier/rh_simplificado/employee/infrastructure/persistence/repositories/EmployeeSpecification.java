package br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories;

import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeeSpecification {

    public static Specification<EmployeeJpaEntity> filter(String name, UUID employerId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();


            predicates.add(cb.equal(root.get("employer").get("id"), employerId));


            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
