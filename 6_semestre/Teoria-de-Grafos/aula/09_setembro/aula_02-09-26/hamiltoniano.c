#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAX_VERTICES 100
#define MAX_NOME 10

typedef struct {
    char nomes[MAX_VERTICES][MAX_NOME];
    int adjacencia[MAX_VERTICES][MAX_VERTICES];
    int quantidadeVertices;
} Grafo;

// --- INICIALIZA O GRAFO ---
void inicializarGrafo(Grafo *grafo) {
    grafo->quantidadeVertices = 0;

    for (int i = 0; i < MAX_VERTICES; i++) {
        for (int j = 0; j < MAX_VERTICES; j++) {
            grafo->adjacencia[i][j] = 0;
        }
    }
}

// --- ADICIONA UM VERTICE AO GRAFO ---
int adicionarVertice(Grafo *grafo, const char nome[]) {
    if (grafo->quantidadeVertices >= MAX_VERTICES) {
        return -1;
    }

    int indice = grafo->quantidadeVertices;
    strcpy(grafo->nomes[indice], nome);
    grafo->quantidadeVertices++;

    return indice;
}

// --- ADICIONA UMA ARESTA NAO DIRECIONADA ---
void adicionarAresta(Grafo *grafo, int origem, int destino) {
    grafo->adjacencia[origem][destino] = 1;
    grafo->adjacencia[destino][origem] = 1;
}

// --- REALIZA O BACKTRACKING PARA ENCONTRAR UM CAMINHO HAMILTONIANO ---
bool backtrackingCaminho(Grafo *grafo, int atual, int caminho[], bool visitados[], int tamanhoCaminho) {
    if (tamanhoCaminho == grafo->quantidadeVertices) {
        return true;
    }

    for (int vizinho = 0; vizinho < grafo->quantidadeVertices; vizinho++) {
        if (grafo->adjacencia[atual][vizinho] && !visitados[vizinho]) {
            visitados[vizinho] = true;
            caminho[tamanhoCaminho] = vizinho;

            if (backtrackingCaminho(grafo, vizinho, caminho, visitados, tamanhoCaminho + 1)) {
                return true;
            }

            // --- DESFAZ A ESCOLHA ---
            visitados[vizinho] = false;
            caminho[tamanhoCaminho] = -1;
        }
    }

    return false;
}

// --- PROCURA UM CAMINHO HAMILTONIANO COMEÇANDO EM QUALQUER VERTICE ---
bool caminhoHamiltoniano(Grafo *grafo, int caminho[]) {
    bool visitados[MAX_VERTICES];

    for (int inicio = 0; inicio < grafo->quantidadeVertices; inicio++) {
        for (int i = 0; i < grafo->quantidadeVertices; i++) {
            visitados[i] = false;
            caminho[i] = -1;
        }

        visitados[inicio] = true;
        caminho[0] = inicio;

        if (backtrackingCaminho(grafo, inicio, caminho, visitados, 1)) {
            return true;
        }
    }

    return false;
}

// --- REALIZA O BACKTRACKING PARA ENCONTRAR UM CICLO HAMILTONIANO ---
bool backtrackingCiclo(Grafo *grafo, int atual, int inicio, int caminho[], bool visitados[], int tamanhoCaminho) {
    if (tamanhoCaminho == grafo->quantidadeVertices) {
        if (grafo->adjacencia[atual][inicio]) {
            caminho[tamanhoCaminho] = inicio;
            return true;
        }

        return false;
    }

    for (int vizinho = 0; vizinho < grafo->quantidadeVertices; vizinho++) {
        if (grafo->adjacencia[atual][vizinho] && !visitados[vizinho]) {
            visitados[vizinho] = true;
            caminho[tamanhoCaminho] = vizinho;

            if (backtrackingCiclo(grafo, vizinho, inicio, caminho, visitados, tamanhoCaminho + 1)) {
                return true;
            }

            // --- DESFAZ A ESCOLHA ---
            visitados[vizinho] = false;
            caminho[tamanhoCaminho] = -1;
        }
    }

    return false;
}

// --- PROCURA UM CICLO HAMILTONIANO ---
bool cicloHamiltoniano(Grafo *grafo, int caminho[]) {
    if (grafo->quantidadeVertices == 0) {
        return false;
    }

    bool visitados[MAX_VERTICES] = {false};
    int inicio = 0;

    for (int i = 0; i <= grafo->quantidadeVertices; i++) {
        caminho[i] = -1;
    }

    visitados[inicio] = true;
    caminho[0] = inicio;

    return backtrackingCiclo(grafo, inicio, inicio, caminho, visitados, 1);
}

// --- EXIBE UM CAMINHO OU CICLO HAMILTONIANO ---
void exibirResultado(Grafo *grafo, int caminho[], int quantidade) {
    for (int i = 0; i < quantidade; i++) {
        printf("%s", grafo->nomes[caminho[i]]);

        if (i < quantidade - 1) {
            printf(" -> ");
        }
    }

    printf("\n");
}

// --- FUNCAO PRINCIPAL ---
int main(void) {
    Grafo grafo;
    inicializarGrafo(&grafo);

    int A = adicionarVertice(&grafo, "A");
    int B = adicionarVertice(&grafo, "B");
    int C = adicionarVertice(&grafo, "C");
    int D = adicionarVertice(&grafo, "D");
    int E = adicionarVertice(&grafo, "E");
    int F = adicionarVertice(&grafo, "F");
    int G = adicionarVertice(&grafo, "G");
    int H = adicionarVertice(&grafo, "H");
    int I = adicionarVertice(&grafo, "I");
    int J = adicionarVertice(&grafo, "J");

    adicionarAresta(&grafo, A, B);
    adicionarAresta(&grafo, A, C);
    adicionarAresta(&grafo, A, D);
    adicionarAresta(&grafo, A, E);
    adicionarAresta(&grafo, A, F);
    adicionarAresta(&grafo, A, G);
    adicionarAresta(&grafo, A, H);
    adicionarAresta(&grafo, A, I);
    adicionarAresta(&grafo, A, J);

    adicionarAresta(&grafo, B, C);
    adicionarAresta(&grafo, B, D);
    adicionarAresta(&grafo, B, E);
    adicionarAresta(&grafo, B, F);
    adicionarAresta(&grafo, B, G);
    adicionarAresta(&grafo, B, H);
    adicionarAresta(&grafo, B, I);
    adicionarAresta(&grafo, B, J);

    adicionarAresta(&grafo, C, D);
    adicionarAresta(&grafo, C, E);
    adicionarAresta(&grafo, C, F);
    adicionarAresta(&grafo, C, G);
    adicionarAresta(&grafo, C, H);
    adicionarAresta(&grafo, C, I);
    adicionarAresta(&grafo, C, J);

    adicionarAresta(&grafo, D, E);
    adicionarAresta(&grafo, D, F);
    adicionarAresta(&grafo, D, G);
    adicionarAresta(&grafo, D, H);
    adicionarAresta(&grafo, D, I);
    adicionarAresta(&grafo, D, J);

    adicionarAresta(&grafo, E, F);
    adicionarAresta(&grafo, E, G);
    adicionarAresta(&grafo, E, H);
    adicionarAresta(&grafo, E, I);
    adicionarAresta(&grafo, E, J);

    adicionarAresta(&grafo, F, G);
    adicionarAresta(&grafo, F, H);
    adicionarAresta(&grafo, F, I);
    adicionarAresta(&grafo, F, J);

    adicionarAresta(&grafo, G, H);
    adicionarAresta(&grafo, G, I);
    adicionarAresta(&grafo, G, J);

    adicionarAresta(&grafo, H, I);
    adicionarAresta(&grafo, H, J);

    adicionarAresta(&grafo, I, J);

    int caminho[MAX_VERTICES];
    int ciclo[MAX_VERTICES + 1];

    if (caminhoHamiltoniano(&grafo, caminho)) {
        printf("Caminho Hamiltoniano:\n");
        exibirResultado(&grafo, caminho, grafo.quantidadeVertices);
    } else {
        printf("Nao existe caminho Hamiltoniano.\n");
    }

    if (cicloHamiltoniano(&grafo, ciclo)) {
        printf("\nCiclo Hamiltoniano:\n");
        exibirResultado(&grafo, ciclo, grafo.quantidadeVertices + 1);
    } else {
        printf("\nNao existe ciclo Hamiltoniano.\n");
    }

    return 0;
}