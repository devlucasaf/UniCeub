CREATE DATABASE empresa_mer_mere;

CREATE TABLE funcionario (
    cpf CHAR(11) PRIMARY KEY,
    pnome VARCHAR(30) NOT NULL,
    minicial CHAR(1),
    unome VARCHAR(30) NOT NULL,
    data_nascimento DATE NOT NULL,
    endereco VARCHAR(80)
);

CREATE TABLE secretaria (
    cpf_func CHAR(11) PRIMARY KEY,
    velocidade_digitacao VARCHAR(30) NOT NULL,
    FOREIGN KEY (cpf_func) REFERENCES funcionario(cpf)
);

CREATE TABLE tecnico (
    cpf_func CHAR(11) PRIMARY KEY,
    grau_tec VARCHAR(30) NOT NULL,
    FOREIGN KEY (cpf_func) REFERENCES funcionario(cpf)
);

CREATE TABLE engenheiro (
    cpf_func CHAR(11) PRIMARY KEY,
    tipo_eng VARCHAR(30) NOT NULL,
    FOREIGN KEY (cpf_func) REFERENCES funcionario(cpf)
);

CREATE TABLE salario_horista (
    cpf_func CHAR(11) PRIMARY KEY,
    escala_pagamento DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (cpf_func) REFERENCES funcionario(cpf)
);

CREATE TABLE salario_mensalista (
    cpf_func CHAR(11) PRIMARY KEY,
    salario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (cpf_func) REFERENCES funcionario(cpf)
);

INSERT INTO funcionario (cpf, pnome, minicial, unome, data_nascimento, endereco)
VALUES 
('11111111111', 'MARIANA', 'M', 'SILVA', '2000-01-01', 'RUA X'),
('22222222222', 'JOAQUINA', 'N', 'MOREIRA', '1991-10-02', 'RUA 8');

INSERT INTO secretaria (cpf_func, velocidade_digitacao)
VALUES ('11111111111', '1000 PALAVRAS MIN');

INSERT INTO engenheiro (cpf_func, tipo_eng)
VALUES ('22222222222', 'ELETRICISTA');

INSERT INTO salario_mensalista (cpf_func, salario)
VALUES ('11111111111', 2000);

INSERT INTO salario_horista (cpf_func, escala_pagamento)
VALUES ('22222222222', 78000);
