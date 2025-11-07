"""
Aula prática de Probabilidade e Estatística
Probabilidade e Estatística
Lucas Freitas
Professora: Ingrid Maria Dittert
Aula: 20-08-25
"""

import random
import math
from math import comb

def linha():
    """Função para criar uma linha divisória visual no output"""
    print("+=+="*69)

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

def mostrar_dados():
        """Função que simula lançamentos de dados e conta pares/ímpares"""
        par = 0
        impar = 0
        rodadas = int(input("Dados: "))  # Solicita quantos dados serão lançados
        
        for p in range(rodadas):
            total = random.randint(0,6)  # Gera um número aleatório entre 0 e 6
            
            if total % 2 == 0:  # Verifica se o número é par
                par += 1
            else:
                impar += 1

        print(f"Pares: {par}")
        print(f"Impar: {impar}")

mostrar_dados()  # Chama a função para executar

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

linha()  # Imprime linha divisória
print("Exercicio 2 \n")  # Título do exercício

def calcula_prob(evento_desejado, resultados_possiveis):
    """Calcula probabilidade simples: eventos favoráveis / eventos possíveis"""
    p = evento_desejado / resultados_possiveis
    return p

evento_desejado = 6  # Casos favoráveis (soma 7 em dois dados)
resultados_possiveis = 6 * 6  # Total de combinações possíveis (6×6)
resultado = calcula_prob(evento_desejado, resultados_possiveis)

print(f"A probabildade é igual a {resultado}")

round(resultado, 2)  # Arredonda para 2 casas decimais (mas não armazena o resultado)

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

linha()
print("Exercicio 3 \n")

def calculaArranjo(n, k):
    """Calcula arranjo de n elementos tomados k a k: n!/(n-k)!"""
    arranjo = math.factorial(n) // math.factorial(n - k)
    return arranjo

n = 10  # Total de elementos
k = 3   # Elementos escolhidos

resultado_arranjo = calculaArranjo(n, k)

print(f"O arranjo de {n} elementos escolhendo {k} elementos de cada vez é: {resultado_arranjo}")

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

linha()
print("Exercício 4\n")

def calcular_combinacao(n, k):
    """Calcula combinação de n elementos tomados k a k: n!/((n-k)! × k!)"""
    combinacao = math.factorial(n) // (math.factorial(n-k) * math.factorial(k))
    return combinacao

# Exemplo:
n = 10  # Total de elementos
k = 2   # Elementos escolhidos

resultado_combinacao = calcular_combinacao(n,k)

print(f"A combinação de {n} elementos escolhendo {k} elemento de cada vez é {resultado_combinacao}")

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

linha()
print("Exercício 5\n")

def calcula_prob(evento_desejado, resultados_possiveis):
    """Calcula probabilidade simples (redefinição da função)"""
    p = evento_desejado/resultados_possiveis
    return p

evento_desejado = 1  # Caso favorável (provavelmente soma 2 ou 12)
resultados_possiveis = 36  # Total de combinações possíveis com dois dados
resultado = calcula_prob(evento_desejado, resultados_possiveis)

print(f"A probabildade é igual a {resultado * 100:.2f}%")  # Formata como porcentagem

round(resultado, 2)  # Arredonda (novamente sem armazenar)

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

linha()
print("Exercício 6\n")

# Calcula probabilidade de tirar 2 ases em 2 cartas de um baralho
evento_desejado = comb(4, 2) # Escolher 2 ases dos 4 disponíveis
resultados_possiveis = comb(52, 2) # Escolher 2 cartas de um baralho de 52
probabilidade = evento_desejado / resultados_possiveis

print(f"A probabilidade é igual a {probabilidade * 100:.2f}%")

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

linha()
print("Exercício 7\n")

# Calcula probabilidade de acertar a sena (6 números em 60)
total = comb(60,6)  # Total de combinações possíveis
p = 1 / total  # Probabilidade de acertar (apenas 1 combinação vencedora)
total, p, p * 100  # Esta linha não imprime nada, apenas calcula

print(total)  # Imprime apenas o total de combinações possíveis
