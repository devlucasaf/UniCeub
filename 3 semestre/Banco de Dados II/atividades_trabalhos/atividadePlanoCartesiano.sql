-- =========================================================
-- BANCO DE DADOS: Empresa_Produto_Cartesiano
-- Autor: Lucas André Ferreira de Freitas
-- Professor: Michel Junio Ferreira Rosa
-- =========================================================

CREATE DATABASE Empresa_Produto_Cartesiano;
\c Empresa_Produto_Cartesiano;

-- =========================================================
-- TABELA: departamento
-- =========================================================
CREATE TABLE departamento (
    dnr SERIAL PRIMARY KEY,
    nome_d VARCHAR(50) NOT NULL
);

-- =========================================================
-- TABELA: funcionario
-- =========================================================
CREATE TABLE funcionario (
    cpf CHAR(11) PRIMARY KEY,
    nome_f VARCHAR(60) NOT NULL,
    fk_dnr INT,
    FOREIGN KEY (fk_dnr) REFERENCES departamento(dnr)
);

-- =========================================================
-- INSERINDO DADOS DE EXEMPLO
-- =========================================================
INSERT INTO departamento (nome_d) VALUES
('RH'),
('Financeiro'),
('C. COMP'),
('Pesquisa');

INSERT INTO funcionario (cpf, nome_f, fk_dnr) VALUES
('11111111111', 'JOANA', 1),
('22222222222', 'PEDRO', 2),
('33333333333', 'MARIA', 3),
('44444444444', 'LUCAS', 3),
('55555555555', 'JULIANA', 4);

-- Consultas SQL para verificar os dados inseridos:

-- questão 1

π nome_f, nome_d (funcionario ⋈ funcionario.fk_dnr = departamento.dnr departamento) 

-- questão 2

π nome_d (σ nome_f = 'JOANA' (funcionario ⋈ funcionario.fk_dnr = departamento.dnr departamento)) 

-- questão 3

π cpf (σ nome_d = 'C. COMP' (funcionario ⋈ funcionario.fk_dnr = departamento.dnr departamento)) 
