-- 1) Criação da Tabela Cliente

CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf CHAR(11) UNIQUE
);

-- 2) Criar a tabela pedido com chave estrangeira referenciando cliente(id_cliente)

CREATE TABLE pedido (
    id_pedido SERIAL PRIMARY KEY,
    id_cliente INT,
    data_pedido DATE,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- 3) Adicionar a coluna email à tabela cliente com restrição UNIQUE

ALTER TABLE cliente
ADD COLUMN email VARCHAR(255) UNIQUE;

-- 4) Criação da tabela produto

CREATE TABLE produto (
    id_produto SERIAL PRIMARY KEY,
    preco DECIMAL(10, 2) CHECK (preco > 0),
    categoria VARCHAR(100) DEFAULT 'outros'
);

-- 5) Criar a tabela estoque com chave composta (id_produto, id_armazem)

CREATE TABLE estoque (
    id_produto INT,
    id_armazem INT,
    quantidade INT,
    PRIMARY KEY (id_produto, id_armazem),
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

-- 6) Adicionar uma restrição CHECK na tabela aluno para garantir que a data de nascimento seja anterior a 2010

ALTER TABLE aluno
ADD CONSTRAINT chk_data_nascimento
CHECK (data_nascimento < '2010-01-01');

-- 7) Criando a tabela "funcionario" com auto-relacionamento para id_gerente

CREATE TABLE funcionario (
    id_funcionario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    id_gerente INT,
    FOREIGN KEY (id_gerente) REFERENCES funcionario(id_funcionario)
);

-- 8)  Removendo a tabela "pedido" e recriá-la com ON DELETE CASCADE na chave estrangeira

DROP TABLE pedido;

CREATE TABLE pedido (
    id_pedido SERIAL PRIMARY KEY,
    id_cliente INT,
    data_pedido DATE,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);

-- 9) Criando a tabela "avaliacao" que registre notas entre 0 e 10 para alunos em disciplinas

CREATE TABLE avaliacao (
    id_avaliacao SERIAL PRIMARY KEY,
    id_aluno INT,
    id_disciplina INT,
    nota DECIMAL(3, 1) CHECK (nota >= 0 AND nota <= 10),
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno),
    FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina)
);

-- 10) Modificando a tabela curso para adicionar uma coluna ativo (boolean) com padrão true

ALTER TABLE curso
ADD COLUMN ativo BOOLEAN DEFAULT TRUE;