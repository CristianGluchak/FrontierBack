package br.com.frontier.rh_simplificado.employee.infrastructure.queries.get;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories.EmployeeRepository;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@Service
@Transactional
@RequiredArgsConstructor
public class GetEmployeeByIdUseCase {

    private final EmployeeRepository repository;

    public GetEmployeeByIdOutput execute(final EmployeeID id) {
        return repository.findById(id.getValue())
                .map(entity -> GetEmployeeByIdOutput.builder()
                        .id(EmployeeID.from(entity.getId()))
                        .employerID(EmployerID.from(entity.getEmployer().getId()))
                        .name(entity.getName())
                        .cpf(entity.getCpf())
                        .position(entity.getPosition())
                        .hours(entity.getHours())
                        .salary(entity.getSalary())
                        .status(entity.getStatus())
                        .inactivationDate(entity.getInactivationDate())
                        .build())
                .orElseThrow(() -> new RuntimeException("Empregador não encontrado"));
    }
}
