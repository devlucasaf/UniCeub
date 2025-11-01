/*
Banco de Dados II
Aula: Joins
Data: 17/03/2024
*/

-- Criar um banco de dados

CREATE DATABASE LojaVirtual;
USE LojaVirtual;

-- Criar tabelas

CREATE TABLE Clientes (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(100),
    cidade VARCHAR(100)
);

CREATE TABLE Pedidos (
    id_pedido INT PRIMARY KEY,
    id_cliente INT,
    data_pedido DATE,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);

-- Inserir dados

INSERT INTO Clientes VALUES
(1, 'Lucas Freitas', 'Brasília'),
(2, 'Ana Souza', 'Rio de Janeiro'),
(3, 'Marcos Lima', 'São Paulo');

INSERT INTO Pedidos VALUES
(101, 1, '2025-01-10'),
(102, 1, '2025-02-02'),
(103, 2, '2025-01-20');

-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- INNER JOIN — traz apenas registros relacionados
-- Clientes que têm pedidos

SELECT Clientes.nome, Pedidos.id_pedido, Pedidos.data_pedido
FROM Clientes
INNER JOIN Pedidos
ON Clientes.id_cliente = Pedidos.id_cliente;


-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- LEFT JOIN — traz todos clientes e seus pedidos (se tiver)

SELECT Clientes.nome, Pedidos.id_pedido
FROM Clientes
LEFT JOIN Pedidos
ON Clientes.id_cliente = Pedidos.id_cliente;


-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- RIGHT JOIN — traz todos pedidos e seus clientes

SELECT Clientes.nome, Pedidos.id_pedido
FROM Clientes
RIGHT JOIN Pedidos
ON Clientes.id_cliente = Pedidos.id_cliente;
