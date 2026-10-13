import random

random.seed(42)

quantidade_clientes = 15
intervalo_chegadas = 10

operacoes = {
    "Investimento": 30,
    "Depósito": 18,
    "Transferência": 20,
    "Consulta": 10,
    "Saque": 15,
    "Empréstimo": 50
}

tempo_livre_caixa = 0
resultados = []

for cliente in range(1, quantidade_clientes + 1):

    tempo_chegada = (cliente - 1) * intervalo_chegadas

    operacao = random.choice(list(operacoes.keys()))
    tempo_operacao = operacoes[operacao]

    inicio_atendimento = max(tempo_chegada, tempo_livre_caixa)
    fim_atendimento = inicio_atendimento + tempo_operacao

    tempo_espera = inicio_atendimento - tempo_chegada

    resultados.append({
        "cliente": cliente,
        "chegada": tempo_chegada,
        "operacao": operacao,
        "tempo_operacao": tempo_operacao,
        "inicio": inicio_atendimento,
        "fim": fim_atendimento,
        "espera": tempo_espera
    })

    tempo_livre_caixa = fim_atendimento


tempo_decima_quinta_chegada = (quantidade_clientes - 1) * intervalo_chegadas

tamanho_fila = 0

for resultado in resultados:
    cliente_ja_chegou = resultado["chegada"] <= tempo_decima_quinta_chegada
    cliente_ainda_nao_foi_atendido = resultado["inicio"] > tempo_decima_quinta_chegada

    if cliente_ja_chegou and cliente_ainda_nao_foi_atendido:
        tamanho_fila = tamanho_fila + 1

clientes_no_sistema = 0

for resultado in resultados:
    cliente_ja_chegou = resultado["chegada"] <= tempo_decima_quinta_chegada
    cliente_ainda_nao_finalizou = resultado["fim"] > tempo_decima_quinta_chegada

    if cliente_ja_chegou and cliente_ainda_nao_finalizou:
        clientes_no_sistema = clientes_no_sistema + 1


print("=" * 105)

print("Cliente")
print("Chegada")
print("Operação")
print("Duração")
print("Início")
print("Fim")
print("Espera")
print("=" * 50)

print("=" * 105)

for resultado in resultados:
    print(f"Cliente: {resultado["cliente"]}")
    print(f"Chegada: {resultado["chegada"]} segundos")
    print(f"Operação: {resultado["operacao"]}")
    print(f"Tempo da operação: {resultado["tempo_operacao"]} segundos")
    print(f"Início do atendimento: {resultado["inicio"]} segundos")
    print(f"Fim do atendimento: {resultado["fim"]} segundos")
    print(f"Tempo de espera: {resultado["espera"]} segundos")
    print("-" * 50)

print("+=+=" * 105)

print(f"Momento da chegada do 15º cliente: {tempo_decima_quinta_chegada} segundos")

print(f"Quantidade de clientes aguardando na fila: {tamanho_fila}")

print(f"Quantidade total de clientes no sistema: {clientes_no_sistema}")