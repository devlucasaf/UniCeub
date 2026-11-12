from conexao import conectar, conectar_com_db

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

    # Inserção individual
    for i in range(3):
        print(f"\nInserção {i+1} de 3:")

        nome = input("Nome: ")
        preco = float(input("Preço: "))
        estoque = int(input("Estoque: "))
        validade = input("Data de validade (YYYY-MM-DD): ")

        cursor.execute("""
            INSERT INTO produtos (nome, preco, estoque, data_validade)
            VALUES (%s, %s, %s, %s)
        """, (nome, preco, estoque, validade))

    # Inserção múltipla
    dados = []
    print("\nInserindo dois produtos em um comando:")

    for i in range(2):
        nome = input("Nome: ")
        preco = float(input("Preço: "))
        estoque = int(input("Estoque: "))
        validade = input("Data de validade (YYYY-MM-DD): ")

        dados.append((nome, preco, estoque, validade))

    cursor.executemany("""
        INSERT INTO produtos (nome, preco, estoque, data_validade)
        VALUES (%s, %s, %s, %s)
    """, dados)

    conexao.commit()
    print("Registros inseridos com sucesso.")
    conexao.close()


def consultar_dados():
    conexao = conectar_com_db()
    cursor = conexao.cursor()

    filtro = input("Digite parte do nome do produto: ")

    cursor.execute("""
        SELECT * FROM produtos
        WHERE nome LIKE %s
    """, (f"%{filtro}%",))

    resultados = cursor.fetchall()

    if not resultados:
        print("Tabela vazia.")
        return [], []

    colunas = [desc[0] for desc in cursor.description]
    conexao.close()

    return resultados, colunas


def deletar_registro():
    conexao = conectar_com_db()
    cursor = conexao.cursor()

    nome = input("Digite o nome exato do produto: ")

    cursor.execute("""
        DELETE FROM produtos
        WHERE nome = %s
    """, (nome,))

    conexao.commit()

    if cursor.rowcount == 0:
        print("Nenhum registro encontrado.")
    else:
        print("Registro excluído com sucesso.")

    conexao.close()
