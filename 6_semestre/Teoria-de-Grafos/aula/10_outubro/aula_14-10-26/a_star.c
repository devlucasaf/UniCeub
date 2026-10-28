#include <stdio.h>
#include <stdbool.h>
#include <limits.h>

#define MAX_VERTICES 26
#define INF INT_MAX
#define SEM_PAI -1
#define SEM_ARESTA 0

typedef struct {
    int g;
    int h;
    int f;
    int pai;
} No;

typedef struct {
    int numVertices;
    int custo[MAX_VERTICES][MAX_VERTICES];
    int heuristica[MAX_VERTICES];
} Grafo;

typedef struct {
    int vertices[MAX_VERTICES];
    int tamanho;
} Caminho;

int heuristica(const Grafo *grafo, int n) {
    return grafo->heuristica[n];
}

int custo(const Grafo *grafo, int atual, int vizinho) {
    return grafo->custo[atual][vizinho];
}

void adicionarAresta(Grafo *grafo, int u, int v, int w) {
    grafo->custo[u][v] = w;
    grafo->custo[v][u] = w;
}

int noComMenorF(const bool open[], const No nos[], int numVertices) {
    int menor = SEM_PAI;

    for (int v = 0; v < numVertices; v++) {
        if (open[v] && (menor == SEM_PAI || nos[v].f < nos[menor].f)) {
            menor = v;
        }
    }

    return menor;
}

bool listaVazia(const bool lista[], int numVertices) {
    for (int v = 0; v < numVertices; v++) {
        if (lista[v]) {
            return false;
        }
    }
    return true;
}

Caminho reconstruirCaminho(const No nos[], int noFinal) {
    Caminho caminho = { .tamanho = 0 };
    int invertido[MAX_VERTICES];
    int atual = noFinal;

    while (atual != SEM_PAI) {
        invertido[caminho.tamanho++] = atual;
        atual = nos[atual].pai;
    }

    for (int i = 0; i < caminho.tamanho; i++) {
        caminho.vertices[i] = invertido[caminho.tamanho - 1 - i];
    }

    return caminho;
}

Caminho aStar(const Grafo *grafo, int inicio, int destino, No nos[]) {
    int n = grafo->numVertices;

    bool open[MAX_VERTICES] = { false };
    bool closed[MAX_VERTICES] = { false };

    for (int v = 0; v < n; v++) {
        nos[v].g = INF;
        nos[v].h = 0;
        nos[v].f = INF;
        nos[v].pai = SEM_PAI;
    }

    nos[inicio].g = 0;
    nos[inicio].h = heuristica(grafo, inicio);
    nos[inicio].f = nos[inicio].g + nos[inicio].h;
    nos[inicio].pai = SEM_PAI;

    open[inicio] = true;

    while (!listaVazia(open, n)) {
        int atual = noComMenorF(open, nos, n);

        if (atual == destino) {
            return reconstruirCaminho(nos, atual);
        }

        open[atual] = false;
        closed[atual] = true;

        for (int vizinho = 0; vizinho < n; vizinho++) {
            if (custo(grafo, atual, vizinho) == SEM_ARESTA) {
                continue;
            }

            if (closed[vizinho]) {
                continue;
            }

            int custoGTemp = nos[atual].g + custo(grafo, atual, vizinho);
            bool foiMelhorCaminho;

            if (!open[vizinho]) {
                open[vizinho] = true;
                foiMelhorCaminho = true;
            } else {
                foiMelhorCaminho = custoGTemp < nos[vizinho].g;
            }

            if (foiMelhorCaminho) {
                nos[vizinho].pai = atual;
                nos[vizinho].g = custoGTemp;
                nos[vizinho].h = heuristica(grafo, vizinho);
                nos[vizinho].f = nos[vizinho].g + nos[vizinho].h;
            }
        }
    }

    Caminho vazio = { .tamanho = 0 };
    return vazio;
}

void imprimirResultado(const char *titulo, Caminho caminho, const No nos[], int destino) {
    printf("%s\n", titulo);

    if (caminho.tamanho == 0) {
        printf("  Caminho nao encontrado\n\n");
        return;
    }

    printf("  Menor caminho: ");
    for (int i = 0; i < caminho.tamanho; i++) {
        printf("%c%s", 'A' + caminho.vertices[i], i < caminho.tamanho - 1 ? " -> " : "\n");
    }
    printf("  Custo total: %d\n\n", nos[destino].g);
}

int main(void) {
    No nos[MAX_VERTICES];

    enum { 
        A1, 
        B1, 
        C1, 
        D1, 
        E1, 
        TOTAL1 
    };

    Grafo g1 = { .numVertices = TOTAL1, .heuristica = { 6, 6, 4, 2, 0 } };

    adicionarAresta(&g1, A1, B1, 2);
    adicionarAresta(&g1, A1, C1, 3);
    adicionarAresta(&g1, B1, D1, 5);
    adicionarAresta(&g1, C1, D1, 2);
    adicionarAresta(&g1, D1, E1, 2);

    Caminho c1 = aStar(&g1, A1, E1, nos);
    imprimirResultado("Exemplo 1 (A ate E):", c1, nos, E1);

    enum { 
        A2,
        B2, 
        C2, 
        D2, 
        E2, 
        F2, 
        G2, 
        TOTAL2 
    };
    
    Grafo g2 = { .numVertices = TOTAL2, .heuristica = { 6, 6, 5, 3, 4, 3, 0 } };

    adicionarAresta(&g2, A2, B2, 4);
    adicionarAresta(&g2, A2, C2, 2);
    adicionarAresta(&g2, B2, D2, 5);
    adicionarAresta(&g2, C2, D2, 2);
    adicionarAresta(&g2, C2, E2, 6);
    adicionarAresta(&g2, D2, F2, 1);
    adicionarAresta(&g2, D2, G2, 3);
    adicionarAresta(&g2, E2, F2, 2);

    Caminho c2 = aStar(&g2, A2, G2, nos);
    imprimirResultado("Exemplo 2 (A ate G):", c2, nos, G2);

    return 0;
}
