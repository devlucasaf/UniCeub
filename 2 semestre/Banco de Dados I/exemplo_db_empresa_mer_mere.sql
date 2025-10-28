-- ===================================================
-- CRIAÇÃO DO BANCO DE DADOS EMPRESA_MER_MERE
-- ===================================================

CREATE DATABASE EMPRESA_MER_MERE;
USE EMPRESA_MER_MERE;

-- ===================================================
-- TABELA PRINCIPAL: FUNCIONARIO
-- ===================================================

CREATE TABLE FUNCIONARIO (
    CPF CHAR(11) PRIMARY KEY,
    PNome VARCHAR(30) NOT NULL,
    MInicial CHAR(1),
    UNome VARCHAR(30) NOT NULL,
    Data_Nascimento DATE NOT NULL,
    Endereco VARCHAR(80)
);

-- ===================================================
-- SUBCLASSES POR TIPO DE FUNÇÃO
-- ===================================================

-- Secretaria

CREATE TABLE SECRETARIA (
    CPF_FUNC CHAR(11) PRIMARY KEY,
    Velocidade_Digitacao VARCHAR(30) NOT NULL,
    FOREIGN KEY (CPF_FUNC) REFERENCES FUNCIONARIO(CPF)
);

-- Técnico

CREATE TABLE TECNICO (
    CPF_FUNC CHAR(11) PRIMARY KEY,
    Grau_Tec VARCHAR(30) NOT NULL,
    FOREIGN KEY (CPF_FUNC) REFERENCES FUNCIONARIO(CPF)
);

-- Engenheiro

CREATE TABLE ENGENHEIRO (
    CPF_FUNC CHAR(11) PRIMARY KEY,
    Tipo_Eng VARCHAR(30) NOT NULL,
    FOREIGN KEY (CPF_FUNC) REFERENCES FUNCIONARIO(CPF)
);

-- ===================================================
-- SUBCLASSES POR TIPO DE PAGAMENTO
-- ===================================================

-- Horista

CREATE TABLE SALARIO_HORISTA (
    CPF_FUNC CHAR(11) PRIMARY KEY,
    Escala_Pagamento DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (CPF_FUNC) REFERENCES FUNCIONARIO(CPF)
);

-- Mensalista

CREATE TABLE SALARIO_MENSALISTA (
    CPF_FUNC CHAR(11) PRIMARY KEY,
    Salario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (CPF_FUNC) REFERENCES FUNCIONARIO(CPF)
);

-- ===================================================
-- INSERÇÃO DE DADOS EXEMPLO
-- ===================================================

INSERT INTO FUNCIONARIO (CPF, PNome, MInicial, UNome, Data_Nascimento, Endereco)
VALUES 
('1111111111', 'MARIANA', 'M', 'SILVA', '2000-01-01', 'RUA X'),
('2222222222', 'JOAQUINA', 'N', 'MOREIRA', '1991-10-02', 'RUA 8');

-- Secretária 

INSERT INTO SECRETARIA (CPF_FUNC, Velocidade_Digitacao)
VALUES ('1111111111', '1000 PALAVRAS MIN');

-- Engenheira 

INSERT INTO ENGENHEIRO (CPF_FUNC, Tipo_Eng)
VALUES ('2222222222', 'ELETRICISTA');

-- Pagamentos

INSERT INTO SALARIO_MENSALISTA (CPF_FUNC, Salario)
VALUES ('1111111111', 2000);

INSERT INTO SALARIO_HORISTA (CPF_FUNC, Escala_Pagamento)
VALUES ('2222222222', 78000);
