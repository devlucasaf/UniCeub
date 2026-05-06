-- Banco de Dados I - Sistema Hospitalar
-- Atividade de Abstração - Questão 3

/* +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
   1. ENTIDADE PACIENTE (O Cliente)
   Armazena os dados sensíveis e histórico base.
   +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+ */
CREATE TABLE Paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    
    -- Identificadores únicos:
    -- O CPF é a chave "natural" para evitar duplicidade de cadastros
    cpf VARCHAR(14) UNIQUE NOT NULL,
    
    telefone VARCHAR(15),
    endereco TEXT,           -- TEXT: Permite endereços longos/detalhados
    plano_saude VARCHAR(50), -- Importante para faturamento (pode ser NULL se for particular)
    genero VARCHAR(10),
    email VARCHAR(100) UNIQUE, -- Garante que um e-mail não seja usado por dois pacientes
    
    -- Campo para anotações gerais pregressas (alergias, cirurgias passadas)
    historico_medico TEXT
);

/* +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
   2. ENTIDADE MÉDICO (O Profissional)
   +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+ */
CREATE TABLE Medico (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    
    -- CRM: Registro profissional. O UNIQUE é crucial aqui para validar o médico.
    crm VARCHAR(10) UNIQUE NOT NULL, 
    
    especialidade VARCHAR(50) NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    setor VARCHAR(50) -- Ex: Cardiologia, Pronto Socorro, Ala Leste
);

/* +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
   3. TABELA DE CONSULTAS (O Agendamento/Atendimento)
   O ponto central que une Médico e Paciente em um horário específico.
   +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+ */
CREATE TABLE Consulta (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    
    -- DATETIME: Essencial aqui, pois precisamos do dia E da hora exata
    data_hora DATETIME NOT NULL,
    
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    
    -- Detalhes do atendimento
    motivo TEXT,                 -- A "queixa principal" do paciente
    diagnostico_provisorio TEXT, -- Primeira impressão antes dos exames finais
    exames_solicitados TEXT,     -- Lista de exames (em um sistema maior, isso seria outra tabela)
    
    -- Chaves Estrangeiras ligando as tabelas anteriores
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente),
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);

/* +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
   4. TABELA DE DIAGNÓSTICO (A Conclusão)
   Geralmente preenchida após o retorno ou análise de exames.
   +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+ */
CREATE TABLE Diagnostico (
    id_diagnostico INT AUTO_INCREMENT PRIMARY KEY,
    
    -- CID (Classificação Internacional de Doenças). 
    -- Fundamental para padronização médica e planos de saúde.
    codigo_cid VARCHAR(10), 
    
    data_diagnostico DATE NOT NULL,
    descricao TEXT NOT NULL,
    
    -- Rastreabilidade: Quem deu o laudo e em qual consulta
    id_medico INT NOT NULL,
    id_consulta INT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico),
    FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta)
);

/* +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
   5. TABELA DE TRATAMENTO (O Plano de Ação)
   Prescrições e acompanhamentos gerados a partir da consulta.
   +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+ */
CREATE TABLE Tratamento (
    id_tratamento INT AUTO_INCREMENT PRIMARY KEY,
    codigo_tratamento VARCHAR(10),
    descricao TEXT NOT NULL,
    
    -- Controle de duração
    data_inicio DATE NOT NULL,
    data_termino DATE, -- Pode ser NULL se for um tratamento contínuo/crônico
    
    medicamentos TEXT, -- Lista de remédios receitados
    
    -- Vínculos
    id_medico INT NOT NULL,
    id_consulta INT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico),
    FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta)
);