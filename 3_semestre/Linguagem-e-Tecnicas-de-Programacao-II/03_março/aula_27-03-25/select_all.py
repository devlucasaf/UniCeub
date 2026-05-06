import mysql.connector
conexao = mysql.connector.connect(user='root',
                                password='ceub123456',
                                host='127.0.0.1',
                                database='db_empresa')
curs = conexao.cursor()

sql = 'select * from funcionario'
curs.execute(sql)

#print curs.fetchall()
for tabela in (curs.fetchall()):
    print(tabela)

curs.close()
conexao.close()

