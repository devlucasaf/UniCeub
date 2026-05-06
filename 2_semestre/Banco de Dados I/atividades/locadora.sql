CREATE DATABASE locadora;

CREATE TABLE filme (
    idfilme SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

CREATE TABLE dvd (
    iddvd SERIAL PRIMARY KEY,
    idfilme INT NOT NULL,
    FOREIGN KEY (idfilme)
        REFERENCES filme(idfilme)
        ON DELETE CASCADE
);

CREATE TABLE ator (
    idator SERIAL PRIMARY KEY,
    nomeator VARCHAR(100) NOT NULL
);

CREATE TABLE filme_ator (
    idfilme INT NOT NULL,
    idator INT NOT NULL,
    PRIMARY KEY (idfilme, idator),
    FOREIGN KEY (idfilme)
        REFERENCES filme(idfilme)
        ON DELETE CASCADE,
    FOREIGN KEY (idator)
        REFERENCES ator(idator)
        ON DELETE CASCADE
);

CREATE TABLE cliente (
    idcliente SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(150)
);

CREATE TABLE emprestimo (
    idemprestimo SERIAL PRIMARY KEY,
    dataemprestimo DATE NOT NULL,
    datadevolucao DATE,
    idcliente INT NOT NULL,
    iddvd INT NOT NULL,
    FOREIGN KEY (idcliente)
        REFERENCES cliente(idcliente)
        ON DELETE CASCADE,
    FOREIGN KEY (iddvd)
        REFERENCES dvd(iddvd)
        ON DELETE CASCADE
);

INSERT INTO filme (titulo, categoria) VALUES
('Vingadores: Ultimato', 'Aventura'),
('O Senhor dos Anéis: A Sociedade do Anel', 'Fantasia'),
('Se Beber, Não Case!', 'Comédia');

INSERT INTO dvd (idfilme) VALUES
(1),
(1),
(2),
(3);

INSERT INTO ator (nomeator) VALUES
('Robert Downey Jr.'),
('Chris Evans'),
('Elijah Wood'),
('Bradley Cooper');

INSERT INTO filme_ator (idfilme, idator) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4);

INSERT INTO cliente (nome, telefone, endereco) VALUES
('Lucas Freitas', '(61) 99999-9999', 'Brasília - DF'),
('Ana Costa', '(61) 98888-8888', 'Taguatinga - DF');

INSERT INTO emprestimo (dataemprestimo, datadevolucao, idcliente, iddvd) VALUES
('2025-10-25', NULL, 1, 1),
('2025-10-26', NULL, 2, 3);

SELECT * FROM filme;

SELECT d.iddvd, f.titulo, f.categoria
FROM dvd d
JOIN filme f ON d.idfilme = f.idfilme;

SELECT f.titulo, a.nomeator
FROM filme_ator fa
JOIN filme f ON fa.idfilme = f.idfilme
JOIN ator a ON fa.idator = a.idator;

SELECT c.nome, f.titulo, d.iddvd
FROM emprestimo e
JOIN cliente c ON e.idcliente = c.idcliente
JOIN dvd d ON e.iddvd = d.iddvd
JOIN filme f ON d.idfilme = f.idfilme;
