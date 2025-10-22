-- ============================================================
-- AULA 07 – ÁLGEBRA RELACIONAL EM SQL
-- Professor: Me. Michel Junio Ferreira Rosa
-- Curso: Ciência da Computação – Banco de Dados II
-- ============================================================

-- ------------------------------------------------------------
-- 1. OPERAÇÃO PROJEÇÃO (π)
-- ------------------------------------------------------------
-- Selecionar colunas específicas de uma relação.
-- Exemplo: Selecionar PNOME, UNOME e ENDERECO da tabela FUNCIONARIO
SELECT PNOME, UNOME, ENDERECO
FROM FUNCIONARIO;

-- ------------------------------------------------------------
-- 2. OPERAÇÃO SELEÇÃO (σ)
-- ------------------------------------------------------------
-- Selecionar tuplas que satisfazem uma condição.
-- Exemplo: Funcionários que trabalham no departamento 4
SELECT *
FROM FUNCIONARIO
WHERE DNR = 4;

-- ------------------------------------------------------------
-- 3. SELEÇÃO COM CONDIÇÕES COMPOSTAS
-- ------------------------------------------------------------
-- Funcionários do departamento 4 com salário > 25000
-- ou departamento 5 com salário > 30000
SELECT *
FROM FUNCIONARIO
WHERE (DNR = 4 AND SALARIO > 25000)
    OR (DNR = 5 AND SALARIO > 30000);

-- ------------------------------------------------------------
-- 4. SEQUÊNCIA DE OPERAÇÕES
-- ------------------------------------------------------------
-- Recuperar PNOME, UNOME e SALARIO dos funcionários do departamento 5
SELECT PNOME, UNOME, SALARIO
FROM FUNCIONARIO
WHERE DNR = 5;

-- ------------------------------------------------------------
-- 5. RENOMEANDO ATRIBUTOS (AS)
-- ------------------------------------------------------------
SELECT PNOME AS NOME, UNOME AS SOBRENOME, SALARIO
FROM FUNCIONARIO
WHERE DNR = 5;

-- ------------------------------------------------------------
-- 6. OPERAÇÃO UNIÃO (∪)
-- ------------------------------------------------------------
-- Recuperar CPF de funcionários que trabalham no depto 5
-- ou que supervisionam funcionários do depto 5.
SELECT CPF
FROM FUNCIONARIO
WHERE DNR = 5
UNION
SELECT CPF_SUPERVISOR
FROM FUNCIONARIO
WHERE DNR = 5;

-- ------------------------------------------------------------
-- 7. OPERAÇÃO INTERSEÇÃO (∩)
-- ------------------------------------------------------------
-- Funcionários que trabalham no depto 5 E supervisionam alguém do depto 5.
SELECT CPF
FROM FUNCIONARIO
WHERE DNR = 5
INTERSECT
SELECT CPF_SUPERVISOR
FROM FUNCIONARIO
WHERE DNR = 5;

-- ------------------------------------------------------------
-- 8. OPERAÇÃO DIFERENÇA (−)
-- ------------------------------------------------------------
-- Funcionários que não são supervisores.
SELECT CPF
FROM FUNCIONARIO
EXCEPT
SELECT CPF_SUPERVISOR
FROM FUNCIONARIO;

-- ------------------------------------------------------------
-- 9. PRODUTO CARTESIANO (×)
-- ------------------------------------------------------------
-- Combinação de todas as tuplas entre duas relações.
-- Exemplo: Todas as combinações entre FUNCIONARIO e DEPARTAMENTO.
SELECT *
FROM FUNCIONARIO, DEPARTAMENTO;

-- ------------------------------------------------------------
-- 10. PRODUTO CARTESIANO + SELEÇÃO (JOIN IMPLÍCITO)
-- ------------------------------------------------------------
-- Recuperar os nomes dos dependentes de cada funcionária (sexo = ‘F’)
SELECT F.PNOME, F.UNOME, D.NOMEDEPENDENTE
FROM FUNCIONARIO AS F, DEPENDENTE AS D
WHERE F.SEXO = 'F' AND F.CPF = D.FCPF;

-- ------------------------------------------------------------
-- 11. JUNÇÃO NATURAL (⋈)
-- ------------------------------------------------------------
-- Combina tuplas de duas tabelas com base em atributos iguais.
-- Exemplo: Recuperar os nomes dos gerentes de cada departamento.
SELECT D.DNOME, F.PNOME, F.UNOME
FROM DEPARTAMENTO AS D
JOIN FUNCIONARIO AS F
ON D.CPF_GERENTE = F.CPF;

-- ------------------------------------------------------------
-- 12. EXERCÍCIO (MODELAGEM EXEMPLO)
-- ------------------------------------------------------------
-- Estrutura base de tabelas conforme o modelo apresentado no slide:

CREATE TABLE ALUNO (
    cod_aluno INT PRIMARY KEY,
    nome VARCHAR(100),
    matricula VARCHAR(20),
    telefone VARCHAR(15),
    cpf VARCHAR(14),
    coeficiente NUMERIC(3,2)
);

CREATE TABLE FUNCIONARIO (
    cod_prof INT PRIMARY KEY,
    nome VARCHAR(100),
    matricula VARCHAR(20),
    telefone VARCHAR(15),
    cpf VARCHAR(14),
    cod_cargo INT
);

CREATE TABLE CARGO (
    cod_cargo INT PRIMARY KEY,
    dcr_cargo VARCHAR(50)
);

CREATE TABLE TURMA (
    cod_turma INT PRIMARY KEY,
    dcr_turma VARCHAR(50),
    cod_professor INT,
    cod_disciplina INT
);

CREATE TABLE ALUNO_TURMA (
    cod_turma INT,
    cod_aluno INT,
    nota NUMERIC(4,2),
    freq NUMERIC(5,2),
    PRIMARY KEY (cod_turma, cod_aluno)
);

CREATE TABLE DISCIPLINA (
    cod_disciplina INT PRIMARY KEY,
    dcr_disciplina VARCHAR(100)
);

CREATE TABLE PREREQUISITO (
    cod_disc_pre INT,
    cod_disc_pos INT,
    PRIMARY KEY (cod_disc_pre, cod_disc_pos)
);

-- Inserindo possíveis cargos
INSERT INTO CARGO (cod_cargo, dcr_cargo) VALUES
(1, 'Professor'),
(2, 'Funcionario');

-- ------------------------------------------------------------
-- 13. EXEMPLOS DE CONSULTAS COM BASE NO MODELO
-- ------------------------------------------------------------

-- Listar nomes dos alunos e suas notas
SELECT A.nome, AT.nota
FROM ALUNO A
JOIN ALUNO_TURMA AT ON A.cod_aluno = AT.cod_aluno;

-- Listar turmas com seus respectivos professores
SELECT T.dcr_turma, F.nome AS professor
FROM TURMA T
JOIN FUNCIONARIO F ON T.cod_professor = F.cod_prof;

-- Listar disciplinas e seus pré-requisitos
SELECT D1.dcr_disciplina AS Disciplina, D2.dcr_disciplina AS Pre_Requisito
FROM DISCIPLINA D1
JOIN PREREQUISITO P ON D1.cod_disciplina = P.cod_disc_pos
JOIN DISCIPLINA D2 ON D2.cod_disciplina = P.cod_disc_pre;
