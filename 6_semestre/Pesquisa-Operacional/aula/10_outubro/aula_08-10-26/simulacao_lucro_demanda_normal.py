import random
import statistics

def linha():
    print("+=+=" * 69)

random.seed(42)

media_demanda = 100
desvio_padrao_demanda = 20

preco_venda = 4.00
custo_produto = 2.00

quantidade_comprada = 100
estoque_inicial = 0

quantidade_simulacoes = 10000

demandas = []
lucros = []

for simulacao in range(quantidade_simulacoes):

    demanda = random.gauss(media_demanda, desvio_padrao_demanda)

    if demanda < 0:
        demanda = 0

    quantidade_disponivel = quantidade_comprada + estoque_inicial

    if demanda < quantidade_disponivel:
        quantidade_vendida = demanda
    else:
        quantidade_vendida = quantidade_disponivel

    receita = preco_venda * quantidade_vendida

    custo_total = custo_produto * (quantidade_comprada - estoque_inicial)

    lucro = receita - custo_total

    demandas.append(demanda)
    lucros.append(lucro)

demanda_media_simulada = statistics.mean(demandas)

lucro_medio = statistics.mean(lucros)

menor_lucro = min(lucros)

maior_lucro = max(lucros)

linha()
print("SIMULAÇÃO DO LUCRO DA EMPRESA")
linha()

print(f"Quantidade comprada: {quantidade_comprada}")
print(f"Estoque inicial: {estoque_inicial}")
print(f"Quantidade de simulações: {quantidade_simulacoes}")

linha()

print(f"Demanda média simulada: {demanda_media_simulada:.2f}")
print(f"Lucro médio esperado: R$ {lucro_medio:.2f}")
print(f"Menor lucro encontrado: R$ {menor_lucro:.2f}")
print(f"Maior lucro encontrado: R$ {maior_lucro:.2f}")

linha()