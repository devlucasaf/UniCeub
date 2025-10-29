-- ==========================================================

-- BANCO DE DADOS: Sistema de Gestão de Academia
-- Descrição: Estrutura de tabelas para gerenciamento de
-- membros, instrutores, planos de treino, aulas, frequência
-- e dados de saúde.

-- ==========================================================

-- =========================

-- TABELA: Membro

-- =========================

CREATE TABLE Membro (
    id_membro INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    endereco TEXT,
    data_cadastro DATE DEFAULT CURRENT_DATE
);

-- Justificativa:

-- VARCHAR usado para textos curtos (nome, email, telefone).
-- DATE para datas de nascimento e cadastro.
-- TEXT para endereços mais longos.
-- id_membro é a chave primária única de identificação do aluno.

-- =========================

-- TABELA: Instrutor

-- =========================

CREATE TABLE Instrutor (
    id_instrutor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE,
    especialidade VARCHAR(100),
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    horario_disponivel VARCHAR(50)
);

-- Justificativa:

-- Armazena dados pessoais e profissionais dos instrutores.
-- Horário disponível em formato de texto (ex: "Seg-Sex 08h-18h").

-- =========================

-- TABELA: PlanoTreino

-- =========================

CREATE TABLE PlanoTreino (
    id_plano INT AUTO_INCREMENT PRIMARY KEY,
    nome_plano VARCHAR(100) NOT NULL,
    data_criacao DATE DEFAULT CURRENT_DATE,
    descricao TEXT,
    id_instrutor INT NOT NULL,
    FOREIGN KEY (id_instrutor) REFERENCES Instrutor(id_instrutor)
);

-- Justificativa:

-- Um plano de treino pertence a um instrutor.
-- TEXT para descrição detalhada dos exercícios.

-- =========================

-- TABELA: MembroPlano
-- (Relação entre Membro e PlanoTreino)

-- =========================

CREATE TABLE MembroPlano (
    id_membro INT NOT NULL,
    id_plano INT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    PRIMARY KEY (id_membro, id_plano),
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro),
    FOREIGN KEY (id_plano) REFERENCES PlanoTreino(id_plano)
);

-- Justificativa:

-- Um membro pode ter vários planos de treino ao longo do tempo.
-- Chave composta (id_membro, id_plano) define exclusividade.

-- =========================

-- TABELA: Aula

-- =========================

CREATE TABLE Aula (
    id_aula INT AUTO_INCREMENT PRIMARY KEY,
    nome_aula VARCHAR(100) NOT NULL,
    descricao TEXT,
    horario DATETIME NOT NULL,
    id_instrutor INT NOT NULL,
    FOREIGN KEY (id_instrutor) REFERENCES Instrutor(id_instrutor)
);

-- Justificativa:

-- Cada aula é ministrada por um instrutor em um horário específico.
-- DATETIME para registrar dia e hora exatos.

-- =========================

-- TABELA: MembroAula
-- (Relação N:N entre Membro e Aula)

-- =========================
CREATE TABLE MembroAula (
    id_membro INT NOT NULL,
    id_aula INT NOT NULL,
    PRIMARY KEY (id_membro, id_aula),
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro),
    FOREIGN KEY (id_aula) REFERENCES Aula(id_aula)
);

-- Justificativa:

-- Permite registrar a inscrição de vários membros em várias aulas.

-- =========================

-- TABELA: Frequencia

-- =========================

CREATE TABLE Frequencia (
    id_frequencia INT AUTO_INCREMENT PRIMARY KEY,
    id_membro INT NOT NULL,
    data_presenca DATE NOT NULL,
    status ENUM('Presente', 'Ausente') DEFAULT 'Presente',
    FOREIGN KEY (id_membro) REFERENCES Membro(id_membro)
);

-- Justificativa:

-- Armazena presença diária de cada membro.
-- ENUM limita os valores de status para manter consistência.

-- =========================

-- TABELA: DadosSaude

-- =========================

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

-- Justificativa:

-- DECIMAL usado para medidas com precisão.
-- TEXT para observações médicas mais detalhadas.
-- Relacionamento 1:1 entre Membro e DadosSaude (um conjunto de dados por membro).

-- ==========================================================

-- RELACIONAMENTOS PRINCIPAIS:
-- Membro (1,N) Frequencia
-- Membro (N,N) Aula (via MembroAula)
-- Membro (N,N) PlanoTreino (via MembroPlano)
-- Instrutor (1,N) Aula
-- Instrutor (1,N) PlanoTreino
-- Membro (1,1) DadosSaude

-- ==========================================================

-- BANCO CONCLUÍDO:

-- a) Entidades: Membro, Instrutor, PlanoTreino, Aula, Frequencia, DadosSaude
-- b) Atributos definidos conforme a realidade da academia
-- c) Domínios escolhidos de acordo com o mundo real (datas, decimais, textos)
-- d) Tipos de dados justificados e aplicados corretamente
