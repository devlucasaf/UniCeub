-- Banco de Dados I - Sistema Hospitalar
-- Atividade de Abstração - Questão 3

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

CREATE TABLE Medico (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    crm VARCHAR(10) UNIQUE NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    setor VARCHAR(50)
);

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
