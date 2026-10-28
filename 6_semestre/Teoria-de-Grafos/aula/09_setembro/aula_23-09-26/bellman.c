#include <stdio.h>
#include <limits.h>
#include <stdbool.h>

#define INF INT_MAX
#define SEM_PRED -1

typedef struct {
    int u;
    int v;
    int w;
} Aresta;

bool bellmanFord(int numVertices, Aresta arestas[], int numArestas, int s, int dist[], int pred[]) {

    for (int v = 0; v < numVertices; v++) {
        dist[v] = INF;
        pred[v] = SEM_PRED;
    }
    dist[s] = 0;

    for (int i = 1; i <= numVertices - 1; i++) {
        bool houveMudanca = false;

        for (int j = 0; j < numArestas; j++) {
            int u = arestas[j].u;
            int v = arestas[j].v;
            int w = arestas[j].w;

            if (dist[u] != INF && dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                pred[v] = u;
                houveMudanca = true;
            }
        }

        if (!houveMudanca) {
            break;
        }
    }

    for (int j = 0; j < numArestas; j++) {
        int u = arestas[j].u;
        int v = arestas[j].v;
        int w = arestas[j].w;

        if (dist[u] != INF && dist[u] + w < dist[v]) {
            printf("Existe ciclo negativo\n");
            return false;
        }
    }

    return true;
}

void imprimirCaminho(int pred[], int v) {
    if (pred[v] != SEM_PRED) {
        imprimirCaminho(pred, pred[v]);
        printf(" -> ");
    }
    printf("%c", 'A' + v);
}

void imprimirResultado(int numVertices, int dist[], int pred[]) {
    printf("No | dist | pred | caminho\n");
    printf("---+------+------+--------\n");

    for (int v = 0; v < numVertices; v++) {
        printf(" %c | ", 'A' + v);

        if (dist[v] == INF) {
            printf("  ∞  |  -   | inalcancavel\n");
            continue;
        }

        printf("%4d | ", dist[v]);
        printf(pred[v] == SEM_PRED ? "  -  " : "  %c  ", 'A' + pred[v]);
        printf(" | ");
        imprimirCaminho(pred, v);
        printf("\n");
    }
}

int main(void) {
    enum { 
        A, 
        B, 
        C, 
        D, 
        E, 
        NUM_VERTICES 
    };

    Aresta arestas[] = {
        {A, B,  6},
        {A, C,  7},
        {B, D,  5},
        {C, D, -3},
        {C, E,  2},
        {E, D,  1}
    };
    int numArestas = sizeof(arestas) / sizeof(arestas[0]);

    int dist[NUM_VERTICES];
    int pred[NUM_VERTICES];

    if (bellmanFord(NUM_VERTICES, arestas, numArestas, A, dist, pred)) {
        imprimirResultado(NUM_VERTICES, dist, pred);
    }

    return 0;
}
