-- Atividade DDL (Data Definition Language)
-- Banco de Dados II
-- Prof. Me. Michel Junio Ferreira Rosa
-- Aluno: Lucas André Ferreira de Freitas
-- RA: 22404067

-- Exercício 1
CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(11) UNIQUE
);

-- Exercício 2
CREATE TABLE pedido (
    id_pedido INT PRIMARY KEY,
    id_cliente INT,
    data_pedido DATE,
    valor_total DECIMAL(10,2),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- Exercício 3
ALTER TABLE cliente ADD email VARCHAR(100) UNIQUE;

-- Exercício 4
CREATE TABLE produto (
    id_produto INT PRIMARY KEY,
    nome VARCHAR(100),
    preco DECIMAL(10,2) CHECK (preco > 0),
    categoria VARCHAR(50) DEFAULT 'outros'
);

-- Exercício 5
CREATE TABLE estoque (
    id_produto INT,
    id_armazem INT,
    quantidade INT,
    PRIMARY KEY (id_produto, id_armazem),
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

-- Exercício 6
-- Supondo que a tabela aluno já existe
ALTER TABLE aluno ADD CONSTRAINT chk_data_nascimento 
CHECK (data_nascimento < '2010-01-01');

-- Exercício 7
CREATE TABLE funcionario (
    id_funcionario INT PRIMARY KEY,
    nome VARCHAR(100),
    cargo VARCHAR(50),
    id_gerente INT,
    FOREIGN KEY (id_gerente) REFERENCES funcionario(id_funcionario)
);

-- Exercício 8
-- Primeiro remove a tabela pedido existente
DROP TABLE pedido;

-- Recria a tabela pedido com ON DELETE CASCADE
CREATE TABLE pedido (
    id_pedido INT PRIMARY KEY,
    id_cliente INT,
    data_pedido DATE,
    valor_total DECIMAL(10,2),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);

-- Exercício 9
CREATE TABLE avaliacao (
    id_avaliacao INT PRIMARY KEY,
    id_aluno INT,
    id_disciplina INT,
    nota DECIMAL(3,1) CHECK (nota >= 0 AND nota <= 10),
    data_avaliacao DATE,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno),
    FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina)
);

-- Exercício 10
-- Supondo que a tabela curso já existe
ALTER TABLE curso ADD ativo BOOLEAN DEFAULT true;
