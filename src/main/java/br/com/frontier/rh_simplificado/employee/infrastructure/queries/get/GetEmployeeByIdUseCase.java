package br.com.frontier.rh_simplificado.employee.infrastructure.queries.get;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeAddressID;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories.EmployeeRepository;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
                .gender(entity.getGender())
                .civilState(entity.getCivilState())
                .birthDate(entity.getBirthDate())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .nationality(entity.getNationality())
                .address(Objects.isNull(entity.getAddress()) ? null :
                    GetEmployeeByIdOutput.Address.builder()
                        .id(EmployeeAddressID.from(entity.getAddress().getId()))
                        .street(entity.getAddress().getStreet())
                        .number(entity.getAddress().getNumber())
                        .district(entity.getAddress().getDistrict())
                        .city(entity.getAddress().getCity())
                        .state(entity.getAddress().getState())
                        .cep(entity.getAddress().getCep())
                        .build())
                .build())
            .orElseThrow(() -> new RuntimeException("Empregado não encontrado"));
    }
}
