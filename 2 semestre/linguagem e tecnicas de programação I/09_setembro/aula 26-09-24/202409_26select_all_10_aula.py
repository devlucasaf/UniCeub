"""                Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

Nome do programa: select_all.py

- CRUD (acrônimo de Create, Read, Update e Delete na língua Inglesa)
pras quatro operações (insere, consulta, atualiza e remove)

Implemente:
- Selecione e mostre todas as colunas e todos os registros da tabela

- Sintaxe:
sql = ''' select (* | nome_coluna1 [, nome_coluna2, ...] )
          from nome_tabela
          [ where ... ] '''
Obs.:
os colchetes [] dentro de uma sintaxe de TI, significa a parte opcional do comando

cur.execute(sql)
l_registros = cur.fetchall()
Obs.: pega o resultado do select e coloca na lista l_registros de tuplas
"""
import sqlite3                          # Nome do programa: select_all.py
conexao=sqlite3.connect('livraria.db')  # Conexão com base de dados livraria.db
cur = conexao.cursor()
sql = "select * from tb_cliente"        # Consulta a tabela tb_cliente
cur.execute(sql)
l_registros = cur.fetchall()  # Coloca o resultado do select na lista l_registros
print(l_registros)  # Mostra uma lista de tuplas com os registros na horizontal
cur.close()
conexao.close()
print("Fechou a base de dados.")
""" ----- ALTERAÇÕES:
a. Mostre a lista de tuplas na vertical.
print('Consulta todos na vertical:')                                   # a.
for registro in l_registros:      # Mostra na vertical
    print(registro)

b. Mostre msg de tabela vazia.

"""
