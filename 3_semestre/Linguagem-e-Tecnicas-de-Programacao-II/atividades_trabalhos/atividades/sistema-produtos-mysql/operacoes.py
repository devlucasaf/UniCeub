from mysql.connector import Error


def insert_product(connection):
    try:
        cursor = connection.cursor()

        print("\n--- Inserir Novo Produto ---")

        codigo = input("Código do produto: ")
        nome = input("Nome do produto: ")
        descricao = input("Descrição: ") or None
        preco = input("Preço: ") or None
        quantidade = input("Quantidade: ") or 0
        data_cadastro = input("Data de cadastro (YYYY-MM-DD): ") or None

        insert_query = """
            INSERT INTO produtos (
                codigo, nome, descricao, preco,
                quantidade_estoque, data_cadastro, ultima_atualizacao
            )
            VALUES (%s, %s, %s, %s, %s, %s, NOW())
        """

        cursor.execute(insert_query, (codigo, nome, descricao, preco, quantidade, data_cadastro))
        connection.commit()

        print(f"Produto inserido com sucesso. ID: {cursor.lastrowid}")

    except Error as e:
        print(f"Erro ao inserir produto: {e}")


def select_all_products_vertical(connection):
    try:
        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT * FROM produtos")
        produtos = cursor.fetchall()

        if not produtos:
            print("Nenhum produto cadastrado.")
            return

        print("\n--- Produtos (Vertical) ---")
        for produto in produtos:
            print("\nProduto:")
            for campo, valor in produto.items():
                print(f"{campo}: {valor}")

        print(f"\nTotal de produtos: {len(produtos)}")

    except Error as e:
        print(f"Erro ao buscar produtos: {e}")


def select_all_products_tabular(connection):
    try:
        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT * FROM produtos")
        produtos = cursor.fetchall()

        if not produtos:
            print("Nenhum produto cadastrado.")
            return

        print("\n--- Produtos (Tabela) ---")
        headers = produtos[0].keys()
        print("|".join(f"{h:^20}" for h in headers))
        print("-" * (20 * len(headers) + len(headers) - 1))

        for produto in produtos:
            print("|".join(f"{str(v)[:18]:^20}" for v in produto.values()))

        print(f"\nTotal de produtos: {len(produtos)}")

    except Error as e:
        print(f"Erro ao buscar produtos: {e}")


def update_product(connection):
    try:
        select_all_products_tabular(connection)
        product_id = input("\nDigite o ID do produto: ")

        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT * FROM produtos WHERE id = %s", (product_id,))
        produto = cursor.fetchone()

        if not produto:
            print("Produto não encontrado.")
            return

        print("\nDeixe em branco para manter o valor atual")

        nome = input(f"Nome [{produto['nome']}]: ") or produto['nome']
        descricao = input(f"Descrição [{produto['descricao']}]: ") or produto['descricao']
        preco = input(f"Preço [{produto['preco']}]: ") or produto['preco']
        quantidade = input(f"Quantidade [{produto['quantidade_estoque']}]: ") or produto['quantidade_estoque']

        update_query = """
            UPDATE produtos
            SET nome = %s, descricao = %s, preco = %s,
                quantidade_estoque = %s, ultima_atualizacao = NOW()
            WHERE id = %s
        """

        cursor.execute(update_query, (nome, descricao, preco, quantidade, product_id))
        connection.commit()

        print("Produto atualizado com sucesso!")

    except Error as e:
        print(f"Erro ao atualizar produto: {e}")


def delete_product(connection):
    try:
        select_all_products_tabular(connection)
        product_id = input("\nDigite o ID do produto que deseja remover: ")

        cursor = connection.cursor()
        cursor.execute("DELETE FROM produtos WHERE id = %s", (product_id,))
        connection.commit()

        if cursor.rowcount > 0:
            print("Produto removido com sucesso!")
        else:
            print("ID não encontrado.")

    except Error as e:
        print(f"Erro ao remover produto: {e}")
