# Formulação do Lucro: L = 10*min{D,Q} + max{0, Q-D} - 6*Q

import random

Q = 75 
numero_dias = 25
lucro_total = 0

print(f"{'dia':>5} |{'Demanda':>10} {'Lucro Diário':<15}")

for dias in range(numero_dias):
    demanda = random.uniform(50,150)
    lucro = 10 * min(Q, demanda) + max(0, Q- demanda) -6 * Q
    lucro_total += lucro

    print(f"{dias:>5} | {demanda:>10} | {lucro:<15}")

lucro_medio = lucro_total/numero_dias

print('O lucro médio em 25 dias é', lucro_medio)

# O valor do lucro médio deve ser próximo de 272. 
