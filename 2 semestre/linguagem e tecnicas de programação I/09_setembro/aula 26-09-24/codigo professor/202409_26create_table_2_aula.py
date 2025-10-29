"""                Comentários de várias linhas

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

nome do programa: create_table.py

Implemente:
- Desenvolva o programa usando a linguagem Python e o banco de dados SQLite.
Crie a base de dados livraria.db e a tabela tb_cliente com as colunas:
cpf text, nome text e idade integer.
- Defina:
Uma chave primária,
Uma coluna obrigatória, e
Uma coluna opcional.

import sqlite3
- Sintaxe:
database = 'nome_database.db'
conexao = sqlite3.connect(database)
# Cria a base de dados nome_database.db e a conexão.
cur = conexao.cursor()              # Cria o cursor pra executar o sql
- Sintaxe:
sql = ''' create table tb_nome_tabela(
        nome_coluna1 tipo primary key,
        nome_coluna2 tipo not null,
        nome_coluna3 tipo [null]) '''
Obs.:
Os colchetes [ ] dentro de uma sintaxe, significa a parte opcional do comando
cur.execute(sql)                    # Executa o comando sql         """
import sqlite3
database = 'livraria.db'             # nome do programa: create_table.py
conexao = sqlite3.connect(database)  # Cria a base de dados livraria.db e a conexão
cur = conexao.cursor()               # Cria o cursor para executar o sql
sql = ''' create table tb_cliente(
        cpf text primary key,
        nome text not null,
        idade integer) '''  # Cria a tabela tb_cliente na base dados livraria.db
cur.execute(sql)            # Executa o comando sql
cur.close()
conexao.close()
print("Fechou a base de dados")
""" ----- Alterações:
a. Quando roda o programa a segunda vez dá erro. Porquê?
        sqlite3.OperationalError: table tb_cliente already exists
        Como evitar esse erro?
----- Dicas:
...                                                             # a.
sql = ''' create table if not exists tb_cliente(            
        "...
        "idade integer) ''' 

"""
