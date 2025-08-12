CREATE TYPE role AS ENUM ('ADMIN');

CREATE TABLE "user" (
    id_user     UUID NOT NULL,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    id_employer UUID NOT NULL,
    role        role NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id_user)
);

ALTER TABLE "user"
ADD CONSTRAINT fk_user_employer
FOREIGN KEY (id_employer)
REFERENCES employer(id_employer);