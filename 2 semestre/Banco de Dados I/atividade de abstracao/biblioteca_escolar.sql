-- Banco de Dados I - Sistema de Biblioteca Escolar
-- Atividade de Abstração - Questão 2

/* +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
   1. TABELAS DE CADASTRO (Entidades Fortes/Independentes)
   Estas tabelas não dependem de outras para existir.
   +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+= */

CREATE TABLE Autor (
    id_autor INT AUTO_INCREMENT PRIMARY KEY, -- PK: O banco gera o ID (1, 2, 3...) automaticamente
    nome VARCHAR(100) NOT NULL,            -- Obrigatório: Não faz sentido autor sem nome
    nacionalidade VARCHAR(50),
    data_nascimento DATE
);

CREATE TABLE Editora (
    id_editora INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(50),
    pais VARCHAR(50)
);

/* +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
   2. TABELA DE INVENTÁRIO (O Acervo)
   Esta tabela conecta o produto (livro) ao seu autor e editora.
   +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+= */

CREATE TABLE Livro (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    isbn VARCHAR(13) UNIQUE NOT NULL,      -- UNIQUE: Impede cadastrar dois livros com o mesmo ISBN
    ano_publicacao YEAR,                   -- Tipo específico para armazenar apenas o ano (ex: 2023)
    categoria VARCHAR(50),
    disponibilidade BOOLEAN DEFAULT TRUE,  -- DEFAULT: Ao criar o livro, ele já nasce "Disponível"
    
    -- Chaves Estrangeiras (Foreign Keys)
    id_autor INT NOT NULL,                 -- Obrigatório associar a um autor existente
    id_editora INT NOT NULL,               -- Obrigatório associar a uma editora existente
    
    -- Definição dos Relacionamentos
    FOREIGN KEY (id_autor) REFERENCES Autor(id_autor),
    FOREIGN KEY (id_editora) REFERENCES Editora(id_editora)
);

/* +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
   3. TABELA DE USUÁRIOS (Clientes)
   +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+= */

CREATE TABLE Aluno (
    id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(15) UNIQUE NOT NULL, -- UNIQUE: Garante que cada aluno tenha uma matrícula única
    serie VARCHAR(10),
    telefone VARCHAR(15),
    email VARCHAR(100)
);

/* 
    +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
    4. TABELA TRANSACIONAL (A Operação)
    Registra o evento de empréstimo conectando Aluno + Livro.
    +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+ 
*/

CREATE TABLE Emprestimo (
    id_emprestimo INT AUTO_INCREMENT PRIMARY KEY,
    
    -- Quem pegou e o que pegou
    id_livro INT NOT NULL,
    id_aluno INT NOT NULL,
    
    -- Datas de controle
    data_emprestimo DATE NOT NULL,
    data_devolucao_prevista DATE NOT NULL, -- Data limite para devolução
    data_devolucao_real DATE,              -- NULL até que o aluno devolva o livro
    
    -- Controle de Estado
    -- ENUM: Trava o campo para aceitar APENAS estes dois valores exatos
    status ENUM('Em andamento', 'Devolvido') DEFAULT 'Em andamento',
    
    -- Relacionamentos
    FOREIGN KEY (id_livro) REFERENCES Livro(id_livro),
    FOREIGN KEY (id_aluno) REFERENCES Aluno(id_aluno)
);