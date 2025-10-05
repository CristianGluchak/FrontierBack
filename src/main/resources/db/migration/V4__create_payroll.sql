CREATE TABLE payroll (
    id_payroll UUID NOT NULL,
    id_employee UUID NOT NULL,
    id_employer UUID NOT NULL,
    reference_month VARCHAR(7) NOT NULL,
    base_salary DECIMAL(15,2) NOT NULL,
    gross_total DECIMAL(15,2) NOT NULL,
    net_total DECIMAL(15,2) NOT NULL,
    total_deductions DECIMAL(15,2) NOT NULL,
    inss DECIMAL(15,2) NOT NULL,
    irrf DECIMAL(15,2) NOT NULL,
    CONSTRAINT pk_payroll PRIMARY KEY (id_payroll)
);

ALTER TABLE payroll
ADD CONSTRAINT fk_payroll_employee
FOREIGN KEY (id_employee)
REFERENCES employee(id_employee);

ALTER TABLE payroll
ADD CONSTRAINT fk_payroll_employer
FOREIGN KEY (id_employer)
REFERENCES employer(id_employer);