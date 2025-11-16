package br.com.frontier.employee.domain.entities;

import br.com.frontier.employee.domain.commands.CreateEmployeeCommand;
import br.com.frontier.employee.domain.commands.UpdateEmployeeCommand;
import br.com.frontier.shared.Entity;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 02/11/25
 */

@Getter
public class EmployeeAddress extends Entity<EmployeeAddressID> {

    private String street;

    private String number;

    private String district;

    private String city;

    private String state;

    private String cep;


    @Builder
    public EmployeeAddress(final EmployeeAddressID id,
        final String street,
        final String number,
        final String district,
        final String city,
        final String state,
        final String cep) {
        super(id);
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
        this.cep = cep;
    }


    public static EmployeeAddress create(CreateEmployeeCommand.Address comamnd) {
        return EmployeeAddress.builder()
            .id(EmployeeAddressID.unique())
            .street(comamnd.getStreet())
            .number(comamnd.getNumber())
            .district(comamnd.getDistrict())
            .city(comamnd.getCity())
            .state(comamnd.getState())
            .cep(comamnd.getCep())
            .build();
    }

    public void update(UpdateEmployeeCommand.Address comamnd) {
        this.street = comamnd.getStreet();
        this.number = comamnd.getNumber();
        this.district = comamnd.getDistrict();
        this.city = comamnd.getCity();
        this.state = comamnd.getState();
        this.cep = comamnd.getCep();
    }
}
