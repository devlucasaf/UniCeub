#include <stdio.h>

#define MAX_VERTICES 64
#define NIL -1

typedef enum 
{
    BRANCO,  
    CINZA,   
    PRETO    
} Cor;

typedef struct 
{
    int numVertices;
    const char *rotulo[MAX_VERTICES];
    int adj[MAX_VERTICES][MAX_VERTICES];
    int grau[MAX_VERTICES];
} Grafo;

typedef struct 
{
    Cor cor[MAX_VERTICES];
    int pi[MAX_VERTICES];
    int d[MAX_VERTICES];
    int f[MAX_VERTICES];
    int t;
    int ordem[MAX_VERTICES];
    int totalDescobertos;
} BuscaDFS;

void adicionarVizinho(Grafo *g, int u, int v) 
{
    g->adj[u][g->grau[u]++] = v;
}

void adicionarAresta(Grafo *g, int u, int v) 
{
    adicionarVizinho(g, u, v);
    adicionarVizinho(g, v, u);
}

void dfsVisit(const Grafo *g, int u, BuscaDFS *b) 
{

    b->t = b->t + 1;
    b->d[u] = b->t;
    b->cor[u] = CINZA;
    b->ordem[b->totalDescobertos++] = u;

    for (int i = 0; i < g->grau[u]; i++) {
        int v = g->adj[u][i];

        if (b->cor[v] == BRANCO) {
            b->pi[v] = u;
            dfsVisit(g, v, b);
        }
    }

    b->cor[u] = PRETO;
    b->t = b->t + 1;
    b->f[u] = b->t;
}

void dfs(const Grafo *g, BuscaDFS *b) 
{

    for (int u = 0; u < g->numVertices; u++) {
        b->cor[u] = BRANCO;
        b->pi[u] = NIL;
    }

    b->t = 0;
    b->totalDescobertos = 0;

    for (int u = 0; u < g->numVertices; u++) {
        if (b->cor[u] == BRANCO) {
            dfsVisit(g, u, b);
        }
    }
}

void imprimirResultado(const char *titulo, const Grafo *g, const BuscaDFS *b) 
{
    printf("%s\n", titulo);

    printf("  Ordem de descoberta: ");
    for (int i = 0; i < b->totalDescobertos; i++) {
        printf("%s%s", g->rotulo[b->ordem[i]], i < b->totalDescobertos - 1 ? " -> " : "\n");
    }

    printf("  No |  d |  f | pi\n");
    for (int v = 0; v < g->numVertices; v++) {
        printf("  %2s | %2d | %2d | %s\n",
                g->rotulo[v], b->d[v], b->f[v],
                b->pi[v] == NIL ? "-" : g->rotulo[b->pi[v]]
        );
    }
    printf("\n");
}

int main(void) 
{
    BuscaDFS b;

    enum { 
        U, 
        V, 
        W, 
        X, 
        Y, 
        Z, 
        TOTAL1 
    };
    
    Grafo g1 = {
        .numVertices = TOTAL1,
        .rotulo = { 
            "u", 
            "v", 
            "w", 
            "x", 
            "y", 
            "z" 
        }
    };

    adicionarVizinho(&g1, U, V);
    adicionarVizinho(&g1, U, X);
    adicionarVizinho(&g1, V, Y);
    adicionarVizinho(&g1, W, Y);
    adicionarVizinho(&g1, W, Z);
    adicionarVizinho(&g1, X, V);
    adicionarVizinho(&g1, Y, X);
    adicionarVizinho(&g1, Z, Z);
    dfs(&g1, &b);
    imprimirResultado("Exemplo 1 (direcionado):", &g1, &b);

    Grafo g2 = {
        .numVertices = 8,
        .rotulo = { 
            "1", 
            "2", 
            "3", 
            "4", 
            "5", 
            "6", 
            "7", 
            "8" 
        }
    };

    int listas2[8][4] = {
        { 2, 3 },        
        { 1, 4, 5 },     
        { 1, 6, 7 },     
        { 2, 8 },        
        { 2, 8 },        
        { 3, 8 },        
        { 3, 8 },        
        { 4, 5, 6, 7 }   
    };

    for (int u = 0; u < 8; u++) {
        for (int i = 0; i < 4 && listas2[u][i] != 0; i++) {
            adicionarVizinho(&g2, u, listas2[u][i] - 1);
        }
    }

    dfs(&g2, &b);
    imprimirResultado("Exemplo 2 (origem = 1):", &g2, &b);

    
    enum { 
        A, 
        B, 
        C, 
        D, 
        E, 
        F, 
        TOTAL3 
    };

    Grafo g3 = {
        .numVertices = TOTAL3,
        .rotulo = { 
            "A", 
            "B", 
            "C", 
            "D", 
            "E", 
            "F" 
        }
    };
    adicionarAresta(&g3, A, B);
    adicionarAresta(&g3, A, C);
    adicionarAresta(&g3, B, D);
    adicionarAresta(&g3, C, E);
    adicionarAresta(&g3, C, F);
    dfs(&g3, &b);
    imprimirResultado("Exemplo 3 (origem = A):", &g3, &b);

    return 0;
}
