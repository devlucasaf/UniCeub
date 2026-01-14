-- Paradigmas de Linguagem de Programação
-- Aula: 23-10-25
-- II Atividade 2 (POO e Diagrama de Classes)
-- Situação 4 - Sistema de Seguros de Carros

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL
);

CREATE TABLE modelo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE carro (
    id SERIAL PRIMARY KEY,
    ano INT NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo_id INT REFERENCES modelo(id),
    cor VARCHAR(30),
    placa VARCHAR(10) UNIQUE NOT NULL
);

CREATE TABLE seguro (
    id SERIAL PRIMARY KEY,
    carro_id INT REFERENCES carro(id),
    cliente_id INT REFERENCES cliente(id),
    valor NUMERIC(10,2) NOT NULL,
    vigencia DATE NOT NULL
);
