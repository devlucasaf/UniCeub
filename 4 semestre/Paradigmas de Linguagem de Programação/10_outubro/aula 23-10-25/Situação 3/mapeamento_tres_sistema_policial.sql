-- Paradigmas de Linguagem de Programação
-- Aula: 23-10-25
-- II Atividade 2 (POO e Diagrama de Classes)

CREATE TABLE ocorrencia (
    id SERIAL PRIMARY KEY,
    numero INT UNIQUE NOT NULL,
    data_ocorrencia DATE NOT NULL,
    local VARCHAR(100),
    descricao TEXT
);

CREATE TABLE policial (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    matricula INT UNIQUE NOT NULL,
    cargo VARCHAR(50)
);

CREATE TABLE viatura (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(50),
    estado VARCHAR(20)
);

CREATE TABLE suspeito (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    situacao VARCHAR(50)
);

CREATE TABLE policial_ocorrencia (
    policial_id INT REFERENCES policial(id),
    ocorrencia_id INT REFERENCES ocorrencia(id),
    PRIMARY KEY (policial_id, ocorrencia_id)
);

CREATE TABLE viatura_ocorrencia (
    viatura_id INT REFERENCES viatura(id),
    ocorrencia_id INT REFERENCES ocorrencia(id),
    PRIMARY KEY (viatura_id, ocorrencia_id)
);

CREATE TABLE suspeito_ocorrencia (
    suspeito_id INT REFERENCES suspeito(id),
    ocorrencia_id INT REFERENCES ocorrencia(id),
    PRIMARY KEY (suspeito_id, ocorrencia_id)
);
