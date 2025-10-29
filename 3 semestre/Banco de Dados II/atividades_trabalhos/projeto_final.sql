-- Projeto Final --
-- Banco de Dados II
-- Alunos: Lorena Araujo, Lucas Freitas e Mateus Nascimento

-- DDL Padrão para TODOS os grupos
-- Criação das tabelas principais

CREATE TABLE perfis (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    limite_emprestimos INTEGER NOT NULL,
    dias_penalidade_por_dia_atraso INTEGER NOT NULL
);

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    perfil_id INTEGER NOT NULL,
    penalizado_ate DATE,
    FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);

CREATE TABLE recursos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    prazo_maximo_dias INTEGER NOT NULL
);

CREATE TABLE emprestimos (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    recurso_id INTEGER NOT NULL,
    data_emprestimo DATE NOT NULL DEFAULT CURRENT_DATE,
    data_devolucao DATE,
    data_prevista DATE NOT NULL,
    renovado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (recurso_id) REFERENCES recursos(id)
);

CREATE TABLE reservas (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    recurso_id INTEGER NOT NULL,
    data_reserva DATE NOT NULL DEFAULT CURRENT_DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (recurso_id) REFERENCES recursos(id)
);

CREATE TABLE penalidades (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    dias_atraso INTEGER NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Perfis
INSERT INTO perfis (nome, limite_emprestimos, dias_penalidade_por_dia_atraso) VALUES
--1
('Aluno', 3, 2),

--2
('Professor', 5, 1),

--3
('Técnico', 2, 3);

-- Usuários
INSERT INTO usuarios (nome, perfil_id) VALUES
('Alice Silva', 1),
('Bruno Costa', 2),
('Carla Mendes', 1),
('Daniel Rocha', 3),
('Eduarda Lima', 2);



-- Recursos
INSERT INTO recursos (nome, tipo, prazo_maximo_dias) VALUES
('Notebook Dell', 'Eletrônico', 7),
('Livro: Estruturas de Dados', 'Livro', 14),
('Projetor Epson', 'Eletrônico', 5),
('Multímetro Digital', 'Ferramenta', 3),
('Livro: Banco de Dados Avançado', 'Livro', 10);

-- Empréstimos
INSERT INTO emprestimos (usuario_id, recurso_id, data_emprestimo, data_prevista) VALUES
(1, 2, CURRENT_DATE - 10, CURRENT_DATE - 2), -- atraso
(2, 1, CURRENT_DATE - 3, CURRENT_DATE + 4),
(3, 3, CURRENT_DATE - 1, CURRENT_DATE + 4),
(4, 4, CURRENT_DATE - 5, CURRENT_DATE - 1), -- atraso
(5, 5, CURRENT_DATE - 2, CURRENT_DATE + 8);


-- Reservas
INSERT INTO reservas (usuario_id, recurso_id) VALUES
(3, 2),
(1, 3),
(2, 5),
(4, 1),
(5, 4);


-----------------------------------------FUNÇÕES-------------------------------------------------------
--Função para verificar se os usuário podem realizar empréstimos 
--(verifica se a data prevista do empréstimo é maior que a data de inserção dos dados)

CREATE OR REPLACE FUNCTION pode_emprestar(p_usuario_id INTEGER)
RETURNS BOOLEAN AS $$
BEGIN
    --Verifica se a data prevista para a devolução é menor que a data de inserção dos dados (16/06/2025)
	RETURN NOT EXISTS (
        SELECT *
        FROM emprestimos
        WHERE usuario_id = p_usuario_id
            AND data_prevista < CURRENT_DATE
    );
END;
$$ LANGUAGE plpgsql;

--Seleciona as informações que desejam ser apresentadas e altera o nome para "emprestimo_liberado"
SELECT u.id AS usuario_id, u.nome, pode_emprestar(u.id) AS emprestimo_liberado
FROM usuarios u
ORDER BY u.id;

-- Apresenta apenas uma informação singular de um usuário específico
SELECT * FROM pode_emprestar(1);

------------------------------------------------------------------------------------------------
-- Função para calcular a data de liberação do usuário

CREATE OR REPLACE FUNCTION calcula_data_liberacao(p_usuario_id INTEGER)
RETURNS DATE AS $$
DECLARE
    total_dias_penalidade INTEGER;
BEGIN
    -- Cálculo geral dos dias da penalidade
	SELECT 
        COALESCE((
            -- Encontra e soma todos os empréstimos não devolvidos e o total de dias que a penalidade será aplicada
			SELECT SUM(GREATEST(CURRENT_DATE - emprestimos.data_prevista, 0))
            FROM emprestimos 
            WHERE emprestimos.usuario_id = p_usuario_id
                AND emprestimos.data_prevista < CURRENT_DATE
                AND emprestimos.data_devolucao IS NULL
        ), 0)
        *
        (
            -- Adquire o multiplicador de penalidade de acordo com o perfil do usuário
			SELECT perfis.dias_penalidade_por_dia_atraso
            FROM usuarios 
            JOIN perfis ON perfis.id = usuarios.perfil_id
            WHERE usuarios.id = p_usuario_id
        )
    
	-- Insere os dados adquiridos à funcção temporária
	INTO total_dias_penalidade;

	-- Caso a pessoa não tenha diso penalizada, retornará como NULL
    IF total_dias_penalidade = 0 THEN
        RETURN NULL;
    ELSE
        -- Caso a pessoa possua devoluções pendentes, será calculado qual dia será permitido que haja um novo empréstimo
		RETURN CURRENT_DATE + (total_dias_penalidade || ' days')::interval;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Apresenta todos os usuários e as suas respectivas datas de liberação e altera o nome para "dia_liberacao"
SELECT usuarios.id AS usuario_id, usuarios.nome, calcula_data_liberacao(usuarios.id) AS dia_liberacao
FROM usuarios 
ORDER BY usuarios.id;

-- Apresenta apenas uma informação singular de um usuário específico
SELECT * FROM calcula_data_liberacao(1);

------------------------------------------------------------------------------------------------
-- Função para verificar a disponibilidade dos recursos

CREATE OR REPLACE FUNCTION recurso_disponivel(p_recurso_id INTEGER)
RETURNS BOOLEAN AS $$
DECLARE
    emprestimo_ativo INTEGER;
BEGIN
    -- Verifica se o recurso já está emprestado
    SELECT COUNT(*) INTO emprestimo_ativo
    FROM emprestimos
    WHERE recurso_id = p_recurso_id
        AND data_devolucao IS NULL;

    -- Se houver empréstimo ativo, recurso não está disponível
    IF emprestimo_ativo > 0 THEN
        RETURN FALSE;
    ELSE
        RETURN TRUE;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Mostra o ID e a disponibilidade do recruso, respectivamente
SELECT id, recurso_disponivel(recursos.id)
FROM recursos
ORDER BY recursos.id;

-- Apresenta apenas a disponibilidade de um recurso específico
SELECT * FROM recurso_disponivel(1);

---------------------------------------------TRIGGER--------------------------------------------------
-- Função com TRIGGER para atualizar a tabela 'penalidades' após uma atualização em 'emprestimos' em que seu valor seja superior ao dia previsto
CREATE OR REPLACE FUNCTION aplicar_penalidade()
RETURNS TRIGGER AS $$
BEGIN
    -- Verifica se houve atraso na devolução
    IF NEW.data_devolucao IS NOT NULL AND NEW.data_devolucao > NEW.data_prevista THEN

        -- Calcula os dias de atraso (dia da devolução menos a data prevista da devolução), busca o fator de penalização de acordo
		-- com o perfil do usuário, calcula a data final da penalização e insere na tabela de 'penalidades'
        INSERT INTO penalidades (usuario_id, dias_atraso, data_inicio, data_fim)
        VALUES (
            NEW.usuario_id,
            NEW.data_devolucao - NEW.data_prevista,
            CURRENT_DATE,
            CURRENT_DATE + (
                (NEW.data_devolucao - NEW.data_prevista) *
                (
                    SELECT perfis.dias_penalidade_por_dia_atraso
                    FROM usuarios 
                    JOIN perfis ON perfis.id = usuarios.perfil_id
                    WHERE usuarios.id = NEW.usuario_id
                )
            )::INT
        );

        -- Atualiza o campo 'penalizado_ate' do usuário com a nova data da penalidade
        UPDATE usuarios
        SET penalizado_ate = GREATEST(
            COALESCE(penalizado_ate, CURRENT_DATE),
            CURRENT_DATE + (
                (NEW.data_devolucao - NEW.data_prevista) *
                (
                    SELECT perfis.dias_penalidade_por_dia_atraso
                    FROM usuarios 
                    JOIN perfis ON perfis.id = usuarios.perfil_id
                    WHERE usuarios.id = NEW.usuario_id
                )
            )::INT
        )
        WHERE id = NEW.usuario_id;

    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- TRIGGER para que, após a ateração do atributo 'data_devolucao', execute a função de aplicar a penalidade
CREATE TRIGGER trg_aplicar_penalidade
AFTER UPDATE OF data_devolucao ON emprestimos
FOR EACH ROW
EXECUTE FUNCTION aplicar_penalidade();

-- Caso deseje atualizar uma devolução em específico, basta modificar a o 'SET data_devolucao = '2025-06-18' para o dia em que a devolução foi realizada'

UPDATE emprestimos
SET data_devolucao = '2025-06-29' 
WHERE id = 4;

-- Para visualizar as penalidades de um usuário em específico, basta modificar o 'usuario_id = #' para o id usado na função anterior
SELECT * FROM penalidades WHERE usuario_id = 4;

------------------------------------------------------------------------------------------------
-- Função com TRIGGER para impedir um novo empréstimo de um usuário que esteja penalizado
CREATE OR REPLACE FUNCTION impedir_emprestimo_se_penalizado()
RETURNS TRIGGER AS $$
BEGIN
    -- Localiza o atributo 'penalizado_ate' e verifica se possui algum valor
    IF EXISTS (
        SELECT 1
        FROM usuarios
        WHERE id = NEW.usuario_id
            -- Verifica se o campo está preenchido e se a a data atual é menor que ou igual a data da penalização
            AND penalizado_ate IS NOT NULL
            AND CURRENT_DATE <= penalizado_ate
    ) THEN
        -- Mostra uma mensagem de erro caso haja um erro (no caso, seria o bloqueio de um novo empréstimo no evento de um usuário ainda estar penalizado)
		RAISE EXCEPTION 'Empréstimo negado: usuário % está penalizado atualmente.', NEW.usuario_id;
    END IF;

    RETURN NEW;
END;

$$ LANGUAGE plpgsql;

-- TRIGGER para que, antes da inserção na tabela 'emprestimos', execute a função de verificação de penalização 
CREATE TRIGGER trg_bloquear_emprestimo_se_penalizado
BEFORE INSERT ON emprestimos
FOR EACH ROW
EXECUTE FUNCTION impedir_emprestimo_se_penalizado();

-- Exemplo de caso um usuário que está penalizado tente fazer um novo empréstimo
INSERT INTO emprestimos (usuario_id, recurso_id, data_emprestimo, data_prevista)
VALUES (4, 2, CURRENT_DATE, CURRENT_DATE + 5);

-- Verificação que o usuário de ID = 1 está penalizado
SELECT * FROM usuarios WHERE id = 1;

------------------------------------------------------------------------------------------------
-- Função com TRIGGER para impedir a renovação de um recurso se o mesmo já tiver sido reservado
CREATE OR REPLACE FUNCTION bloquear_renovacao()
RETURNS TRIGGER AS $$
BEGIN
	-- Caso o recurso já tenha sido renovado múltiplas vezes, apresenta uma mensagem de erro
    IF OLD.renovado = TRUE THEN
        RAISE EXCEPTION 'Renovação negada: já foi renovado.';

    -- Caso o recurso já tenha sido renovado por outro usuário, impede a renovação do mesmo recurso
	ELSIF EXISTS (
        SELECT 1 FROM reservas
		-- Verifica se o recurso da reserva é o mesmo do recurso que está sendo renovado
		WHERE recurso_id = NEW.recurso_id
            -- Verifica se a reserva já não foi feita por outro usuário
            AND usuario_id <> NEW.usuario_id
    ) THEN
        RAISE EXCEPTION 'Renovação negada: recurso reservado por outro usuário.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--TRIGGER para que, antes da atualização do atributo 'renovado' da tabela 'emprestimos', execute a função de bloquear a renovação, caso seja necessário
CREATE TRIGGER trg_bloquear_renovacao
BEFORE UPDATE OF renovado ON emprestimos
FOR EACH ROW
WHEN (NEW.renovado = TRUE)
EXECUTE FUNCTION bloquear_renovacao();

-- Exemplo de tentativa de renovação onde um recurso já tenha sido reservado por outro usuário
UPDATE emprestimos
SET renovado = TRUE
WHERE id = 1;

-- Verificação de qual usuário que já fez a reserva do recurso de ID = 1
SELECT usuario_id, recurso_id, data_reserva FROM reservas WHERE recurso_id = 1;

-- Verificação de qual usuário está com o recurso no momento
SELECT usuario_id, recurso_id, data_emprestimo FROM emprestimos WHERE recurso_id = 1;

-----------------------------------------CONSULTAS-------------------------------------------------------
-- Consulta para verificar a quantidade de empréstimos por tipo de recurso e por perfil
SELECT perfis.nome AS perfil, recursos.tipo AS tipo_recurso, COUNT(emprestimos.id) AS quantidade_emprestimos
FROM emprestimos 
	JOIN usuarios ON emprestimos.usuario_id = usuarios.id
	JOIN perfis ON usuarios.perfil_id = perfis.id
	JOIN recursos ON emprestimos.recurso_id = recursos.id
		GROUP BY perfis.nome, recursos.tipo
		ORDER BY perfis.nome, recursos.tipo;

-- Consulta para verificar os usuários atualmente penalizados
SELECT * FROM usuarios WHERE penalizado_ate IS NOT NULL;

-- Consulta para verificar os recursos mais emprestados e com maior número de reservas
SELECT recursos.id, recursos.nome, recursos.tipo, COUNT(emprestimos.id) AS emprestimos, COUNT(reservas.id) AS nmr_reservas
FROM recursos
	JOIN emprestimos ON recursos.id = emprestimos.recurso_id
	JOIN reservas ON recursos.id = reservas.recurso_id
		GROUP BY recursos.id, recursos.nome, recursos.tipo, reservas.id
		ORDER BY COUNT(emprestimos.id) DESC;
