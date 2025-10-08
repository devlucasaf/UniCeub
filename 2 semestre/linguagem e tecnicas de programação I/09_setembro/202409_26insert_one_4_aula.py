"""                Comentários de várias linhas

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

nome do programa: insert_one.py

- CRUD (acrônimo de Create, Read, Update e Delete) para as
quatro operações (insere, consulta, atualiza e remove)

Inserir um cliente na tabela tb_cliente: '123', 'Paula', 31

- Sintaxe 1:
sql= ''' insert into nome_tabela
        [(nome_coluna1, nome_coluna2,...)]
        values(valor1, valor2, ...) '''
- Sintaxe 2:
sql = ''' insert into nome_tabela
    values(valor1, valor2, ...) '''
Obs.:
Os colchetes [ ] dentro de uma sintaxe, significa a parte opcional do comando
"""
import sqlite3                            # nome do programa: insert_one.py
conexao = sqlite3.connect('livraria.db')  # Conexão com a base livraria.db
cur = conexao.cursor()
sql = """ insert into tb_cliente
    (cpf, nome, idade) 
    values('123', 'Alice', 31) """
cur.execute(sql)
conexao.commit()            # Obrigatório, persiste os dados no banco
cur.close()
conexao.close()
print("Fechou a base de dados")
""" ----- ALTERAÇÕES:
a. Quando roda o insert a segunda vez dá erro. Porquê?
b. Insira um cliente sem usar o nome das colunas no comando insert.
c. Insira o terceiro cliente só com cpf e nome
d. Insira o quarto cliente só com cpf. Deu erro, porquê?
    ----- DICAS:
sqlite3.IntegrityError: UNIQUE constraint failed: tb_cliente.cpf    # a.
cur.execute('''create table if not exists tb_cliente (
    cpf text primary key,
Obs.: a chave primária não pode ter valor repetido
sql = ''' insert into tb_cliente                                    # b.
          values('124', 'Paula', 31) '''
# sql = "insert into tb_cliente(cpf, nome) values('125', 'Paula')"  # c.
# sql = "insert into tb_cliente(cpf) values('125')"                 # d.
sqlite3.IntegrityError: NOT NULL constraint failed: tb_cliente.nome
Obs.: a coluna nome tem a constraint Not Null 

"""
