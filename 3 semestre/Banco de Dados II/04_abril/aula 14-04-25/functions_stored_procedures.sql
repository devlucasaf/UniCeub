/*
Banco de Dados II
Aula: Functions e Stored Procedures
Data: 14/04/2024
*/

-- Criando Banco de Dados

CREATE DATABASE LojaDB;
USE LojaDB;

-- Criando tabela de produtos

CREATE TABLE Produto (
    id_produto INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    estoque INT NOT NULL
);

-- Inserindo dados

INSERT INTO Produto VALUES
(1, 'Teclado Mecânico', 250.00, 15),
(2, 'Mouse Gamer', 150.00, 30),
(3, 'Monitor 24"', 950.00, 8);


-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- FUNCTION: Calcula valor total de produtos em estoque

-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

DELIMITER $$

CREATE FUNCTION TotalEstoque()
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total DECIMAL(10,2);
    
    SELECT SUM(preco * estoque)
    INTO total
    FROM Produto;
    
    RETURN total;
END $$

DELIMITER ;


-- TESTAR FUNCTION

SELECT TotalEstoque() AS Total_Estoque;

-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- FUNCTION: Calcula imposto sobre um valor

-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

DELIMITER $$

CREATE FUNCTION CalcularImposto(valor DECIMAL(10,2))
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    RETURN valor * 0.10; -- imposto de 10%
END $$

DELIMITER ;

-- TESTAR FUNCTION

SELECT nome, preco, CalcularImposto(preco) AS Imposto
FROM Produto;


-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- ✅ STORED PROCEDURE: Atualiza estoque após uma venda

-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

DELIMITER $$

CREATE PROCEDURE VenderProduto(
    IN pid INT,
    IN quantidade INT
)
BEGIN
    UPDATE Produto
    SET estoque = estoque - quantidade
    WHERE id_produto = pid;
END $$

DELIMITER ;

-- TESTAR PROCEDURE

CALL VenderProduto(1, 2); -- vende 2 teclados

SELECT * FROM Produto;


-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- STORED PROCEDURE: Adiciona um novo produto

-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

DELIMITER $$

CREATE PROCEDURE InserirProduto(
    IN pid INT,
    IN pnome VARCHAR(100),
    IN ppreco DECIMAL(10,2),
    IN pestoque INT
)
BEGIN
    INSERT INTO Produto (id_produto, nome, preco, estoque)
    VALUES (pid, pnome, ppreco, pestoque);
END $$

DELIMITER ;

-- TESTAR PROCEDURE

CALL InserirProduto(4, 'Headset Gamer', 320.00, 12);
SELECT * FROM Produto;
