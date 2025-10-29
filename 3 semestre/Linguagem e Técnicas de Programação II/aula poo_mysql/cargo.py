from mysql.connector import Error
from conexao_dml import *

class Cargo():
    def __init__(self):
        self.obj = Conexao()
    
    def insert(self):
        try:
            v_nome = input("Nome do Cargo: ")
            sql = f"""insert into tb_cargo (nome)
                values("{v_nome}")"""

            print(sql)
            self.obj.executa_dml(sql)

        except Error as erro:
            print(f"Erro no insert. \n{Error}")

    def select(self):
        try:
            sql = "select * from cargo"
            print(sql)
            lista = self.obj.executa_dql(sql)
            for i in lista:
                print(f"\nID cargo: {i[0]} | Nome: {i[1]}")

        except Error as erro:
            print(f"Erro no select. \n{erro}")

    def delete(self):...