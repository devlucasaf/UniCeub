from conexao_dml import Conexao
from cargo import Cargo

def create_db():
    cursor.execute("CREATE DATABASE if not exists db_cadastro")
    cursor.execute("use db_cadastro")
    print("Criou a data base")

def create_table():

    #cursor.execute("USE db_cadastro")

    sql = """CREATE TABLE if not exists cargo (
            idt INT AUTO_INCREMENT PRIMARY KEY,
            nome VARCHAR(80) NOT NULL UNIQUE
            )"""
        
    cursor.execute(sql)

    print("Tabela criada")

if __name__ == "__main__":

    conexao1 = Conexao(db=None)  #objeto
    print(conexao1)

    a_conex = conexao1.conecta() #criaçao da conexao

    cursor = a_conex.cursor()
    create_db()
    create_table()

    o_cargo = Cargo()
    o_cargo.insert()

    #o_cargo.select()
    #select_all()