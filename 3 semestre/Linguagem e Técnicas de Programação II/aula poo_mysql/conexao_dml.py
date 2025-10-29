from mysql.connector import connect, Error

class Conexao:
    def __init__(self, host="localhost", user="root", pwd="ceub123456", db="db_cadastro"):
        self.host = host
        self.user = user
        self.password = pwd
        self.database = db

    def conecta(self):
        try:
            self.conexao = connect(user="root",
                                    password="ceub123456",
                                    host="127.0.0.1")
                                    #database=""  
            return self.conexao 
        
        except Error as erro:
            print(f"Erro no conecta. \n{erro}")

    def executa_dml(self, sql):
        try:
            self.conecta()
            self.cursor.execute(sql)
            self.conexao.commit()
            print("Executou DML (Data Manipulation Language)")
            self.desconecta

        except Error as erro:
            print(f"Erro no executa_dml. \n{erro}")

    def executa_dql(self, sql):
        try:
            self.conecta()
            self.cursor.execute(sql)
            n_registros = self.cursor.fetchall()
            self.desconecta()
            return n_registros
        
        except Error as erro:
            print(f"Erro no executa_dql. \n{erro}")

