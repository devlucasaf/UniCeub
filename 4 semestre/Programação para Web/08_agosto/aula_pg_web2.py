"""
Try, Except e Finally
Programação para Web
Aula: 04-08-25
"""

# QUESTÃO 1

numerador = int(input("Digite o numerador: "))
denominador = int(input("Digite o denominador: "))

try:
    divisao = numerador / denominador
except ValueError:
    print("Erro: valor digitado é inválido!")
except ZeroDivisionError:
    print("Não é possível dividir por zero")
else:
    print(f"O resultado é {divisao}")
finally:
    print("Código encerrado")

# ------------------------------------------------------------------------------------

# QUESTÃO 2

f = None

try:
    f = open("dados.txt", "r")
    content = f.read()
    print(content)
except FileNotFoundError:
    print("Arquivo não encontrado!")
finally:
    if f is not None:
        f.close()
    print("Fim da leitura")

# ------------------------------------------------------------------------------------

# JOGO DE ADIVINHAÇÃO

import numpy as np  
from random import randint  

num_pc = 100  
x = np.arange(num_pc)  
num_random = randint(1, 100)  
tentativas = 0  

while True:
    num_usuario = int(input("Digite um número: "))
    tentativas += 1  

    if num_usuario == num_random:
        print(f"Você acertou o número escolhido! O número era: {num_random}")
        print(f"Você tentou {tentativas} vezes!")
        break  

    elif num_usuario > num_random:
        print("O número digitado é maior que o escolhido! Tente um menor!")
    else:
        print("O número digitado é menor que o escolhido! Tente um maior!")

# ------------------------------------------------------------------------------------

# EXERCÍCIO

def exibir_menu():
    print("\nAgenda Telefônica")
    print("1 - Adicionar telefone")
    print("2 - Remover telefone")
    print("3 - Verificar telefone")
    print("4 - Mostrar agenda")
    print("0 - Sair\n")


def adicionar_telefone(agenda):
    nome = input("Digite o nome do contato: ")
    telefone = input("Digite o telefone: ")

    if telefone in agenda:
        print("Contato já cadastrado")
    else:
        agenda[telefone] = nome
        print("Contato adicionado com sucesso.")


def remover_telefone(agenda):
    telefone = input("Digite o telefone a ser removido: ")
    if telefone in agenda:
        del agenda[telefone]
        print("Contato removido com sucesso.")
    else:
        print("Contato não encontrado")


def verificar_telefone(agenda):
    telefone = input("Digite o telefone para verificar: ")
    if telefone in agenda:
        print(f"Contato encontrado: {agenda[telefone]} - {telefone}")
    else:
        print("Contato não encontrado")


def mostrar_agenda(agenda):
    if agenda:
        print("\nLista de Contatos:")
        for telefone, nome in agenda.items():
            print(f"{nome} - {telefone}")
    else:
        print("Agenda vazia.")


def main():
    agenda = {}
    while True:
        exibir_menu()
        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            adicionar_telefone(agenda)
        elif opcao == "2":
            remover_telefone(agenda)
        elif opcao == "3":
            verificar_telefone(agenda)
        elif opcao == "4":
            mostrar_agenda(agenda)
        elif opcao == "0":
            print("Saindo da agenda telefônica.")
            break
        else:
            print("Opção inválida. Tente novamente.")

main()
