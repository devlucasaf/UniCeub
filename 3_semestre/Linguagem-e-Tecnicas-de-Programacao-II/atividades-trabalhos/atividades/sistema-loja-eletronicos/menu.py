from database import criar_banco_tabela

from consultas import (
    inserir_dados,
    consultar_com_filtro,
    consultar_todos
)


def menu_principal():

    while True:
        print("\n=== MENU PRINCIPAL ===")

        print("1. Criar banco e tabela")
        print("2. Inserir dados")
        print("3. Consultar com filtro")
        print("4. Consultar todos")
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
            print("\nOpção inválida.")