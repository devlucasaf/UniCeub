# QUESTÃO 1

import math

def calcular_volume_esfera():
    """
    Calcula o volume de uma esfera dado o raio fornecido pelo usuário
    """
    raio = float(input("Digite o raio da esfera: "))
    volume = (4 * math.pi * raio**3) / 3
    print(f"O volume da esfera com raio {raio} é: {volume:.2f}")

# Executar o exercício 1
calcular_volume_esfera()

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

# QUESTÃO 2

def ordenar_valores():
    """
    Ordena dois valores inteiros em ordem crescente
    """
    valor1 = int(input("Digite o primeiro valor inteiro: "))
    valor2 = int(input("Digite o segundo valor inteiro: "))
    
    if valor1 <= valor2:
        print(f"Valores em ordem crescente: {valor1}, {valor2}")
    else:
        print(f"Valores em ordem crescente: {valor2}, {valor1}")

# Executar o exercício 2
ordenar_valores()

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

# QUESTÃO 3

def analisar_valores():
    """
    Analisa vários valores reais fornecidos pelo usuário
    """
    valores = []
    print("Digite valores reais (digite 'fim' para terminar):")
    
    while True:
        entrada = input("Digite um valor: ")
        if entrada.lower() == 'fim':
            break
        try:
            valor = float(entrada)
            valores.append(valor)
        except ValueError:
            print("Por favor, digite um valor numérico válido.")
    
    # Cálculos
    quantidade = len(valores)
    soma = sum(valores)
    media = soma / quantidade if quantidade > 0 else 0
    maiores_que_20 = len([v for v in valores if v > 20])
    
    # Resultados
    print(f"\n--- RESULTADOS ---")
    print(f"Quantidade de valores digitados: {quantidade}")
    print(f"Soma dos valores: {soma:.2f}")
    print(f"Média aritmética: {media:.2f}")
    print(f"Valores maiores que 20: {maiores_que_20}")

# Executar o exercício 3
analisar_valores()
