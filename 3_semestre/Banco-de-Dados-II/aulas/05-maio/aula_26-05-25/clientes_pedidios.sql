-- Banco de Dados II
-- Prática Índices
-- Aula Indice 26-05-25

CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    cidade VARCHAR(50),
    estado VARCHAR(2)
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER REFERENCES clientes(id),
    data_pedido DATE,
    valor NUMERIC(10,2)
);

INSERT INTO clientes (nome, email, cidade, estado)
SELECT
    'Cliente_' || i,
    'cliente' || i || '@teste.com',
    'Cidade_' || (i % 100),
    CASE WHEN i % 2 = 0 THEN 'SP' ELSE 'DF' END
FROM generate_series(1, 10000000) AS s(i);

INSERT INTO pedidos (cliente_id, data_pedido, valor)
SELECT
    (RANDOM() * 9999999 + 1)::INTEGER,
    CURRENT_DATE - (RANDOM() * 365)::INTEGER,
    ROUND((RANDOM() * 1000 + 100)::numeric, 2)
FROM generate_series(1, 1000000) AS s(i);

EXPLAIN ANALYZE
SELECT c.nome, p.valor
FROM pedidos p
JOIN clientes c ON c.id = p.clientes_id
WHERE c.email ilike 'cliete50000@teste.com';

CREATE INDEX idx_clientes_email ON clientes(email);
CREATE INDEX idx_pedidos_cliente_id ON pedidos(cliente_id);
