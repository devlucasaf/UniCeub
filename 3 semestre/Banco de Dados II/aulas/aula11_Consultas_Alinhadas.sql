-- ============================================================
-- AULA 11 – CONSULTAS ANINHADAS, JOINS E FUNÇÕES AGREGADAS
-- Disciplina: Banco de Dados II
-- Curso: Ciência da Computação
-- ============================================================

-- ------------------------------------------------------------
-- 1. CONSULTAS ANINHADAS
-- ------------------------------------------------------------

-- Exemplo 1: Funcionários que trabalham no departamento 'Pesquisa'
SELECT PNOME, UNOME, ENDERECO
FROM FUNCIONARIO
WHERE DEPARTAMENTO_DNUMERO IN (
    SELECT DNUMERO
    FROM DEPARTAMENTO
    WHERE DNOME = 'Pesquisa'
);

-- ------------------------------------------------------------
-- 2. CONSULTAS ANINHADAS CORRELACIONADAS
-- ------------------------------------------------------------

-- Funcionários que possuem dependente com o mesmo nome
SELECT F.PNOME, F.UNOME
FROM FUNCIONARIO AS F
WHERE F.CPF IN (
    SELECT FUNCIONARIO_CPF
    FROM DEPENDENTE
    WHERE FUNCIONARIO_CPF = F.CPF
        AND F.PNOME = NOMEDEPENDENTE
);

-- Forma equivalente com JOIN
SELECT F.PNOME, F.UNOME
FROM FUNCIONARIO AS F
JOIN DEPENDENTE AS D
    ON F.CPF = D.FUNCIONARIO_CPF
WHERE F.PNOME = D.NOMEDEPENDENTE;

-- ------------------------------------------------------------
-- 3. FUNÇÃO EXISTS
-- ------------------------------------------------------------

-- Funcionários com dependente de mesmo nome
SELECT F.PNOME, F.UNOME
FROM FUNCIONARIO AS F
WHERE EXISTS (
    SELECT * FROM DEPENDENTE
    WHERE FUNCIONARIO_CPF = F.CPF
        AND F.PNOME = NOMEDEPENDENTE
);

-- Funcionários sem dependentes
SELECT PNOME, UNOME
FROM FUNCIONARIO
WHERE NOT EXISTS (
    SELECT * FROM DEPENDENTE
    WHERE CPF = FUNCIONARIO_CPF
);

-- Gerentes que possuem ao menos um dependente
SELECT PNOME, UNOME
FROM FUNCIONARIO
WHERE EXISTS (
    SELECT * FROM DEPENDENTE
    WHERE CPF = FCPF
)
AND EXISTS (
    SELECT * FROM DEPARTAMENTO
    WHERE CPF = CPF_SUPERVISOR
);

-- ------------------------------------------------------------
-- 4. CONJUNTOS EXPLÍCITOS
-- ------------------------------------------------------------

-- Funcionários que trabalham nos projetos 1, 2 ou 3
SELECT DISTINCT FUNCIONARIO_CPF
FROM TRABALHA_EM
WHERE PROJETO_PROJNUMERO IN (1, 2, 3);

-- ------------------------------------------------------------
-- 5. TIPOS DE JOINS
-- ------------------------------------------------------------

-- CROSS JOIN
SELECT CPF, DNOME
FROM FUNCIONARIO CROSS JOIN DEPARTAMENTO;

-- INNER JOIN
SELECT f.PNOME, f.UNOME, f.ENDERECO
FROM FUNCIONARIO AS f
INNER JOIN DEPARTAMENTO AS d
    ON f.DEPARTAMENTO_DNUMERO = d.DNUMERO
WHERE d.DNOME = 'Pesquisa';

-- LEFT OUTER JOIN (funcionários e seus supervisores)
SELECT F.PNOME AS FUNCIONARIO, F.UNOME AS SOBRENOME,
        S.PNOME AS SUPERVISOR, S.UNOME AS SOBRENOME_SUP
FROM FUNCIONARIO AS F
LEFT OUTER JOIN FUNCIONARIO AS S
    ON F.SUPERVISOR_CPF = S.CPF;

-- RIGHT OUTER JOIN
SELECT *
FROM TABELA1
RIGHT OUTER JOIN TABELA2
    ON TABELA1.COD = TABELA2.COD;

-- FULL OUTER JOIN
SELECT *
FROM TABELA1
FULL OUTER JOIN TABELA2
    ON TABELA1.COD = TABELA2.COD;

-- NATURAL JOIN
SELECT DNOME, DLOCALIZACAO
FROM DEPARTAMENTO
NATURAL JOIN LOCAIS_DEPTO
WHERE DNOME = 'Pesquisa';

-- ------------------------------------------------------------
-- 6. FUNÇÕES AGREGADAS
-- ------------------------------------------------------------

-- Contar total de funcionários
SELECT COUNT(*) AS TOTAL_FUNCIONARIOS
FROM FUNCIONARIO;

-- Contar salários distintos
SELECT COUNT(DISTINCT SALARIO) AS SALARIOS_DIFERENTES
FROM FUNCIONARIO;

-- Soma total de salários
SELECT SUM(SALARIO) AS TOTAL_SALARIOS
FROM FUNCIONARIO;

-- Maior salário
SELECT MAX(SALARIO) AS MAIOR_SALARIO
FROM FUNCIONARIO;

-- Menor salário no departamento 'Pesquisa'
SELECT MIN(SALARIO) AS MENOR_SALARIO_PESQUISA
FROM FUNCIONARIO
JOIN DEPARTAMENTO ON DNO = DNUMERO
WHERE DNOME = 'Pesquisa';

-- Média salarial da empresa
SELECT AVG(SALARIO) AS MEDIA_SALARIO
FROM FUNCIONARIO;

-- Funcionários com dois ou mais dependentes
SELECT PNOME, UNOME
FROM FUNCIONARIO
WHERE (
    SELECT COUNT(*)
    FROM DEPENDENTE
    WHERE CPF = FCPF
) >= 2;

-- ------------------------------------------------------------
-- 7. GROUP BY e HAVING
-- ------------------------------------------------------------

-- Média salarial e quantidade de funcionários por departamento
SELECT DNO, COUNT(*) AS QTD_FUNCIONARIOS, AVG(SALARIO) AS MEDIA_SALARIAL
FROM FUNCIONARIO
GROUP BY DNO;

-- Projetos com mais de dois funcionários
SELECT PNUMERO, PJNOME, COUNT(*) AS QTD_FUNCIONARIOS
FROM PROJETO, TRABALHA_EM
WHERE PNUMERO = PNO
GROUP BY PNUMERO, PJNOME
HAVING COUNT(*) > 2;

-- ------------------------------------------------------------
-- 8. ESTRUTURA GERAL DE UMA CONSULTA SQL
-- ------------------------------------------------------------

-- SELECT <lista de atributos>
-- FROM <lista de tabelas>
-- [WHERE <condição>]
-- [GROUP BY <atributos>]
-- [HAVING <condição de agrupamento>]
-- [ORDER BY <lista de atributos>];
