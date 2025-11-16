package br.com.frontier.employee.infrastructure.queries.findAll;


import br.com.frontier.employee.infrastructure.persistence.repositories.EmployeeRepository;
import br.com.frontier.employee.infrastructure.persistence.repositories.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllEmployeeUseCase {

    private final EmployeeRepository repository;

    public Page<FindAllEmployeeOutput> execute(String name, UUID employerId, Pageable pageable) {
        return repository
            .findAll(EmployeeSpecification.filter(name, employerId), pageable)
            .map(FindAllEmployeeOutput::fromEntity);
    }
}
