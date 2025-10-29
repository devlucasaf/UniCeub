-- ==========================================================

-- BANCO DE DADOS: Sistema de Loja Online
-- Descrição: Estrutura de tabelas para gestão de produtos,
-- categorias, clientes, pedidos e promoções.

-- ==========================================================

-- =========================

-- TABELA: Categoria

-- =========================

CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- Justificativa:

-- Armazena as categorias de produtos.
-- VARCHAR para nomes curtos e TEXT para descrição mais detalhada.

-- =========================

-- TABELA: Produto

-- =========================

CREATE TABLE Produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade_disponivel INT NOT NULL DEFAULT 0,
    sku VARCHAR(15) UNIQUE NOT NULL,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

-- Justificativa:

-- DECIMAL(10,2) para valores monetários.
-- INT para quantidade em estoque.
-- SKU identifica cada produto unicamente.
-- Relação com Categoria (muitos produtos podem pertencer a uma categoria).

-- =========================

-- TABELA: Cliente

-- =========================

CREATE TABLE Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    endereco TEXT,
    telefone VARCHAR(15),
    receber_notificacoes BOOLEAN DEFAULT TRUE
);

-- Justificativa:

-- Armazena dados cadastrais dos clientes.
-- BOOLEAN indica se o cliente deseja receber promoções.
-- A senha seria posteriormente armazenada criptografada no sistema.

-- =========================

-- TABELA: Pedido

-- =========================

CREATE TABLE Pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    data_pedido DATETIME NOT NULL,
    status ENUM('Em processamento', 'Enviado', 'Entregue', 'Cancelado') DEFAULT 'Em processamento',
    valor_total DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

-- Justificativa:

-- DATETIME registra o dia e hora da compra.
-- ENUM define os estados do pedido.
-- DECIMAL para armazenar valores monetários.
-- Relacionamento muitos-para-um com Cliente.

-- =========================

-- TABELA: ItemPedido

-- =========================

CREATE TABLE ItemPedido (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

-- Justificativa:

-- Relaciona produtos e pedidos (N:N).
-- Armazena quantidade e preço unitário de cada item vendido.
-- DECIMAL para precisão monetária.

-- =========================

-- TABELA: Promocao

-- =========================

CREATE TABLE Promocao (
    id_promocao INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    percentual_desconto DECIMAL(5, 2) NOT NULL
);

-- Justificativa:

-- Percentual de desconto armazenado com duas casas decimais.
-- Datas de início e fim controlam a validade da promoção.

-- =========================

-- TABELA: ProdutoPromocao (relação N:N entre Produto e Promocao)

-- =========================

CREATE TABLE ProdutoPromocao (
    id_produto INT NOT NULL,
    id_promocao INT NOT NULL,
    PRIMARY KEY (id_produto, id_promocao),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto),
    FOREIGN KEY (id_promocao) REFERENCES Promocao(id_promocao)
);

-- Justificativa:

-- Permite que um produto participe de várias promoções e vice-versa.

-- ==========================================================

-- RELACIONAMENTOS PRINCIPAIS:
-- Categoria (1,N) Produto
-- Cliente (1,N) Pedido
-- Pedido (1,N) ItemPedido
-- Produto (1,N) ItemPedido
-- Promocao (N,N) Produto (via ProdutoPromocao)

-- ==========================================================

-- BANCO CONCLUÍDO:

-- a) Entidades: Produto, Categoria, Cliente, Pedido, ItemPedido, Promocao
-- b) Atributos definidos de acordo com o cenário descrito
-- c) Domínios definidos conforme a realidade comercial
-- d) Tipos de dados escolhidos e justificados para cada uso
