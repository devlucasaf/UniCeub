-- Transações bancários em Banco de Dados
-- Banco de Dados II
-- Aula: 09/06/2025

CREATE TABLE contas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    saldo NUMERIC(10,2)
);

INSERT INTO contas (nome, saldo) VALUES
('Alice', 1000.00),
('Bob', 1000.00);

BEGIN;

UPDATE contas SET saldo = saldo - 100 WHERE nome = 'Alice';
UPDATE contas SET saldo = saldo + 100 WHERE nome = 'Bob';
UPDATE conta SET saldo = saldo + 100 WHERE nome = 'Bob';

COMMIT;

SELECT * FROM conta;

BEGIN;
UPDATE contas SET saldo = saldo - 50 WHERE nome = 'Alice';
INSERT INTO log_transacoes VALUES ('erro proposital');
ROLLBACK;

SELECT * FROM contas;

BEGIN;
UPDATE contas SET saldo = saldo - 50 WHERE nome = 'Alice';
SAVEPOINT antes_credito;
UPDATE contas SET saldo = saldo + 50 WHERE nome = 'Bob';
ROLLBACK TO antes_credito;
COMMIT;

BEGIN;
SELECT * FROM contas WHERE nome = 'Alice' FOR UPDATE;

BEGIN;
UPDATE contas SET saldo = saldo - 200 WHERE nome = 'Alice';
