import random

quantidade = 100
numero_dias = 10
lucro_total = 0

for dia in range(1, numero_dias + 1):
    moeda = random.choice(["cara", "coroa"])

    if moeda == "cara":
        demanda = 150
    else:
        demanda = 50

    vendas = min(quantidade, demanda)
    lucro = 5 * vendas - 3 * quantidade
    lucro_total = lucro + lucro_total

    lucro_medio = lucro_total / dia

print(f"O lucro médio é: {lucro_medio}")
