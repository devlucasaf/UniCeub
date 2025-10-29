# Importa o módulo necessário para conectar ao MySQL
import mysql.connector

#+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

# Função para criar a conexão com o MySQL
def create_connection():
    con = mysql.connector.connect(user="root",  # usuário do MySQL
                                password="ceub123456",  # senha do usuário
                                host="127.0.0.1")  # endereço do servidor (localhost)
    print(f"Conexão: {con}")  # imprime o objeto de conexão
    return con  # retorna a conexão criada

#+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

# Função para criar o banco de dados (se ainda não existir) e selecioná-lo
def create_dataBase():
    sql_create = "CREATE DATABASE if not exists db_loja_3"  # comando SQL para criar o banco
    cursor.execute(sql_create)  # executa o comando para criar o banco

    sql_use = "use db_loja_3"  # comando SQL para usar o banco
    cursor.execute(sql_use)  # executa o comando para selecionar o banco

#+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

# Função para criar a tabela 'produto' no banco de dados
def create_table():
    sql = """CREATE TABLE if not exists produto (
        codigo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  # chave primária autoincrementável
        nome VARCHAR(80) NOT NULL UNIQUE,                # nome do produto, único e obrigatório
        preco DECIMAL(9,2) NOT NULL,                     # preço com 2 casas decimais, obrigatório
        data_validade DATE NULL)"""                      # data de validade, opcional

    cursor.execute(sql)  # executa o comando SQL para criar a tabela
    print("Tabela criada!")  # confirma a criação da tabela

#+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

# Função para inserir um único produto no banco
def insert_one():
    # Coleta os dados do usuário
    prod_nome = input("Nome do produto: ")
    prod_preco = input("Preço: ")
    prod_data = input("Data de validade (aaaa-mm-dd): ")

    # Cria o comando SQL de inserção com os dados informados
    sql = f"""INSERT INTO produto
    (nome, preco, data_validade)
    VALUES ('{prod_nome}', "{prod_preco}", "{prod_data}")"""

    # Executa o comando SQL de inserção
    cursor.execute(sql)
    connection.commit()  # salva (confirma) as alterações no banco
    print("Inseriu os dados")  # mensagem de sucesso

#+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

# Ponto de entrada do programa
if __name__ == "__main__":
    connection = create_connection()  # cria conexão com o MySQL
    cursor = connection.cursor()      # cria um cursor para executar comandos SQL

    create_dataBase()  # cria o banco de dados e o seleciona
    create_table()     # cria a tabela 'produto'
    insert_one()       # insere um produto via input do usuário
