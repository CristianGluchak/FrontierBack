package br.com.frontier.rh_simplificado.employer.domain;

import br.com.frontier.rh_simplificado.employer.domain.commands.CreateEmployerCommand;
import br.com.frontier.rh_simplificado.employer.domain.entities.Employer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
@DisplayName("Testes unitarios da entidade de Empregador")
class EmployeeJpaEntityTest {

    @Test
    @DisplayName("Deve ser possivel criar um Empregador")
    void shouldBeAbleToCreateANewEmployer(){

        final var expectedRazao = "Empresas Joao";
        final var expectedfantasia = "nome fantasia";
        final var expectedCnpj = "9999";
        final var expectedEmail = "Email@mail";

        CreateEmployerCommand command = CreateEmployerCommand.builder()
                .razaoSocial(expectedRazao)
                .fantasyName(expectedfantasia)
                .cnpj(expectedCnpj)
                .email(expectedEmail)
                .build();

        final var result= Employer.create(command);

        assertNotNull(result);
        assertEquals(expectedRazao,result.getRazaoSocial());
        assertEquals(expectedfantasia,result.getNomeFantasia());
        assertEquals(expectedCnpj,result.getCnpj());
        assertEquals(expectedEmail,result.getEmail());
    }

}