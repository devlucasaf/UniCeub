import mysql.connector

conexao = mysql.connector.connect(user='root',
                                  password='ceub123456',
                                  host='127.0.0.1',
                                  database='db_loja2')

cursor = conexao.cursor()

sql = '''CREATE TABLE produto (
    codigo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL UNIQUE,
    preco DECIMAL(9,2) NOT NULL,
    data_validade DATE NULL)'''

cursor.execute(sql)
cursor.close()
conexao.close()
print('Tabela criada')
