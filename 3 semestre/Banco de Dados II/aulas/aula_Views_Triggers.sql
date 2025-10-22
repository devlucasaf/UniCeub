-- ============================================================
-- AULA: VIEWS (VISÕES) e TRIGGERS (GATILHOS)
-- Professor: Michel Junio Ferreira Rosa
-- Curso: Ciência da Computação
-- Disciplina: Banco de Dados II
-- ============================================================

-- ------------------------------------------------------------
-- 1. CRIAÇÃO DE VIEW (VISÃO)
-- ------------------------------------------------------------

-- Exemplo: Criar uma visão que mostra o nome e o endereço
-- de funcionários que trabalham no projeto “Produto X”.
CREATE VIEW empregados_produtoX (nome, endereco) AS
SELECT f.pnome, f.endereco
FROM funcionario AS f
JOIN trabalha_em AS t ON f.cpf = t.fcpf
JOIN projeto AS p ON t.projnumero = p.projnumero
WHERE p.projnome = 'Produto X';

-- Visualizar os dados da VIEW
SELECT * FROM empregados_produtoX;

-- ------------------------------------------------------------
-- 2. ALTERAÇÃO DE UMA VIEW
-- ------------------------------------------------------------

-- Exemplo: Alterar a view adicionando o salário do funcionário.
CREATE OR REPLACE VIEW empregados_produtoX (nome, endereco, salario) AS
SELECT f.pnome, f.endereco, f.salario
FROM funcionario AS f
JOIN trabalha_em AS t ON f.cpf = t.fcpf
JOIN projeto AS p ON t.projnumero = p.projnumero
WHERE p.projnome = 'Produto X';

-- ------------------------------------------------------------
-- 3. EXCLUSÃO DE UMA VIEW
-- ------------------------------------------------------------

DROP VIEW empregados_produtoX;

-- ------------------------------------------------------------
-- 4. CRIAÇÃO DE TRIGGER (GATILHO)
-- ------------------------------------------------------------

-- Exemplo: Inserir um registro na tabela FuncionarioLog
-- sempre que um novo funcionário for adicionado.
CREATE TRIGGER tg_Funcionario_Insert
ON Funcionario
AFTER INSERT
AS
INSERT INTO FuncionarioLog (FunCPF, Comando, Usuario, Data)
VALUES (cpf, 'INSERT', CURRENT_USER, CURRENT_DATE);

-- ------------------------------------------------------------
-- 5. ALTERAÇÃO DE TRIGGER
-- ------------------------------------------------------------

-- Renomear o trigger existente
ALTER TRIGGER tg_Funcionario_Insert
ON Funcionario
RENAME TO tgFuncionarioInsert;

-- ------------------------------------------------------------
-- 6. EXCLUSÃO DE TRIGGER
-- ------------------------------------------------------------

DROP TRIGGER tgFuncionarioInsert ON Funcionario CASCADE;

-- ------------------------------------------------------------
-- 7. HABILITAR E DESABILITAR TRIGGERS
-- ------------------------------------------------------------

-- Desabilitar trigger
ALTER TABLE Funcionario
DISABLE TRIGGER tgFuncionarioInsert;

-- Habilitar trigger
ALTER TABLE Funcionario
ENABLE TRIGGER tgFuncionarioInsert;

-- ------------------------------------------------------------
-- 8. TRIGGER NO POSTGRESQL
-- ------------------------------------------------------------

-- Criar função associada à trigger
CREATE OR REPLACE FUNCTION insert_funcionario()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO FuncionarioLog (FunCPF, Comando, Usuario, Data)
    VALUES (NEW.cpf, 'INSERT', CURRENT_USER, CURRENT_DATE);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Criar trigger que chama a função
CREATE TRIGGER tg_funcionario_insert
AFTER INSERT ON funcionario
FOR EACH ROW
EXECUTE PROCEDURE insert_funcionario();

-- Alterar nome da trigger
ALTER TRIGGER tg_validaCpf ON Funcionario RENAME TO tgValidaCPF;

-- Excluir trigger
DROP TRIGGER tg_validaCpf ON Funcionario CASCADE;

-- Habilitar e desabilitar trigger
ALTER TABLE funcionario DISABLE TRIGGER tg_funcionario_insert;
ALTER TABLE funcionario ENABLE TRIGGER tg_funcionario_insert;

-- Consultar triggers do banco
SELECT * FROM INFORMATION_SCHEMA.TRIGGERS;

-- ------------------------------------------------------------
-- 9. RULES (REGRAS) – POSTGRESQL
-- ------------------------------------------------------------

-- Criar tabela de log
CREATE TABLE log_customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    usuario VARCHAR(50),
    acao VARCHAR(20),
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criar rule para armazenar deleções
CREATE OR REPLACE RULE rl_customers_delete AS
ON DELETE TO customers DO
INSERT INTO log_customers (name, acao, usuario)
VALUES (OLD.name, 'DELETE', CURRENT_USER);

-- Excluir rule
DROP RULE rl_customers_delete ON customers CASCADE;

-- Consultar rules
SELECT * FROM pg_rules WHERE tablename = 'customers';

-- ------------------------------------------------------------
-- 10. TRIGGER NO MYSQL
-- ------------------------------------------------------------

DELIMITER $$

CREATE TRIGGER tg_funcionario_insert
AFTER INSERT ON Funcionario
FOR EACH ROW
BEGIN
    INSERT INTO FuncionarioLog (FunCPF, Comando, Usuario, Data)
    VALUES (NEW.cpf, 'INSERT', CURRENT_USER(), CURRENT_DATE());
END$$

DELIMITER ;

-- Excluir trigger no MySQL
DROP TRIGGER tg_funcionario_insert;

-- Ver triggers existentes
SHOW TRIGGERS;

-- ------------------------------------------------------------
-- 11. TRIGGER NO SQL SERVER
-- ------------------------------------------------------------

CREATE TRIGGER tg_funcionario_insert
ON funcionario
FOR INSERT
AS
BEGIN
    DECLARE @fcpf INT;
    SELECT @fcpf = cpf FROM INSERTED;

    INSERT INTO FuncionarioLog (FunCPF, Comando, Usuario, Data)
    VALUES (@fcpf, 'INSERT', CURRENT_USER, GETDATE());
END;

-- Alterar trigger no SQL Server
ALTER TRIGGER tg_funcionario_insert
ON funcionario
FOR INSERT
AS
BEGIN
    -- Código revisado aqui
END;

-- Excluir trigger
DROP TRIGGER tg_funcionario_insert;

-- Habilitar e desabilitar trigger
ALTER TABLE funcionario ENABLE TRIGGER tg_funcionario_insert;
ALTER TABLE funcionario DISABLE TRIGGER tg_funcionario_insert;
