package br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.repositories;


import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.entities.PayrollJpaEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PayrollSpecification {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    public static Specification<PayrollJpaEntity> byEmployer(UUID employerId) {
        return (root, query, cb) -> cb.equal(root.get("employer").get("id"), employerId);
    }

    public static Specification<PayrollJpaEntity> byReferenceMonth(String referenceMonth) {
        return (root, query, cb) -> cb.equal(root.get("referenceMonth"), referenceMonth);
    }

    public static Specification<PayrollJpaEntity> byCurrentMonth() {
        String current = LocalDate.now().format(FORMATTER);
        return byReferenceMonth(current);
    }
}
