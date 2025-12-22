import mysql.connector
from mysql.connector import Error


def criar_banco_tabela():
    #Função para criar o banco de dados e a tabela de produtos
    try:
        # Conectar ao MySQL sem especificar o banco de dados
        conexao = mysql.connector.connect(
            host='localhost',
            user='root',
            password=''  # Coloque sua senha aqui se necessário
        )

        if conexao.is_connected():
            cursor = conexao.cursor()

            # Criar o banco de dados se não existir
            cursor.execute("CREATE DATABASE IF NOT EXISTS loja_eletronicos")
            cursor.execute("USE loja_eletronicos")

            # Criar a tabela produtos
            cursor.execute("""
                CREATE TABLE IF NOT EXISTS produtos (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(100) NOT NULL,
                    modelo VARCHAR(50) NOT NULL UNIQUE,
                    preco DECIMAL(10,2) NOT NULL,
                    quantidade INT,
                    data_lancamento DATE,
                    fabricante VARCHAR(50),
                    garantia_meses INT
                )
            """)

            print("\nBanco de dados 'loja_eletronicos' e tabela 'produtos' criados com sucesso!")

    except Error as e:
        print(f"Erro ao conectar ao MySQL: {e}")
    finally:
        if conexao.is_connected():
            cursor.close()
            conexao.close()


def inserir_dados():
    #Função para inserir dados na tabela de produtos
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

            # Inserção múltipla em um único comando
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
            print(f"\nForam inseridos {cursor.rowcount} registros na tabela!")

    except Error as e:
        print(f"Erro ao inserir dados: {e}")
    finally:
        if conexao.is_connected():
            cursor.close()
            conexao.close()


def consultar_com_filtro():
    #Função para consultar dados com filtro
    try:
        conexao = mysql.connector.connect(
            host='localhost',
            user='root',
            password='',
            database='loja_eletronicos'
        )

        if conexao.is_connected():
            cursor = conexao.cursor()

            termo = input("\nDigite o termo de pesquisa (nome, modelo ou fabricante): ")

            cursor.execute("""
                SELECT * FROM produtos 
                WHERE nome LIKE %s OR modelo LIKE %s OR fabricante LIKE %s
            """, (f'%{termo}%', f'%{termo}%', f'%{termo}%'))

            resultados = cursor.fetchall()

            if not resultados:
                print("\nNenhum registro encontrado ou tabela vazia.")
            else:
                print("\n=== Resultados da Pesquisa ===")
                print(f"Encontrados {len(resultados)} registros:")

                # Mostrar na horizontal (como tabela)
                print("\n--- Formato Horizontal ---")
                print(
                    f"{'ID':<5} {'Nome':<15} {'Modelo':<15} {'Preço':<10} {'Qtd':<5} {'Lançamento':<12} {'Fabricante':<10} {'Garantia'}")
                print("-" * 80)
                for linha in resultados:
                    print(
                        f"{linha[0]:<5} {linha[1]:<15} {linha[2]:<15} R${linha[3]:<8.2f} {linha[4] if linha[4] else 'N/A':<5} "
                        f"{linha[5].strftime('%d/%m/%Y') if linha[5] else 'N/A':<12} {linha[6] if linha[6] else 'N/A':<10} "
                        f"{linha[7] if linha[7] else 'N/A'} meses")

                # Mostrar na vertical (detalhado)
                print("\n--- Formato Vertical ---")
                for i, linha in enumerate(resultados, 1):
                    print(f"\nRegistro {i}:")
                    print(f"  ID: {linha[0]}")
                    print(f"  Nome: {linha[1]}")
                    print(f"  Modelo: {linha[2]}")
                    print(f"  Preço: R${linha[3]:.2f}")
                    print(f"  Quantidade: {linha[4] if linha[4] else 'N/A'}")
                    print(f"  Data de Lançamento: {linha[5].strftime('%d/%m/%Y') if linha[5] else 'N/A'}")
                    print(f"  Fabricante: {linha[6] if linha[6] else 'N/A'}")
                    print(f"  Garantia: {linha[7] if linha[7] else 'N/A'} meses")

    except Error as e:
        print(f"Erro ao consultar dados: {e}")
    finally:
        if conexao.is_connected():
            cursor.close()
            conexao.close()


def consultar_todos():
    #Função para consultar todos os registros
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
                # Mostrar na horizontal com cabeçalhos
                print("\n=== Todos os Registros ===")
                print(
                    f"{'ID':<5} {'Nome':<15} {'Modelo':<15} {'Preço':<10} {'Qtd':<5} {'Lançamento':<12} {'Fabricante':<10} {'Garantia'}")
                print("-" * 80)
                for linha in resultados:
                    print(
                        f"{linha[0]:<5} {linha[1]:<15} {linha[2]:<15} R${linha[3]:<8.2f} {linha[4] if linha[4] else 'N/A':<5} "
                        f"{linha[5].strftime('%d/%m/%Y') if linha[5] else 'N/A':<12} {linha[6] if linha[6] else 'N/A':<10} "
                        f"{linha[7] if linha[7] else 'N/A'} meses")

                # Mostrar na vertical
                print("\n--- Detalhamento por Registro ---")
                for i, linha in enumerate(resultados, 1):
                    print(f"\n[Registro {i}]")
                    for col, valor in zip(cursor.column_names, linha):
                        print(f"  {col}: {valor if valor is not None else 'N/A'}")

    except Error as e:
        print(f"Erro ao consultar dados: {e}")
    finally:
        if conexao.is_connected():
            cursor.close()
            conexao.close()


def menu_principal():
    #Função para exibir o menu principal
    while True:
        print("\n=== MENU PRINCIPAL ===")
        print("1. Criar banco de dados e tabela")
        print("2. Inserir dados na tabela")
        print("3. Consultar com filtro")
        print("4. Consultar todos os registros")
        print("5. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == '1':
            criar_banco_tabela()
        elif opcao == '2':
            inserir_dados()
        elif opcao == '3':
            consultar_com_filtro()
        elif opcao == '4':
            consultar_todos()
        elif opcao == '5':
            print("\nSaindo do programa...")
            break
        else:
            print("\nOpção inválida. Tente novamente.")


if __name__ == "__main__":
    print("=== SISTEMA DE GERENCIAMENTO DE PRODUTOS ELETRÔNICOS ===")
    menu_principal()
