# a)
import numpy as np

LAMBDA = 1.8
minutos = 60

chegada_carros_pedagio = np.random.poisson(lam=LAMBDA, size=minutos)

for minutos, carros in enumerate(chegada_carros_pedagio, start=1):
    print(f"Minuto: {minutos}, Carros: {carros}")

total = chegada_carros_pedagio.sum()

print(f"Total: {total}")
print(f"Média de chegada de carros por minutos: {minutos:.2f}")

# b) A quantidade total de carros obtidos não foi igual. Eles se diferem devido a geração de números aleatórios