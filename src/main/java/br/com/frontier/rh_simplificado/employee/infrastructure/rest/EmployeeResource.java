package br.com.frontier.rh_simplificado.employee.infrastructure.rest;

import br.com.frontier.rh_simplificado.employee.application.create.CreateEmployeeUseCase;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.dtos.CreateEmployeeRequest;
import br.com.frontier.rh_simplificado.employee.infrastructure.dtos.GetEmployeeByIDResponse;
import br.com.frontier.rh_simplificado.employee.infrastructure.queries.get.GetEmployeeByIdOutput;
import br.com.frontier.rh_simplificado.employee.infrastructure.queries.get.GetEmployeeByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public EmployeeID create(@RequestBody @Valid CreateEmployeeRequest request){
        return createEmployeeUseCase.execute(CreateEmployeeRequest.from(request));
    }

    @GetMapping("/{id}")
    public GetEmployeeByIDResponse get(@PathVariable(name = "id") UUID id){
        final GetEmployeeByIdOutput output = getEmployeeByIdUseCase.execute(EmployeeID.from(id));
        return GetEmployeeByIDResponse.from(output);
    }

}
