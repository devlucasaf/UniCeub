CREATE DATABASE biblioteca;

CREATE TABLE usuario (
    rg CHAR(9) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    CONSTRAINT ck_rg CHECK (rg <> '')
);

CREATE TABLE telefone (
    id_telefone SERIAL PRIMARY KEY,
    rg_usuario CHAR(9) NOT NULL,
    numero VARCHAR(15) NOT NULL,
    FOREIGN KEY (rg_usuario) REFERENCES usuario(rg)
);

CREATE TABLE livro (
    isbn CHAR(13) PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    edicao INT,
    ano_publicacao INT,
    CONSTRAINT ck_ano CHECK (ano_publicacao >= 0)
);

CREATE TABLE autor (
    id_autor SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE livro_autor (
    isbn CHAR(13),
    id_autor INT,
    PRIMARY KEY (isbn, id_autor),
    FOREIGN KEY (isbn) REFERENCES livro(isbn),
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor)
);

CREATE TABLE exemplar (
    id_exemplar SERIAL PRIMARY KEY,
    isbn CHAR(13) NOT NULL,
    qrcode VARCHAR(100) UNIQUE,
    data_compra DATE,
    FOREIGN KEY (isbn) REFERENCES livro(isbn)
);

CREATE TABLE funcionario (
    matricula INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    funcao VARCHAR(50),
    salario DECIMAL(10,2)
);

CREATE TABLE genero (
    id_genero SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);

CREATE TABLE livro_genero (
    isbn CHAR(13),
    id_genero INT,
    PRIMARY KEY (isbn, id_genero),
    FOREIGN KEY (isbn) REFERENCES livro(isbn),
    FOREIGN KEY (id_genero) REFERENCES genero(id_genero)
);

CREATE TABLE secao (
    id_secao SERIAL PRIMARY KEY,
    localizacao VARCHAR(100) NOT NULL
);

ALTER TABLE livro
ADD COLUMN id_secao INT,
ADD FOREIGN KEY (id_secao) REFERENCES secao(id_secao);

CREATE TABLE emprestimo (
    id_emprestimo SERIAL PRIMARY KEY,
    rg_usuario CHAR(9) NOT NULL,
    matricula_funcionario INT NOT NULL,
    id_exemplar INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    FOREIGN KEY (rg_usuario) REFERENCES usuario(rg),
    FOREIGN KEY (matricula_funcionario) REFERENCES funcionario(matricula),
    FOREIGN KEY (id_exemplar) REFERENCES exemplar(id_exemplar)
);
