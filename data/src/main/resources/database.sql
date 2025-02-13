CREATE DATABASE "multi-part"
    WITH
    OWNER = "practice-root"
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    birth_date date NOT NULL,
    UNIQUE (first_name, last_name)
);

CREATE TABLE models (
    id SERIAL PRIMARY KEY,
    model VARCHAR (20) UNIQUE NOT NULL
);

CREATE TABLE contacts (
    id SERIAL PRIMARY KEY,
    model_id INT REFERENCES models(id),
    telephone_number VARCHAR (25),
    person_id INT references persons(id),
    UNIQUE (model_id, person_id)
);

INSERT INTO models (model) VALUES ('Mobile');
INSERT INTO models (model) VALUES ('Home');
INSERT INTO persons (first_name, last_name, birth_date) VALUES ('Duke', 'Zubov', '1992-03-04');
INSERT INTO contacts (model_id, telephone_number, person_id) VALUES (1, 89997502222, 1);
INSERT INTO contacts (model_id, telephone_number, person_id) VALUES (2, 8499500390, 1);
INSERT INTO persons (first_name, last_name, birth_date) VALUES ('Alex', 'Alexsandrov', '1989-09-05');
INSERT INTO contacts (model_id, telephone_number, person_id) VALUES (1, 89156540255, 2);