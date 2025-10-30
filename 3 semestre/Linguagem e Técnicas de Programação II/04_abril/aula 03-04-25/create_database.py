import mysql.connector

conexao = mysql.connector.connect(user='root',
                                  password='ceub123456',
                                  host='127.0.0.1')
                                 #database='')

print(f'Conexao: {conexao}')
cursor_db = conexao.cursor()
cursor_db.execute("CREATE DATABASE db_loja")
cursor_db.close()
conexao.close()
print('\nConexão fechada')



