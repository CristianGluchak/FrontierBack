CREATE TYPE CIVIL_STATE AS ENUM ('SOLTEIRO','CASADO','DIVORCIADO','VIUVO');
CREATE TYPE GENDER AS ENUM ('MASCULINO','FEMININO','OUTRO');

ALTER TABLE employee ADD COLUMN gender GENDER;
ALTER TABLE employee ADD COLUMN civil_state CIVIL_STATE;
ALTER TABLE employee ADD COLUMN birth_date DATE;
ALTER TABLE employee ADD COLUMN phone_number VARCHAR(15);
ALTER TABLE employee ADD COLUMN email VARCHAR(100);
ALTER TABLE employee ADD COLUMN nationality VARCHAR(50);


CREATE TABLE employeeaddress (
    id_employeeaddres UUID NOT NULL DEFAULT gen_random_uuid(),
    id_employee UUID NOT NULL,
    street VARCHAR(100) NULL,
    number VARCHAR(10) NULL,
    district VARCHAR(50) NULL,
    city VARCHAR(50) NULL,
    state VARCHAR(50) NULL,
    cep VARCHAR(10) NULL,
    CONSTRAINT pk_employeeaddress PRIMARY KEY (id_employeeaddres)
);

ALTER TABLE employeeaddress
ADD CONSTRAINT fk_employeeaddres_employee
FOREIGN KEY (id_employee)
REFERENCES employee(id_employee);

