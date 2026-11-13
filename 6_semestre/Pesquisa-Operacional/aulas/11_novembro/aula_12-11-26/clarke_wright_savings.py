from http import cookiejar
from itertools import permutations
from itertools import product

# --- DADOS DO PROBLEMA ---
DEPOSITO = 0
CAPACIDADE = 50
DEMANDA = {
    1: 15, 
    2: 17,
    3: 27, 
    4: 12, 
    5: 23
    }

# --- ARESTAS COM DISTÂNCIA INDICADA NO GRAFO ---
ARESTAS = {
    (0, 1): 28, 
    (0, 2): 27, 
    (0, 3): 13, 
    (0, 4): 38, 
    (0, 5): 24,
    (1, 2): 52, 
    (2, 3): 20, 
    (3, 4): 28, 
    (4, 5): 43, 
    (5, 1): 52,
}


# --- MATRIZ DE DISTÂNCIAS ---
def montar_matriz(arestas, n):
    inf = float("inf")
    d = [[0 if i == j else inf for j in range(n)] for i in range(n)]
    for (i, j), c in arestas.items():
        d[i][j] = d[j][i] = c
    
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if d[i][k] + d[k][j] < d[i][j]:
                    d[i][j] = d[i][k] + d[k][j]
    return d

D = montar_matriz(ARESTAS, len(DEMANDA) + 1)


# --- LINHA 1: UMA ROTA 0 -> i -> 0 PARA CADA CLIENTE ---
def create_initial_routes(clientes):
    return [[c] for c in clientes]


# --- LINHA 2: S_ij = C(i,0) + C(0,j) - C(i,j), ORDEM DECRESCENTE ---
def compute_savings_list(clientes):
    savings = []
    for i in clientes:
        for j in clientes:
            if i < j:
                s = D[i][DEPOSITO] + D[DEPOSITO][j] - D[i][j]
                savings.append((s, i, j))
    savings.sort(key=lambda x: (-x[0], x[1], x[2]))
    return savings

def carga(rota):
    return sum(DEMANDA[c] for c in rota)

def custo_rota(rota):
    caminho = [DEPOSITO] + rota + [DEPOSITO]
    return sum(D[a][b] for a, b in zip(caminho, caminho[1:]))

def rota_de(cliente, routes):
    for r in routes:
        if cliente in r:
            return r
    return None

# --- LINHA 4: i E j EM ROTAS DIFERENTES, NAS PONTAS, SEM ESTOURAR Q ---
def feasible_merge(i, j, routes, q):
    ri = rota_de(i, routes)
    rj = rota_de(j, routes)

    if ri is rj:
        return False
    
    if i not in (ri[0], ri[-1]) or j not in (rj[0], rj[-1]):
        return False
    return carga(ri) + carga(rj) <= q

# --- LINHA 5: JUNTA AS ROTAS LIGANDO i A j ---
def merge_routes(i, j, routes):
    ri = rota_de(i, routes)
    rj = rota_de(j, routes)
    
    if ri[-1] != i:
        ri = ri[::-1]
    
    if rj[0] != j:
        rj = rj[::-1]
    
    routes.remove(rota_de(i, routes))
    routes.remove(rota_de(j, routes))
    routes.append(ri + rj)

# --- ALGORITMO 1 ---
def clarke_wright(clientes, q, verbose=False):
    routes = create_initial_routes(clientes)
    savings_list = compute_savings_list(clientes)
    for s, i, j in savings_list:
        ok = feasible_merge(i, j, routes, q)
        if verbose:
            print(f"  S({i},{j}) = {s:>3}  ->  {'junta' if ok else 'rejeita'}")
        if ok:
            merge_routes(i, j, routes)
    return routes

# --- SOLUÇÃO EXATA POR FORÇA BRUTA ---
def melhor_ordem(grupo):
    if not grupo:
        return [], 0
    melhor = min(permutations(grupo), key=lambda p: custo_rota(list(p)))
    return list(melhor), custo_rota(list(melhor))

def otimo(clientes, q, max_veiculos):
    melhor = (float("inf"), None)
    for atribuicao in product(range(max_veiculos), repeat=len(clientes)):
        grupos = [[c for c, v in zip(clientes, atribuicao) if v == k]
                    for k in range(max_veiculos)]
        if any(carga(g) > q for g in grupos):
            continue
        rotas = [melhor_ordem(g) for g in grupos if g]
        total = sum(c for _, c in rotas)
        if total < melhor[0]:
            melhor = (total, [r for r, _ in rotas])
    return melhor

def mostrar(rotas):
    total = 0
    for k, r in enumerate(rotas, 1):
        c = custo_rota(r)
        total += c
        caminho = " -> ".join(map(str, [0] + r + [0]))
        print(f"  Veículo {k}: {caminho:<22} carga {carga(r):>2}/{CAPACIDADE}  distância {c} km")
    print(f"  Veículos usados: {len(rotas)}  |  Distância total: {total} km")

if __name__ == "__main__":
    clientes = sorted(DEMANDA)

    print("=== MATRIZ DE DISTÂNCIAS (km) ===")
    print("     " + "".join(f"{j:>5}" for j in range(len(D))))
    for i, linha in enumerate(D):
        print(f"{i:>5}" + "".join(f"{int(x):>5}" for x in linha))

    print("\n=== LISTA DE ECONOMIAS E DECISÕES ===")
    rotas = clarke_wright(clientes, CAPACIDADE, verbose=True)

    print("\n=== RESULTADO CLARKE & WRIGHT ===")
    mostrar(rotas)

    for frota in (2, 4):
        total, rotas_ot = otimo(clientes, CAPACIDADE, frota)
        print(f"\n=== ÓTIMO EXATO COM FROTA DE {frota} VEÍCULOS ===")
        mostrar(rotas_ot)