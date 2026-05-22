import mysql.connector

conexao = mysql.connector.connect(user='root',
                                  password='ceub123456',
                                  host='127.0.0.1')
                                  #database=''

print(f'Conexao: {conexao}')

cursor = conexao.cursor()
cursor.execute("CREATE DATABASE db_loja2")
cursor.close()
conexao.close()

print('Criou a data base')


