import random 
import matplotlib.pyplot as plt

Q = 90 #quantidade comprada
total_dias = 300

lucro_total = 0

Estoque = 10

lucros =[]

print(f"{"Demanda":>10} | {"Lucro (R$)":>12}")
print("-" * 25)

for i in range(1, total_dias +1 ):

    D = max(0, random.gauss(100,20))

    vendas = min(D, Q + Estoque)

    disponivel = Q + Estoque

    lucro = 4 * vendas - 2 * (disponivel)
    lucros.append(lucro)

    print(f"{D:>10} | {lucro:>12.2f}")

    lucro_total = lucro_total + lucro

    lucro_medio = lucro_total/i

    Estoque = disponivel - vendas

print("LUCRO MEDIO:", lucro_medio )

dias = range(1, total_dias + 1)

plt.plot(dias, lucros)
plt.axhline(
    y=lucro_medio,
    color="red",
    linestyle="--",
    label=f"Média: R$ {lucro_medio:.2f}"
)

plt.legend()

plt.xlabel("Dia")
plt.ylabel("Lucro diário (R$)")
plt.title("Lucro ao longo dos dias")
plt.grid(True)

plt.show()


