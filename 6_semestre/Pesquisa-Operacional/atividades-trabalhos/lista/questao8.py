# Respostas:
# a) Taxa média de chegada: lamda = 1,5 caminhão/hora.
#    Taxa média de atendimento: 1 caminhão a cada 30 min, ou seja, mi = 2 caminhões/hora.
# b) Utilização: rho = 1,5/2 = 0,75. O sistema é estável, pois rho < 1
#    (em média, a equipe atende mais rápido do que os caminhões chegam).

####### SIMULAÇÃO (item c) #######
# Se chegam 1,5 caminhão por hora, o tempo médio entre chegadas é
# 1/1,5 h = 60/1,5 min = 40 min. O tempo médio de atendimento é 30 min.
# Todos os tempos abaixo estão em minutos.

import random

Tempo_total_chegadas = 480   # 8 horas

print(f"{'caminhão':>8} |{'chegada':>10} |{'serviço':>10} |{'início':>10} |{'partida':>10} |{'espera fila':>12}")

# Primeiro caminhão: encontra a equipe livre, então não espera
quantidade_caminhoes = 1

tempo_chegadas = random.expovariate(1/40)
tempo_servico = random.expovariate(1/30)

inicio = tempo_chegadas
partida = inicio + tempo_servico
tempo_espera_fila = 0

soma_esperas_fila = 0
soma_esperas_sistema = tempo_servico     # caminhão 1: espera 0 + serviço

print(f"{quantidade_caminhoes:>8} |{tempo_chegadas:>10.2f} |{tempo_servico:>10.2f} |{inicio:>10.2f} |{partida:>10.2f} |{tempo_espera_fila:>12.2f}")

# Demais caminhões: só entram os que chegam até as 8 horas
while True:
    chegada_proximo = tempo_chegadas + random.expovariate(1/40)
    if chegada_proximo > Tempo_total_chegadas:   # chegou depois das 8 h: encerra as chegadas
        break

    quantidade_caminhoes += 1
    tempo_servico_proximo = random.expovariate(1/30)

    if partida <= chegada_proximo:   # o anterior já saiu: equipe livre
        inicio = chegada_proximo
        tempo_espera_fila = 0
    else:                            # equipe ocupada: espera o anterior sair
        inicio = partida
        tempo_espera_fila = partida - chegada_proximo

    partida_proximo = inicio + tempo_servico_proximo
    tempo_espera_sistema = tempo_espera_fila + tempo_servico_proximo

    print(f"{quantidade_caminhoes:>8} |{chegada_proximo:>10.2f} |{tempo_servico_proximo:>10.2f} |{inicio:>10.2f} |{partida_proximo:>10.2f} |{tempo_espera_fila:>12.2f}")

    soma_esperas_fila += tempo_espera_fila
    soma_esperas_sistema += tempo_espera_sistema

    tempo_chegadas = chegada_proximo
    partida = partida_proximo

####### Item d #######
print()
print('Total de caminhões atendidos:', quantidade_caminhoes)
print(f'Tempo médio de espera na fila: {soma_esperas_fila / quantidade_caminhoes:.2f} min')
print(f'Tempo médio no sistema: {soma_esperas_sistema / quantidade_caminhoes:.2f} min')
print(f'Último caminhão saiu em: {partida:.2f} min')
