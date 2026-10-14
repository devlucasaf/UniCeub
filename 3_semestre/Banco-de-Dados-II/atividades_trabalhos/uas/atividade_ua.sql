-- a) Tabela Estudante

CREATE TABLE Estudante (
    Matricula INT NOT NULL,
    Nome VARCHAR(100) NOT NULL,
    Idioma VARCHAR(20) NOT NULL,
    DataNascimento DATE NOT NULL,
    Idade INT NOT NULL CHECK (Idade >= 12),
    Sexo CHAR(1) NOT NULL,
    Endereco VARCHAR(200) NOT NULL,
    Telefone VARCHAR(20) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    PRIMARY KEY (Matricula)
);

-- b) Tabela Turma

CREATE TABLE Turma (
    CodigoTurma INT NOT NULL,
    CodigoMateria INT NOT NULL,
    Ano INT NOT NULL,
    CodigoProfessor INT NOT NULL,
    PRIMARY KEY (CodigoTurma),
    FOREIGN KEY (CodigoMateria) REFERENCES Materia(CodigoMateria),
    FOREIGN KEY (CodigoProfessor) REFERENCES Professor(CodigoProfessor)
);
