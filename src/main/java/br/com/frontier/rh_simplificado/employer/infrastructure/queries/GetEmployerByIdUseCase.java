package br.com.frontier.rh_simplificado.employer.infrastructure.queries;

import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.employer.infrastructure.persistence.repositories.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@Service
@RequiredArgsConstructor
public class GetEmployerByIdUseCase {

    private final EmployerRepository repository;

    public GetEmployerByIdOutput execute(final EmployerID id) {
        return repository.findById(id.getValue())
                .map(entity -> GetEmployerByIdOutput.builder()
                        .id(EmployerID.from(entity.getId()))
                        .razaoSocial(entity.getRazaoSocial())
                        .fantasyName(entity.getFantasyName())
                        .email(entity.getEmail())
                        .cnpj(entity.getCnpj())
                        .build())
                .orElseThrow(() -> new RuntimeException("Empregador nao encontrado"));
    }
}
