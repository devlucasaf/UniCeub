/*
Banco de Dados II
Aula: Functions e Stored Procedures
Data: 14/04/2024
*/

CREATE DATABASE LojaDB;

CREATE TABLE Produto (
    id_produto INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    estoque INT NOT NULL
);

INSERT INTO Produto VALUES
(1, 'Teclado Mecânico', 250.00, 15),
(2, 'Mouse Gamer', 150.00, 30),
(3, 'Monitor 24"', 950.00, 8);

CREATE OR REPLACE FUNCTION TotalEstoque()
RETURNS DECIMAL(10,2)
LANGUAGE plpgsql
AS $$
DECLARE 
    total DECIMAL(10,2);
BEGIN
    SELECT SUM(preco * estoque)
    INTO total
    FROM Produto;
    
    RETURN COALESCE(total, 0);
END;
$$;

SELECT TotalEstoque() AS Total_Estoque;

CREATE OR REPLACE FUNCTION CalcularImposto(valor DECIMAL(10,2))
RETURNS DECIMAL(10,2)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN valor * 0.10;
END;
$$;

SELECT nome, preco, CalcularImposto(preco) AS Imposto
FROM Produto;

CREATE OR REPLACE PROCEDURE VenderProduto(
    pid INT,
    quantidade INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE Produto
    SET estoque = estoque - quantidade
    WHERE id_produto = pid;
END;
$$;

CALL VenderProduto(1, 2);

SELECT * FROM Produto;

CREATE OR REPLACE PROCEDURE InserirProduto(
    pid INT,
    pnome VARCHAR(100),
    ppreco DECIMAL(10,2),
    pestoque INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Produto (id_produto, nome, preco, estoque)
    VALUES (pid, pnome, ppreco, pestoque);
END;
$$;

CALL InserirProduto(4, 'Headset Gamer', 320.00, 12);

SELECT * FROM Produto;