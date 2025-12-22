"""
Try, Except e Finally
Programação para Web
Aula: 04-08-25
"""

# QUESTÃO 1

# Solicita entrada do usuário para numerador e denominador
numerador = int(input("Digite o numerador: "))
denominador = int(input("Digite o denominador: "))

try:
    # Tenta realizar a divisão
    divisao = numerador / denominador
except ValueError:
    # Captura erro se o usuário digitar um valor não numérico
    print("Erro: valor digitado é inválido!")
except ZeroDivisionError:
    # Captura tentativa de divisão por zero
    print("Não é possível dividir por zero")
else:
    # Executa se não ocorrerem erros
    print(f"O resultado é {divisao}")
finally:
    # Sempre executa, independente de erros
    print("Código encerrado")

# ------------------------------------------------------------------------------------

# QUESTÃO 2

# Inicializa variável do arquivo como None
f = None

try:
    # Tenta abrir o arquivo em modo leitura
    f = open("dados.txt", "r")
    # Lê todo o conteúdo do arquivo
    content = f.read()
    print(content)
except FileNotFoundError:
    # Captura erro se o arquivo não existir
    print("Arquivo não encontrado!")
finally:
    # Garante que o arquivo será fechado se estiver aberto
    if f is not None:
        f.close()
    print("Fim da leitura")

# ------------------------------------------------------------------------------------

# JOGO DE ADIVINHAÇÃO

import numpy as np  # Importa numpy
from random import randint  # Para gerar números aleatórios

# Configurações iniciais
num_pc = 100  # Número máximo para sorteio
x = np.arange(num_pc)  # Cria array
num_random = randint(1, 100)  # Sorteia número entre 1 e 100
tentativas = 0  # Contador de tentativas

# Loop principal do jogo
while True:
    # Solicita palpite do usuário
    num_usuario = int(input("Digite um número: "))
    tentativas += 1  # Incrementa contador

    # Verifica se acertou
    if num_usuario == num_random:
        print(f"Você acertou o número escolhido! O número era: {num_random}")
        print(f"Você tentou {tentativas} vezes!")
        break  # Encerra o jogo

    # Dicas (maior/menor)
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
