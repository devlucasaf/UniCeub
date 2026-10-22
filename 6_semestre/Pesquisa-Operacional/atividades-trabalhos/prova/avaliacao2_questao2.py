# a)

"""
Expressão do lucro:

Vendas = min(Q, D)
Sobras = max(Q - D, 0)
L = 10 - min(Q, D) + 1 * max(Q - D, 0) - 6 * Q
"""

# b)

import numpy as np

Q = 75
dias = 25

def lucro(qntd, dias):
    vendidos = min(qntd, dias)
    sobras = max(qntd - dias, 0)
    return 10 * vendidos + sobras - 6 * qntd

lucros = []
for dia in range(1, dias + 1):
    demanda = np.random.randint(50, 150)
    l = lucro(Q, demanda)
    lucros.append(l)
    print(f"Dia {dia:0d}, Demanda {demanda}, lucro {l:.2f}")

print(f"O lucro médio diário é: R$ {np.mean(lucros):.2f}")

# c)

import numpy as np

DIAS = 1000
QUANTIDADE = np.random.randint(50, 151)

def lucro(q, d):
    vendidos = np.minimum(q, d)
    sobras = np.maximum(q - d, 0)
    return 10 * vendidos + sobras - 6 * q

melhor_q = None
melhor_lucro = -np.inf

for q in range(QUANTIDADE):
    demanda = np.random.randint(50, 151, size=DIAS)
    media = lucro(q, demanda).mean()
    print(f"Quantidade = {q:3d}: lucro médio diário: R$ {melhor_lucro:.2f}")
    if media > melhor_lucro:
        melhor_lucro = media
        melhor_q = q

print(f"\nMelhor q: {melhor_q} jornais")
print(f"O maior lucro diário é: R$ {melhor_lucro:.2f}")

# d) Foi necessário simular 1000 dias, pois o lucro de cada dia é aleatório e a média de poucos dias oscila muito