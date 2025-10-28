CREATE DATABASE Biblioteca;
USE Biblioteca;

CREATE TABLE Usuario (
    RG CHAR(9) PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Email VARCHAR(100),
    -- Um usuário pode ter vários telefones (relacionamento 1:N com Telefone)
    CONSTRAINT ck_rg CHECK (RG <> '')
);

CREATE TABLE Telefone (
    ID_Telefone INT AUTO_INCREMENT PRIMARY KEY,
    RG_Usuario CHAR(9) NOT NULL,
    Numero VARCHAR(15) NOT NULL,
    FOREIGN KEY (RG_Usuario) REFERENCES Usuario(RG)
);

CREATE TABLE Livro (
    ISBN CHAR(13) PRIMARY KEY,
    Nome VARCHAR(150) NOT NULL,
    Edicao INT,
    Ano_Publicacao INT,
    CONSTRAINT ck_ano CHECK (Ano_Publicacao >= 0)
);

CREATE TABLE Autor (
    ID_Autor INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL
);

-- Associação N:N entre Livro e Autor
CREATE TABLE Livro_Autor (
    ISBN CHAR(13),
    ID_Autor INT,
    PRIMARY KEY (ISBN, ID_Autor),
    FOREIGN KEY (ISBN) REFERENCES Livro(ISBN),
    FOREIGN KEY (ID_Autor) REFERENCES Autor(ID_Autor)
);

CREATE TABLE Exemplar (
    ID_Exemplar INT AUTO_INCREMENT PRIMARY KEY,
    ISBN CHAR(13) NOT NULL,
    QRCode VARCHAR(100) UNIQUE,
    Data_Compra DATE,
    FOREIGN KEY (ISBN) REFERENCES Livro(ISBN)
);

CREATE TABLE Funcionario (
    Matricula INT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Funcao VARCHAR(50),
    Salario DECIMAL(10,2)
);

CREATE TABLE Genero (
    ID_Genero INT AUTO_INCREMENT PRIMARY KEY,
    Descricao VARCHAR(50) NOT NULL
);

-- Associação N:N entre Livro e Gênero
CREATE TABLE Livro_Genero (
    ISBN CHAR(13),
    ID_Genero INT,
    PRIMARY KEY (ISBN, ID_Genero),
    FOREIGN KEY (ISBN) REFERENCES Livro(ISBN),
    FOREIGN KEY (ID_Genero) REFERENCES Genero(ID_Genero)
);

CREATE TABLE Secao (
    ID_Secao INT AUTO_INCREMENT PRIMARY KEY,
    Localizacao VARCHAR(100) NOT NULL
);

-- Um livro pertence a apenas uma seção
ALTER TABLE Livro
ADD COLUMN ID_Secao INT,
ADD FOREIGN KEY (ID_Secao) REFERENCES Secao(ID_Secao);


CREATE TABLE Emprestimo (
    ID_Emprestimo INT AUTO_INCREMENT PRIMARY KEY,
    RG_Usuario CHAR(9) NOT NULL,
    Matricula_Funcionario INT NOT NULL,
    ID_Exemplar INT NOT NULL,
    Data_Emprestimo DATE NOT NULL,
    Data_Devolucao DATE,
    FOREIGN KEY (RG_Usuario) REFERENCES Usuario(RG),
    FOREIGN KEY (Matricula_Funcionario) REFERENCES Funcionario(Matricula),
    FOREIGN KEY (ID_Exemplar) REFERENCES Exemplar(ID_Exemplar)
);
