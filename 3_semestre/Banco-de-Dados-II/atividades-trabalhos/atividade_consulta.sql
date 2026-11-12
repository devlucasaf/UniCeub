-- Banco de Dados II
-- Atividade: Consultas SQL

-- a)

SELECT Produto.Codigo_produto, Produto.Descricao, Produto.Quantidade_Inicial - Saida.Quantidade
FROM Produto LEFT OUTER JOIN
    (SELECT Codigo_produto,
            SUM(Quantidade) as Quantidade
    FROM Saida
    GROUP BY Codigo_produto) Saida
ON (Produto.Codigo_produto = Saida.Codigo_produto);

-- b)

SELECT *
FROM Fornecedor
ORDER BY Nome;

-- c)

SELECT Nome, Telefone
FROM Fornecedor
WHERE Cidade = 'Florianopolis'
ORDER BY Nome;
