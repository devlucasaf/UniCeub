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
    cursor.execute("CREATE DATABASE if not exists db_empresa")
    cursor.execute("use db_empresa")
    print("Criou a data base")

#-------------------------------------------------------------------

def create_table():
    sql = """CREATE TABLE if not exists cargo (
        cod INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(80) NOT NULL UNIQUE
        );"""
    
    cursor.execute(sql)
    
    sql = """CREATE TABLE if not exists empregado (
        idt INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(80) NOT NULL UNIQUE,
        data_nasc DATE NULL,
        genero CHAR(1) NOT NULL,
        cod_cargo INT NOT NULL,
        FOREIGN KEY (cod_cargo) REFERENCES cargo(cod))"""
    
    cursor.execute(sql)

    print("Tabela criada")

#-------------------------------------------------------------------

def insert_one():

    #cargo_nome = input("Nome do cargo: ")

    #sql = f"""insert into cargo
            #(nome)
            #values ("{cargo_nome}")"""
    
    emp_nome = input("Nome do empregado: ")
    emp_data_nasc = input("Data de nascimento (aaaa-mm-dd): ")
    emp_genero = input("Genero: ")
    emp_cod_cargo = input("Codigo do cargo: ")

    sql2 = f"""insert into empregado
            (nome, data_nasc, genero, cod_cargo)
            values ("{emp_nome}", "{emp_data_nasc}", "{emp_genero}", "{emp_cod_cargo}")"""

    #cursor.execute(sql)
    cursor.execute(sql2)
    conexao.commit()
    print("Inseriu os dados")

#-------------------------------------------------------------------

def select_all():
    #sql_cargo = "SELECT * FROM cargo"
    #cursor.execute(sql_cargo)
    
    #VETOR
    #for i in (cursor.fetchall()):
        #print(f"\nCódigo curso: {i[0]} | Nome do curso: {i[1]}")


    sql_emp = """SELECT empregado.idt, empregado.nome, empregado.data_nasc, empregado.genero, cargo.nome
                FROM empregado
                INNER JOIN cargo WHERE empregado.cod_cargo = cargo.cod"""
    
    cursor.execute(sql_emp)

    #VETOR
    for i in (cursor.fetchall()):
        print(f"\nID empregado: {i[0]} | Nome: {i[1]} | Data de nascimento: {i[2]} | Gênero: {i[3]} | Nome cargo: {i[4]}\n")

#-------------------------------------------------------------------

if __name__ == "__main__":

    conexao = create_connection()
    cursor = conexao.cursor()
    create_db()
    #create_table()
    #insert_one()
    select_all()

