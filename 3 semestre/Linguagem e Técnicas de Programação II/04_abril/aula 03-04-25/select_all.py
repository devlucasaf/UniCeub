import mysql.connector
conexao = mysql.connector.connect(user='root',
                                password='ceub123456',
                                host='127.0.0.1',
                                database='db_loja')
cursor = conexao.cursor()

print('----------------------------------------------------------------------------')
sql = 'SELECT * FROM produto'
cursor.execute(sql)

print(f"Registros inseridos: {cursor.rowcount}")
print(f"Cursor.statement: \n\n[{cursor.statement}]")

#print curs.fetchall()
'''for i in (cursor.fetchall()):
    print(i)'''

for i in (cursor.fetchall()):
    print(f'Código: {i[0]} | Nome: {i[1]} | Preço {i[2]} | Validade: {i[3]}')

print('----------------------------------------------------------------------------')
