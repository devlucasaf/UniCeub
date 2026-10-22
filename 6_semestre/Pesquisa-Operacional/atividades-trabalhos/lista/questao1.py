# Simulação de chegadas com média de 1,8 pessoa por minuto.
import numpy as np

numero_simulacoes = 100
soma_totais = 0

# Repete 100 vezes o experimento de observar chegadas durante 60 minutos.
for i in range(numero_simulacoes):
    total_chegadas = 0  # Zera o total no início de cada simulação.

    for k in range(60):
        # Sorteia a quantidade de pessoas que chegam neste minuto.
        chegadas_no_minuto = np.random.poisson(1.8)

        # Acumula as chegadas ao longo dos 60 minutos.
        total_chegadas += chegadas_no_minuto

    # Acrescenta o total desta simulação à soma dos totais.
    soma_totais += total_chegadas

# Calcula a média dos totais obtidos nas 100 simulações.
media_por_simulacao = soma_totais / numero_simulacoes

print('A média de chegadas em 60 minutos foi:', media_por_simulacao)

# A média esperada é de aproximadamente 108.