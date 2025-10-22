
CREATE TABLE departamento (
    dnumero INT PRIMARY KEY,
    dnome VARCHAR(45),
    localizacao VARCHAR(45)
);

CREATE TABLE funcionario (
    cpf CHAR(11) PRIMARY KEY,
    pnome VARCHAR(30),
    unome VARCHAR(30),
    sexo CHAR(1),
    dataNasc DATE,
    salario DECIMAL(10,2),
    cpfSuperv CHAR(11),
    numDept INT,
    FOREIGN KEY (numDept) REFERENCES departamento(dnumero)
);

CREATE TABLE projeto (
    projNumero INT PRIMARY KEY,
    projNome VARCHAR(50),
    projLocal VARCHAR(30),
    numDept INT,
    FOREIGN KEY (numDept) REFERENCES departamento(dnumero)
);

CREATE TABLE trabalhaEm (
    fcpf CHAR(11),
    projNumero INT,
    horas INT,
    FOREIGN KEY (fcpf) REFERENCES funcionario(cpf),
    FOREIGN KEY (projNumero) REFERENCES projeto(projNumero)
);

CREATE TABLE dependente (
    fcpf CHAR(11),
    nomeDependete VARCHAR(40),
    sexo CHAR(1),
    dataNasc DATE,
    parentesco VARCHAR(20),
    FOREIGN KEY (fcpf) REFERENCES funcionario(cpf)
);

-- Exercícios DDL + DML: Consultas SQL

-- lETRA A

SELECT pnome, unome FROM funcionario;

-- LETRA B

SELECT pnome, unome FROM funcionario
WHERE sexo = 'F';

-- LETRA C

SELECT pnome, unome,
        DATE_PART('year', AGE(dataNasc)) AS idade
FROM funcionario
WHERE pnome LIKE 'A%n';

-- LETRA D

SELECT MAX(f.salario)
FROM funcionario F
JOIN departamento D ON F.numDept = d.dnumero
WHERE pnome LIKE 'A%n';

-- LETRA E

SELECT MIN(f.salario)
FROM funcionario f
JOIN departamento d ON f.numDept = d.numero
WHERE d.dnome = 'Pesquisa';

-- LETRA F

SELECT MIN(f.salario)
FROM dependente d
JOIN funcionario f ON d.fcpf = f.cpf
WHERE f.pnome LIKE 'A%n';

-- LETRA G

SELECT fcpf AS cpf_responsavel
FROM dependente
WHERE nomeDependete = 'Joana Bagual';

-- LETRA H

SELECT f.pnome ||' '|| f.unome AS nome_funcionario,
        d.dnome AS departamento
FROM trabalhaEm t
JOIN funcionario f ON t.fcpf = f.cpf
JOIN projeto p ON t.projNumero = p.projNumero
JOIN departamento d ON f.numDept = d.dnumero
WHERE p.projNome = 'Cama Hiperbarica';

-- LETRA I

SELECT DISTINCT f.cpfSuperv AS cpf_supervisor, f.cpf AS cpf_supervisor
FROM funcionario f
WHERE f.cpfSuperv IS NOT NULL;

-- LETRA J

SELECT 
    f.pnome || ' ' || f.unome AS funcionario,
    SUM(t.horas) AS total_horas,
    d.dnome AS departamento,
    p.projLocal AS local
FROM trabalhaEm t
JOIN funcionario f ON t.fcpf = f.cpf
JOIN projeto p ON t.projnumero = p.projNumero
JOIN departamento d ON f.numDept = d.dnumero
GROUP BY f.cpf, f.pnome, f.unome, d.dnome, p.projLocal
ORDER BY total_horas DESC
LIMIT 1;

-- LETRA K

SELECT 
    pnome || ' ' || unome AS funcionario_sem_supervisor
FROM funcionario
WHERE cpfSuperv IS NULL;

-- LETRA L

SELECT DISTINCT f.pnome || ' ' || f.unome AS funcionario
FROM trabalhaEm t
JOIN funcionario f ON t.fcpf = f.cpf
WHERE t.horas > 15 AND t.horas < 30;

-- LETRA M

SELECT DISTINCT f.pnome || ' ' || f.unome AS funcionario
FROM trabalhaEm t
JOIN funcionario f ON t.fcpf = f.cpf
JOIN projeto p ON t.projnumero = p.projNumero
WHERE (p.projLocal = 'São Paulo' AND t.horas BETWEEN 10 AND 20)
    OR (p.projLocal = 'Rio de Janeiro' AND t.horas >= 15);
