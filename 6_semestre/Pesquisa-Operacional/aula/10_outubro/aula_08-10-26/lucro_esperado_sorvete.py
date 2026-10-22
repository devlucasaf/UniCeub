quantidade_sorvetes = 100

probabilidade_sol = 0.75
probabilidade_chuva = 0.25

lucro_com_sol = 0.0
lucro_com_chuva = 0.0

lucro_esperado = (probabilidade_sol * lucro_com_sol + probabilidade_chuva * lucro_com_chuva)

print(f"Quantidade de sorvetes comprada: {quantidade_sorvetes}")
print(f"Probabilidade de fazer sol: {probabilidade_sol:.0%}")
print(f"Probabilidade de chover: {probabilidade_chuva:.0%}")
print(f"Lucro esperado: R$ {lucro_esperado:.2f}")
