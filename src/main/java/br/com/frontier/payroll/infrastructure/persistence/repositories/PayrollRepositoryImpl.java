package br.com.frontier.payroll.infrastructure.persistence.repositories;

import br.com.frontier.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import br.com.frontier.payroll.infrastructure.persistence.entities.PayrollJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.time.YearMonth;

@Repository
public class PayrollRepositoryImpl implements PayrollRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<PayrollJpaEntity> findByEmployerAndEmployeeAndReferenceMonth(
        UUID employerId,
        UUID employeeId,
        YearMonth referenceMonth) {

        String refMonth = referenceMonth.toString(); // "2025-09"

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PayrollJpaEntity> query = cb.createQuery(PayrollJpaEntity.class);
        Root<PayrollJpaEntity> root = query.from(PayrollJpaEntity.class);

        Join<PayrollJpaEntity, EmployerJpaEntity> employerJoin = root.join("employer");
        Join<PayrollJpaEntity, EmployeeJpaEntity> employeeJoin = root.join("employee");

        Predicate predicate = cb.and(
            cb.equal(employerJoin.get("id"), employerId),
            cb.equal(employeeJoin.get("id"), employeeId),
            cb.equal(root.get("referenceMonth"), refMonth)
        );

        query.select(root).where(predicate);

        TypedQuery<PayrollJpaEntity> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultStream().findFirst();
    }
}
