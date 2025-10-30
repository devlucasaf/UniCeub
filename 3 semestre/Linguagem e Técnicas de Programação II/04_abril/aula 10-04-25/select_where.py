import mysql.connector

conexao = mysql.connector.connect(user='root',
                                  password='ceub123456',
                                  host='127.0.0.1',
                                  database='db_loja2')

cursor = conexao.cursor()

search_selc = input('Nome: ') #variavel seleçao

#SELECIONAR TUDO DO PRODUTO COM TAL NOME
sql = f"""select *
          from produto  
          where nome = '{search_selc}' """

#DELETAR ITEM DO PRODUTO
#sql = f"""delete 
          #from produto  
          #where nome = '{search_selc}' """

cursor.execute(sql)

#---------------------------------------------------------------------------------------#
#print(cursor.fetchall())
for i in (cursor.fetchall()):
    print(f'Código: {i[0]} | Nome: {i[1]} | Preço {i[2]} | Validade: {i[3]}\n')
#---------------------------------------------------------------------------------------#

cursor.close()
conexao.close()