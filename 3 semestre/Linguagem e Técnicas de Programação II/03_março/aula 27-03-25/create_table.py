import mysql.connector

conexao = mysql.connector.connect(user='root',
                                password='ceub123456',
                                host='127.0.0.1',
                                database='db_empresa')
curs=conexao.cursor()

sql = '''create table funcionario (
    cpf int primary key,
    nome VARCHAR(50) NOT NULL,
    salario DECIMAL(9,2) NULL)'''


curs.execute(sql)
curs.close()
conexao.close()
print("Conexao fechada")