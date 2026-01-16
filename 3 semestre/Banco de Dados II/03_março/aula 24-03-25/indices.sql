-- Banco de Dados II
-- Prática de Índices
-- Aula: 24-03-25

CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    cidade VARCHAR(50),
    estado VARCHAR(2)
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER REFERENCES clientes(id),
    data_pedido DATE,
    valor NUMERIC(10, 2)
);

-- Popula a tabela clientes

INSERT INTO clientes(nome, email, cidade, estado)
SELECT
    'Cliente_' || i,
    'cliente' || i || '@teste.com',
    'Cidade_' || (i % 100),
    CASE WHEN i % 2 = 0 THEN 'SP' ELSE 'DF' END
FROM generate_series(1, 10000000) AS s(i);

-- Popula a tabela pedidos

INSERT INTO pedidos (cliente_id, data_pedido, valor)
SELECT 
    (RANDOM() * 9999999 + 1) :: INTEGER, -- cliente_id aleatório
    CURRENT_DATE - (RANDOM() * 1000 + 100)::NUMERIC, 2)
FROM generate_series(1, 10000000) AS s(i);

EXPLAIN ANALYZE
SELECT c.nome, p.valor
FROM pedidos p
JOIN clientes c ON c.id = p.cliente_id
WHERE c.email ILIKE 'cliente%25@teste.com';

CREATE INDEX idx_clientes_email ON clientes(email);
CREATE INDEX idx_pedidos_clientes ON pedidos(cliente_id);
