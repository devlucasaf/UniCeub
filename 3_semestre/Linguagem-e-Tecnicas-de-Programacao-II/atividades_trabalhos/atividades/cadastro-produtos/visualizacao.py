def mostrar_horizontal(resultados):
    for linha in resultados:
        print(" | ".join(str(campo)for campo in linha))

def mostrar_vertical(resultados, colunas):
    for linha in resultados:
        for i, valor in enumerate(linha):
            print(f"{colunas[i]}: {valor}")

        print("-" * 30)