# Questão 4: Problema do estoque.

import random

EI = 200
t = 365 # quantidades de dias simulados.
dias_segundo_turno = 0

print(f"{'dia'}|{'Estoque final Diário'} | {'demanda'} |{'Produção'}")

for i in range(1, t + 1):
    D = random.gauss(210, 30) # sorteie a demanda

    if EI < 100: # veja quanto precisa ser produzido
        P = 400
        dias_segundo_turno += 1
    else:
        P = 200
    
    EF = EI + P - D #encontre o estoque final do dia
    EI = EF # o estoque inicial do dia seguinte é o final do dia atual.

    print(f"{i}|{EF} | {D} | {P}")

print(f'Foram necessários {dias_segundo_turno} dias de segundo turno de produção.')

#Para fazer mais observações sobre o estoque, podemos repetir esse processo diversas vezes. 
