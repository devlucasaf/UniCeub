import random

n = 200     # número de entidades
rep = 10    # número de repetições

soma_rep_tef = 0
soma_rep_tes = 0
soma_rep_taf = 0
soma_rep_tas = 0

for r in range(rep):
    # Primeiro cliente: encontra o atendente livre
    tempo_chegadas = random.expovariate(1/15)
    tempo_servico = random.expovariate(1/13)
    partida = tempo_chegadas + tempo_servico

    soma_tef = 0
    soma_tes = tempo_servico          # espera 0 + serviço

    for j in range(1, n):
        chegada_proximo = tempo_chegadas + random.expovariate(1/15)
        tempo_servico_proximo = random.expovariate(1/13)

        if partida <= chegada_proximo:            # atendente livre
            tempo_espera = 0
            partida_proximo = chegada_proximo + tempo_servico_proximo
        else:                                     # atendente ocupado
            tempo_espera = partida - chegada_proximo
            partida_proximo = partida + tempo_servico_proximo

        soma_tef += tempo_espera
        soma_tes += tempo_espera + tempo_servico_proximo

        tempo_chegadas = chegada_proximo
        partida = partida_proximo

    # Médias desta repetição
    soma_rep_tef += soma_tef / n          # tempo médio na fila
    soma_rep_tes += soma_tes / n          # tempo médio no sistema
    soma_rep_taf += soma_tef / partida    # tamanho médio da fila
    soma_rep_tas += soma_tes / partida    # tamanho médio do sistema

# Média entre as repetições
print('Tempo médio de espera na fila:  %.2f s' % (soma_rep_tef / rep))
print('Tempo médio no sistema: %.2f s' % (soma_rep_tes / rep))
print('Tamanho médio da fila:  %.2f' % (soma_rep_taf / rep))
print('Tamanho médio do sistema: %.2f' % (soma_rep_tas / rep))