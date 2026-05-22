'''database = 'nome_database.db'
conexao = sqlite3.connect(database)
cursor = conexao.cursor()'''

import sqlite3

database = 'livraria.db'
conexao = sqlite3.connect(database)
curs = conexao.cursor()

'''sql = create table tb_cliente (
    cpf text primary key,
    nome text not null,
    idade integer)'''

sql = '''create table if not exists tb_cliente (
    cpf text primary key,
    nome text not null, 
    idade integer)'''

curs.execute(sql)
curs.close()
conexao.close()
print("Fechou a base de dados")

# ------------------------------------------------------

# Conectando e inserindo dados na tabela criada

conexao = sqlite3.connect('livraria.db')
curs = conexao.cursor()

sql = '''insert into tb_cliente  
    (cpf, nome, idade)
    values('372656401','Rafael', 18)'''


curs.execute(sql)
conexao.commit()
curs.close()
conexao.close()
print("Fechou a base de dados")

# ------------------------------------------------------

# Conectando e consultando dados na tabela criada

#sql = '''select (* | nome_coluna1 [, nome_coluna2, ...]) 
        #from nome_tabela
        #[where ...]'''

conexao = sqlite3.connect('livraria.db')
curs = conexao.cursor()

sql = 'select * from tb_cliente'
curs.execute(sql)

l_registros = curs.fetchall()
print(l_registros)

curs.close()
conexao.close()
print("Fechou a base de dados")