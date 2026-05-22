import mysql.connector
from mysql.connector import Error


def criar_banco_tabela():
    try:
        conexao = mysql.connector.connect(
            host='localhost',
            user='root',
            password=''
        )

        if conexao.is_connected():
            cursor = conexao.cursor()

            # Criação do banco de dados
            cursor.execute("CREATE DATABASE IF NOT EXISTS loja_eletronicos")
            cursor.execute("USE loja_eletronicos")

            # Criação da tabela
            cursor.execute("""
                CREATE TABLE IF NOT EXISTS produtos (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(100) NOT NULL,
                    modelo VARCHAR(50) NOT NULL UNIQUE,
                    preco DECIMAL(10,2) NOT NULL,
                    quantidade INT,
                    data_lancamento DATE,
                    fabricante VARCHAR(50),
                    garantia_meses INT
                )
            """)

            print("\nBanco de dados 'loja_eletronicos' e tabela 'produtos' criados com sucesso!")

    except Error as e:
        print(f"Erro ao conectar ao MySQL: {e}")

    finally:
        if conexao.is_connected():
            cursor.close()
            conexao.close()
