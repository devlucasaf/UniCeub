# Só faz sentido variar a quantidade comprada Q dentro do intervalo da demanda. Nesse caso, entre 50 e 150.

import random

melhor_lucro = 0 #variavel auxiliar para comparar os lucros diários de acordo com a compra de produtos.

print(f"{'Valor comprado':>18} | {'Lucro Médio':<14}")

for Q in range(50,151, 5):
    numero_dias = 100
    lucro_total = 0

    for dias in range(numero_dias):
        demanda = random.uniform(50,150)
        lucro = 10 * min(Q, demanda) + max(0, Q - demanda) - 6 * Q
        lucro_total += lucro
    
    lucro_medio = lucro_total/numero_dias
    print(f"{Q:>18} | {lucro_medio:<14}")

    if lucro_medio > melhor_lucro:
        melhor_lucro = lucro_medio
        melhor_Q = Q

print('A melhor contidade comprada é', melhor_Q, 'com lucro diario aproximado de', melhor_lucro)
    
# Espera-se que a melhor quantidade comprada varie entre 90 e 110. 
