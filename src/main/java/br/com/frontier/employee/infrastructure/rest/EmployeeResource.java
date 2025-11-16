package br.com.frontier.employee.infrastructure.rest;

import br.com.frontier.core.jwt.AuthenticatedUser;
import br.com.frontier.employee.application.create.CreateEmployeeInput;
import br.com.frontier.employee.application.create.CreateEmployeeUseCase;
import br.com.frontier.employee.application.update.UpdateEmployeeUseCase;
import br.com.frontier.employee.domain.entities.EmployeeID;
import br.com.frontier.employee.infrastructure.dtos.CreateEmployeeRequest;
import br.com.frontier.employee.infrastructure.dtos.FindAllEmployeeResponse;
import br.com.frontier.employee.infrastructure.dtos.GetEmployeeByIDResponse;
import br.com.frontier.employee.infrastructure.dtos.UpdateEmployeeRequest;
import br.com.frontier.employee.infrastructure.queries.findAll.FindAllEmployeeUseCase;
import br.com.frontier.employee.infrastructure.queries.get.GetEmployeeByIdOutput;
import br.com.frontier.employee.infrastructure.queries.get.GetEmployeeByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
public class EmployeeResource {

    private final CreateEmployeeUseCase createEmployeeUseCase;

    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;

    private final UpdateEmployeeUseCase updateEmployeeUseCase;

    private final FindAllEmployeeUseCase findAllEmployeeUseCase;

    private final AuthenticatedUser loggedUser;

    @PostMapping
    public EmployeeID create(@RequestBody @Valid CreateEmployeeRequest request) {
        return createEmployeeUseCase.execute(CreateEmployeeInput.builder()
            .employerID(loggedUser.getEmployerID())
            .name(request.getName())
            .cpf(request.getCpf())
            .position(request.getPosition())
            .hours(request.getHours())
            .salary(request.getSalary())
            .status(request.getStatus())
            .gender(request.getGender())
            .civilState(request.getCivilState())
            .birthDate(request.getBirthDate())
            .phoneNumber(request.getPhoneNumber())
            .email(request.getEmail())
            .nationality(request.getNationality())
            .address(Objects.isNull(request.getAddress()) ? null
                : CreateEmployeeInput.Address.builder()
                .street(request.getAddress().getStreet())
                .number(request.getAddress().getNumber())
                .district(request.getAddress().getDistrict())
                .city(request.getAddress().getCity())
                .state(request.getAddress().getState())
                .cep(request.getAddress().getCep())
                .build())
            .build());
    }

    @GetMapping("/{id}")
    public GetEmployeeByIDResponse get(@PathVariable(name = "id") UUID id) {
        final GetEmployeeByIdOutput output = getEmployeeByIdUseCase.execute(EmployeeID.from(id));
        return GetEmployeeByIDResponse.from(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id,
        @RequestBody @Valid UpdateEmployeeRequest request) {
        updateEmployeeUseCase.execute(UpdateEmployeeRequest.from(request, id));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "List employees (paginated, filter by name, implicit employer ID)")
    public Page<FindAllEmployeeResponse> findAll(
        @RequestParam(required = false) String name,
        Pageable pageable
    ) {

        return findAllEmployeeUseCase.execute(name, loggedUser.getEmployerID().getValue(), pageable)
            .map(FindAllEmployeeResponse::fromOutput);
    }

}
