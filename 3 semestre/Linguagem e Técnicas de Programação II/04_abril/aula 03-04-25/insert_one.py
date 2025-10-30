import mysql.connector

conexao = mysql.connector.connect(user='root',
                                  password='ceub123456',
                                  host='127.0.0.1',
                                  database='db_loja')

cursor = conexao.cursor()

#sql = '''insert into produto
   # (nome, preco, dta_validade)
    #values('Arroz', 40.00, '2025-11-21'),
    #('Enroladinho de salsicha', 6.90, '2025-04-13')'''

sql = '''insert into produto
    (nome, preco, dta_validade)
    values('Coca-cola', 5.90, '2025-10-30'),
    ('Enroladinho de salsicha', 6.90, '2025-04-13')'''

cursor.execute(sql)
conexao.commit()

print("Inseriu a base de dados")