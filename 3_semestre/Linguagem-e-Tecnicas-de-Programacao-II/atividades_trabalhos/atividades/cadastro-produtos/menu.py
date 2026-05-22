from operacoes import (
    criar_banco_e_tabela,
    inserir_dados,
    consultar_dados,
    deletar_registro
)

from visualizacao import (
    mostrar_horizontal,
    mostrar_vertical
)

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
            resultados = consultar_dados()
            _ = consultar_dados()
            if resultados:
                mostrar_horizontal(resultados)
        elif opcao == "4":
            resultados = (consultar_dados())
            colunas = (consultar_dados())
            if resultados:
                mostrar_vertical(resultados, colunas)
        elif opcao == "5":
            deletar_registro()
        elif opcao == "0":
            print("Encerrando programa.")
            break
        else:
            print("Opção inválida.")