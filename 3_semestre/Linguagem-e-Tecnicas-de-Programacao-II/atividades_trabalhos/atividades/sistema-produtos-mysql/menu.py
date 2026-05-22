from operacoes import (
    insert_product,
    select_all_products_vertical,
    select_all_products_tabular,
    update_product,
    delete_product
)


def menu(connection):
    while True:
        print("\n=== MENU PRINCIPAL ===")
        print("1. Listar produtos (vertical)")
        print("2. Listar produtos (tabular)")
        print("3. Adicionar produto")
        print("4. Atualizar produto")
        print("5. Remover produto")
        print("0. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            select_all_products_vertical(connection)

        elif opcao == "2":
            select_all_products_tabular(connection)

        elif opcao == "3":
            insert_product(connection)

        elif opcao == "4":
            update_product(connection)

        elif opcao == "5":
            delete_product(connection)

        elif opcao == "0":
            print("Saindo do sistema...")
            break

        else:
            print("Opção inválida.")
