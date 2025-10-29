-- Questão 1

CREATE TABLE Paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    data_nascimento DATE,
    cpf CHAR(11) UNIQUE,
    telefone CHAR(11),
    endereco VARCHAR(150),
    plano_saude VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE Medico (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    especialidade VARCHAR(100),
    email_corporativo VARCHAR(100),
    setor_hospital VARCHAR(100)
);

CREATE TABLE Consulta (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    horario DATETIME,
    data_consulta DATETIME,
    id_medico INT,
    motivo TEXT,
    exames TEXT,
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);

CREATE TABLE Diagnostico (
    id_diagnostico INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255),
    id_medico INT,
    data_diagnostico DATE,
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);

CREATE TABLE Tratamento (
    id_tratamento INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255),
    data_inicio DATE,
    data_termino DATE,
    receita TEXT,
    remedio TEXT,
    id_medico INT,
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);

-- Questão 2

CREATE TABLE Aluno (
    id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    serie VARCHAR(20),
    turma VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE Autor (
    id_autor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    nacionalidade VARCHAR(50),
    data_nascimento DATE
);

CREATE TABLE Editora (
    id_editora INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cidade VARCHAR(50),
    pais VARCHAR(50)
);

CREATE TABLE Livro (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150),
    data_lancamento DATE,
    id_autor INT,
    id_editora INT,
    FOREIGN KEY (id_autor) REFERENCES Autor(id_autor),
    FOREIGN KEY (id_editora) REFERENCES Editora(id_editora)
);

-- Questão 3

CREATE TABLE Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    telefone CHAR(11),
    endereco VARCHAR(150),
    senha CHAR(60),
    historico_compras TEXT
);

CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    descricao TEXT
);

CREATE TABLE Produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    descricao TEXT,
    valor DECIMAL(10,2),
    quantidade_disponivel INT,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

CREATE TABLE Pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    codigo_rastreamento CHAR(20),
    data_pedido DATE,
    data_entrega DATE,
    id_cliente INT,
    preco_total DECIMAL(10,2),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

-- Questão 4

CREATE TABLE Dados_Saude (
    id_dado INT AUTO_INCREMENT PRIMARY KEY,
    peso DECIMAL(5,2),
    altura DECIMAL(4,2),
    imc DECIMAL(4,2),
    observacoes TEXT
);

CREATE TABLE Professor (
    id_professor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    data_nascimento DATE
);

CREATE TABLE Recepcionista (
    id_recepcionista INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    data_nascimento DATE
);

CREATE TABLE Aluno (
    id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    endereco VARCHAR(150),
    email VARCHAR(100),
    telefone CHAR(11),
    id_dado INT,
    identificacao VARCHAR(50),
    FOREIGN KEY (id_dado) REFERENCES Dados_Saude(id_dado)
);

CREATE TABLE Treino (
    id_treino INT AUTO_INCREMENT PRIMARY KEY,
    nome_treino VARCHAR(100),
    data_criacao DATE,
    data_termino DATE,
    id_professor INT,
    FOREIGN KEY (id_professor) REFERENCES Professor(id_professor)
);

CREATE TABLE Aula (
    id_aula INT AUTO_INCREMENT PRIMARY KEY,
    nome_aula VARCHAR(100),
    horario DATETIME,
    id_professor INT,
    FOREIGN KEY (id_professor) REFERENCES Professor(id_professor)
);
