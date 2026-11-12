CREATE TABLE departamento (
    dnr INT PRIMARY KEY,
    nome_d VARCHAR(50)
);

INSERT INTO departamento (dnr, nome_d) VALUES
(1, 'C. COMP'),
(2, 'ENG.');

CREATE TABLE funcionario (
    cpf INT PRIMARY KEY,
    nome_f VARCHAR(50),
    fk_dnr INT,
    FOREIGN KEY (fk_dnr) REFERENCES departamento(dnr)
);

INSERT INTO funcionario (cpf, nome_f, fk_dnr) VALUES
(106, 'MICHEL', 1),
(107, 'JOANA', 2);

SELECT f.nome_f, d.nome_d
FROM funcionario f
JOIN departamento d ON f.fk_dnr = d.dnr;

SELECT d.nome_d
FROM funcionario f
JOIN departamento d ON f.fk_dnr = d.dnr
WHERE f.nome_f = 'JOANA';

SELECT f.cpf
FROM funcionario f
JOIN departamento d ON f.fk_dnr = d.dnr
WHERE d.nome_d = 'C. COMP';
