-- Questão 1

CREATE VIEW vw_funcionario_departamento AS
SELECT
    f.pnome || ' ' || f.unome AS nome_completo,
    d.dnome
FROM funcionario f
JOIN departamento d ON f.numdept = d.dnumero;

-- Questão 2

CREATE VIEW vw_projeto_departamento AS 
SELECT  
    p.projnome, 
    d.dnome 
FROM projeto p 
JOIN departamento d ON p.dnum = d.dnumero; 

-- Questão 3

CREATE VIEW vw_funcionario_projeto_horas AS 
SELECT 
    f.pnome || ' ' || f.unome AS nome_completo, 
    p.projnome, 
    te.horas
FROM funcionario f
JOIN trabalha_em te ON f.cpf = te.cpf

-- Questão 4

CREATE VIEW vw_funcionario_supervisor AS 
SELECT 
    f.pnome || ' ' || f.unome AS funcionario, 
    s.pnome || ' ' || s.unome AS supervisor
FROM funcionario f
LEFT JOIN funcionario s ON f.cpf_supervisor = s.cpf;

-- Questão 5

CREATE VIEW vw_funcionario_dependentes_contagem AS 
SELECT
	f.cpf,
	f.pnome ||''|| f.unome AS nome_completo,
	COUNT(d.nome_dependente) AS total_dependentes
FROM funcionario f
JOIN dependente d ON f.cpf = d.cpf
GROUP BY f.cpf, f.pnome, f.unome;

-- Questão 6

CREATE MATERIALIZED VIEW mvw_total_horas_projeto AS 
SELECT  
	p.projnumero, 
	p.projnome, 
	SUM(te.horas) AS total_horas 
FROM projeto p 
JOIN trabalhaem te ON p.projnumero = te.projnumero 
GROUP BY p.projnumero, p.projnome;

-- Questão 7

CREATE MATERIALIZED VIEW mvw_media_salarial_departamento AS 
SELECT  
	d.dnumero, 
	d.dnome, 
	AVG(f.salario) AS media_salarial 
FROM departamento d 
JOIN funcionario f ON f.numdept = d.dnumero 
GROUP BY d.dnumero, d.dnome; 

-- Questão 8

CREATE MATERIALIZED VIEW mvw_gerentes_departamento AS 
SELECT  
	f.pnome || ' ' || f.unome AS gerente, 
	d.dnome, 
	d.dataInicio 
FROM departamento d 
JOIN funcionario f ON d.gerente = f.cpf; 

-- Questão 9

SELECT *
FROM vw_funcionario_departamento
WHERE dnome = 'Pesquisa';

-- Questão 10

-- Atualizar a view materializada 
REFRESH MATERIALIZED VIEW mvw_total_horas_projeto; 

-- Consulta 
SELECT * 
FROM mvw_total_horas_projeto 
WHERE total_horas > 40;