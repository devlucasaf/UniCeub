Teste a IA diretamente nos seus apps favoritos … Use o Gemini para criar rascunhos e editar conteúdo e acesse o Gemini Pro com a IA de última geração do Google
import mysql.connector


def create_db():
    create = '''CREATE DATABASE IF NOT EXISTS db_Wixus'''
    cursor_db.execute(create)
    use = '''USE db_Wixus'''
    cursor_db.execute(use)
    print("Data base criada")


def conection_db():
    conection = mysql.connector.connect(
        user='root',
        host='localhost',
        database='db_Wixus',
        password='ceub123456'
    )

    print("Conexão concluida")
    print("Conexão", conection)
    return conection


# -------------------------------------------JOGOS--------------------------------------------------------

def create_tb_jogos(cursor_db):
    create = '''CREATE TABLE IF NOT EXISTS tb_jogos(
            id_jogo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(50) NOT NULL,
            price DECIMAL(9,2),
            faixa_etaria INT,
            developer VARCHAR(30) NOT NULL,
            data_lanc DATE,
            plataformas VARCHAR(30),
            genre VARCHAR(50)
            )'''
    cursor_db.execute(create)
    print("Tabela Jogos criada")


def Insert_table_jogos(cursor_db, conex_db, blablabla):
    sql = '''
    INSERT INTO tb_jogos (name, price, data_lanc, faixa_etaria, developer, plataformas, genre)
    VALUES (%s, %s, %s, %s, %s, %s, %s)
    '''
    dados_geral = blablabla.get(), \
        float(input('Digite o preço do jogo: ')), \
        input('Digite a data de lançamento (YYYY-MM-DD): '), \
        int(input('Digite a faixa etária do jogo: ')), \
        input('Digite o nome do desenvolvedor: '), \
        input('Digite as plataformas do jogo: '), \
        input('Digite o gênero do jogo: ')
    dados_geral = tuple(dados_geral)
    cursor_db.execute(sql, dados_geral)
    conex_db.commit()
    print('Dados do jogo inseridos')


def Procurar_ID_Jogo():
    Escolha = input("Digite o ID do jogo: ")
    sql = f''' SELECT * FROM tb_jogos WHERE id_jogo = "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(f'Id: {row[0]} | Nome: {row[1]} | Preço: {row[2]} | Faixa Etaria: {row[3]} | Desenvolvedor: {row[4]} | Data de Lançamento: {row[5]} | plataformas: {row[6]} | Genero: {row[7]} |')


def Procurar_Nome():
    Escolha = input("Digite o nome do jogo: ")
    sql = f''' SELECT * FROM tb_jogos WHERE name = "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Procurar_Preco():
    Escolha = input("Digite o preço máximo do jogo: ")
    sql = f''' SELECT * FROM tb_jogos WHERE price <= "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Procurar_Idade():
    Escolha = input("Digite a faixa etária do jogo: ")
    sql = f''' SELECT * FROM tb_jogos WHERE faixa_etaria <= "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Procurar_developer():
    Escolha = input("Digite o produtor(a) do jogo: ")
    sql = f''' SELECT * FROM tb_jogos WHERE developer = "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Procurar_data_lanc():
    Escolha = input("Digite a data de lançamento do jogo: ")
    sql = f''' SELECT * FROM tb_jogos WHERE data_lanc  = "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Procurar_plataformas():
    Escolha = input("Digite o gênero do jogo: ")
    sql = f''' SELECT * FROM tb_jogos WHERE plataformas  = "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Procurar_Genero():
    Escolha = input("Digite o gênero do jogo: ")
    sql = f''' SELECT * FROM tb_jogos WHERE genre  = "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Update_tb_jogos(cursor_db, conex_db):
    escolha = input("Digite o ID do jogo que deseja atualizar: ")
    escolha_coluna = input(
        "Digite a coluna que deseja atualizar (name, price, faixa_etaria, developer, data_lanc, plataformas, genre): ")
    novo_valor = input(f"Digite o novo valor para {escolha_coluna}: ")
    sql = f'''UPDATE tb_jogos SET {escolha_coluna} = %s WHERE id_jogo = %s'''
    dados = (novo_valor, escolha)
    cursor_db.execute(sql, dados)
    conex_db.commit()
    print(f'Dados do jogo com ID {escolha} atualizados')


def delete_tb_jogos(cursor_db, conex_db):
    escolha = input("Digite o ID do jogo que deseja deletar: ")
    sql = f'''DELETE FROM tb_jogos WHERE id_jogo = "{escolha}" '''
    cursor_db.execute(sql)
    conex_db.commit()
    print(f'Dados do jogo com ID {escolha} deletados')


# ---------------------------------------------USUARIO--------------------------------------------------------

def create_tb_user(cursor_db):
    # não coloquei a parametro 'jogos_criados' deve ser colocado na tabela tb_devs (Ricardo)
    create = '''CREATE TABLE IF NOT EXISTS tb_user(
            id_user INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            nome VARCHAR(50) NOT NULL,
            idade INT NOT NULL,
            pais VARCHAR(50),
            status VARCHAR(50),
            developer bool DEFAULT FALSE
            )'''
    cursor_db.execute(create)
    print("Tabela User criada")


def Insert_table_user(cursor_db, conex_db):
    sql = '''
    INSERT INTO tb_user (nome, idade, pais, status, developer)
    VALUES (%s, %s, %s, %s, %s)
    '''
    dados = input('Digite o nome do usuário: '), \
        int(input('Digite a idade do usuário: ')), \
        input('Digite o país do usuário: '), \
        input('Digite o status do usuário: '), \
        int(input('O usuário é um desenvolvedor? (1/0): '))

    dados = tuple(dados)
    cursor_db.execute(sql, dados)
    conex_db.commit()
    print('Dados do usuario inseridos')


def Procurar_Nome():
    Escolha = input("Digite o nome do usuário: ")
    sql = f''' SELECT * FROM tb_user WHERE nome = "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Procurar_ID_Usuario():
    Escolha = input("Digite o ID do usuário: ")
    sql = f''' SELECT * FROM tb_user WHERE id_user = "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Update_tb_user(cursor_db, conex_db):
    escolha = input("Digite o ID do usuario que deseja atualizar: ")
    escolha_coluna = input("Digite a coluna que deseja atualizar (nome, idade, pais, status, developer): ")
    novo_valor = input(f"Digite o novo valor para {escolha_coluna}: ")
    sql = f'''UPDATE tb_user SET {escolha_coluna} = %s WHERE id_user = %s'''
    dados = (novo_valor, escolha)
    cursor_db.execute(sql, dados)
    conex_db.commit()
    print(f'Dados do usuario com ID {escolha} atualizados')


def delete_tb_user(cursor_db, conex_db):
    escolha = input("Digite o ID do usuario que deseja deletar: ")
    sql = f'''DELETE FROM tb_user WHERE id_user = "{escolha}" '''
    cursor_db.execute(sql)
    conex_db.commit()
    print(f'Dados do usuario com ID {escolha} deletados')


# --------------------------------------------CARRINHO--------------------------------------------------------

def create_tb_cart(cursor_db):
    create = '''CREATE TABLE IF NOT EXISTS tb_cart(
            id_cart INT NOT NULL AUTO_INCREMENT,
            preco_total INT NOT NULL,
            forma_pagamento VARCHAR(30),
            PRIMARY KEY (id_cart),
            FOREIGN KEY (id_cart) REFERENCES tb_user(id_user)
            )'''
    cursor_db.execute(create)
    print("Tabela Cart criada")


def Insert_table_cart(cursor_db, conex_db):
    sql = '''
    INSERT INTO tb_cart (preco_total, forma_pagamento)
    VALUES (%s, %s)
    '''
    dados = input('Digite o preço total do carrinho: '), \
        input('Digite a forma de pagamento: ')
    dados = tuple(dados)
    cursor_db.execute(sql, dados)
    conex_db.commit()
    print('Dados do cartão inseridos')


def Procurar_Produtos():
    Escolha = input("Digite o ID do usuário: ")
    sql = f''' SELECT * FROM tb_carrinho_compras WHERE id_cart = "{Escolha}" '''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


def Update_tb_cart(cursor_db, conex_db):
    escolha = input("Digite o ID do carrinho que deseja atualizar: ")
    escolha_coluna = input("Digite a coluna que deseja atualizar (preco_total, forma_pagamento): ")
    novo_valor = input(f"Digite o novo valor para {escolha_coluna}: ")
    sql = f'''UPDATE tb_cart SET {escolha_coluna} = %s WHERE id_cart = %s'''
    dados = (novo_valor, escolha)
    cursor_db.execute(sql, dados)
    conex_db.commit()
    print(f'Dados do carrinho com ID {escolha} atualizados')


def delete_tb_cart(cursor_db, conex_db):
    escolha = input("Digite o ID do jogo que deseja deletar: ")
    sql = f'''DELETE FROM tb_cart WHERE id_cart = "{escolha}" '''
    cursor_db.execute(sql)
    conex_db.commit()
    print(f'Dados do carrinho com ID {escolha} deletados')


# ---------------------------------------------GERAL---------------------------------------------------------

def Ver_tudo():
    print("Todas os jogos do banco de dados:")
    sql = f'''SELECT * FROM tb_jogos'''
    cursor_db.execute(sql)
    rows = cursor_db.fetchall()
    for row in rows:
        print(row)


# -----------------------------------------------------------------------------------------------------------

if __name__ == '__main__':
    conex_db = conection_db()
    cursor_db = conex_db.cursor()

    # Criando as tabelas (Ricardo)
    #create_db()
    #create_tb_jogos(cursor_db)
    #create_tb_user(cursor_db)
    #create_tb_cart(cursor_db)

    # Insertando dados nas tabelas (Rafael)
    #Insert_table_jogos(cursor_db, conex_db)
    #Insert_table_user(cursor_db, conex_db)
    #Insert_table_cart(cursor_db, conex_db)

    # Atualizando os dados nas tabelas (Rafael)
    #Update_tb_jogos(cursor_db, conex_db)
    #Update_tb_user(cursor_db, conex_db)
    #Update_tb_cart(cursor_db, conex_db)

    # Removendo os dados das tabelas (Rafael)
    #delete_tb_jogos(cursor_db, conex_db)
    #delete_tb_cart(cursor_db, conex_db)
    #delete_tb_user(cursor_db, conex_db)

    # Procurando por informações nos jogos (Mateus)
    #Procurar_ID_Jogo()
    #Procurar_Nome()
    #Procurar_Preco()
    #Procurar_Idade()
    #Procurar_developer()
    #Procurar_data_lanc()
    #Procurar_plataformas()
    #Procurar_Genero()

    # Procurar informações dos usuários (Mateus)
    #Procurar_ID_Usuario()
    #Procurar_Nome()

    # Procurar informações no carrinho (Mateus)
    #Procurar_Produtos()

    # Ver tudo (Mateus)
    #Ver_tudo()
