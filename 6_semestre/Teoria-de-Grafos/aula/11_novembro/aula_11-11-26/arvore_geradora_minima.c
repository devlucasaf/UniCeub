#include <stdio.h>
#include <stdbool.h>
#include <limits.h>

#define MAX_VERTICES 32
#define MAX_ARESTAS 256
#define INF INT_MAX
#define NIL -1
#define SEM_ARESTA 0

// --- ESTRUTURA DA ARESTA ---
typedef struct {
    int u;
    int v;
    int w;
} Aresta;

// --- GRAFO NÃO DIRECIONADO ---
typedef struct {
    int numVertices;
    const char *rotulo[MAX_VERTICES];
    int peso[MAX_VERTICES][MAX_VERTICES];
    Aresta arestas[MAX_ARESTAS];
    int numArestas;
} Grafo;

// --- ADICIONA ARESTA NOS DOIS SENTIDOS ---
void adicionarAresta(Grafo *g, int u, int v, int w) 
{
    g->peso[u][v] = w;
    g->peso[v][u] = w;
    g->arestas[g->numArestas++] = (Aresta){ u, v, w };
}

// --- Extrair_Min(Q): NÓ DE Q COM A MENOR key ---
int extrairMin(bool emQ[], const int key[], int numVertices) 
{
    int menor = NIL;

    for (int v = 0; v < numVertices; v++) {
        if (emQ[v] && (menor == NIL || key[v] < key[menor])) {
            menor = v;
        }
    }

    if (menor != NIL) {
        emQ[menor] = false;
    }
    return menor;
}

// --- VERIFICA SE Q ESTÁ VAZIA ---
bool filaVazia(const bool emQ[], int numVertices) 
{
    for (int v = 0; v < numVertices; v++) {
        if (emQ[v]) {
            return false;
        }
    }
    return true;
}

void agmPrim(const Grafo *g, int r, int key[], int pi[]) 
{
    bool emQ[MAX_VERTICES];

    for (int u = 0; u < g->numVertices; u++) {
        key[u] = INF;
        pi[u] = NIL;
    }

    key[r] = 0;

    for (int u = 0; u < g->numVertices; u++) {
        emQ[u] = true;
    }

    while (!filaVazia(emQ, g->numVertices)) {
        int u = extrairMin(emQ, key, g->numVertices);

        if (key[u] == INF) {
            break;
        }

        for (int v = 0; v < g->numVertices; v++) {
            int w = g->peso[u][v];

            if (w == SEM_ARESTA) {
                continue;
            }

            if (emQ[v] && w < key[v]) {
                pi[v] = u;
                key[v] = w;
            }
        }
    }
}

// --- IMPRIME A TABELA FINAL DO PRIM ---
void imprimirPrim(const Grafo *g, const int key[], const int pi[]) 
{
    int pesoTotal = 0;

    printf("  No | key |  pi  | aresta\n");
    for (int v = 0; v < g->numVertices; v++) {
        if (pi[v] == NIL) {
            printf("  %2s | %3d | NULL |   -\n", g->rotulo[v], key[v]);
            continue;
        }
        printf("  %2s | %3d | %4s | %s-%s\n", g->rotulo[v], key[v], g->rotulo[pi[v]], g->rotulo[pi[v]], g->rotulo[v]);
        pesoTotal += key[v];
    }
    printf("  Peso total = %d\n\n", pesoTotal);
}

// --- CONJUNTOS DISJUNTOS ---
int pai[MAX_VERTICES];
int rank_[MAX_VERTICES];

// --- CRIA UM CONJUNTO SÓ COM v ---
void makeSet(int v) 
{
    pai[v] = v;
    rank_[v] = 0;
}

// --- REPRESENTANTE DO CONJUNTO DE v ---
int findSet(int v) 
{
    if (pai[v] != v) {
        pai[v] = findSet(pai[v]);
    }
    return pai[v];
}

// --- JUNTA AS DUAS ÁRVORES ---
void unionSet(int u, int v) 
{
    int ru = findSet(u);
    int rv = findSet(v);

    if (rank_[ru] < rank_[rv]) {
        pai[ru] = rv;
    } else if (rank_[ru] > rank_[rv]) {
        pai[rv] = ru;
    } else {
        pai[rv] = ru;
        rank_[ru]++;
    }
}

// --- ORDENA AS ARESTAS POR PESO ---
void ordenarArestas(Aresta arestas[], int n) 
{
    for (int i = 1; i < n; i++) {
        Aresta atual = arestas[i];
        int j = i - 1;

        while (j >= 0 && arestas[j].w > atual.w) {
            arestas[j + 1] = arestas[j];
            j--;
        }
        arestas[j + 1] = atual;
    }
}

// --- RETORNA O Nº DE ARESTAS DA AGM ---
int kruskal(const Grafo *g, Aresta agm[]) 
{
    Aresta ordenadas[MAX_ARESTAS];
    int total = 0;

    for (int v = 0; v < g->numVertices; v++) {
        makeSet(v);
    }

    for (int i = 0; i < g->numArestas; i++) {
        ordenadas[i] = g->arestas[i];
    }
    ordenarArestas(ordenadas, g->numArestas);

    for (int i = 0; i < g->numArestas; i++) {
        int u = ordenadas[i].u;
        int v = ordenadas[i].v;

        printf("  (%s, %s) w = %2d -> ", g->rotulo[u], g->rotulo[v], ordenadas[i].w);

        if (findSet(u) != findSet(v)) {
            agm[total++] = ordenadas[i];
            unionSet(u, v);
            printf("adicionada\n");
        } else {
            printf("rejeitada (formaria ciclo)\n");
        }
    }

    return total;
}

// --- IMPRIME AS ARESTAS ESCOLHIDAS PELO KRUSKAL ---
void imprimirKruskal(const Grafo *g, const Aresta agm[], int total) 
{
    int pesoTotal = 0;

    printf("  AGM = { ");
    for (int i = 0; i < total; i++) {
        printf("(%s, %s)%s", g->rotulo[agm[i].u], g->rotulo[agm[i].v], i < total - 1 ? ", " : " }\n");
        pesoTotal += agm[i].w;
    }
    printf("  Arestas: %d (|V| - 1 = %d)\n", total, g->numVertices - 1);
    printf("  Peso total = %d\n\n", pesoTotal);
}

// --- MAIN ---
int main(void) 
{

    enum { 
        A, 
        B, 
        C,
        D, 
        E, 
        TOTAL_P 
    };

    Grafo gp = {
        .numVertices = TOTAL_P,
        .rotulo = { "A", "B", "C", "D", "E" }
    };
    adicionarAresta(&gp, A, B, 3);
    adicionarAresta(&gp, A, C, 5);
    adicionarAresta(&gp, A, D, 8);
    adicionarAresta(&gp, A, E, 9);
    adicionarAresta(&gp, B, D, 2);
    adicionarAresta(&gp, C, D, 8);
    adicionarAresta(&gp, C, E, 2);
    adicionarAresta(&gp, D, E, 1);

    int key[MAX_VERTICES];
    int pi[MAX_VERTICES];

    printf("Prim (raiz = A):\n");
    agmPrim(&gp, A, key, pi);
    imprimirPrim(&gp, key, pi);

    enum { 
        a, 
        b, 
        c, 
        d, 
        e, 
        f, 
        g, 
        h, 
        i, 
        TOTAL_K 
    };

    Grafo gk = {
        .numVertices = TOTAL_K,
        .rotulo = { "a", "b", "c", "d", "e", "f", "g", "h", "i" }
    };
    adicionarAresta(&gk, g, h, 1);
    adicionarAresta(&gk, c, i, 2);
    adicionarAresta(&gk, f, g, 2);
    adicionarAresta(&gk, a, b, 4);
    adicionarAresta(&gk, c, f, 4);
    adicionarAresta(&gk, g, i, 6);
    adicionarAresta(&gk, c, d, 7);
    adicionarAresta(&gk, h, i, 7);
    adicionarAresta(&gk, a, h, 8);
    adicionarAresta(&gk, b, c, 8);
    adicionarAresta(&gk, d, e, 9);
    adicionarAresta(&gk, e, f, 10);
    adicionarAresta(&gk, b, h, 11);
    adicionarAresta(&gk, d, f, 14);

    Aresta agm[MAX_VERTICES];

    printf("Kruskal:\n");
    int total = kruskal(&gk, agm);
    imprimirKruskal(&gk, agm, total);

    return 0;
}
