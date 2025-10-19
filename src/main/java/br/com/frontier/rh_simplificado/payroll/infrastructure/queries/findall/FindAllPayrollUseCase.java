package br.com.frontier.rh_simplificado.payroll.infrastructure.queries.findall;

import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.entities.PayrollJpaEntity;
import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.repositories.PayrollRepository;
import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.repositories.PayrollSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllPayrollUseCase {

    private final PayrollRepository payrollRepository;

    public Page<FindAllPayrollOutput> execute(UUID employerId,
        String referenceMonth,
        Pageable pageable) {
        Specification<PayrollJpaEntity> spec = PayrollSpecification.byEmployer(employerId);

        if (referenceMonth != null && !referenceMonth.isBlank()) {
            spec = spec.and(PayrollSpecification.byReferenceMonth(referenceMonth));
        } else {
            spec = spec.and(PayrollSpecification.byCurrentMonth());
        }

        return payrollRepository.findAll(spec, pageable)
            .map(FindAllPayrollOutput::fromEntity);
    }
}
