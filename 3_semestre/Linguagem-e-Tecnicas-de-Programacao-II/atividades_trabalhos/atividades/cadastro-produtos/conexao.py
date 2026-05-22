import mysql.connector

def conectar():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="sua_senha",
    )


def conectar_com_db():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="sua_senha",
        database="cadastro_produtos"
    )