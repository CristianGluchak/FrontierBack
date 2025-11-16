package br.com.frontier.employee.domain.entities;

import br.com.frontier.shared.Identifier;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
public class EmployeeAddressID extends Identifier<UUID> {

    protected EmployeeAddressID( UUID id){
        super(id);
    }

    public static EmployeeAddressID unique(){
       return new EmployeeAddressID(UUID.randomUUID());
    }

    public static EmployeeAddressID from(final String id){
        return new EmployeeAddressID(UUID.fromString(id));
    }

    public static EmployeeAddressID from(final UUID id){
        return new EmployeeAddressID(id);
    }

}
