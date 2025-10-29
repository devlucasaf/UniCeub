-- =========================================================
-- BANCO DE DADOS: Locadora de Vídeos
-- Descrição: Modelo completo com DVDs, Filmes, Atores,
-- Clientes e Empréstimos.
-- =========================================================

-- Criação do Banco de Dados
CREATE DATABASE Locadora;
USE Locadora;

-- =========================================================
-- TABELA: Filme
-- Cada filme tem um identificador próprio, título e categoria.
-- =========================================================
CREATE TABLE Filme (
    idFilme INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

-- =========================================================
-- TABELA: DVD
-- Cada DVD possui um número único e está vinculado a um filme.
-- Um filme pode ter um ou mais DVDs.
-- =========================================================
CREATE TABLE DVD (
    idDVD INT PRIMARY KEY AUTO_INCREMENT,
    idFilme INT NOT NULL,
    CONSTRAINT fk_dvd_filme
        FOREIGN KEY (idFilme)
        REFERENCES Filme(idFilme)
        ON DELETE CASCADE
);

-- =========================================================
-- TABELA: Ator
-- Contém os atores que estrelam os filmes.
-- =========================================================
CREATE TABLE Ator (
    idAtor INT PRIMARY KEY AUTO_INCREMENT,
    nomeAtor VARCHAR(100) NOT NULL
);

-- =========================================================
-- TABELA: Filme_Ator (tabela associativa N:M)
-- Um filme pode ter vários atores, e um ator pode atuar em vários filmes.
-- =========================================================
CREATE TABLE Filme_Ator (
    idFilme INT NOT NULL,
    idAtor INT NOT NULL,
    PRIMARY KEY (idFilme, idAtor),
    CONSTRAINT fk_filmeator_filme
        FOREIGN KEY (idFilme)
        REFERENCES Filme(idFilme)
        ON DELETE CASCADE,
    CONSTRAINT fk_filmeator_ator
        FOREIGN KEY (idAtor)
        REFERENCES Ator(idAtor)
        ON DELETE CASCADE
);

-- =========================================================
-- TABELA: Cliente
-- Clientes cadastrados que podem alugar DVDs.
-- =========================================================
CREATE TABLE Cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(150)
);

-- =========================================================
-- TABELA: Emprestimo
-- Registra quais DVDs foram emprestados a quais clientes.
-- =========================================================
CREATE TABLE Emprestimo (
    idEmprestimo INT PRIMARY KEY AUTO_INCREMENT,
    dataEmprestimo DATE NOT NULL,
    dataDevolucao DATE,
    idCliente INT NOT NULL,
    idDVD INT NOT NULL,
    CONSTRAINT fk_emprestimo_cliente
        FOREIGN KEY (idCliente)
        REFERENCES Cliente(idCliente)
        ON DELETE CASCADE,
    CONSTRAINT fk_emprestimo_dvd
        FOREIGN KEY (idDVD)
        REFERENCES DVD(idDVD)
        ON DELETE CASCADE
);

-- =========================================================
-- INSERÇÕES DE EXEMPLO
-- =========================================================

-- Inserindo filmes
INSERT INTO Filme (titulo, categoria) VALUES
('Vingadores: Ultimato', 'Aventura'),
('O Senhor dos Anéis: A Sociedade do Anel', 'Fantasia'),
('Se Beber, Não Case!', 'Comédia');

-- Inserindo DVDs
INSERT INTO DVD (idFilme) VALUES
(1), (1), (2), (3);

-- Inserindo atores
INSERT INTO Ator (nomeAtor) VALUES
('Robert Downey Jr.'),
('Chris Evans'),
('Elijah Wood'),
('Bradley Cooper');

-- Associando filmes e atores
INSERT INTO Filme_Ator (idFilme, idAtor) VALUES
(1, 1), (1, 2),
(2, 3),
(3, 4);

-- Inserindo clientes
INSERT INTO Cliente (nome, telefone, endereco) VALUES
('Lucas Freitas', '(61) 99999-9999', 'Brasília - DF'),
('Ana Costa', '(61) 98888-8888', 'Taguatinga - DF');

-- Inserindo empréstimos
INSERT INTO Emprestimo (dataEmprestimo, dataDevolucao, idCliente, idDVD) VALUES
('2025-10-25', NULL, 1, 1),
('2025-10-26', NULL, 2, 3);

-- =========================================================
-- CONSULTAS DE EXEMPLO
-- =========================================================

-- Mostrar todos os filmes e suas categorias
SELECT * FROM Filme;

-- Mostrar todos os DVDs e qual filme cada um contém
SELECT D.idDVD, F.titulo, F.categoria
FROM DVD D
JOIN Filme F ON D.idFilme = F.idFilme;

-- Mostrar todos os atores de um filme específico
SELECT F.titulo, A.nomeAtor
FROM Filme_Ator FA
JOIN Filme F ON FA.idFilme = F.idFilme
JOIN Ator A ON FA.idAtor = A.idAtor;

-- Mostrar quais DVDs cada cliente possui emprestado
SELECT C.nome AS Cliente, F.titulo AS Filme, D.idDVD AS DVD
FROM Emprestimo E
JOIN Cliente C ON E.idCliente = C.idCliente
JOIN DVD D ON E.idDVD = D.idDVD
JOIN Filme F ON D.idFilme = F.idFilme;

