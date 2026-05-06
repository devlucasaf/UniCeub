import mysql.connector

conexao = mysql.connector.connect(user='root',
                                  password='ceub123456',
                                  host='127.0.0.1',
                                  database='db_loja2')

cursor = conexao.cursor()

prod_nome = input('Nome: ')
prod_preco = input('Preço: ')
prod_data = input('Data aaaa-mm-dd: ')

sql = f'''insert into produto
         (nome, preco, data_validade)
         values ('{prod_nome}', {prod_preco}, '{prod_data}')'''

cursor.execute(sql)
cursor.close()
conexao.commit()
conexao.close()
print('Inseriu os dados')


