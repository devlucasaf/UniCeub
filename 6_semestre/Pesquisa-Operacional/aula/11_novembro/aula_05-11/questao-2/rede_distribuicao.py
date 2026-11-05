import pulp as pl

saldo = {
    "BA": -500,
    "SP": -600,
    "D":  -300,  
    "MG":  200,
    "RJ":  350,
    "GO":  150,
    "PR":  300,
    "SC":  150,
    "RS":  250,
}

arcos = {
    ("BA", "GO"): 40,
    ("BA", "MG"): 25,
    ("BA", "RJ"): 30,
    ("MG", "GO"): 20,
    ("MG", "RJ"): 20,
    ("SP", "MG"): 20,
    ("SP", "RJ"): 15,
    ("SP", "PR"): 20,
    ("SP", "SC"): 35,
    ("SP", "RS"): 50,
    ("SC", "RS"): 20,
    ("D", "MG"): 0,
    ("D", "RJ"): 0,
    ("D", "GO"): 0,
    ("D", "PR"): 0,
    ("D", "SC"): 0,
    ("D", "RS"): 0,
}

Problema = pl.LpProblem(name="minimizar_Z", sense=pl.LpMinimize)

X = {}
for (i, j) in arcos:
    X[(i, j)] = pl.LpVariable(name=f"x_{i}_{j}", lowBound=0, cat="Continuous")

Problema += pl.lpSum(arcos[a] * X[a] for a in arcos), "Custo_total"

for no, valor in saldo.items():
    entradas = pl.lpSum(X[(i, j)] for (i, j) in arcos if j == no)
    saidas = pl.lpSum(X[(i, j)] for (i, j) in arcos if i == no)
    Problema += (entradas - saidas == valor), f"no_{no}"

Problema.solve(pl.PULP_CBC_CMD(msg=False))

print("Status:", pl.LpStatus[Problema.status])
print(f"Custo mínimo total: {pl.value(Problema.objective):.2f}\n")
print(f"{'Arco':<10} {'Custo':>6} {'Qtd':>8} {'Subtotal':>10}")
for (i, j), custo in arcos.items():
    q = X[(i, j)].varValue
    print(f"{i + '->' + j:<10} {custo:>6} {q:>8.0f} {q * custo:>10.0f}")
