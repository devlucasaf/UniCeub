import mysql.connector
from mysql.connector import Error
from datetime import datetime


def create_connection():
    """Cria e retorna uma conexão com o servidor MySQL"""
    try:
        connection = mysql.connector.connect(
            host='localhost',
            user='root',  # substitua pelo seu usuário
            password=''  # substitua pela sua senha
        )
        print("Conexão ao MySQL estabelecida com sucesso")
        return connection
    except Error as e:
        print(f"Erro ao conectar ao MySQL: {e}")
        return None


def create_database(connection, database_name):
    """Cria um banco de dados se não existir"""
    try:
        cursor = connection.cursor()
        cursor.execute(f"CREATE DATABASE IF NOT EXISTS {database_name}")
        cursor.execute(f"USE {database_name}")
        print(f"Banco de dados '{database_name}' criado/verificado com sucesso")
    except Error as e:
        print(f"Erro ao criar banco de dados: {e}")


def create_table(connection):
    """Cria a tabela produtos se não existir"""
    try:
        cursor = connection.cursor()

        create_table_query = """
        CREATE TABLE IF NOT EXISTS produtos (
            id INT AUTO_INCREMENT PRIMARY KEY,
            codigo VARCHAR(20) NOT NULL UNIQUE,
            nome VARCHAR(100) NOT NULL,
            descricao VARCHAR(200),
            preco DECIMAL(10, 2),
            quantidade_estoque INT DEFAULT 0,
            data_cadastro DATE,
            ultima_atualizacao DATETIME
        )
        """
        cursor.execute(create_table_query)
        print("Tabela 'produtos' criada/verificada com sucesso")
    except Error as e:
        print(f"Erro ao criar tabela: {e}")


def insert_product(connection):
    """Insere um novo produto na tabela com inputs do usuário"""
    try:
        cursor = connection.cursor()

        print("\n--- Inserir Novo Produto ---")
        codigo = input("Código do produto (único): ")
        nome = input("Nome do produto: ")
        descricao = input("Descrição (opcional): ") or None
        preco = input("Preço (opcional): ") or None
        quantidade = input("Quantidade em estoque (opcional): ") or 0
        data_cadastro = input("Data de cadastro (YYYY-MM-DD, opcional): ") or None

        insert_query = """
        INSERT INTO produtos 
        (codigo, nome, descricao, preco, quantidade_estoque, data_cadastro, ultima_atualizacao)
        VALUES (%s, %s, %s, %s, %s, %s, NOW())
        """

        cursor.execute(insert_query, (codigo, nome, descricao, preco, quantidade, data_cadastro))
        connection.commit()
        print(f"Produto inserido com sucesso. ID: {cursor.lastrowid}")
    except Error as e:
        print(f"Erro ao inserir produto: {e}")


def select_all_products_vertical(connection):
    """Seleciona todos os produtos e exibe no formato vertical"""
    try:
        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT * FROM produtos")
        produtos = cursor.fetchall()

        if not produtos:
            print("Nenhum produto cadastrado.")
            return

        print("\n--- Todos os Produtos (Layout Vertical) ---")
        for produto in produtos:
            print("\nProduto:")
            for campo, valor in produto.items():
                print(f"{campo}: {valor}")
        print(f"\nTotal de produtos: {len(produtos)}")
    except Error as e:
        print(f"Erro ao buscar produtos: {e}")


def select_all_products_tabular(connection):
    """Seleciona todos os produtos e exibe no formato tabular"""
    try:
        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT * FROM produtos")
        produtos = cursor.fetchall()

        if not produtos:
            print("Nenhum produto cadastrado.")
            return

        print("\n--- Todos os Produtos (Layout Tabular) ---")
        if produtos:
            # Cabeçalho
            headers = produtos[0].keys()
            print("|".join(f"{h:^20}" for h in headers))
            print("-" * (20 * len(headers) + len(headers) - 1))

            # Linhas
            for produto in produtos:
                print("|".join(f"{str(v)[:18]:^20}" for v in produto.values()))

        print(f"\nTotal de produtos: {len(produtos)}")
    except Error as e:
        print(f"Erro ao buscar produtos: {e}")


def update_product(connection):
    """Atualiza um produto existente"""
    try:
        select_all_products_tabular(connection)
        product_id = input("\nDigite o ID do produto que deseja atualizar: ")

        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT * FROM produtos WHERE id = %s", (product_id,))
        produto = cursor.fetchone()

        if not produto:
            print("Produto não encontrado.")
            return

        print("\nDeixe em branco para manter o valor atual")
        print(f"Valores atuais:")
        for campo, valor in produto.items():
            print(f"{campo}: {valor}")

        print("\nNovos valores:")
        nome = input(f"Nome [{produto['nome']}]: ") or produto['nome']
        descricao = input(f"Descrição [{produto['descricao']}]: ") or produto['descricao']
        preco = input(f"Preço [{produto['preco']}]: ") or produto['preco']
        quantidade = input(f"Quantidade [{produto['quantidade_estoque']}]: ") or produto['quantidade_estoque']

        update_query = """
        UPDATE produtos 
        SET nome = %s, descricao = %s, preco = %s, quantidade_estoque = %s, ultima_atualizacao = NOW()
        WHERE id = %s
        """

        cursor.execute(update_query, (nome, descricao, preco, quantidade, product_id))
        connection.commit()
        print("Produto atualizado com sucesso!")
    except Error as e:
        print(f"Erro ao atualizar produto: {e}")


def delete_product(connection):
    """Remove um produto do banco de dados"""
    try:
        select_all_products_tabular(connection)
        product_id = input("\nDigite o ID do produto que deseja remover: ")

        cursor = connection.cursor()
        cursor.execute("DELETE FROM produtos WHERE id = %s", (product_id,))
        connection.commit()

        if cursor.rowcount > 0:
            print("Produto removido com sucesso!")
        else:
            print("Nenhum produto foi removido (ID não encontrado).")
    except Error as e:
        print(f"Erro ao remover produto: {e}")


def main():
    """Função principal que coordena o programa"""
    # Conectar ao servidor
    connection = create_connection()
    if not connection:
        return

    # Criar e usar o banco de dados
    database_name = "sistema_produtos"
    create_database(connection, database_name)

    # Criar tabela se não existir
    create_table(connection)

    # Menu principal
    while True:
        print("\n=== MENU PRINCIPAL ===")
        print("1. Listar produtos (vertical)")
        print("2. Listar produtos (tabular)")
        print("3. Adicionar novo produto")
        print("4. Atualizar produto")
        print("5. Remover produto")
        print("0. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            select_all_products_vertical(connection)
        elif opcao == "2":
            select_all_products_tabular(connection)
        elif opcao == "3":
            insert_product(connection)
        elif opcao == "4":
            update_product(connection)
        elif opcao == "5":
            delete_product(connection)
        elif opcao == "0":
            print("Saindo do sistema...")
            break
        else:
            print("Opção inválida. Tente novamente.")

    # Fechar conexão ao sair
    if connection.is_connected():
        connection.close()
        print("Conexão ao MySQL encerrada")


if __name__ == "__main__":
    main()
