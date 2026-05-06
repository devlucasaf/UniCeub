-- Criação de tabelas
CREATE TABLE Clientes (
    ClienteID INT PRIMARY KEY,
    Nome VARCHAR(100),
    Cidade VARCHAR(50),
    Estado VARCHAR(50)
);

CREATE TABLE Produtos (
    ProdutoID INT PRIMARY KEY,
    Nome VARCHAR(100),
    Categoria VARCHAR(50),
    Preco DECIMAL(10,2)
);

CREATE TABLE Pedidos (
    PedidoID INT PRIMARY KEY,
    ClienteID INT,
    DataPedido DATE,
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID)
);

CREATE TABLE ItensPedido (
    ItemID INT PRIMARY KEY,
    PedidoID INT,
    ProdutoID INT,
    Quantidade INT,
    FOREIGN KEY (PedidoID) REFERENCES Pedidos(PedidoID),
    FOREIGN KEY (ProdutoID) REFERENCES Produtos(ProdutoID)
);

-- Inserção de dados
INSERT INTO Clientes VALUES (1, 'Maria Silva', 'São Paulo', 'SP');
INSERT INTO Clientes VALUES (2, 'João Souza', 'Rio de Janeiro', 'RJ');
INSERT INTO Clientes VALUES (3, 'Ana Costa', 'Belo Horizonte', 'MG');

INSERT INTO Produtos VALUES (1, 'Notebook', 'Eletrônicos', 3500.00);
INSERT INTO Produtos VALUES (2, 'Celular', 'Eletrônicos', 2000.00);
INSERT INTO Produtos VALUES (3, 'Cadeira Gamer', 'Móveis', 800.00);
INSERT INTO Produtos VALUES (4, 'Mesa Escritório', 'Móveis', 1200.00);

INSERT INTO Pedidos VALUES (1, 1, '2024-01-10');
INSERT INTO Pedidos VALUES (2, 2, '2024-02-15');
INSERT INTO Pedidos VALUES (3, 1, '2024-03-20');

INSERT INTO ItensPedido VALUES (1, 1, 1, 1);
INSERT INTO ItensPedido VALUES (2, 1, 2, 2);
INSERT INTO ItensPedido VALUES (3, 2, 3, 1);
INSERT INTO ItensPedido VALUES (4, 3, 4, 1);
INSERT INTO ItensPedido VALUES (5, 3, 2, 1);

-- Consultas declarativas
-- 1. Selecionar todos os clientes
SELECT * FROM Clientes;

-- 2. Selecionar produtos da categoria 'Eletrônicos'
SELECT Nome, Preco FROM Produtos WHERE Categoria = 'Eletrônicos';

-- 3. Listar pedidos com seus clientes
SELECT P.PedidoID, C.Nome, P.DataPedido
FROM Pedidos P
JOIN Clientes C ON P.ClienteID = C.ClienteID;

-- 4. Calcular valor total de cada pedido
SELECT P.PedidoID, SUM(I.Quantidade * Pr.Preco) AS ValorTotal
FROM Pedidos P
JOIN ItensPedido I ON P.PedidoID = I.PedidoID
JOIN Produtos Pr ON I.ProdutoID = Pr.ProdutoID
GROUP BY P.PedidoID;

-- 5. Mostrar clientes que compraram mais de 1 produto
SELECT C.Nome, COUNT(I.ItemID) AS TotalItens
FROM Clientes C
JOIN Pedidos P ON C.ClienteID = P.ClienteID
JOIN ItensPedido I ON P.PedidoID = I.PedidoID
GROUP BY C.Nome
HAVING COUNT(I.ItemID) > 1;

-- 6. Média de preços por categoria
SELECT Categoria, AVG(Preco) AS MediaPreco
FROM Produtos
GROUP BY Categoria;

-- 7. Produtos acima de R$1000
SELECT Nome, Preco FROM Produtos WHERE Preco > 1000;

-- 8. Clientes de São Paulo
SELECT Nome FROM Clientes WHERE Cidade = 'São Paulo';

-- 9. Pedidos realizados em março de 2024
SELECT PedidoID FROM Pedidos WHERE MONTH(DataPedido) = 3 AND YEAR(DataPedido) = 2024;

-- 10. Ranking de produtos mais vendidos
SELECT Pr.Nome, SUM(I.Quantidade) AS TotalVendido
FROM Produtos Pr
JOIN ItensPedido I ON Pr.ProdutoID = I.ProdutoID
GROUP BY Pr.Nome
ORDER BY TotalVendido DESC;
