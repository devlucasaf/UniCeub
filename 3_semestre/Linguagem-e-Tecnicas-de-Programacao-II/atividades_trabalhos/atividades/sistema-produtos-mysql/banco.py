from mysql.connector import Error


def create_database(connection, database_name):
    try:
        cursor = connection.cursor()

        # Criação do banco de dados
        cursor.execute(f"CREATE DATABASE IF NOT EXISTS {database_name}")
        cursor.execute(f"USE {database_name}")

        print(f"Banco de dados '{database_name}' criado/verificado com sucesso")

    except Error as e:
        print(f"Erro ao criar banco de dados: {e}")


def create_table(connection):
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
