package br.com.frontier.rh_simplificado.payroll.infrastructure.queries;

import br.com.frontier.rh_simplificado.payroll.domain.entities.PayrollID;
import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.repositories.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@Service
@RequiredArgsConstructor
public class GetPayrollByIdUseCase {

    private final PayrollRepository repository;

    public GetPayrollByIdOutput execute(final PayrollID id) {
        return repository.findById(id.getValue())
                .map()
                .orElseThrow(() -> new RuntimeException("Empregador nao encontrado"));
    }
}
