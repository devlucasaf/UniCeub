-- Paradigmas de Linguagem de Programação
-- Aula: 23-10-25
-- II Atividade 2 (POO e Diagrama de Classes)


CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    matricula INT UNIQUE NOT NULL,
    curso VARCHAR(100)
);

CREATE TABLE professor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    departamento VARCHAR(100)
);

CREATE TABLE disciplina (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(100),
    carga_horaria INT,
    professor_id INT REFERENCES professor(id)
);

CREATE TABLE matricula (
    id SERIAL PRIMARY KEY,
    aluno_id INT REFERENCES aluno(id),
    disciplina_id INT REFERENCES disciplina(id),
    semestre VARCHAR(10),
    nota_final NUMERIC(4,2),
    UNIQUE (aluno_id, disciplina_id, semestre)
);
