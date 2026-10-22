"""# a) Taxa de estabilidade

Chegadas: tempo médio = 15s
y = 60/15 = 4 clientes/min
p = y/u = 4/4,62 = 13/15 = 0,867

O sistema é estável, pois quando 'p < 1' o atendente consegue em média atender mais rápido do que os clientes chegam
"""

"""
import numpy as np

N_CLIENTES = 50
MEDIA_CHEGADA = 15
MEDIA_ATENDIMENTO = 13

def simular(n):
    entre_chegadas = np.random.exponential(MEDIA_CHEGADA, size=n)
    atendimentos = np.random.exponential(MEDIA_ATENDIMENTO, size=n)

    chegadas = np.cumsum(entre_chegadas)
    inicio = np.zeros(n)
    fim = np.zeros(n)

    for i in range(n):
        if i == 0:
            inicio[i] = chegadas[i]
        else:
            inicio[i] = max(chegadas[i], fim[i - 1])

        fim[i] = inicio[i] + atendimentos[i]

    espera_fila = inicio - chegadas
    tempo_sistema = fim - chegadas
    return tempo_sistema.mean(), espera_fila.mean()

media_sistema, media_fila =  simular(N_CLIENTES)
print(f"O tempo de espera no sistema é: {media_sistema:.2f} s")
print(f"Tempo médio de espera na fila: {media_fila:.2f} s")
"""

# c)

import numpy as np

N_CLIENTES = 50
MEDIA_CHEGADA = 15
MEDIA_ATENDIMENTO = 13
N_OBS = 10
sistema = []
filas = []

def simular(n):
    entre_chegadas = np.random.exponential(MEDIA_CHEGADA, size=n)
    atendimentos = np.random.exponential(MEDIA_ATENDIMENTO, size=n)

    chegadas = np.cumsum(entre_chegadas)
    inicio = np.zeros(n)
    fim = np.zeros(n)

    for i in range(n):
        if i == 0:
            inicio[i] = chegadas[i]
        else:
            inicio[i] = max(chegadas[i], fim[i - 1])

        fim[i] = inicio[i] + atendimentos[i]

    espera_fila = inicio - chegadas
    tempo_sistema = fim - chegadas
    return tempo_sistema.mean(), espera_fila.mean()

for obs in range(1, N_OBS + 1):
    s, f = simular(N_CLIENTES)
    sistema.append(s)
    filas.append(f)
    print(f"Observação: {obs:02d}")

media_sistema, media_fila =  simular(N_CLIENTES)
print(f"O tempo de espera no sistema é: {media_sistema:.2f} s")
print(f"Tempo médio de espera na fila: {media_fila:.2f} s")