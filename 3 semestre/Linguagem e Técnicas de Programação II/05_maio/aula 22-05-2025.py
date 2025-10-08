from mysql.connector import connect, Error

class Connection():
    def __init__(self, host = "localhost", user = "root", password = "ceub123456", db = "db_cadastro"):
        self.host = host
        self.user = user
        self.password = password
        self.dataBase = db

    def connection(self):
        try:
            self.connexion = connect(host= self.host, user=self.user, password=self.password,
                                     dataBase=self.dataBase)
            self.cursor = self.connexion.cursor()
            return self.connexion
        except Error as error:
            print(f"Erro no conecta. \n Erro: {error}")

    def executeDel(self, sql):
        try:
            self.connection()
            self.cursor.execute(sql)
            self.connexion.commit()
            print(f"Executou DML(Dara Manipulation)")
            sql.disconnect()
        except Error as error:
            print(f"Erro no executa_del. \n Erro: {error}")

class Cargo():
    def __init__(self):
        self.object = Connection()

    def insert(self):
        try:
            name = input("Nome do cargo: ")
            sql = f"insert into tb_cargo (nome)"
            sql += f"values ('{name}')"
            print(sql)
            self.object.executeDel(sql)
        except Error as error:
            print(f"Erro no insert. \n Erro: {error}")

def createDataBase():
    sqlCreate = "CREATE DATABASE IF NOT EXISTS db_cadastro"
    cursor.execute(sqlCreate)
    sqlUse = "use db_cadastro"
    cursor.execute(sqlUse)

def createTable():
    sqlCargo = """ CREATE TABLE IF NOT EXISTS td_cargo (
    idt int AUTO_INCREMENT,
    nome varchar(45) NOT NULL UNIQUE,
    PRIMARY KEY (idt)
    )"""
    cursor.execute(sqlCargo)

if __name__ == "__main__":
    oConnection = Connection(db=None)
    print(oConnection)
    connection = oConnection.connection()
    cursor = connection.cursor()
    createDataBase()
    createTable()
    cargo = Cargo()
    cargo.insert()
    
