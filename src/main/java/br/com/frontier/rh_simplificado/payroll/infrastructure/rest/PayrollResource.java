package br.com.frontier.rh_simplificado.payroll.infrastructure.rest;

import br.com.frontier.rh_simplificado.core.jwt.AuthenticatedUser;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.payroll.application.calculate.CreatePayrollInput;
import br.com.frontier.rh_simplificado.payroll.application.calculate.CreatePayrollUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@RestController
@RequestMapping(value = "/payroll")
@RequiredArgsConstructor
public class PayrollResource {

    private final CreatePayrollUseCase createPayrollUseCase;

    private final AuthenticatedUser loggedUser;

    @PostMapping("/employee/{id}")
    public ResponseEntity<Void> calculateSigle(@PathVariable(name = "id") UUID id) {

        createPayrollUseCase.execute(CreatePayrollInput.builder()
            .employerID(loggedUser.getEmployerID())
            .employeeID(EmployeeID.from(id))
            .build());
        return ResponseEntity.noContent().build();
    }


}
