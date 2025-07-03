package br.com.frontier.rh_simplificado.employer.domain.entities;

import br.com.frontier.rh_simplificado.employer.domain.commands.CreateEmployerCommand;
import br.com.frontier.rh_simplificado.employer.domain.commands.UpdateEmployerCommand;
import br.com.frontier.rh_simplificado.shared.AggregateRoot;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
@Getter
public class Employer extends AggregateRoot<EmployerID> {

    private String razaoSocial;

    private String nomeFantasia;

    private String cnpj;

    private String email;

    @Builder
    public Employer(final EmployerID id,final String razaoSocial,final String nomeFantasia,final String cnpj,final String email) {
        super(id);
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.email = email;
    }

    public static Employer create(CreateEmployerCommand command){
        return Employer.builder()
                .id(EmployerID.unique())
                .razaoSocial(command.getRazaoSocial())
                .nomeFantasia(command.getFantasyName())
                .cnpj(command.getCnpj())
                .email(command.getEmail())
                .build();
    }

    public void update(UpdateEmployerCommand command){
        this.razaoSocial = command.getRazaoSocial();
        this.nomeFantasia = command.getFantasyName();
        this.cnpj = command.getCnpj();
        this.email = command.getEmail();
    }

}
