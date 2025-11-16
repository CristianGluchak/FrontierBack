package br.com.frontier.payroll.infrastructure.persistence.repositories;

import br.com.frontier.payroll.infrastructure.persistence.entities.PayrollJpaEntity;

import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 21/09/25
 */

public interface PayrollRepositoryCustom {
    public Optional<PayrollJpaEntity> findByEmployerAndEmployeeAndReferenceMonth(
        UUID employerId,
        UUID employeeId,
        YearMonth referenceMonth);
}
