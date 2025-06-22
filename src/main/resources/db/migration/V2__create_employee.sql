CREATE TYPE ATIVO_INATIVO AS ENUM ('ATIVO', 'INATIVO');

CREATE TABLE employee (
    id_employee         UUID NOT NULL,
    id_employer         UUID NOT NULL,
    name                VARCHAR(100) NOT NULL,
    cpf                 VARCHAR(14) NOT NULL,
    position            VARCHAR(60) NOT NULL,
    hours               VARCHAR(20) NOT NULL,
    salary              NUMERIC(15, 2) NOT NULL,
    status              ativo_inativo NOT NULL,
    inactivation_date   DATE NULL,
CONSTRAINT pk_employee PRIMARY KEY (id_employee)
);

ALTER TABLE employee
ADD CONSTRAINT fk_employee_employer
FOREIGN KEY (id_employer)
REFERENCES employer(id_employer);
