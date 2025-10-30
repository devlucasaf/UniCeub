import mysql.connector
conexao = mysql.connector.connect(user='root',
                                password='ceub123456',
                                host='127.0.0.1',
                                database='db_empresa')
curs = conexao.cursor()

sql = '''insert into funcionario 
    (cpf, nome, salario)
    values(024,'Ricardo Ribeiro', 7500)'''

curs.execute(sql)
conexao.commit()

print("Inseriu a base de dados")