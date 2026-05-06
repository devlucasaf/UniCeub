/*
Banco de Dados II
Aula: Índices em Bancos de Dados
Data: 28/04/2025
*/

CREATE DATABASE LojaOnline;
USE LojaOnline; 

-- Tabela de Clientes

CREATE TABLE Cliente (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    cpf CHAR(11) NOT NULL UNIQUE,
    cidade VARCHAR(100)
);

-- Inserindo dados

INSERT INTO Cliente VALUES
(1, 'Lucas Freitas', 'lucas@email.com', '12345678901', 'Brasília'),
(2, 'Ana Souza', 'ana@gmail.com', '98765432100', 'Rio de Janeiro'),
(3, 'Rafael Lima', 'rafael@gmail.com', '22233344455', 'São Paulo'),
(4, 'Mariana Silva', 'mariana@gmail.com', '11122233344', 'Brasília');


CREATE INDEX idx_cliente_nome
ON Cliente(nome);

CREATE INDEX idx_cliente_cidade_nome
ON Cliente(cidade, nome);

SELECT * FROM Cliente
WHERE nome LIKE 'Lucas%';


SELECT * FROM Cliente
WHERE cidade = 'Brasília'
ORDER BY nome;

SHOW INDEX FROM Cliente;
