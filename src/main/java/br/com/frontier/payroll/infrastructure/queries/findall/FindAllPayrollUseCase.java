package br.com.frontier.payroll.infrastructure.queries.findall;

import br.com.frontier.payroll.infrastructure.persistence.entities.PayrollJpaEntity;
import br.com.frontier.payroll.infrastructure.persistence.repositories.PayrollRepository;
import br.com.frontier.payroll.infrastructure.persistence.repositories.PayrollSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FindAllPayrollUseCase {

    private final PayrollRepository payrollRepository;

    public Page<FindAllPayrollOutput> execute(UUID employerId,
        String referenceMonth,
        Pageable pageable) {
        Specification<PayrollJpaEntity> spec = PayrollSpecification.byEmployer(employerId);

        if (referenceMonth != null && !referenceMonth.isBlank()) {
            spec = spec.and(PayrollSpecification.byReferenceMonth(referenceMonth));
        }

        return payrollRepository.findAll(spec, pageable)
            .map(FindAllPayrollOutput::fromEntity);
    }
}
