import mysql.connector

def create_connection():
    conexao = mysql.connector.connect(user="root",
                                    password="ceub123456",
                                    host="127.0.0.1")
                                    #database=""
    print(f"Conexão: {conexao}")
    return conexao

#-------------------------------------------------------------------

def create_db():

    cursor.execute("CREATE DATABASE if not exists db_loja3")
    cursor.execute("use db_loja3")
    print("Criou a data base")

#-------------------------------------------------------------------

def create_table():
    sql = """CREATE TABLE if not exists produto (
        codigo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(80) NOT NULL UNIQUE,
        preco DECIMAL(9,2) NOT NULL,
        data_validade DATE NULL)"""
    
    cursor.execute(sql)

    print("Tabela criada")
#-------------------------------------------------------------------

def insert_one():

    prod_nome = input("Nome do produto: ")
    prod_preco = input("Preço: ")
    prod_data = input("Data de validade (aaaa-mm-dd): ")

    sql = f"""insert into produto
            (nome, preco, data_validade)
            values ("{prod_nome}", {prod_preco}, "{prod_data}")"""

    cursor.execute(sql)
    conexao.commit()
    print("Inseriu os dados")

#-------------------------------------------------------------------

def select_all():
    sql = "SELECT * FROM produto"
    cursor.execute(sql)


    # num_registros = cursor.fetchall()
    #if num_registros == 0:
        #print("TABELA VAZIA")
    #else:
        #print("\n-------------------------------- VERTICAL ---------------------------------")
        #for i in num_registros:
            #print(i)
        #print("\n")

    for i in (cursor.fetchall()):
        print(f"\nCódigo: {i[0]} | Nome: {i[1]} | Preço {i[2]} | Validade: {i[3]}\n")
        
#-------------------------------------------------------------------

if __name__ == "__main__":

    conexao = create_connection()
    cursor = conexao.cursor()
    create_db()
    create_table()
    #insert_one()
    select_all()