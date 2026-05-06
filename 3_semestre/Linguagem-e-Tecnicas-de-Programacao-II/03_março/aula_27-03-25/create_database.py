import mysql.connector

conexao_db = mysql.connector.connect(user='root',
                                     password='ceub123456',
                                     host='127.0.0.1')
                                     #database='')

print('Conexão: ', conexao_db)

cursor_db = conexao_db.cursor()
cursor_db.execute("CREATE DATABASE if not exists db_empresa")
cursor_db.close()
conexao_db.close()

print('\nConexão fechada')
