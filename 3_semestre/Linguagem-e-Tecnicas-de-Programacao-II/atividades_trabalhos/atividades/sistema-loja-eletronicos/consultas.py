import mysql.connector
from mysql.connector import Error


def inserir_dados():
    try:
        conexao = mysql.connector.connect(
            host='localhost',
            user='root',
            password='',
            database='loja_eletronicos'
        )

        if conexao.is_connected():
            cursor = conexao.cursor()

            # Inserções individuais
            cursor.execute("""
                INSERT INTO produtos (nome, modelo, preco, quantidade, data_lancamento, fabricante, garantia_meses)
                VALUES ('Smartphone', 'Galaxy S21', 3999.99, 50, '2021-01-14', 'Samsung', 12)
            """)

            cursor.execute("""
                INSERT INTO produtos (nome, modelo, preco, quantidade, fabricante)
                VALUES ('Notebook', 'Inspiron 15', 4299.00, 30, 'Dell')
            """)

            cursor.execute("""
                INSERT INTO produtos (nome, modelo, preco, data_lancamento, garantia_meses)
                VALUES ('Tablet', 'iPad Air', 3499.90, '2022-03-08', 12)
            """)

            # Inserção múltipla
            sql_multiplo = """
                INSERT INTO produtos (nome, modelo, preco, quantidade, data_lancamento, fabricante, garantia_meses)
                VALUES (%s, %s, %s, %s, %s, %s, %s)
            """

            valores = [
                ('Smart TV', 'OLED55C1', 5499.00, 15, '2021-05-20', 'LG', 24),
                ('Fone de Ouvido', 'WH-1000XM4', 1799.90, 40, '2020-08-06', 'Sony', 12),
                ('Console', 'PlayStation 5', 4499.99, 10, '2020-11-12', 'Sony', 12)
            ]

            cursor.executemany(sql_multiplo, valores)
            conexao.commit()

            print(f"\nForam inseridos {cursor.rowcount} registros!")

    except Error as e:
        print(f"Erro ao inserir dados: {e}")

    finally:
        if conexao.is_connected():
            cursor.close()
            conexao.close()


def consultar_com_filtro():
    try:
        conexao = mysql.connector.connect(
            host='localhost',
            user='root',
            password='',
            database='loja_eletronicos'
        )

        if conexao.is_connected():
            cursor = conexao.cursor()

            termo = input("\nDigite o termo de pesquisa: ")

            cursor.execute("""
                SELECT * FROM produtos
                WHERE nome LIKE %s OR modelo LIKE %s OR fabricante LIKE %s
            """, (f'%{termo}%', f'%{termo}%', f'%{termo}%'))

            resultados = cursor.fetchall()

            if not resultados:
                print("\nNenhum registro encontrado.")
            else:
                print("\n=== Resultados da Pesquisa ===")
                print(f"{'ID':<5} {'Nome':<15} {'Modelo':<15} {'Preço':<10}")
                print("-" * 60)

                for linha in resultados:
                    print(f"{linha[0]:<5} {linha[1]:<15} {linha[2]:<15} R${linha[3]:<8.2f}")

    except Error as e:
        print(f"Erro ao consultar dados: {e}")

    finally:
        if conexao.is_connected():
            cursor.close()
            conexao.close()


def consultar_todos():
    try:
        conexao = mysql.connector.connect(
            host='localhost',
            user='root',
            password='',
            database='loja_eletronicos'
        )

        if conexao.is_connected():
            cursor = conexao.cursor()
            cursor.execute("SELECT * FROM produtos")

            resultados = cursor.fetchall()

            if not resultados:
                print("\nTabela vazia.")
            else:
                print("\n=== Todos os Registros ===")
                print(f"{'ID':<5} {'Nome':<15} {'Modelo':<15} {'Preço':<10}")
                print("-" * 60)

                for linha in resultados:
                    print(f"{linha[0]:<5} {linha[1]:<15} {linha[2]:<15} R${linha[3]:<8.2f}")

    except Error as e:
        print(f"Erro ao consultar dados: {e}")

    finally:
        if conexao.is_connected():
            cursor.close()
            conexao.close()
