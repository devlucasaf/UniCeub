-- ==========================================================

-- BANCO DE DADOS: Sistema de Biblioteca Escolar
-- Descrição: Estrutura de tabelas para gerenciamento de livros,
-- autores, editoras, alunos e empréstimos.

-- ==========================================================

-- =========================

-- TABELA: Autor

-- =========================

CREATE TABLE Autor (
    id_autor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(50),
    data_nascimento DATE
);

-- Justificativa:

-- VARCHAR(100) para nomes longos, VARCHAR(50) para nacionalidade.
-- DATE para data de nascimento.

-- =========================

-- TABELA: Editora

-- =========================

CREATE TABLE Editora (
    id_editora INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(50),
    pais VARCHAR(50)
);

-- Justificativa:

-- VARCHAR utilizado por serem informações curtas e textuais.
-- Identificador único id_editora como chave primária.

-- =========================

-- TABELA: Livro

-- =========================

CREATE TABLE Livro (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    ano_publicacao YEAR,
    categoria VARCHAR(50),
    disponibilidade BOOLEAN DEFAULT TRUE,
    id_autor INT NOT NULL,
    id_editora INT NOT NULL,
    FOREIGN KEY (id_autor) REFERENCES Autor(id_autor),
    FOREIGN KEY (id_editora) REFERENCES Editora(id_editora)
);

-- Justificativa:

-- VARCHAR(150) para títulos longos.
-- ISBN é um identificador único (13 caracteres).
-- YEAR para armazenar o ano de publicação.
-- BOOLEAN indica se o livro está disponível ou não.

-- =========================

-- TABELA: Aluno

-- =========================

CREATE TABLE Aluno (
    id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(15) UNIQUE NOT NULL,
    serie VARCHAR(10),
    telefone VARCHAR(15),
    email VARCHAR(100)
);

-- Justificativa:

-- VARCHAR para textos curtos.
-- UNIQUE em matrícula para evitar duplicidade.
-- EMAIL e telefone armazenados como VARCHAR por variação de formato.

-- =========================

-- TABELA: Emprestimo

-- =========================

CREATE TABLE Emprestimo (
    id_emprestimo INT AUTO_INCREMENT PRIMARY KEY,
    id_livro INT NOT NULL,
    id_aluno INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao_real DATE,
    status ENUM('Em andamento', 'Devolvido') DEFAULT 'Em andamento',
    FOREIGN KEY (id_livro) REFERENCES Livro(id_livro),
    FOREIGN KEY (id_aluno) REFERENCES Aluno(id_aluno)
);

-- Justificativa:

-- DATE usado para datas de empréstimo e devolução.
-- ENUM para status do empréstimo.
-- Relacionamentos com Livro e Aluno permitem rastrear empréstimos.

-- ==========================================================

-- RELACIONAMENTOS:
-- Autor (1,N) Livro
-- Editora (1,N) Livro
-- Livro (1,N) Emprestimo
-- Aluno (1,N) Emprestimo

-- ==========================================================

-- BANCO CONCLUÍDO:

-- a) Entidades: Autor, Editora, Livro, Aluno, Emprestimo
-- b) Atributos definidos conforme o contexto do sistema
-- c) Domínios alinhados à realidade (texto, datas, booleanos)
-- d) Tipos de dados justificados para cada caso
