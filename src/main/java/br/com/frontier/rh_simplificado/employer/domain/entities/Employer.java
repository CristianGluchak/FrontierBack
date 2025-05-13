package br.com.frontier.rh_simplificado.employer.domain;

import br.com.frontier.rh_simplificado.shared.AggregateRoot;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

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

    public static Employer create( String razaoSocial, String nomeFantasia, String cnpj, String email){
        return Employer.builder()
                .id(EmployerID.unique())
                .razaoSocial(razaoSocial)
                .nomeFantasia(nomeFantasia)
                .cnpj(cnpj)
                .email(email)
                .build();
    }

}
