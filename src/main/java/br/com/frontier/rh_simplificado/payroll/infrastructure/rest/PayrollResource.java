package br.com.frontier.rh_simplificado.payroll.infrastructure.rest;

import br.com.frontier.rh_simplificado.core.jwt.AuthenticatedUser;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.payroll.application.calculate.CalculatePayrollInput;
import br.com.frontier.rh_simplificado.payroll.application.calculate.CalculatePayrollUseCase;
import br.com.frontier.rh_simplificado.payroll.application.calculateall.CalculateAllPayrollUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@RestController
@RequestMapping(value = "/payroll")
@RequiredArgsConstructor
public class PayrollResource {

    private final CalculatePayrollUseCase calculatePayrollUseCase;

    private final CalculateAllPayrollUseCase calculateAllPayrollUseCase;

    private final AuthenticatedUser loggedUser;

    @PostMapping("/employee/{id}")
    public ResponseEntity<Void> calculateSigle(@PathVariable(name = "id") UUID id) {

        calculatePayrollUseCase.execute(CalculatePayrollInput.builder()
            .employerID(loggedUser.getEmployerID())
            .employeeID(EmployeeID.from(id))
            .build());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/employee/all")
    public ResponseEntity<Void> calculateAll() {
        calculateAllPayrollUseCase.execute(CalculatePayrollInput.builder()
            .employerID(loggedUser.getEmployerID())
            .build());

        return ResponseEntity.noContent().build();
    }


}
