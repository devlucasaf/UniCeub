-- Criar tabela de produtos
CREATE TABLE Produtos (
    id_produto INT PRIMARY KEY,
    nome_produto VARCHAR(100),
    preco DECIMAL(10,2)
);

-- Criar tabela de itens do pedido
CREATE TABLE ItensPedido (
    id_pedido INT,
    id_produto INT,
    quantidade INT,
    PRIMARY KEY (id_pedido, id_produto),
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES Produtos(id_produto)
);

-- Inserir produtos
INSERT INTO Produtos VALUES
(201, 'Teclado Mecânico', 250.00),
(202, 'Mouse Gamer', 150.00),
(203, 'Monitor 24"', 900.00);

-- Inserir itens de pedidos
INSERT INTO ItensPedido VALUES
(101, 201, 1),
(101, 202, 2),
(102, 203, 1),
(103, 202, 1);

-- =====================================================
-- ✅ JOIN entre 3 tabelas (Clientes + Pedidos + Itens)
SELECT C.nome, P.id_pedido, I.id_produto, I.quantidade
FROM Clientes C
INNER JOIN Pedidos P ON C.id_cliente = P.id_cliente
INNER JOIN ItensPedido I ON P.id_pedido = I.id_pedido;


-- =====================================================
-- ✅ JOIN com 4 tabelas (exibindo o nome do produto)
SELECT  C.nome AS Cliente,
        P.id_pedido AS Pedido,
        Pr.nome_produto AS Produto,
        I.quantidade,
        (I.quantidade * Pr.preco) AS Total_Produto
FROM Clientes C
INNER JOIN Pedidos P ON C.id_cliente = P.id_cliente
INNER JOIN ItensPedido I ON P.id_pedido = I.id_pedido
INNER JOIN Produtos Pr ON I.id_produto = Pr.id_produto;


-- =====================================================
-- ✅ FULL JOIN (em bancos que suportam)
-- MySQL usa UNION de LEFT + RIGHT
SELECT * FROM Clientes
LEFT JOIN Pedidos ON Clientes.id_cliente = Pedidos.id_cliente
UNION
SELECT * FROM Clientes
RIGHT JOIN Pedidos ON Clientes.id_cliente = Pedidos.id_cliente;
