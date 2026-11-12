import mysql.connector
from mysql.connector import Error


def create_connection():
    try:
        connection = mysql.connector.connect(
            host='localhost',
            user='root',
            password=''
        )

        if connection.is_connected():
            print("Conexão ao MySQL estabelecida com sucesso")
            return connection

    except Error as e:
        print(f"Erro ao conectar ao MySQL: {e}")
        return None
