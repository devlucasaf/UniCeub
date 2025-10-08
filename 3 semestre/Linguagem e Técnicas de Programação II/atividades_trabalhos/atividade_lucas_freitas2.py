import mysql.connector

def conectar():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="sua_senha",
    )

def conectar_com_db():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="sua_senha",
        database="cadastro_produtos"
    )

def criar_banco_e_tabela():
    conexao = conectar()
    cursor = conexao.cursor()
    cursor.execute("CREATE DATABASE IF NOT EXISTS cadastro_produtos")
    cursor.execute("USE cadastro_produtos")
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS produtos (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nome VARCHAR(100) NOT NULL UNIQUE,
            preco DECIMAL(10,2) NOT NULL,
            estoque INT,
            data_validade DATE
        )
    """)
    print("Banco de dados e tabela criados com sucesso.")
    conexao.close()

def inserir_dados():
    conexao = conectar_com_db()
    cursor = conexao.cursor()

    # Inserção 1 a 1
    for i in range(3):
        print(f"\nInserção {i+1} de 3:")
        nome = input("Nome: ")
        preco = float(input("Preço: "))
        estoque = int(input("Estoque: "))
        validade = input("Data de validade (YYYY-MM-DD): ")

        cursor.execute("INSERT INTO produtos (nome, preco, estoque, data_validade) VALUES (%s, %s, %s, %s)",
                       (nome, preco, estoque, validade))

    # Inserção múltipla
    dados = []
    print("\nInserindo dois produtos em um comando:")
    for i in range(2):
        nome = input("Nome: ")
        preco = float(input("Preço: "))
        estoque = int(input("Estoque: "))
        validade = input("Data de validade (YYYY-MM-DD): ")
        dados.append((nome, preco, estoque, validade))

    cursor.executemany("INSERT INTO produtos (nome, preco, estoque, data_validade) VALUES (%s, %s, %s, %s)", dados)
    conexao.commit()
    print("Registros inseridos com sucesso.")
    conexao.close()

def consultar_dados():
    conexao = conectar_com_db()
    cursor = conexao.cursor()

    filtro = input("Digite parte do nome do produto para buscar: ")
    cursor.execute("SELECT * FROM produtos WHERE nome LIKE %s", (f"%{filtro}%",))
    resultados = cursor.fetchall()

    if not resultados:
        print("Tabela vazia.")
        return [], []

    colunas = [desc[0] for desc in cursor.description]
    return resultados, colunas

def mostrar_horizontal(resultados):
    for linha in resultados:
        print(" | ".join(str(campo) for campo in linha))

def mostrar_vertical(resultados, colunas):
    for linha in resultados:
        for i, valor in enumerate(linha):
            print(f"{colunas[i]}: {valor}")
        print("-" * 30)

def deletar_registro():
    conexao = conectar_com_db()
    cursor = conexao.cursor()
    nome = input("Digite o nome exato do produto a ser excluído: ")
    cursor.execute("DELETE FROM produtos WHERE nome = %s", (nome,))
    conexao.commit()

    if cursor.rowcount == 0:
        print("Nenhum registro encontrado.")
    else:
        print("Registro excluído com sucesso.")
    conexao.close()

def menu():
    while True:
        print("\nMENU")
        print("1 - Criar banco e tabela")
        print("2 - Inserir dados")
        print("3 - Consultar dados (horizontal)")
        print("4 - Consultar dados (vertical)")
        print("5 - Deletar registro")
        print("0 - Sair")
        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            criar_banco_e_tabela()
        elif opcao == "2":
            inserir_dados()
        elif opcao == "3":
            resultados, _ = consultar_dados()
            if resultados:
                mostrar_horizontal(resultados)
        elif opcao == "4":
            resultados, colunas = consultar_dados()
            if resultados:
                mostrar_vertical(resultados, colunas)
        elif opcao == "5":
            deletar_registro()
        elif opcao == "0":
            print("Encerrando programa.")
            break
        else:
            print("Opção inválida.")

if __name__ == "__main__":
    menu()
