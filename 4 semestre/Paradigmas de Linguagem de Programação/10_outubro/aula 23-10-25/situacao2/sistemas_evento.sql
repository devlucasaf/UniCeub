-- Paradigmas de Linguagem de Programação
-- Aula: 23-10-25
-- II Atividade 2 (POO e Diagrama de Classes)
-- Situação 2 - Sistema de Gestão de Eventos

CREATE TABLE evento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    data TIMESTAMP,
    local VARCHAR(120)
);

CREATE TABLE artista (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    genero VARCHAR(60)
);

CREATE TABLE evento_artista (
    evento_id INT REFERENCES evento(id),
    artista_id INT REFERENCES artista(id),
    PRIMARY KEY (evento_id, artista_id)
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(120)
);

CREATE TABLE ingresso (
    id SERIAL PRIMARY KEY,
    codigo INT UNIQUE,
    valor NUMERIC(10,2),
    assento VARCHAR(20),
    evento_id INT REFERENCES evento(id),
    cliente_id INT REFERENCES cliente(id),
    UNIQUE (evento_id, assento)  
);
