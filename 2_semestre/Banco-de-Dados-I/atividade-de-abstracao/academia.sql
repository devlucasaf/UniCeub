-- Banco de Dados I - Sistema de Gestão de Academia
-- Atividade de Abstração - Questão 1

-- Criação das tabelas para o sistema de gestão de academia

-- Criação da tabela Membro
CREATE TABLE membro (
    id_membro INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    endereco TEXT,
    data_cadastro DATE DEFAULT CURRENT_DATE
);

-- Criação da tabela Instrutor
CREATE TABLE instrutor (
    id_instrutor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE,
    especialidade VARCHAR(100),
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    horario_disponivel VARCHAR(50)
);

-- Criação da tabela PlanoTreino
CREATE TABLE plano_treino (
    id_plano INT AUTO_INCREMENT PRIMARY KEY,
    nome_plano VARCHAR(100) NOT NULL,
    data_criacao DATE DEFAULT CURRENT_DATE,
    descricao TEXT,
    id_instrutor INT NOT NULL,
    FOREIGN KEY (id_instrutor) REFERENCES Instrutor(id_instrutor)
);

-- Criação da tabela MembroPlano para associar membros aos planos de treino
CREATE TABLE membro_plano (
    id_membro INT NOT NULL,
    id_plano INT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    PRIMARY KEY (id_membro, id_plano),
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro),
    FOREIGN KEY (id_plano) REFERENCES PlanoTreino(id_plano)
);

-- Criação da tabela Aula
CREATE TABLE aula (
    id_aula INT AUTO_INCREMENT PRIMARY KEY,
    nome_aula VARCHAR(100) NOT NULL,
    descricao TEXT,
    horario DATETIME NOT NULL,
    id_instrutor INT NOT NULL,
    FOREIGN KEY (id_instrutor) REFERENCES Instrutor(id_instrutor)
);

-- Criação da tabela MembroAula para associar membros às aulas
CREATE TABLE membro_aula (
    id_membro INT NOT NULL,
    id_aula INT NOT NULL,
    PRIMARY KEY (id_membro, id_aula),
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro),
    FOREIGN KEY (id_aula) REFERENCES Aula(id_aula)
);

-- Criação da tabela Frequencia para registrar a presença dos membros
CREATE TABLE frequencia (
    id_frequencia INT AUTO_INCREMENT PRIMARY KEY,
    id_membro INT NOT NULL,
    data_presenca DATE NOT NULL,
    status ENUM('Presente', 'Ausente') DEFAULT 'Presente',
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro)
);

-- Criação da tabela DadosSaude para registrar informações de saúde dos membros
CREATE TABLE dados_saude (
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
