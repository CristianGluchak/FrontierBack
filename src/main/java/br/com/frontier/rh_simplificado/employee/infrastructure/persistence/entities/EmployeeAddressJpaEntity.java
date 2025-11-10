package br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeAddress;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeAddressID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 08/11/25
 */

@Entity
@Getter
@Setter
@Table(name = "employeeaddress")
public class EmployeeAddressJpaEntity {

    @Id
    @Column(name = "id_employeeaddres")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private EmployeeJpaEntity employee;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "cep")
    private String cep;

    public static EmployeeAddressJpaEntity from(final UUID id) {
        final var entity = new EmployeeAddressJpaEntity();
        entity.setId(id);
        return entity;
    }

    public static EmployeeAddressJpaEntity from(final EmployeeAddress address) {
        final EmployeeAddressJpaEntity entity = new EmployeeAddressJpaEntity();
        entity.setId(address.getId().getValue());
        entity.setStreet(address.getStreet());
        entity.setNumber(address.getNumber());
        entity.setDistrict(address.getDistrict());
        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setCep(address.getCep());
        return entity;
    }

    public EmployeeAddress toDomain() {
        return EmployeeAddress.builder()
            .id(EmployeeAddressID.from(id))
            .street(street)
            .number(number)
            .district(district)
            .city(city)
            .state(state)
            .cep(cep)
            .build();
    }
}

