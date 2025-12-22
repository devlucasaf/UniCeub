# QUESTÃO 1

def mostra_mensagem(mensagem, numero):
    """
    Função que recebe uma mensagem e um número e os mostra na tela
    """
    print(f"Mensagem: {mensagem}")
    print(f"Número: {numero}")

def main():
    # Lendo os dados do usuário
    mensagem = input("Digite uma mensagem: ")
    numero = int(input("Digite um número: "))
    
    # Chamando a função
    mostra_mensagem(mensagem, numero)

# Executando o programa
if __name__ == "__main__":
    main()

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

# QUESTÃO 2

from datetime import datetime

def calcula_idade(ano_nascimento):
    """
    Função que calcula a idade baseada no ano de nascimento
    """
    ano_atual = datetime.now().year
    idade = ano_atual - ano_nascimento
    return idade

def main():
    # Lendo o ano de nascimento
    ano_nascimento = int(input("Digite o ano de nascimento: "))
    
    # Chamando a função e obtendo o resultado
    idade = calcula_idade(ano_nascimento)
    
    # Mostrando o resultado
    print(f"A idade é: {idade} anos")

# Executando o programa
if __name__ == "__main__":
    main()
