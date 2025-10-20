CREATE TABLE IF NOT EXISTS employer(
        id_employer  UUID          NOT NULL DEFAULT gen_random_uuid(),
        fantasyname  VARCHAR(255)  NOT NULL,
        razaosocial  VARCHAR(255)  NOT NULL,
        cnpj         VARCHAR(14)   NOT NULL,
        email        VARCHAR(255)  NOT NULL,
       CONSTRAINT pk_employer PRIMARY KEY (id_employer)
);