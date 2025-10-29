-- ==========================================================

-- BANCO DE DADOS: Sistema Hospitalar
-- Descrição: Estrutura de tabelas para gestão de pacientes,
-- médicos, consultas, diagnósticos e tratamentos.

-- ==========================================================

-- =========================

-- TABELA: Paciente

-- =========================

CREATE TABLE Paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(15),
    endereco TEXT,
    plano_saude VARCHAR(50),
    genero VARCHAR(10),
    email VARCHAR(100) UNIQUE,
    historico_medico TEXT
);

-- Justificativa:

-- VARCHAR utilizado para campos de texto curtos (nomes, CPF, email, etc.).
-- TEXT usado para endereços e histórico médico (textos mais longos).
-- DATE para armazenar datas de nascimento.
-- Chave primária: id_paciente.

-- =========================

-- TABELA: Medico

-- =========================

CREATE TABLE Medico (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    crm VARCHAR(10) UNIQUE NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    setor VARCHAR(50)
);

-- Justificativa:

-- Cada médico é identificado pelo CRM e tem uma especialidade e setor.
-- Chave primária: id_medico.
-- VARCHAR usado para strings curtas e TEXT não é necessário aqui.

-- =========================

-- TABELA: Consulta

-- =========================

CREATE TABLE Consulta (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    data_hora DATETIME NOT NULL,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    motivo TEXT,
    diagnostico_provisorio TEXT,
    exames_solicitados TEXT,
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente),
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);

-- Justificativa:

-- DATETIME permite registrar data e hora da consulta.
-- TEXT usado para motivos e diagnósticos mais extensos.
-- Relacionamentos entre pacientes e médicos estabelecidos por chaves estrangeiras.

-- =========================

-- TABELA: Diagnostico

-- =========================

CREATE TABLE Diagnostico (
    id_diagnostico INT AUTO_INCREMENT PRIMARY KEY,
    codigo_cid VARCHAR(10),
    data_diagnostico DATE NOT NULL,
    descricao TEXT NOT NULL,
    id_medico INT NOT NULL,
    id_consulta INT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico),
    FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta)
);

-- Justificativa:

-- CID representado por VARCHAR(10) (alfanumérico).
-- Relaciona-se com médico e consulta para rastreabilidade.

-- =========================

-- TABELA: Tratamento

-- =========================

CREATE TABLE Tratamento (
    id_tratamento INT AUTO_INCREMENT PRIMARY KEY,
    codigo_tratamento VARCHAR(10),
    descricao TEXT NOT NULL,
    data_inicio DATE NOT NULL,
    data_termino DATE,
    medicamentos TEXT,
    id_medico INT NOT NULL,
    id_consulta INT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico),
    FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta)
);

-- Justificativa:

-- Permite acompanhar tratamentos por consulta e médico responsável.
-- TEXT usado para descrição e medicamentos (possível lista extensa).
-- DATE para acompanhar período de tratamento.

-- ==========================================================

-- RELACIONAMENTOS PRINCIPAIS:
-- Paciente (1,N) Consulta
-- Medico (1,N) Consulta
-- Consulta (1,1) Diagnostico
-- Consulta (1,N) Tratamento

-- ==========================================================

-- BANCO CONCLUÍDO COM BASE NAS ETAPAS:
-- a) Entidades: Paciente, Medico, Consulta, Diagnostico, Tratamento
-- b) Atributos definidos com base nas necessidades do sistema
-- c) Domínios alinhados à realidade hospitalar
-- d) Tipos de dados justificados conforme uso prático
