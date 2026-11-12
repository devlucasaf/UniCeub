-- Código disponibilizado para a Atividade de DDL e DML

CREATE TABLE funcionario (
    cpf VARCHAR(15) NOT NULL,
    pnome VARCHAR(45),
    minicial CHAR(1),
    unome VARCHAR(30),
    dataNasc DATE,
    endereco VARCHAR(90),
    sexo CHAR(1),
    salario NUMERIC(10,2),
    cpfSuperv VARCHAR(15),
    numDept INTEGER,
    PRIMARY KEY (cpf),
    FOREIGN KEY (cpfSuperv) REFERENCES funcionario (cpf)
);

CREATE TABLE departamento (
    dnumero INTEGER NOT NULL,
    dnome VARCHAR(45),
    cpfGerente VARCHAR(15),
    dataInicio DATE,
    PRIMARY KEY (dnumero),
    FOREIGN KEY (cpfGerente) REFERENCES funcionario(cpf)
);

CREATE TABLE projeto (
    projNumero INTEGER NOT NULL,
    projNome VARCHAR(45),
    projLocal VARCHAR(30),
    dnum INTEGER,
    PRIMARY KEY (projNumero),
    FOREIGN KEY (dnum) REFERENCES departamento (dnumero)
);

CREATE TABLE trabalhaEm (
    fcpf VARCHAR(15) NOT NULL,
    projnumero INTEGER NOT NULL,
    horas INTEGER,
    PRIMARY KEY (projnumero, fcpf),
    FOREIGN KEY (projnumero) REFERENCES projeto (projNumero),
    FOREIGN KEY (fcpf) REFERENCES funcionario (cpf)
);

CREATE TABLE dependente (
    fcpf VARCHAR(15) NOT NULL,
    nomeDependente VARCHAR(45),
    sexo CHAR(1),
    dataNasc DATE,
    parentesco VARCHAR(20),
    PRIMARY KEY (fcpf, nomeDependente),
    FOREIGN KEY (fcpf) REFERENCES funcionario (cpf)
);

ALTER TABLE funcionario ADD CONSTRAINT empregado_depto_fk FOREIGN KEY (numDept) REFERENCES departamento(dnumero);

-- Insert na tabela departamento
INSERT INTO departamento VALUES (5,'Pesquisa',NULL,'1988-05-22');
INSERT INTO departamento VALUES (4,'Administracao',NULL,'1985-01-01');
INSERT INTO departamento VALUES (1,'Direcao',NULL,'1981-06-19');

-- Insert na tabela funcionario
INSERT INTO funcionario VALUES ('0123456789','Joao','B','Silva','1955-01-09','Rua dos Camelos, 23','M',30000,NULL,5);
INSERT INTO funcionario VALUES ('0987654321','Horacio',NULL,'Bagual','1970-10-10','Av. dos Tapejaras, 90','M',40000,'0123456789',5);
INSERT INTO funcionario VALUES ('1230984567','Ana',NULL,'Bacana','1980-06-20','Av. Atacama, 10 apto 22','F',25000,'0123456789',4);
INSERT INTO funcionario VALUES ('0981234567','Antonio',NULL,'Pestana','1990-04-13','Rod. Imigrantes, 1940','M',45000,'0987654321',4);
INSERT INTO funcionario VALUES ('1029384756','Maria','A','Real','1982-11-14','Rua Petropolis, 13','F',38000,'0123456789',5);
INSERT INTO funcionario VALUES ('0192837465','Ada','M','Lovelace','1965-10-15','Rua dos Ingleses, 1020','F',25000,'0123456789',5);
INSERT INTO funcionario VALUES ('1234555555','Joaquina',NULL,'Pasqualini','1968-08-17','Rua dos Ingleses, 1010','F',25000,'0123456789',4);
INSERT INTO funcionario VALUES ('0987655555','Jaime',NULL,'Bonde','1958-01-01','Rua dos Ingleses, 1010','M',70000,'0123456789',1);

-- Atualização do campo cpf_gerente na tabela departamento
UPDATE departamento SET cpfgerente = '1230984567' WHERE dnumero = 5;
UPDATE departamento SET cpfgerente = '1029384756' WHERE dnumero = 4;
UPDATE departamento SET cpfgerente = '1234555555' WHERE dnumero = 1;

-- Inserção na tabela projeto
INSERT INTO projeto VALUES (1,'Transmogrifador','São Paulo',5);
INSERT INTO projeto VALUES (2,'Cama Hiperbarica','Porto Alegre',5);
INSERT INTO projeto VALUES (3,'Emissor de Raios Z','Rio de Janeiro',5);
INSERT INTO projeto VALUES (10,'Gestao dos 80/20','Brasilia',4);
INSERT INTO projeto VALUES (20,'Business Inteligence','São Paulo',1);
INSERT INTO projeto VALUES (30,'Bonus para Inventores','Natal',4);

-- Inserção na tabela trabalha_em
INSERT INTO trabalhaEm VALUES ('0123456789',1,32);
INSERT INTO trabalhaEm VALUES ('0123456789',2,7.5);
INSERT INTO trabalhaEm VALUES ('1029384756',3,40);
INSERT INTO trabalhaEm VALUES ('0192837465',1,20);
INSERT INTO trabalhaEm VALUES ('0192837465',2,20);
INSERT INTO trabalhaEm VALUES ('0987654321',2,10);
INSERT INTO trabalhaEm VALUES ('0987654321',3,10);
INSERT INTO trabalhaEm VALUES ('0987654321',10,10);
INSERT INTO trabalhaEm VALUES ('0987654321',20,10);
INSERT INTO trabalhaEm VALUES ('1230984567',30,30);
INSERT INTO trabalhaEm VALUES ('1230984567',10,10);
INSERT INTO trabalhaEm VALUES ('0987655555',10,35);
INSERT INTO trabalhaEm VALUES ('0987655555',30,5);
INSERT INTO trabalhaEm VALUES ('0981234567',30,20);
INSERT INTO trabalhaEm VALUES ('0981234567',20,15);
INSERT INTO trabalhaEm VALUES ('1234555555',20,0);
INSERT INTO trabalhaEm VALUES ('0123456789',3,15);

-- Inserção na tabela dependente
INSERT INTO dependente VALUES ('0987654321','Joana Bagual','F','1986-05-05','Filha');
INSERT INTO dependente VALUES ('0987654321','Pedro Bagual','M','1983-10-25','Filho');
INSERT INTO dependente VALUES ('0987654321','Alice Bagual','F','1958-05-03','Esposa');
INSERT INTO dependente VALUES ('1230984567','Maximo Bacana','M','1942-02-28','Esposo');
INSERT INTO dependente VALUES ('0123456789','Jagunco da Silva','M','1978-01-01','Filho');
INSERT INTO dependente VALUES ('0123456789','Jaqueline da Silva','F','1978-12-31','Filha');
INSERT INTO dependente VALUES ('0123456789','Joana da Silva','F','1957-05-05','Esposa');

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
