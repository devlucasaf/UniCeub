CREATE VIEW empregados_produtoX AS
SELECT f.pnome AS nome, f.endereco
FROM funcionario f
JOIN trabalha_em t ON f.cpf = t.fcpf
JOIN projeto p ON t.projnumero = p.projnumero
WHERE p.projnome = 'Produto X';

CREATE OR REPLACE VIEW empregados_produtoX AS
SELECT f.pnome AS nome, f.endereco, f.salario
FROM funcionario f
JOIN trabalha_em t ON f.cpf = t.fcpf
JOIN projeto p ON t.projnumero = p.projnumero
WHERE p.projnome = 'Produto X';

DROP VIEW empregados_produtoX;

CREATE OR REPLACE FUNCTION insert_funcionario()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO FuncionarioLog (FunCPF, Comando, Usuario, Data)
    VALUES (NEW.cpf, 'INSERT', CURRENT_USER, CURRENT_DATE);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tg_funcionario_insert
AFTER INSERT ON funcionario
FOR EACH ROW
EXECUTE FUNCTION insert_funcionario();

ALTER TRIGGER tg_funcionario_insert ON funcionario RENAME TO tgFuncionarioInsert;

DROP TRIGGER tgFuncionarioInsert ON funcionario CASCADE;

ALTER TABLE funcionario DISABLE TRIGGER tgFuncionarioInsert;
ALTER TABLE funcionario ENABLE TRIGGER tgFuncionarioInsert;

SELECT * FROM information_schema.triggers;

CREATE TABLE log_customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    usuario VARCHAR(50),
    acao VARCHAR(20),
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE RULE rl_customers_delete AS
ON DELETE TO customers DO
INSERT INTO log_customers (name, acao, usuario)
VALUES (OLD.name, 'DELETE', CURRENT_USER);

DROP RULE rl_customers_delete ON customers CASCADE;

SELECT * FROM pg_rules WHERE tablename = 'customers';
