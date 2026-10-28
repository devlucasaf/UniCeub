#include <stdio.h>
#include <stdbool.h>
#include <limits.h>

#define MAX_VERTICES 64
#define INF INT_MAX
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
    int itens[MAX_VERTICES];
    int inicio;
    int fim;
} Fila;

void filaIniciar(Fila *q) 
{
    q->inicio = 0;
    q->fim = 0;
}

bool filaVazia(const Fila *q) 
{
    return q->inicio == q->fim;
}

void enqueue(Fila *q, int v) 
{
    q->itens[q->fim++] = v;
}

int dequeue(Fila *q)
{
    return q->itens[q->inicio++];
}

void adicionarVizinho(Grafo *g, int u, int v) 
{
    g->adj[u][g->grau[u]++] = v;
}

void bfs(const Grafo *g, int s, Cor cor[], int d[], int pi[], int ordem[], int *totalVisitados) 
{
    Fila q;
    *totalVisitados = 0;

    for (int u = 0; u < g->numVertices; u++) {
        if (u == s) {
            continue;
        }
        cor[u] = BRANCO;
        d[u] = INF;
        pi[u] = NIL;
    }

    cor[s] = CINZA;
    d[s] = 0;
    pi[s] = NIL;
    filaIniciar(&q);
    enqueue(&q, s);

    while (!filaVazia(&q)) {
        int u = dequeue(&q);
        ordem[(*totalVisitados)++] = u;

        for (int i = 0; i < g->grau[u]; i++) {
            int v = g->adj[u][i];

            if (cor[v] == BRANCO) {
                cor[v] = CINZA;
                d[v] = d[u] + 1;
                pi[v] = u;
                enqueue(&q, v);
            }
        }

        cor[u] = PRETO;
    }
}

void bfsSimples(const Grafo *g, int s, int ordem[], int *totalVisitados) 
{
    bool visitado[MAX_VERTICES] = { false };
    Fila q;
    *totalVisitados = 0;

    filaIniciar(&q);
    visitado[s] = true;
    enqueue(&q, s);

    while (!filaVazia(&q)) {
        int u = dequeue(&q);
        ordem[(*totalVisitados)++] = u;

        for (int i = 0; i < g->grau[u]; i++) {
            int v = g->adj[u][i];

            if (!visitado[v]) {
                visitado[v] = true;  
                enqueue(&q, v);      
            }
        }
    }
}

void imprimirOrdem(const Grafo *g, const int ordem[], int total) 
{
    printf("  Ordem de visita: ");
    for (int i = 0; i < total; i++) {
        printf("%s%s", g->rotulo[ordem[i]], i < total - 1 ? " -> " : "\n");
    }
}

void imprimirTabela(const Grafo *g, const int d[], const int pi[]) 
{
    printf("  No | d | pi\n");
    for (int v = 0; v < g->numVertices; v++) {
        printf("  %2s | ", g->rotulo[v]);
        if (d[v] == INF) {
            printf("∞ | -\n");
        } else {
            printf("%d | %s\n", d[v], pi[v] == NIL ? "-" : g->rotulo[pi[v]]);
        }
    }
}

void executarExemplo(const char *titulo, const Grafo *g, int s) 
{
    Cor cor[MAX_VERTICES];
    int d[MAX_VERTICES];
    int pi[MAX_VERTICES];
    int ordem[MAX_VERTICES];
    int total;

    printf("%s\n", titulo);

    printf(" [BFS completa - slide 13]\n");
    bfs(g, s, cor, d, pi, ordem, &total);
    imprimirOrdem(g, ordem, total);
    imprimirTabela(g, d, pi);

    printf(" [BFS simplificada - slide 15]\n");
    bfsSimples(g, s, ordem, &total);
    imprimirOrdem(g, ordem, total);
    printf("\n");
}

int main(void) 
{

    Grafo g1 = {
        .numVertices = 8,
        .rotulo = { "1", "2", "3", "4", "5", "6", "7", "8" }
    };
    int listas1[8][4] = {
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
        for (int i = 0; i < 4 && listas1[u][i] != 0; i++) {
            adicionarVizinho(&g1, u, listas1[u][i] - 1);
        }
    }
    executarExemplo("Exemplo 1 (origem = 1):", &g1, 0);

    enum { 
        A, 
        B, 
        C, 
        D, 
        E, 
        F, 
        TOTAL2 
    };

    Grafo g2 = {
        .numVertices = TOTAL2,
        .rotulo = { "A", "B", "C", "D", "E", "F" }
    };
    adicionarVizinho(&g2, A, B); adicionarVizinho(&g2, A, C);
    adicionarVizinho(&g2, B, A); adicionarVizinho(&g2, B, D);
    adicionarVizinho(&g2, C, A); adicionarVizinho(&g2, C, E); adicionarVizinho(&g2, C, F);
    adicionarVizinho(&g2, D, B);
    adicionarVizinho(&g2, E, C);
    adicionarVizinho(&g2, F, C);
    executarExemplo("Exemplo 2 (origem = A):", &g2, A);

    return 0;
}
