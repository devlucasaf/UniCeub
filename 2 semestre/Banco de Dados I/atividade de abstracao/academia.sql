-- Banco de Dados I - Sistema de Gestão de Academia
-- Atividade de Abstração - Questão 1

CREATE TABLE Membro (
    id_membro INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    endereco TEXT,
    data_cadastro DATE DEFAULT CURRENT_DATE
);

CREATE TABLE Instrutor (
    id_instrutor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE,
    especialidade VARCHAR(100),
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    horario_disponivel VARCHAR(50)
);

CREATE TABLE PlanoTreino (
    id_plano INT AUTO_INCREMENT PRIMARY KEY,
    nome_plano VARCHAR(100) NOT NULL,
    data_criacao DATE DEFAULT CURRENT_DATE,
    descricao TEXT,
    id_instrutor INT NOT NULL,
    FOREIGN KEY (id_instrutor) REFERENCES Instrutor(id_instrutor)
);

CREATE TABLE MembroPlano (
    id_membro INT NOT NULL,
    id_plano INT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    PRIMARY KEY (id_membro, id_plano),
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro),
    FOREIGN KEY (id_plano) REFERENCES PlanoTreino(id_plano)
);

CREATE TABLE Aula (
    id_aula INT AUTO_INCREMENT PRIMARY KEY,
    nome_aula VARCHAR(100) NOT NULL,
    descricao TEXT,
    horario DATETIME NOT NULL,
    id_instrutor INT NOT NULL,
    FOREIGN KEY (id_instrutor) REFERENCES Instrutor(id_instrutor)
);

CREATE TABLE MembroAula (
    id_membro INT NOT NULL,
    id_aula INT NOT NULL,
    PRIMARY KEY (id_membro, id_aula),
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro),
    FOREIGN KEY (id_aula) REFERENCES Aula(id_aula)
);


CREATE TABLE Frequencia (
    id_frequencia INT AUTO_INCREMENT PRIMARY KEY,
    id_membro INT NOT NULL,
    data_presenca DATE NOT NULL,
    status ENUM('Presente', 'Ausente') DEFAULT 'Presente',
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro)
);

CREATE TABLE DadosSaude (
    id_saude INT AUTO_INCREMENT PRIMARY KEY,
    id_membro INT NOT NULL,
    peso DECIMAL(5,2),
    altura DECIMAL(4,2),
    imc DECIMAL(4,2),
    pressao_arterial VARCHAR(10),
    observacoes TEXT,
    data_registro DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro)
);
